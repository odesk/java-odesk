package com.oDesk.api.Routers.Activities;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.oDesk.api.Routers.Helper;
import com.oDesk.api.Routers.Activities.Team;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    Team.class
})
public class TeamTest extends Helper {
	@Test public void getList() throws Exception {
		Team activities = new Team(client);
    	JSONObject json = activities.getList("company", "team");
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void getFullList() throws Exception {
		Team activities = new Team(client);
    	JSONObject json = activities.getFullList("company", "team");
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void getSpecificList() throws Exception {
		Team activities = new Team(client);
    	JSONObject json = activities.getSpecificList("company", "team", "code");
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void addActivity() throws Exception {
		Team activities = new Team(client);
    	JSONObject json = activities.addActivity("company", "team", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void updateActivity() throws Exception {
		Team activities = new Team(client);
    	JSONObject json = activities.updateActivity("company", "team", "code", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void deleteActivities() throws Exception {
		Team activities = new Team(client);
    	JSONObject json = activities.deleteActivities("company", "team", "code");
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void deleteAllActivities() throws Exception {
		Team activities = new Team(client);
    	JSONObject json = activities.deleteAllActivities("company", "team");
        
        assertTrue(json instanceof JSONObject);
	}
}