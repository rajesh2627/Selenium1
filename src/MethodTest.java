import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MethodTest {
	
	@Test
	public void login(){
		
		System.out.println("Login");
	}
	
	@Test
	public void logout(){
		
		System.out.println("Logout");
	}
	
	@BeforeTest	
	public void before(){
		
		System.out.println("Before Login");
	}
	
	@BeforeMethod
	
	public void beforeMethod(){
		
		System.out.println("Before Method");
	}
	
@AfterMethod
	
	public void afterMethod(){
		
		System.out.println("After Method");
	}



}
