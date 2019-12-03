package com.king.game.service;

import com.king.game.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private final UserService userService = new UserService();
    private final static String SESSION_KEY_1 = "sessionKey1";
    private final static String SESSION_KEY_2 = "sessionKey2";
    private final static int USER_ID_1 = 1;
    private final static int USER_ID_2 = 2;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
    }

    @org.junit.jupiter.api.Test
    public void create_user_session_should_save_user_with_user_id() {
        userService.createUserSession(SESSION_KEY_1, USER_ID_1);
        assertEquals(USER_ID_1, userService.getUser(SESSION_KEY_1).getUserId());
    }

    @org.junit.jupiter.api.Test
    public void create_user_session_should_save_two_user_with_two_ids() {
        userService.createUserSession(SESSION_KEY_1, USER_ID_1);
        userService.createUserSession(SESSION_KEY_2, USER_ID_2);
        assertArrayEquals(new Integer[]{USER_ID_1, USER_ID_2},
                new Integer[]{userService.getUser(SESSION_KEY_1).getUserId(),
                        userService.getUser(SESSION_KEY_2).getUserId()});
    }

    @org.junit.jupiter.api.Test
    public void get_user_should_return_user_with_given_session_key() {
        userService.createUserSession(SESSION_KEY_1, USER_ID_1);
        assertNotEquals(user(USER_ID_1), userService.getUser(SESSION_KEY_2));
    }

    @org.junit.jupiter.api.Test
    public void get_user_should_return_empty_user_with_wrong_session_key() {
        userService.createUserSession(SESSION_KEY_1, USER_ID_1);
        assertEquals(new User(), userService.getUser(SESSION_KEY_2));
    }

    @org.junit.jupiter.api.Test
    public void creat_score_should_save_score_with_given_user() {
        userService.createUserSession(SESSION_KEY_1, USER_ID_1);
        final User user = userService.getUser(SESSION_KEY_1);
        userService.createScore(user, 1200);
        assertEquals(1200, user.getScore());
    }

    private static User user(int userId) {
        User user = new User();
        user.setUserId(userId);
        return user;
    }

}