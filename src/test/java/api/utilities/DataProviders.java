package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {

        String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";

        XLUtility xl = new XLUtility(path);

        int rowCount = xl.getRowCount("Sheet1");
        int cellCount = xl.getCellCount("Sheet1", 1);

        Object[][] data = new Object[rowCount][cellCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < cellCount; j++) {
                data[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }

        return data;
    }

    @DataProvider(name = "UserNames")
    public Object[] getUsernames() throws IOException {

        String path = System.getProperty("user.dir")
                + "\\testData\\UserData.xlsx";

        XLUtility xl = new XLUtility(path);

        int rowCount = xl.getRowCount("Sheet1");

        Object[] usernames = new Object[rowCount];

        for (int i = 1; i <= rowCount; i++) {
            usernames[i - 1] = xl.getCellData("Sheet1", i, 0);
        }

        return usernames;
        
    }
}