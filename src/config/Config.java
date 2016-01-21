package config;

import java.io.InputStream;

public class Config{
	public static InputStream createStream(){
		return Config.class.getResourceAsStream("config.properties");
	}
}