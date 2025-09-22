package com.example.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties p = new Properties();

    static {  // static block executes once when the class is loaded
    	String env=System.getProperty("env"); //read from -Denv=qa
    	if(env==null || env.isEmpty()) {
    		env="dev"; //default
    	}
    	String fileName="config-"+env+ ".properties";
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            p.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
    	//check environment variable first (convert to upper case with underscores)
    	String envKey=System.getenv(key.toUpperCase().replace('.', '_'));
    	if(envKey!=null && !envKey.isEmpty())
    	{
    		return envKey;
    	}
        return p.getProperty(key);
    }
}
