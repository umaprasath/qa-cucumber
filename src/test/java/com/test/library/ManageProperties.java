package com.test.library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
 
public class ManageProperties {
  public Properties read() {
 
	Properties prop = new Properties();
	InputStream input = null;
 
	try {
 
		input = new FileInputStream("src/test/java/config.properties");
 
		// load a properties file
		prop.load(input);
 
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	return prop;
 
  }
  
  public void write(String propName, String propValue) {
	  
		Properties prop = new Properties();

		InputStream input = null;
		OutputStream output = null;
	 
		try {

			input = new FileInputStream("bin/config.properties");
			prop.load(input);
	        input.close();
			output = new FileOutputStream("bin/config.properties");
	 
			// set the properties value
			prop.setProperty(propName, propValue);
	 
			// save properties to project root folder
			prop.store(output, null);
			
			output.close();
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	  }
	}