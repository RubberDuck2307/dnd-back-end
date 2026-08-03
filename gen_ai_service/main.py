from flask import Flask, jsonify, request

from gen_ai_service.tools.link_maker import LinkMaker

app = Flask(__name__)

link_maker = LinkMaker()
@app.post('/link/generate')
def get_items():
    data = request.get_json()
    if not data or 'text' not in data:
        return jsonify({"error": "Missing 'text' in request body"}), 400
    link_maker.execute(data['text'])
    return jsonify({"status": "success"}), 200

if __name__ == '__main__':
    app.run(debug=True)