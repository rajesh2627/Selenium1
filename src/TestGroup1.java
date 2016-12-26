import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGroup1 {
	@Test(priority =1, groups={"high" })
	public void login(){
		System.out.println("Login");
		//Assert.fail();
	}

	@Test(priority =2, dependsOnMethods = {"login"}, groups={"high" })
	public void register(){
		System.out.println("register");
	}
	
	@Test(priority =3, dependsOnMethods = {"login"},alwaysRun=true, groups={"med" })
	public void runthird(){
		System.out.println("Third Run");
	}
	
	@Test(priority =4, dependsOnMethods = {"login"},alwaysRun=true, groups={"low" })
	public void runfourth(){
		System.out.println("Fourth Run");
	}
}
