package demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializationTestGoogleMapsApi {
    @Test
    public static void addPlace(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress("29, side layout, cohen 09");
        place.setLanguage("French-IN");
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setName("Frontline house");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        place.setTypes(myList);
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        place.setLocation(location);
        Response res = given().queryParam("key","qaclick123")
                .body(place)
                .when().log().all()
                .post("/maps/api/place/add/json")
                .then().log().all()
                .assertThat().statusCode(200).extract().response();
        String responseString = res.asString();
        System.out.println(responseString);
    }
}
