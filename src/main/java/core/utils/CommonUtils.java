package core.utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.List;

public class CommonUtils {

    /**
     * Function to check for invalid emails, prints invalid email(s) if present otherwise give message via extent reporter
     * @param invalidEmails
     */
    public static void printInvalidEmail(List<String> invalidEmails, ExtentTest extentTest) {
        if (!invalidEmails.isEmpty()) {
            for (String email : invalidEmails)
                extentTest.log(LogStatus.INFO, email + ": this email address has invalid format");
        } else
            extentTest.log(LogStatus.INFO, "All email address have valid format");
    }
}
