package com.king.game.service;

import com.king.game.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelServiceTest {
    private static final LevelService levelService = new LevelService();
    private static final int LEVEL_ID_1 = 1;
    private static final int USER_ID_1 = 1;
    private static final int USER_ID_2 = 2;
    private static final int score = 10;

    @Test
    public void create_level_should_save_level_with_user() {
        levelService.createLevel(LEVEL_ID_1, user(USER_ID_1, score));
        assertTrue(levelService.getUsersByLevel(LEVEL_ID_1).size() > 0);
    }

    @Test
    public void get_users_by_level_should_return_two_users_with_level_id() {
        levelService.createLevel(LEVEL_ID_1, user(USER_ID_1, score));
        levelService.createLevel(LEVEL_ID_1, user(USER_ID_2, score));
        assertEquals(2, levelService.getUsersByLevel(LEVEL_ID_1).size());
    }

    @Test
    public void get_users_by_level_should_return_empty_users_with_wrong_level_id() {
        levelService.createLevel(LEVEL_ID_1, user(USER_ID_2, score));
        assertEquals(0, levelService.getUsersByLevel(3).size());
    }

    private static User user(int userId, int score) {
        User user = new User();
        user.setUserId(userId);
        user.setScore(score);
        return user;
    }
}