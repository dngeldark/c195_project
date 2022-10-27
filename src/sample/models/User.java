package sample.models;

import java.util.Objects;

/** Class for the user objects.*/
public final class User {
    private final int userId;
    private final String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    /** Get user id
     *
     * @return user id
     */
    public int userId() {
        return userId;
    }

    /** get Username
     *
     * @return username
     */
    public String username() {
        return userName;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null || obj.getClass() != this.getClass()) return false;
//        var that = (User) obj;
//        return this.userId == that.userId &&
//                Objects.equals(this.userName, that.userName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, userName);
//    }
//
//    @Override
//    public String toString() {
//        return  userId + ", " + userName;
//    }
}
