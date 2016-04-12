package core;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Selenium {
	public String[][] twoDimArray() throws IOException {
		String csvFile = "./src/main/resources/Title_Validation.csv";
		BufferedReader br = null;
		String line = null;
		String[] column = null;
		int lines = 0;
		int columns = 0;
		String SplitBy = ",";
		String text_case_id = null;
		String url = null;
		String title_expected = null;
		
		//counting lines and columns
		
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null){
			lines++;
			column = line.split(SplitBy);
			columns = column.length; 
		}
		br.close();
		
		String splitString[][] = new String[lines][columns];
		br = new BufferedReader(new FileReader(csvFile));
		WebDriver driver = new FirefoxDriver();
		int i = 0;
		while ((line = br.readLine()) != null) {
			String[] csv = line.split(SplitBy);
			text_case_id = csv[0];
			url = csv[1];
			title_expected = csv[2];
			
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String title_actual = driver.getTitle();
			
			splitString[i][0] = text_case_id;
			splitString[i][1] = title_expected;
			splitString[i][2] = title_actual;
			
			i++;
		}
		driver.quit();
		br.close();
		return splitString;
	}

		public static void main(String[] args) throws IOException {
			core.Selenium selenium = new core.Selenium();
			selenium.twoDimArray();
		}

	}