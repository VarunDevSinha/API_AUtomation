package api.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.github.javafaker.Faker;
import api.endpoints.userEndpointsCRUD;
import api.payloads.UsersPOJO;
import io.restassured.response.Response;

public class userTest {

	static Faker dataFaker = new Faker();
	static UsersPOJO userPayload = new UsersPOJO();
	static Assertion assertion = new Assertion();

	@BeforeTest
	public void setupData() {

		userPayload.setUsername(dataFaker.name().username());
		userPayload.setFirstName(dataFaker.name().firstName());
		userPayload.setLastName(dataFaker.name().lastName());
		userPayload.setEmail(dataFaker.internet().safeEmailAddress());
		userPayload.setPassword(dataFaker.internet().password(6, 12));
		userPayload.setPhone(dataFaker.phoneNumber().cellPhone());
		userPayload.setId(dataFaker.idNumber().hashCode());
	}

	@Test(priority = 1)
	public void postUser() {

		Response createUserResponse = userEndpointsCRUD.createuser(userPayload);
		assertion.assertEquals(createUserResponse.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void getUser() {

		Response readUserResponse = userEndpointsCRUD.readuser(userPayload.getUsername());
		readUserResponse.then().log().body();

		assertion.assertEquals(readUserResponse.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void updateUser() {

		// Updating data in pay-load.
		userPayload.setFirstName(dataFaker.name().firstName());
		userPayload.setLastName(dataFaker.name().lastName());
		userPayload.setEmail(dataFaker.internet().safeEmailAddress());

		// Sending update request to API.
		Response updateUserResponse = userEndpointsCRUD.updateuser(userPayload, userPayload.getPassword());
		updateUserResponse.then().log().body();
		assertion.assertEquals(updateUserResponse.getStatusCode(), 200);

		// Reading the updated data.
		Response readUserResponse = userEndpointsCRUD.readuser(userPayload.getUsername());
		readUserResponse.then().log().body();
		assertion.assertEquals(readUserResponse.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void deleteUser() {

		Response deleteUserResponse = userEndpointsCRUD.deleteuser(userPayload.getUsername());
		deleteUserResponse.then().log().body();
		assertion.assertEquals(deleteUserResponse.getStatusCode(), 200);
	}
}
