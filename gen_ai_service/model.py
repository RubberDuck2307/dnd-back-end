from transformers import AutoProcessor, AutoModelForMultimodalLM

model_id = "google/gemma-4-26B-A4B-it"
local_dir = "/home/user/src_wsl/dnd-back-end/gen_ai_service"

processor = AutoProcessor.from_pretrained(model_id, cache_dir=local_dir)
model = AutoModelForMultimodalLM.from_pretrained(model_id, cache_dir=local_dir)