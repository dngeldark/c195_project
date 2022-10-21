package sample.models;

public class AppState {
    private static User loggedUser;


    public static void setUser(User user){
        loggedUser = user;
    }

    public static User getLoggedUser(){
        return loggedUser;
    }

}
