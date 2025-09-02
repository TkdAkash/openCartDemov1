package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String[][] getLoginData() throws IOException {

		String path = ".\\testData\\testDataFile.xlsx";
		ExcelUtility exutility = new ExcelUtility(path);

		int totalRows = exutility.getRowCount("Sheet1");
		int totalCell = exutility.getCellCount("Sheet1", 1);

		String loginData[][] = new String[totalRows][totalCell];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCell; j++) {
				loginData[i - 1][j] = exutility.getCellData("Sheet1", i, j);
			}
		}
		return loginData;

	}

}
