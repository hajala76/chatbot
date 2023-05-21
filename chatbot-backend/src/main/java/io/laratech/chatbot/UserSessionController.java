package io.laratech.chatbot;

import java.io.IOException;
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

       // save answer to db
       userSessionService.update(id, "answer");

       // return answer to frontend
       return JSONObject.quote("answer");
   }

   @DeleteMapping("/{id}")
   public void deleteById(@PathVariable String id) {
       userSessionService.deleteById(id);
   }

}
