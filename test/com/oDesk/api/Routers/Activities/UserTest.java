package com.oDesk.api.Routers.Activities;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.oDesk.api.Routers.Helper;
import com.oDesk.api.Routers.Activities.User;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    User.class
})
public class UserTest extends Helper {
	@Test public void getList() throws Exception {
		User activities = new User(client);
    	JSONObject json = activities.getList("company", "team", "activitiesname");
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void getFullList() throws Exception {
		User activities = new User(client);
    	JSONObject json = activities.getFullList("company", "team", "activitiesname");
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void getSpecificList() throws Exception {
		User activities = new User(client);
    	JSONObject json = activities.getSpecificList("company", "team", "activitiesname", "code");
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void addActivity() throws Exception {
		User activities = new User(client);
    	JSONObject json = activities.addActivity("company", "team", "activitiesname", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void updateActivity() throws Exception {
		User activities = new User(client);
    	JSONObject json = activities.updateActivity("company", "team", "activitiesname", "code", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void deleteActivities() throws Exception {
		User activities = new User(client);
    	JSONObject json = activities.deleteActivities("company", "team", "activitiesname", "code");
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void deleteAllActivities() throws Exception {
		User activities = new User(client);
    	JSONObject json = activities.deleteAllActivities("company", "team", "activitiesname");
        
        assertTrue(json instanceof JSONObject);
	}
}