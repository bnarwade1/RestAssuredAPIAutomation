package api.test;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		//logs
		logger= LogManager.getLogger(this.getClass());
		
		
		
		
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		
		logger.info("-----------------creating user------------------");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("************user is created**********");
	}
	 
	@Test(priority = 2)
	public void testGetuserbyName() {
		logger.info("************user info reading*****----");
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	 
		@Test(priority = 3)
		public void testupdateUser() {
			logger.info("************user is updated**********");
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			
			Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
			response.then().log().body();
			
			Assert.assertEquals(response.getStatusCode(),200);
			
			//checking data after update
			Response responseData = UserEndPoints2.readUser(this.userPayload.getUsername());
			responseData.then().log().all();
			
			Assert.assertEquals(responseData.getStatusCode(),200);
			
		}
		
		@Test(priority = 4)
		public void testDeleteuserbyName() {
			Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
			response.then().log().all();
			
			Assert.assertEquals(response.getStatusCode(),200);
			
		}

}
