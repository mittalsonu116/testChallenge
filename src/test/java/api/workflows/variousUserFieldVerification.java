package api.workflows;

import com.relevantcodes.extentreports.LogStatus;
import core.api.Master;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static core.api.modues.User.*;

public class variousUserFieldVerification extends Master {

    private List<String> phoneNumberListOfUsers,invalidPhoneNumber,zipCodeOfUser,invalidZipCodes;

    @BeforeTest(alwaysRun = true)
    public void beforeTest(){
        extentTest=extentReports.startTest(this.getClass().getSimpleName());
        extentTest.setDescription("Workflow to validate correctness of user's field like phone number, zip code");
    }

    @Test(testName = "ValidationOnUserFields",description = "verify user's phone number format")
    public void test() {
        checkUserPhoneNumberFormat();
        checkAddressZipCode();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        Reporter.getCurrentTestResult();
        extentReports.endTest(extentTest);
    }

    /**
     * Check user's phone number format
     */
    public void checkUserPhoneNumberFormat() {
        phoneNumberListOfUsers=getUserPhoneNumberList();
        Assert.assertNotNull(phoneNumberListOfUsers,"List of phone number is empty");
        invalidPhoneNumber=checkPhoneNumberCorrectness(phoneNumberListOfUsers);
        extentTest.log(LogStatus.INFO,"Invalid phone numbers are: "+invalidPhoneNumber);
        Reporter.log("phone number format checking completed...");
    }

    /**
     * Check user's address's Zip Code field correctness
     */
    public void checkAddressZipCode(){
        zipCodeOfUser=getUserZipCodeList();
        Assert.assertNotNull(zipCodeOfUser,"List of zip code is empty");
        invalidZipCodes=checkZipCodeCorrectness(zipCodeOfUser);
        printInvalidZipCode(invalidZipCodes,extentTest);
        Reporter.log("zip code format checking completed...");
    }
}
