package com.king.game.model;

import java.util.Objects;

public class User {
    private int userId;
    private int score;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                score == user.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, score);
    }
}
