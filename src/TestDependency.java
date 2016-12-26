import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDependency {
	
	
	@Test(priority =1)
	public void login(){
		System.out.println("Login");
		Assert.fail();
	}

	@Test(priority =2, dependsOnMethods = {"login"})
	public void register(){
		System.out.println("register");
	}
	
	@Test(priority =3, dependsOnMethods = {"login"},alwaysRun=true)
	public void runthird(){
		System.out.println("Third Run");
	}
	
	@Test(priority =4, dependsOnMethods = {"login"},alwaysRun=true)
	public void runfourth(){
		System.out.println("Fourth Run");
	}
	
	
}
