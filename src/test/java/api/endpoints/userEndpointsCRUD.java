package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.UsersPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpointsCRUD {

	public static Response createuser(UsersPOJO payload) {

		Response postResponse = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.postURL);

		return postResponse;
	}

	public static Response readuser(String username) {

		Response getResponse = given().pathParam("username", username).when().get(Routes.getURL);

		return getResponse;
	}

	public static Response updateuser(UsersPOJO payload, String username) {

		Response getResponse = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).body(payload).when().put(Routes.updateURL);

		return getResponse;
	}

	public static Response deleteuser(String username) {

		Response getResponse = given().pathParam("username", username).when().delete(Routes.deleteURL);

		return getResponse;
	}
}
