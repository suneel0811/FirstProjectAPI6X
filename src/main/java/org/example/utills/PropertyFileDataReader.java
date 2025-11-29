package org.example.utills;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileDataReader {

    public String readData(String key){
        Properties properties=new Properties();
        try{
            FileInputStream fileInputStream=new FileInputStream("src/main/resources/Data.properties");
            properties.load(fileInputStream);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return properties.getProperty(key);
    }
}
