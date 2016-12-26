import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Guru99Login {
	public static ExcelReader excel = null;
	public WebDriver driver;
	public String url = ("http://www.demo.guru99.com/V4/");
	public Alert alert; 
	public boolean Result;
	
	@BeforeMethod
	public void Login(){
		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\Gekodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	  @Test(dataProvider = "getData")
	  public void read(String UserID,String Password) {
		  		  			
		
			driver.findElement(By.xpath("html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys(UserID);
			driver.findElement(By.xpath("html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys(Password);
			driver.findElement(By.xpath("html/body/form/table/tbody/tr[3]/td[2]/input[1]")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Result = driver.findElement(By.xpath("html/body/div[1]/h2")).isDisplayed();
			
			try{
				if (Result == true){
					  System.out.println("Valid Login");
				  }
			}catch (Throwable t){
					  	 
			 driver.switchTo().alert();
			 alert.accept();
			 
			 // Assert.fail("Invalid Login", t);
			 }
			  		  
	  }
	  
	  @DataProvider
	  public static Object[][] getData(){
		  
		  if (excel==null){
			  
			  excel = new ExcelReader("C:\\Selenium\\Login.xlsx");
		  }
		  
		  
		  String sheetName = "Guru99";
		  
		  int row = excel.getRowCount(sheetName);
		  int col = excel.getColumnCount(sheetName);
		  
		  Object[][] data = new Object[row-1][col];
		  
		  for (int rowNum = 2;rowNum<=row;rowNum++){
			  for (int colNum =0;colNum<col;colNum++){
				  
				  data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			  }
		  }
			  
			  	    
		  
		return data;
		  
	  }
	 
	 
	  
	  
}
