import re

from gen_ai_service.model import processor, model

class LinkMaker:
    def __init__(self):
        self.model = model
        self.processor = processor
        self.tools = [
            {
                "type": "function",
                "function": {
                    "name": "create_entity",
                    "description": "Call this to create a single distinct D&D entity (NPC, Location, Item, Organization, etc.).",
                    "parameters": {
                        "type": "object",
                        "properties": {
                            "name": {"type": "string"},
                            "type": {"type": "string"},
                            "description": {"type": "string"},
                        },
                        "required": ["name", "type", "description"],
                    },
                },
            },
            {
                "type": "function",
                "function": {
                    "name": "create_relationship",
                    "description": "Call this to create a relationship between two entities.",
                    "parameters": {
                        "type": "object",
                        "properties": {
                            "source_entity": {"type": "string"},
                            "target_entity": {"type": "string"},
                            "relationship_type": {"type": "string"},
                        },
                        "required": [
                            "source_entity",
                            "target_entity",
                            "relationship_type",
                        ],
                    },
                },
            },
        ]

    # -----------------------
    # Public API
    # -----------------------
    def execute(self, user_input: str):
        messages = [
            {
                "role": "system",
                "content": [
                    {
                        "type": "text",
                        "text": (
                            "You are a knowledge graph extractor for D&D lore. "
                            "First execute create_entity for every entity, then "
                            "create_relationship for every connection."
                        ),
                    }
                ],
            },
            {
                "role": "user",
                "content": [{"type": "text", "text": user_input}],
            },
        ]

        inputs = self.processor.apply_chat_template(
            messages,
            tools=self.tools,
            add_generation_prompt=True,
            tokenize=True,
            return_dict=True,
            return_tensors="pt",
        ).to(self.model.device)

        outputs = self.model.generate(**inputs, max_new_tokens=1000)

        response = self.processor.decode(
            outputs[0][inputs["input_ids"].shape[-1] :],
            skip_special_tokens=True,
        ).strip()

        self._process_response(response)

    # -----------------------
    # Internal methods
    # -----------------------
    def _process_response(self, response_text: str):
        calls = re.split(r"(?=call:)", response_text)

        for call in calls:
            print(call)
            call = call.strip()
            if not call:
                continue

            try:
                if "create_entity" in call:
                    arg_string = call.split("create_entity")[-1].strip()
                    # Convert python-like loose dict layout to standard key-values
                    fixed_args = re.sub(r'(\w+)\s*:', r'"\1":', arg_string)
                    # Safe clean fallback mapping using regex pairs if JSON format string remains stubborn
                    name = re.search(r'name:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")
                    ent_type = re.search(r'type:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")
                    desc = re.search(r'description:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")

                    # Execute
                    self.create_entity(name=name, en_type=ent_type, description=desc)

                elif "create_relationship" in call:
                    arg_string = call.split("create_relationship")[-1].strip()
                    source = re.search(r'source_entity:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")
                    target = re.search(r'target_entity:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")
                    rel_type = re.search(r'relationship_type:\s*([^,\}]+)', arg_string).group(1).strip("'\" ")

                    # Execute
                    self.create_relationship(source_entity=source, target_entity=target, relationship_type=rel_type)

            except Exception as e:
                print(f"Failed to process call: {e}")


    # -----------------------
    # Replace these with DB code later
    # -----------------------
    def create_entity(self, name: str, en_type: str, description: str):
        print(
            f"[DB] Entity -> {name} | {en_type} | {description}"
        )

    def create_relationship(
        self,
        source_entity: str,
        target_entity: str,
        relationship_type: str,
    ):
        print(
            f"[DB] Link -> ({source_entity}) -[{relationship_type}]-> ({target_entity})"
        )