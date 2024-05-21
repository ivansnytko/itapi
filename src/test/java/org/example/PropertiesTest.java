package org.example;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesTest {

    @Test
    public void bundle(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("api");
        System.out.println(resourceBundle.getString("id"));
    }

    @Test
    public void properties() throws IOException {
        Properties properties = new Properties();
        FileReader file = new FileReader("src/test/resources/api.properties");
        properties.load(file);
        System.out.println(properties.getProperty("id"));
    }
}
