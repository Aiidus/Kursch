package sample;

public class User {
    public User() {

    }

    public User(String login) {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private String FirstName;
    private String LastName;
    private String Group;
    private String Login;
    private String Password;

    public User(String firstName, String lastName, String group, String login, String password) {
        FirstName = firstName;
        LastName = lastName;
        Group = group;
        Login = login;
        Password = password;
    }

}
