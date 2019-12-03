package com.king.game.service;

import com.king.game.model.User;
import com.king.game.repository.UserRepository;

public class UserService {
    public void createUserSession(String sessionKey, int userId) {
        UserRepository.getInstance().createUser(sessionKey, userId);
    }

    public User getUser(String sessionKey) {
         return UserRepository.getInstance().getUser(sessionKey);
    }

    public void createScore(User user, int score) {
        UserRepository.getInstance().saveScore(user, score);
    }

    public int getScore(User user) {
        return UserRepository.getInstance().getUserScore(user);
    }

    public int getUserId(User user) {
        return UserRepository.getInstance().getUserId(user);
    }
}
