package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.utilities.*;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority =1, dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String firstName, String lname, String useremail, String password, String ph)
	{
		
		User userpayLoad = new User();
		userpayLoad.setId((int) Double.parseDouble(userID));
		userpayLoad.setUsername(userName);
		userpayLoad.setFirstName(firstName);
		userpayLoad.setLastName(lname);
		userpayLoad.setEmail(useremail);
		userpayLoad.setPassword(password);
		userpayLoad.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userpayLoad);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@Test(priority =2, dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDelete(String userName) {
		
		Response res = UserEndPoints.deleteUser(userName);
		
		Assert.assertEquals(res.getStatusCode(),404);
		
	}

}
