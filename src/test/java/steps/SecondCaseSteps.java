package steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class SecondCaseSteps {
    private static final RequestSpecification REQ_SPEC2 =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api/users?page=2")
                    .setContentType(ContentType.JSON)
                    .build();

    public static void findEmailByLastNameAndFirstNameWithPagination() {
        for (int i = 1; i >= numberOfPages() + 1; i++) {
            given()
                    .baseUri("https://reqres.in/api/users?page=" + i)
                    .contentType(ContentType.JSON)
                    .when().get()
                    .then()
                    .statusCode(200)
                    .body("data.find{it.first_name == 'Michael' & it.last_name == 'Lawson'}.email",
                            equalTo("michael.lawson@reqres"));
        }
    }

    public static void test() {
        String firstName = given()
                .baseUri("https://reqres.in/api/users?page=2")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("data[0].first_name");
        Assertions.assertEquals(firstName, "Michael");
    }

    public static int numberOfPages() {
        return
                given()
                        .baseUri("https://reqres.in/api/users")
                        .contentType(ContentType.JSON)
                        .when().get()
                        .then()
                        .extract().jsonPath().getInt("total_pages");
    }

    public static void checkCurrectFirstNameSecondCase() {
        String firstName = given()
                .spec(REQ_SPEC2)
                .when().get()
                .then()
                .extract().jsonPath().getString("data[0].first_name");

        Assertions.assertEquals(firstName, "Michael");
    }

    public static void checkCurrectLastNameSecondCase() {
        String lastName = given()
                .spec(REQ_SPEC2)
                .when().get()
                .then()
                .extract().jsonPath().getString("data[0].last_name");

        Assertions.assertEquals(lastName, "Lawson");
    }

    public static void checkCurrectEmailSecondCase() {
        String email = given()
                .spec(REQ_SPEC2)
                .when().get()
                .then()
                .extract().jsonPath().getString("data[0].email");

        Assertions.assertEquals(email, "michael.lawson@reqres");
    }

    public static void checkCurrectCountPages() {
        Assertions.assertEquals(numberOfPages(), 2);
    }

}

