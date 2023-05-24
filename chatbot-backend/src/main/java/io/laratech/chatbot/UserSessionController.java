package io.laratech.chatbot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONObject;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserSessionController {

   @Autowired
   private UserSessionService userSessionService;

   @GetMapping
   public List<UserSession> findAll() {
       return userSessionService.findAll();
   }

   @GetMapping("/{id}")
   public UserSession findById(@PathVariable String id) {
       return userSessionService.findById(id);
   }

   @PostMapping
   public UserSession create(@RequestBody UserSession userSession) {
       return userSessionService.save(userSession);
   }

   @PutMapping("/{id}")
   public String update(@RequestBody String messageFromFrontend, @PathVariable String id) throws IOException, InterruptedException {

      // set conversation from frontend
      userSessionService.update(id, messageFromFrontend.replaceAll("\"", ""));
      System.out.println(messageFromFrontend);

      // send message to python service
      String uri = "http://host.docker.internal:5000/send";
     // String uri = "http://localhost:5000/send";

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder(URI.create(uri))
              .header("content-type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString("\"" + messageFromFrontend + "\""))
              .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(response.body());

      // save answer to db
      userSessionService.update(id, response.body().replaceAll("\"", ""));

      // return answer to frontend
      return JSONObject.quote(response.body());
   }

   @DeleteMapping("/{id}")
   public void deleteById(@PathVariable String id) {
       userSessionService.deleteById(id);
   }

}
