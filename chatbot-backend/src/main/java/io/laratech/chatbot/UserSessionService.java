package io.laratech.chatbot;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {

   @Autowired
   private UserSessionRepository userSessionRepository;

   public List<UserSession> findAll() {
       return userSessionRepository.findAll();
   }

   public UserSession findById(String id) {
       if (userSessionRepository.findById(id).isEmpty()) {
           return null;
       }
       return userSessionRepository.findById(id).orElseThrow(UnsupportedOperationException::new);
   }

   public UserSession save(UserSession userSession) {
       return userSessionRepository.save(userSession);
   }

   public void deleteById(String id) {
       userSessionRepository.deleteById(id);
   }

   public void update(String id, String newEntry) {
       Optional<UserSession> session = userSessionRepository.findById(id);
       try {
           session.get().updateConversation(newEntry);
           userSessionRepository.deleteById(id);
           userSessionRepository.save(session.get());
       } catch (Exception e) {
           UserSession newSession = new UserSession(id, new ArrayList());
           newSession.updateConversation(newEntry);
           userSessionRepository.deleteById(id);
           userSessionRepository.save(newSession);
       }
   }
}