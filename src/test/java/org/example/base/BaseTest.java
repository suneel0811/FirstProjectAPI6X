package org.example.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.AssertActions;
import org.example.endPoints.APIConstants;
import org.example.modules.PayloadManager;
import org.example.utills.PropertyFileDataReader;
import org.testng.annotations.BeforeClass;

public class BaseTest { //single inheritance
    //Base tet -Father -->Testcase -son --->Single inheritanceCuuu

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PropertyFileDataReader propertyFileDataReader;
    @BeforeClass
    public void setConfig(){
        System.out.println("BeforeClass");
        payloadManager=new PayloadManager();
        assertActions=new AssertActions();
        propertyFileDataReader = new PropertyFileDataReader();
        //We can use any type of below requestSpecification
        //requestSpecification= RestAssured.given()
        // .contentType(ContentType.JSON).log().all();

        requestSpecification=new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();


    }

    public String getToken(){
        //Set up the URLs
        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_Url);
        System.out.println("Auth URL:"+requestSpecification);
        //Setting the payload
        String payload=payloadManager.setAuthPayload();

        //Getting the response
        response= requestSpecification
                .contentType(ContentType.JSON).body(payload)
                      .when()
                      .post();
        System.out.println("response:"+response.asString());
        //Extracting of the token via Deserialization
        String token=payloadManager.getTokenFromJSON(response.asString());
        System.out.println(token);
        //Verify
        return token;

    }
}
