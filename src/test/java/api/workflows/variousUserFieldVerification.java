package api.workflows;

import core.api.Master;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

import static core.api.modues.User.*;

public class variousUserFieldVerification extends Master {

    private List<String> phoneNumberListOfUsers,invalidPhoneNumber,zipCodeOfUser,invalidZipCodes;

    @Test(testName = "ValidationOnUserFields",description = "verify user's phone number format")
    public void test()throws Exception {
        checkUserPhoneNumberFormat();
        checkAddressZipCode();
    }

    @AfterTest(alwaysRun = true)
    public void aftertest(){
        Reporter.getCurrentTestResult();
    }

    /**
     * Check user's phone number format
     */
    public void checkUserPhoneNumberFormat() {
        phoneNumberListOfUsers=getUserPhoneNumberList();
        Assert.assertNotNull(phoneNumberListOfUsers,"List of phone number is empty");
        invalidPhoneNumber=checkPhoneNumberCorrectness(phoneNumberListOfUsers);
        System.out.println(invalidPhoneNumber);
        Reporter.log("phone number format checking completed...");
    }

    /**
     * Check user's address's Zip Code field correctness
     */
    public void checkAddressZipCode(){
        zipCodeOfUser=getUserZipCodeList();
        Assert.assertNotNull(zipCodeOfUser,"List of zip code is empty");
        invalidZipCodes=checkZipCodeCorrectness(zipCodeOfUser);
        System.out.println(invalidZipCodes);
        Reporter.log("zip code format checking completed...");
    }
}
