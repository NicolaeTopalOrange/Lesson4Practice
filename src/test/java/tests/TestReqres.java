package tests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.sound.midi.Patch;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static utils.Steps.*;

public class TestReqres {

    @BeforeSuite
    public void setUp() {
        baseURI = "https://reqres.in/api";
    }

    @Test
    public void testListUsers() {

        String url = "/users?page=2";

        Response response = GET(url);

        isStatusCodeValid(response, 200);

    }

    @Test
    public void testSingleUser() {
        String url = "/users/2";

        Response response = GET(url);

        isStatusCodeValid(response, 200);
        isBodyContains(response, "id");
    }

    @Test
    public void testCreate() {
        String url = "/users";

        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = POST(body, url);

        isStatusCodeValid(response, 201);
        isBodyContains(response, "id");

    }

    @Test
    public void testUpdatePut() {
        String url = "/users/2";
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        Response response = PUT(body, url);

        isStatusCodeValid(response, 200);
        isBodyContains(response, "updatedAt");

    }

    @Test
    public void testUpdatePatch() {
        String url = "/users/2";
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        Response response = PATCH(body, url);

        isStatusCodeValid(response, 200);
        isBodyContains(response, "updatedAt");

    }

    @Test
    public void testDelete(){
        String url = "/users/2";

        Response response = DELETE(url);

        isStatusCodeValid(response, 204);
    }



}
