# ai-chatbot-answer-generator

This is a simple chatbot API that generates an answer to a string that is passed to this module.

To generate the answer, a neural net has been trained on the intents.json. Only a few simple intents have been added, like greetings, telling jokes and and generating answers about who/what the bot is.

The intents have been copied and modified from [Karan-Malik](https://github.com/Karan-Malik/Chatbot/blob/master/chatbot_codes/intents.json).

## Usage

The recommended way to use this API is with Docker.

run `docker build -t flask-restapi .` from within the root folder of this project to build the docker image.

This image will be used later on in the docker-compose file of the [main application](https://github.com/gradealx/ai-chatbot).
