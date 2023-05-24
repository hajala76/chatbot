# chatbot

link to solution: https://essentialistengineer.com/how-to-build-a-chat-ui-with-angular/

ai-chatbot-frontend
In ai-chatbot-frontend/frontend run : docker build -t frontend . 
to build the docker image for the frontend.

ai-chatbot-backend
in ai-chatbot-backend run maven install to build the spring app.

run docker build --file=backend.dockerfile -t springapp . to build the docker image.

running the whole application
Once all docker images are in place, the docker-compose file can be run from the root directory of the project with:

docker-compose -f docker-compose-dev.yml up

The UI can be opened in http://localhost:4200/

The DB can be viewed in http://localhost:8081/ (pw/user: mexpress)