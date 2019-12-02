package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
	
	private Properties properties;
	
	public Properties getProperties(String path) {
		
		this.properties = new Properties();
		
		try {
			InputStream input = new FileInputStream(path);
			properties.load(input);
        } catch (IOException ex) {
        	System.out.println("Favor informar o local para o arquivo properties");
            ex.printStackTrace();
        }
		
		return properties;
	}

}
