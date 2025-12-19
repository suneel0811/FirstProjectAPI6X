package org.example.testcases.ddUsingExcel;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.utills.UtilExcel;
import org.testng.annotations.Test;

import static org.example.utills.UtilExcel.getData;

public class VWOLoginAPITc {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Integer integer;
    Response response;
    @Test(dataProvider ="getData", dataProviderClass = UtilExcel.class)
    public void testVWLogin(String email, String password){
        System.out.println("---Login VW API testing");
        System.out.println(email);
        System.out.println(password);

        //RestAsusred code

        VWOLoginPojo vwoLoginPojo=new VWOLoginPojo();
        vwoLoginPojo.setUsername(email);
        vwoLoginPojo.setPassword(password);
        vwoLoginPojo.setRecaptchaResponseField(" ");
        vwoLoginPojo.setRemember(false);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://app.vwo.com").basePath("/login");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(vwoLoginPojo).log().all();

            validatableResponse=requestSpecification.when().post().then().log().all();

            String res=validatableResponse.toString();
        System.out.println(res);
        validatableResponse.statusCode(401);


    }
}
