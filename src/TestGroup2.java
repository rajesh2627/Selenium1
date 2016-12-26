import org.testng.annotations.Test;

public class TestGroup2 {
	@Test(groups={"high" })
	public void runfifth(){
		System.out.println("Fifth Run");
	}
}
