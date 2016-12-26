import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadExcelParam {
	
	public static ExcelReader excel = null;
	
  @Test(dataProvider = "getData")
  public void read(Hashtable <String,String> data) {
	  
	  //System.out.println(UserID +"  "+Password+"  "+Result);
	  
	  System.out.println(data.get("UserID") +"    " + data.get("Password"));
	  
  }
  
  @DataProvider
  public static Object[][] getData(){
	  
	  if (excel==null){
		  
		  excel = new ExcelReader("C:\\Selenium\\Login.xlsx");
	  }
	  
	  
	  String sheetName = "Guru99";
	  
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
  
  
}
