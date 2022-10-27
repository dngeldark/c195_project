package sample.models;

/** Class to hold the state of the application.*/
public class AppState {

    // Logged in user or null
    private static User loggedUser;

    /** Set user after authentication
     *
     * @param user
     */
    public static void setUser(User user){
        loggedUser = user;
    }

    /** Get user.
     *
     * @return user.
     */
    public static User getLoggedUser(){
        return loggedUser;
    }

}
