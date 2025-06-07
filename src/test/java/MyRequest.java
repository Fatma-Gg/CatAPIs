import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MyRequest {
    RequestSpecification requestSpecification;

    @BeforeTest
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://catfact.ninja")
                .addHeader("Content-Type","application/json")
                .build();
    }

    @Test
    public void facts_test() {

        given().log().all()
                .spec(requestSpecification)
                .queryParam("max_length",20)
                .when()
                .get("/fact")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void facts_list_test() {

        given().log().all().spec(requestSpecification)
                .queryParam("limit","5")
                .queryParam("length","4")
                .when().get("/facts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);


    }
    @Test
    public void breeds_test() {
        given().log().all().spec(requestSpecification)
                .queryParam("limit","5")
                .when().get("breeds/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);


    }
}
