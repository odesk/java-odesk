package com.oDesk.api.Routers;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.oDesk.api.Routers.Helper;
import com.oDesk.api.Routers.Workdiary;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
	Workdiary.class
})
public class WorkdiaryTest extends Helper {
	@Test public void get() throws Exception {
		Workdiary workdiary = new Workdiary(client);
    	JSONObject json = workdiary.get("company", "username", "date", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
}