import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static io.restassured.RestAssured.given;

public class RestTest {

    @Test
    public void firstCase() {
        given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("data.find{it.first_name == 'George' & it.last_name == 'Bluth'}.email",
                        equalTo("george.bluth@reqres.in"));

    }

    public int numberOfPages()
    {
        return
        given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .extract().jsonPath().getInt("total_pages");
    }

    @Test
    public void secondCase() {
        for(int i = 1; i >= numberOfPages() + 1; i++) {
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
}
