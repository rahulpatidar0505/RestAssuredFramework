package testutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class TestBase {

	public static Properties pro;
//@BeforeMethod
	@BeforeTest
	public static void initialization() {
		pro = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream("./Configuration/config.properties");
			pro.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
