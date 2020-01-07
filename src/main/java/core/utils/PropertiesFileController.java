package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFileController {

    private Map<String,String> configData = new HashMap<>();

    /**
     * Function to load configuration data into map object
     * @return Map<String,String>
     */
    public Map<String,String> readConfigFileData() {
        try {
            InputStream input = new FileInputStream(System.getProperty("user.dir")+ File.separator+"config.properties");
            Properties prop = new Properties();
            prop.load(input);
            for (String key : prop.stringPropertyNames()) {
                configData.put(key, prop.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configData;
    }
}
