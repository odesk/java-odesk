package com.oDesk.api.Routers.Organization;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.oDesk.api.Routers.Helper;
import com.oDesk.api.Routers.Organization.Users;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
	Users.class
})
public class UsersTest extends Helper {
	@Test public void getMyInfo() throws Exception {
		Users users = new Users(client);
    	JSONObject json = users.getMyInfo();
        
        assertTrue(json instanceof JSONObject);
	}
}