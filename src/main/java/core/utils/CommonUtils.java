package core.utils;

import java.util.List;

public class CommonUtils {

    /**
     * Function to fetch user id from user json list
     * @param invalidEmails
     */
    public static void printInvalidEmail(List<String> invalidEmails) {
        if (!invalidEmails.isEmpty()) {
            for (String email : invalidEmails)
                System.out.println(email + ": this email address has invalid format");
        } else {
            System.out.println("All email address have valid format");
        }
    }
}
