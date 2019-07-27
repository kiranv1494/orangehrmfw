package com.orangehrm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	
	static Properties prop;

	public static void intializeConfig() throws IOException {
		File file = new File("..\\orangehrmfw\\resources\\testdata\\Configuration.properties");
		FileInputStream fis = new FileInputStream(file);
		prop = new Properties();
		prop.load(fis);
	}

	public static String readProperty(String keyName) {
		return prop.getProperty(keyName);
	}

}
