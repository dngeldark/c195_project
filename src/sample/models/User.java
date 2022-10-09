package sample.models;

import java.util.Objects;

public final class User {
    private final int userId;
    private final String userName;
    private final String password;

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public int userId() {
        return userId;
    }

    public String userName() {
        return userName;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return this.userId == that.userId &&
                Objects.equals(this.userName, that.userName) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password);
    }

    @Override
    public String toString() {
        return "User[" +
                "userId=" + userId + ", " +
                "userName=" + userName + ", " +
                "password=" + password + ']';
    }

}
