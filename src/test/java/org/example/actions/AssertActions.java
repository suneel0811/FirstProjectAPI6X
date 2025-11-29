package org.example.actions;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertActions {

    public static void verifyResponseBody(String actual, String expected,String description){
        assertEquals(actual, expected, description);
    }
    public static void verifyResponseBody(float actual, float expected,String description){
        assertEquals(actual, expected, description);
    }
    public static void verifyResponseBody(int actual, int expected,String description){
        assertEquals(actual, expected, description);
    }

    public static void verifyResponseBody(double actual, double expected,String description){
        assertEquals(actual, expected, description);
    }

    public static void verifyResponseBody(boolean actual, boolean expected,String description){
        assertEquals(actual, expected, description);
    }

    public void verifyStatusCodeInvalidReq(Response response){
        assertEquals(String.valueOf(response.getStatusCode()).startsWith("50"),true,
                "Value of status code is "+response.getStatusCode());
    }

    public void verifyStatusCode(Response response){
        assertEquals(String.valueOf(response.getStatusCode()).startsWith("20"),true,
                "Value of status code is "+response.getStatusCode());
    }

}
