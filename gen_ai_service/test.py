from transformers import AutoProcessor, AutoModelForMultimodalLM

model_id = "google/gemma-4-26B-A4B-it"
local_dir ="/home/user/src_wsl/dnd-back-end/gen_ai_service"

processor = AutoProcessor.from_pretrained(model_id, cache_dir=local_dir)
model = AutoModelForMultimodalLM.from_pretrained(model_id, cache_dir=local_dir)
messages = [
    {
        "role": "system",
        "content": [
            {"type": "text", "text": "You are an assistant for Dungeons and Dragons. You will be given few monsters. And your task is to come up with a short quest involving those monsters. The quest should be no more than 3 sentences long."}
        ]

    },
    {
        "role": "user",
        "content": [
            {"type": "text", "text": "Here are the monsters: Goblin, 2x Orc, and a Wolf."}
        ]
    }
]
inputs = processor.apply_chat_template(
	messages,
	add_generation_prompt=True,
	tokenize=True,
	return_dict=True,
	return_tensors="pt",
).to(model.device)

outputs = model.generate(**inputs, max_new_tokens=200)
print(processor.decode(outputs[0][inputs["input_ids"].shape[-1]:]))