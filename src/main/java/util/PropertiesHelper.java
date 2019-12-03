package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private static PropertiesHelper instance;

    private Properties properties;

    private PropertiesHelper(String resourcesName) {
        this.properties = getProperties(resourcesName);
    }

    public static PropertiesHelper load(String resourcesName) {
        if (instance == null) {
            instance = new PropertiesHelper(resourcesName);
        }

        return instance;
    }

    private Properties getProperties(String resourcesName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcesName)) {
            Properties prop = new Properties();
            prop.load(inputStream);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        throw new IllegalArgumentException("Key " + key + " not found.");
    }
}