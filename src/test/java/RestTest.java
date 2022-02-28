import org.junit.jupiter.api.Test;
import steps.FirstCaseSteps;
import steps.SecondCaseSteps;

import static io.restassured.RestAssured.given;

public class RestTest {


    @Test
    public void firstCase() {
        FirstCaseSteps.findEmailByLastNameAndFirstName();
        FirstCaseSteps.checkCurrectFirstNameFirstCase();
        FirstCaseSteps.checkCurrectLastNameFirstCase();
        FirstCaseSteps.checkCurrectEmailFirstCase();

    }

    @Test
    public void secondCase() {
        SecondCaseSteps.findEmailByLastNameAndFirstNameWithPagination();
        SecondCaseSteps.checkCurrectFirstNameSecondCase();
        SecondCaseSteps.checkCurrectLastNameSecondCase();
        SecondCaseSteps.checkCurrectEmailSecondCase();
        SecondCaseSteps.checkCurrectCountPages();
    }
}

