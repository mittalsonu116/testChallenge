package core.utils.Reporter;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentReporter {
    private static ExtentReports extent;

    /**
     * Function to initial extent reporter instance.
     * Implementing singleton design pattern.
     * @return ExtentReports
     */
    public synchronized static ExtentReports initReporter(){
        if(extent == null){
            //set reporting file location
            extent=new ExtentReports(System.getProperty("user.dir")+ File.separator+"ExtentReports"+File.separator+"ExtentReportResults.html",true);
        }
        return extent;
    }

    /**
     * Function to clean the extent reporter instance
     */
    public static void cleanExtentReport(){
        extent.flush();
        extent.close();
    }
}
