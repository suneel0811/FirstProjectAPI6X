package org.example.testcases.crud.testCreateBooking;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.example.base.BaseTest;
import org.example.endPoints.APIConstants;
import org.example.pojos.BookingResponse;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TestCreateBookingPost extends BaseTest {

    @Test(groups = "Smoke")
    @Owner("Suneel")
    @Severity(SeverityLevel.NORMAL)
    @Description("First simple create booking testcase")
    public void testCaseBooking(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response=RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse= response.then().log().all();

        //validatable assertion
        validatableResponse.statusCode(200);

        //Deser - response
        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());
        System.out.println(bookingResponse);

        //Assertj
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotEmpty().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("suneel");

        //TestNG Assertions
        assertActions.verifyStatusCode(response);

        System.out.println("Booking id " +bookingResponse.getBookingid());


    }

}
