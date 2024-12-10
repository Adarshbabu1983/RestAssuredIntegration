package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//import org.junit.Assert;
//import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.Test;



public class RestAssuredTest {
//    String baseUrl = "https://api.instantwebtools.net/v1/airlines";
    @Test
    public void verifyGetMethod(){
        Response response = RestAssured.given().log().all()
                .baseUri("https://api.instantwebtools.net/v1/airlines")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"_id\":\"252d3bca-d9bb-476c-9a97-562d685e235c\",\n" +
                        "    \"name\": \"Sri Lankan Airways\",\n" +
                        "    \"country\": \"Sri Lanka\",\n" +
                        "    \"logo\": \"https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/sri_lanka.png\",\n" +
                        "    \"slogan\": \"From Sri Lanka\",\n" +
                        "    \"head_quaters\": \"Katunayake, Sri Lanka\",\n" +
                        "    \"website\": \"www.srilankaaairways.com\",\n" +
                        "    \"established\": \"1990\"\n" +
                        "}")
                .post()
                    .then().log().all().extract().response();
        Assert.assertEquals(response.statusCode(),200);


    }
}
