package com.king.game.repository;

import com.king.game.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LevelRepository {
    private static LevelRepository levelRepository;
    private HashMap<Integer, Set<User>> levelMap = new HashMap<>();

    private LevelRepository() {
    }

    public static synchronized LevelRepository getInstance() {
        if (levelRepository == null) {
            levelRepository = new LevelRepository();
        }
        return levelRepository;
    }

    public void createLevel(int levelId, User user) {
        Set<User> users = levelMap.get(levelId);
        if (users == null) {
            users = new HashSet<>();
            users.add(user);
        } else {
            users.add(user);
        }
        levelMap.put(levelId, users);
    }

    public Set<User> getUsersByLevel(int levelId) {
        return levelMap.get(levelId) != null ? levelMap.get(levelId) : new HashSet<>();
    }
}
