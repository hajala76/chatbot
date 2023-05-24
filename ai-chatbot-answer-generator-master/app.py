#!/usr/bin/env python
# encoding: utf-8
from flask import Flask, jsonify, request
from chatbot import generate_answer

app = Flask(__name__)


@app.route('/send', methods=["POST"])
def get_answer():
    message = request.get_json(force=True)
    res = generate_answer(message)
    return res


app.run()
