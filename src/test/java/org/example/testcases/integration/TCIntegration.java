package org.example.testcases.integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.base.BaseTest;
import org.example.endPoints.APIConstants;
import org.example.pojos.Booking;
import org.example.pojos.BookingResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TCIntegration extends BaseTest {

    //Create a booking , Create a Token - token is common across the project so we will create token in Base test
    //Get booking
    //Update booking
    //Delete booking

    @Test(groups = "integration", priority = 1)
    @Owner("Suneel")
    @Description("TC#INT-1 Step 1:Verify that booking can be created")
    public void testCreateBooking(ITestContext iTestContext) {
        Assert.assertTrue(true);
        iTestContext.setAttribute("token", getToken());

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response
                = RestAssured.given().spec(requestSpecification)
                .body(payloadManager.createPayloadBookingAsString())
                .post();
        validatableResponse = response.then().log().all();

        //Validatable Assertion
        validatableResponse.statusCode(200);

        //Deser - response
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        System.out.println(bookingResponse);

        //Assertj
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotEmpty().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(propertyFileDataReader.readData("booking.firstName"));

        //Set the booking id
        iTestContext.setAttribute("bookingId", bookingResponse.getBookingid());

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Suneel")
    @Description("TC#INT-1 Step 2:Verify the booking is created")
    public void testVerifyBookingID(ITestContext iTestContext) {
        Assert.assertTrue(true);
        System.out.println("Token :" + iTestContext.getAttribute("token"));
        String bookingID = iTestContext.getAttribute("bookingId").toString();
        System.out.println("booking id:"+bookingID);
        //URL - restfull booker/booking/1234(booking id)
        String basePathGet = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingID;
        System.out.println("Booking URL:"+basePathGet);

        requestSpecification = requestSpecification.basePath(basePathGet);
        response = RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all();

        //Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking=payloadManager.getResponseFromJson(response.asString());
        //Assertj
        assertThat(booking.getFirstname()).isNotNull();
        assertThat(booking.getFirstname()).isNotEmpty().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("suneel");

    }

    @Test(groups = "integration", priority = 3)
    @Owner("Suneel")
    @Description("TC#INT-1 Step 3:Update the booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {
        Assert.assertTrue(true);
        System.out.println("Token :" + iTestContext.getAttribute("token"));

        String token= iTestContext.getAttribute("token").toString();
        String bookingID = iTestContext.getAttribute("bookingId").toString();
        System.out.println("booking id:"+bookingID);
        //URL - restfull booker/booking/1234(booking id)
        String basePathPutPatch = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingID;
        System.out.println("Update booking URL:"+basePathPutPatch);

        requestSpecification = requestSpecification.basePath(basePathPutPatch);
        response = RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadBookingAsString()).put();

        validatableResponse = response.then().log().all();

        //Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking=payloadManager.getResponseFromJson(response.asString());
        //Assertj
        assertThat(booking.getFirstname()).isNotNull();
        assertThat(booking.getFirstname()).isNotEmpty().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("kumar");

    }

    @Test(groups = "integration", priority = 4)
    @Owner("Suneel")
    @Description("TC#INT-1 Step 4:Delete the booking by ID")
    public void testDeleteBookingByID(ITestContext iTestContext) {
        Assert.assertTrue(true);
        System.out.println("Token :" + iTestContext.getAttribute("token"));

        String token= iTestContext.getAttribute("token").toString();
        String bookingID = iTestContext.getAttribute("bookingId").toString();
        System.out.println("booking id:"+bookingID);
        //URL - restfull booker/booking/1234(booking id)
        String basePathDelete = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingID;
        System.out.println("Update booking URL:"+basePathDelete);

        requestSpecification = requestSpecification.basePath(basePathDelete);
        response = RestAssured.given(requestSpecification).cookie("token", token)
                .when().delete();

        validatableResponse = response.then().log().all();

        //Validatable Assertion
        validatableResponse.statusCode(201);
    }
}
