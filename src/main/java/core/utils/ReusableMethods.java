package core.utils;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    private RequestSpecification requestSpecification;

    /**
     * Parameterized constructor which assigns request specification of current instance
     * @param requestSpecification
     */
    public ReusableMethods(RequestSpecification requestSpecification){
        this.requestSpecification = requestSpecification;
    }

    /**
     * Generic method to fetch the reference of request specification of current instance
     * @return The request specification reference
     */
    private RequestSpecification getReqSpec(){
        return given(requestSpecification).log().all();
    }

    /**
     * Generic method to get the response
     * @param: url
     * @return: Validate-able Response object reference of Get request
     */
    public Response get(String url){
        requestSpecification = getReqSpec();

        Response response =
                requestSpecification.
                        when().
                        get(url);
        response.then().log().all();
        return response;
    }

    /**
     * Function to convert raw data to json
     * @param res
     * @return JsonPath
     */
    public static JsonPath rawToJson(Response res){
        return new JsonPath(res.asString());
    }

    /**
     * Get method to get the response through POJO classes
     */
    public Response getByPOJO(String url){
        requestSpecification = getReqSpec();

        Response response =
                requestSpecification.expect().defaultParser(Parser.JSON).
                        when().
                        get(url);
        //response.then().log().all().extract().response().asString();
        return response;
    }
}
