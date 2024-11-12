package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private String name;
    private String password;
    private String email;

    public CommonUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

}
