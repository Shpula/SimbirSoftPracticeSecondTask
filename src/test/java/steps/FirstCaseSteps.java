package steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class FirstCaseSteps {
    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api/users")
                    .setContentType(ContentType.JSON)
                    .build();

    public static void findEmailByLastNameAndFirstName() {
        given()
                .spec(REQ_SPEC)
                .when().get()
                .then()
                .statusCode(200)
                .body("data.find{it.first_name == 'George' & it.last_name == 'Bluth'}.email",
                        equalTo("george.bluth@reqres.in"));
    }

    public static void checkCurrectFirstNameFirstCase() {
        String firstName = given()
                .spec(REQ_SPEC)
                .when().get()
                .then()
                .extract().jsonPath().getString("data[0].first_name");

        Assertions.assertEquals(firstName, "George");
    }

    public static void checkCurrectLastNameFirstCase() {
        String lastName = given()
                .spec(REQ_SPEC)
                .when().get()
                .then()
                .extract().jsonPath().getString("data[0].last_name");

        Assertions.assertEquals(lastName, "Bluth");
    }

    public static void checkCurrectEmailFirstCase() {
        String email = given()
                .spec(REQ_SPEC)
                .when().get()
                .then()
                .extract().jsonPath().getString("data[0].email");

        Assertions.assertEquals(email, "george.bluth@reqres.in");
    }
}

