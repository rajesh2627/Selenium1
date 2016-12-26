import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSite {
	public static ExcelReader excel = null;
	public WebDriver driver;
	public String url = ("http://www.seleniumframework.com//Practiceform");
	public Alert alert; 
	public boolean Result;
	
	@BeforeMethod
	public void Login(){
		System.setProperty("webdriver.gecko.driver","C:\\Selenium\\Gekodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	 @DataProvider
	  public static Object[][] getData(){
		  
		  if (excel==null){
			  
			  excel = new ExcelReader("C:\\Selenium\\TestSite.xlsx");
		  }
		  
		  String sheetName = "Complaint" ;
		  
		  int row = excel.getRowCount(sheetName);
		  int col = excel.getColumnCount(sheetName);
		  
		  Object[][] data = new Object[row-1][1];
		  Hashtable <String,String> table = null;
		  
		  for (int rowNum = 2;rowNum<=row;rowNum++){
			  
			  table = new Hashtable<String,String>();
			  
			  for (int colNum =0;colNum<col;colNum++){
				  
				  //data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				  
				  table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				  data[rowNum-2][0] = table;
			  }
		  }
		  
		  return data;
		  
	  }
	 
	 @Test(dataProvider = "getData")
	  public void read(Hashtable <String,String> data) {
		 
		 System.out.println(data.get("Name")+"  "+data.get("Email")+"   "+data.get("Telephone")+"   "+data.get("Country")+"   "+data.get("Company")+"   "+data.get("Message"));
		 
		 driver.findElement(By.xpath("//*[@id='presscore-contact-form-widget-3']/form/div/span[1]/input")).sendKeys(data.get("Name"));
		 driver.findElement(By.xpath("//*[@id='presscore-contact-form-widget-3']/form/div/span[2]/input")).sendKeys(data.get("Email"));
		 driver.findElement(By.xpath("//*[@id='presscore-contact-form-widget-3']/form/div/span[3]/input")).sendKeys(data.get("Telephone"));
		 driver.findElement(By.xpath("//*[@id='presscore-contact-form-widget-3']/form/div/span[4]/input")).sendKeys(data.get("Country"));
		 driver.findElement(By.xpath("//*[@id='presscore-contact-form-widget-3']/form/div/span[5]/input")).sendKeys(data.get("Company"));
		 driver.findElement(By.xpath("//*[@id='presscore-contact-form-widget-3']/form/span/textarea")).sendKeys(data.get("Message"));
		 driver.findElement(By.xpath("//*[@id='presscore-contact-form-widget-3']/form/p/a[1]")).click();
		 
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 
		 String Actual = driver.findElement(By.xpath("//*[@id='presscore-contact-form-widget-3']/form/div[1]/div")).getText();
		 String Expected = "Feedback has been sent to the administrator";
		 
		 System.out.println(Actual);
		 
		 if (Actual.equals(Expected)){
			System.out.println("Pass"); 
		 }else {
			 System.out.println("Fail");	
		 }
			 
			 driver.close();
		 
	 }
}
