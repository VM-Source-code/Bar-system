package models;

public class User {
    public String name;
    public String code;
    public UserType type;

    public User(String name, String code, UserType type) {
        this.name = name;
        this.code = code;
        this.type = type;
    }
}
