package one.digitalinovation.parking.controller;



import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import one.digitalinovation.parking.controller.dto.ParkingCreateDTO;

@SpringBootTest(webEnvironment =WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractControllerBase{

	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void WhenFindAllThenCheckResult() {
		RestAssured.given().auth().basic("user", "Dio@123456").when().get("/parking").then().statusCode(HttpStatus.OK.value()).extract().response().body().prettyPrint();
	}

	@Test
	void WhenCreatedThenCheckIsCreated() {
		var createDTO = new ParkingCreateDTO("ASX-0129","SC", "Chevet", "CINZA");
	 RestAssured.given().when().auth().basic("user", "Dio@123456").contentType(MediaType.APPLICATION_JSON_VALUE).body(createDTO).post("/parking").then()
	 .statusCode(HttpStatus.CREATED.value()).body("license", Matchers.equalTo("ASX-0129"));
	}

}
