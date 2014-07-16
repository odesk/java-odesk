package com.oDesk.api.Routers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.oDesk.api.Routers.Helper;
import com.oDesk.api.Routers.Mc;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    Mc.class
})
public class McTest extends Helper {
	@Test public void getTrays() throws Exception {
		Mc mc = new Mc(client);
    	JSONObject json = mc.getTrays();
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void getTrayByType() throws Exception {
		Mc mc = new Mc(client);
    	JSONObject json = mc.getTrayByType("username", "inbox");
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void getThreadDetails() throws Exception {
		Mc mc = new Mc(client);
    	JSONObject json = mc.getThreadDetails("username", "1234");
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void startNewThread() throws Exception {
		Mc mc = new Mc(client);
    	JSONObject json = mc.startNewThread("username", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void replyToThread() throws Exception {
		Mc mc = new Mc(client);
    	JSONObject json = mc.replyToThread("username", "1234", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
	
	@Test public void markThread() throws Exception {
		Mc mc = new Mc(client);
    	JSONObject json = mc.markThread("username", "1234", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
}