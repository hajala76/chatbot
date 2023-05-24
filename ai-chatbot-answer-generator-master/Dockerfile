FROM python:3.9

WORKDIR /opt/app

COPY . .

RUN pip install --no-cache-dir -r requirements.txt

RUN python3 -m nltk.downloader punkt
RUN python3 -m nltk.downloader wordnet
RUN python3 -m nltk.downloader omw-1.4

EXPOSE 5000

CMD ["python3", "-m", "flask", "run", "--host=0.0.0.0"]