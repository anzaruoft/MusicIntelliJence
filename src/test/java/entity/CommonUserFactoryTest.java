package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserFactoryTest {

    @Test
    void create() {
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        String username = "MichaelYe";
        String password = "Password";
        String email = "myemail@gmail.com";
        User commonUser = commonUserFactory.create("MichaelYe",
                "Password", "myemail@gmail.com");

        Assertions.assertEquals(username, commonUser.getName());
        Assertions.assertEquals(password, commonUser.getPassword());
        Assertions.assertEquals(email, commonUser.getEmail());

    }
}