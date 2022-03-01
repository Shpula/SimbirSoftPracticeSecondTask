import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.FirstCaseSteps;
import steps.SecondCaseSteps;

import static io.restassured.RestAssured.given;
import static steps.FirstCaseSteps.*;
import static steps.SecondCaseSteps.*;

public class RestTest {


    @Test
    public void firstCase() {
        findEmailByLastNameAndFirstName();
        Assertions.assertEquals(checkCurrectFirstNameFirstCase() , "George");
        Assertions.assertEquals(checkCurrectLastNameFirstCase(), "Bluth");
        Assertions.assertEquals(checkCurrectEmailFirstCase(), "george.bluth@reqres.in");

    }

    @Test
    public void secondCase() {
        findEmailByLastNameAndFirstNameWithPagination();
        Assertions.assertEquals(checkCurrectFirstNameSecondCase(), "Michael");
        Assertions.assertEquals(checkCurrectLastNameSecondCase(), "Lawson");
        Assertions.assertEquals(checkCurrectEmailSecondCase(), "michael.lawson@reqres");
        Assertions.assertEquals(numberOfPages(), 2);
    }
}

