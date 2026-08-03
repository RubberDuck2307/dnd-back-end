import re
from model import model, processor

def create_entity(name: str, type: str, description: str) -> str:
    """Creates a distinct D&D entity/node."""
    # In production, replace this print statement with your actual DB/ORM insert
    print(f"       [DB EXECUTE] Created Entity -> Name: {name} | Type: {type} | Desc: {description}")
    return f"Successfully created entity: {name}"


def create_relationship(source_entity: str, target_entity: str, relationship_type: str) -> str:
    """Creates a directed edge linking two entities together."""
    # In production, replace this print statement with your graph database link
    print(f"       [DB EXECUTE] Created Link -> ({source_entity}) -[{relationship_type}]-> ({target_entity})")
    return f"Successfully linked {source_entity} to {target_entity}"

tools = [
    {
        "type": "function",
        "function": {
            "name": "create_entity",
            "description": "Call this to create a single distinct D&D entity (NPC, Location, Item, Organization, etc.). Call it multiple times if there are multiple entities.",
            "parameters": {
                "type": "object",
                "properties": {
                    "name": {"type": "string",
                             "description": "The exact name of the entity, e.g., 'Rudolf' or 'Honeywood'"},
                    "type": {"type": "string", "description": "Category: NPC, Location, Item, Creature, etc."},
                    "description": {"type": "string",
                                    "description": "A short summary of what is known about them from the text."}
                },
                "required": ["name", "type", "description"]
            }
        }
    },
    {
        "type": "function",
        "function": {
            "name": "create_relationship",
            "description": "Call this to map a connection or relationship between two entities that already exist.",
            "parameters": {
                "type": "object",
                "properties": {
                    "source_entity": {"type": "string", "description": "The originating entity name (subject)."},
                    "target_entity": {"type": "string", "description": "The destination entity name (object)."},
                    "relationship_type": {"type": "string",
                                          "description": "The verb or connector, e.g., 'lives in', 'married to', 'contains'"}
                },
                "required": ["source_entity", "target_entity", "relationship_type"]
            }
        }
    }
]

text = "Rudolf is a blacksmith, he lives in city of Honeywood. Rudolf is married to Catrine"

messages = [
    {
        "role": "system",
        "content": [
            {
                "type": "text",
                "text": (
                    "You are a knowledge graph extractor for D&D lore. Parse user descriptions into structural actions. "
                    "First, execute 'create_entity' for every individual entity found. Then, execute 'create_relationship' "
                    "to link them. Use a strict sequential format for tool calls."
                )
            }
        ]
    },
    {
        "role": "user",
        "content": [
            {"type": "text",
             "text": text}
        ]
    }
]

print(text)

inputs = processor.apply_chat_template(
    messages,
    tools=tools,
    add_generation_prompt=True,
    tokenize=True,
    return_dict=True,
    return_tensors="pt",
).to(model.device)

# ==========================================
# 4. EXECUTE PIPELINE & PARSE BULK CALLS
# ==========================================
outputs = model.generate(**inputs, max_new_tokens=1000)
response_text = processor.decode(outputs[0][inputs["input_ids"].shape[-1]:], skip_special_tokens=True).strip()

print("\n--- Raw Model Decisions ---")
print(response_text)
print("---------------------------\n")

print("🤖 Processing Sequence Extracted by Gemma:\n")

# Gemma 4 typically prints multiple blocks sequentially when handling multiple actions.
# We will split on the call keyword to iterate through every tool invocation requested.
calls = re.split(r'(?=call:)', response_text)

for call in calls:
    call = call.strip()
    if not call:
        continue

    try:
        # Check which function Gemma wants to execute
        if "create_entity" in call:
            # Isolate the argument content block
            arg_string = call.split("create_entity")[-1].strip()
            # Convert python-like loose dict layout to standard key-values
            fixed_args = re.sub(r'(\w+)\s*:', r'"\1":', arg_string)
            # Safe clean fallback mapping using regex pairs if JSON format string remains stubborn
            name = re.search(r'name:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")
            ent_type = re.search(r'type:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")
            desc = re.search(r'description:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")

            # Execute
            create_entity(name=name, type=ent_type, description=desc)

        elif "create_relationship" in call:
            arg_string = call.split("create_relationship")[-1].strip()
            source = re.search(r'source_entity:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")
            target = re.search(r'target_entity:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")
            rel_type = re.search(r'relationship_type:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")

            # Execute
            create_relationship(source_entity=source, target_entity=target, relationship_type=rel_type)

    except Exception as parse_error:
        # Gracefully skipping logging structures that are conversational texts or typos
        pass