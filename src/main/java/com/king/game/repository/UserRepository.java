package com.king.game.repository;

import com.king.game.model.User;

import java.util.HashMap;

public class UserRepository {
    private static UserRepository userRepository;
    private HashMap<String, User> userMap = new HashMap<>();

    private UserRepository() {
    }

    public static synchronized UserRepository getInstance()  {
        if (userRepository == null){ 
            userRepository = new UserRepository();
        }
        return userRepository;
    }
    
    public void createUser(String sessionKey, int userId) {
        User user = new User();
        user.setUserId(userId);
        userMap.put(sessionKey, user);
    }

    public User getUser(String sessionKey) {
         return userMap.get(sessionKey) != null ? userMap.get(sessionKey) : new User();
    }

    public void saveScore(User user, int score) {
        if (user.getScore() < score) {
            user.setScore(score);
        }
    }

    public int getUserScore(User user) {
        return user.getScore();
    }
    
    public int getUserId(User user){
        return user.getUserId();
    }
}
