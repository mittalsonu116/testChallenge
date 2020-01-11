package core.api.modues;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import core.api.EndPoints;
import core.api.Master;
import core.utils.ReusableMethods;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User extends Master {

    /**
     * Function to fetch user id from user json list
     * @param userName
     * @return String
     */
    public static String getUserID(String userName){
        String id=null;
        Response resp=getUserApiResponse();
        List<String> respList=resp.jsonPath().getList("$");
        for(int i=0;i<respList.size();i++){
            String username = resp.jsonPath().getString("username["+i+"]");
            if(username.equals(userName)){
                id= resp.jsonPath().getString("id["+i+"]");
                break;
            }
        }
        return id;
    }

    /**
     * Function to check total number of user available on server
     * @param userCount
     * @return boolean
     */
    public static boolean checkUserCount(String userCount){
        boolean flag=false;
        List<String> respList=getUserApiResponse().jsonPath().getList("$");
        if(respList.size() == Integer.parseInt(userCount)){
            flag=true;
        }
        return flag;
    }

    /**
     * Function to check user's phone number format
     * @param phoneNumbers
     * @return List<String>
     */
    public static List<String> checkPhoneNumberCorrectness(List<String> phoneNumbers){
        List<String> invalidPhoneNumbers=new LinkedList<>();
        Pattern p=Pattern.compile("^(1|024|\\(254\\)|(775))?(-)?[0-9]{3}(-)?([0-9]{3}(-))?[0-9]{4}$");
        for (String phoneno:phoneNumbers){
            Matcher m = p.matcher(phoneno);
            if(!m.find())
                invalidPhoneNumbers.add(phoneno);
        }
       return invalidPhoneNumbers;
    }

    /**
     * Function to fetch user's phone number from json response array objects
     * @return List<String>
     */
    public static List<String> getUserPhoneNumberList(){
        List<String> phoneNumbers=new ArrayList<>();
        Response resp=getUserApiResponse();
        List<String> respList=resp.jsonPath().getList("$");
        for (int i=0;i<respList.size();i++){
            phoneNumbers.add(resp.jsonPath().getString("phone["+i+"]"));
        }
        return phoneNumbers;
    }

    /**
     * Function to check user's zip code format
     * @param zipCode
     * @return List<String>
     */
    public static List<String> checkZipCodeCorrectness(List<String> zipCode){
        List<String> invalidZipCode=new LinkedList<>();
        Pattern p=Pattern.compile("^[0-9]{5}(-)?([0-9]{4})?$");
        for (String zip:zipCode){
            Matcher m = p.matcher(zip);
            if(!m.find())
                invalidZipCode.add(zip);
        }
        return invalidZipCode;
    }

    /**
     * Function to fetch user's zip code from json response array objects
     * @return List<String>
     */
    public static List<String> getUserZipCodeList(){
        List<String> zipCode=new ArrayList<>();
        Response resp=getUserApiResponse();
        List<String> respList=resp.jsonPath().getList("$");
        for (int i=0;i<respList.size();i++){
            zipCode.add(resp.jsonPath().getString("address.zipcode["+i+"]"));
        }
        return zipCode;
    }

    /**
     * Function to get user api response
     * @return Response
     */
    public static Response getUserApiResponse(){
        Response resp=new ReusableMethods(portalSpec).get(EndPoints.USER);
        resp.then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        return resp;
    }

    /**
     * Function to fetch all user id from user json list
     * @return List<String>
     */
    public static List<String> getUserIDList(){
        List<String> idList=new ArrayList<>();
        Response resp=getUserApiResponse();
        List<String> respList=resp.jsonPath().getList("$");
        for(int i=0;i<respList.size();i++){
            idList.add(resp.jsonPath().getString("id["+i+"]"));
        }
        return idList;
    }

    /**
     * Function to fetch response through POJO classes
     */
    public static List<core.utils.response_POJO.User> getUserResponse(){
        return Arrays.asList(new ReusableMethods(portalSpec).getByPOJO(EndPoints.USER)
                .as(core.utils.response_POJO.User[].class));
    }

    /**
     * Function to print invalid zip code
     * @param invalidZipCodes
     * @param extentTest
     */
    public static void printInvalidZipCode(List<String> invalidZipCodes, ExtentTest extentTest){
        if(invalidZipCodes.size() == 0)
            extentTest.log(LogStatus.INFO,"There is no invalid Zip Code present");
        else
            extentTest.log(LogStatus.INFO,"Invalid Zip Code are: "+invalidZipCodes);
    }
}
