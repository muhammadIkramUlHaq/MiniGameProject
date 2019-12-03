package com.king.game.service;

import com.king.game.model.User;
import com.king.game.repository.LevelRepository;

import java.util.Set;

public class LevelService {
    
    public void createLevel(int levelId, User user) {
        LevelRepository.getInstance().createLevel(levelId, user);
    }

    public Set<User> getUsersByLevel(int levelId) {
        return LevelRepository.getInstance().getUsersByLevel(levelId);
    }
}
