package api.tests;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UserTests2 {
    Faker faker;
    User userPayload;
    public Logger logger;
    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    @Test(priority=1)
    public void testPostUser() {
        logger.info("********** Reading User Info ***************");
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("**********User info  is created ***************");
    }

    @Test(priority=2)
    public void testGetUserByName()
    {
        logger.info("********** Reading User Info ***************");

        Response response = UserEndPoints2.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("**********User info  is displayed ***************");
    }
    @Test(priority=3)
    public void testUpdateUserByName()
    {
        logger.info("********** Updating User ***************");
        //update data using payload
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());

        Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("********** User updated ***************");

        //Checking data after update
        Response responseAfterUpdate = UserEndPoints2.getUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
    }

    @Test(priority=4)
    public void testDeleteUserByName()
    {
        logger.info("**********   Deleting User  ***************");

        Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("********** User deleted ***************");
    }

}
