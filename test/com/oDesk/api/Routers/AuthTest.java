package com.oDesk.api.Routers;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.*;

import static org.mockito.Mockito.*;

import com.oDesk.api.OAuthClient;
import com.oDesk.api.Routers.Auth;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    Auth.class
})
public class AuthTest {
	@Mock OAuthClient client;
	
	@Test public void getUserInfo() throws Exception {
		MockitoAnnotations.initMocks(this);
        when(client.get(Matchers.anyString())).thenReturn(new JSONObject("{'key': 'value'}"));
		
        Auth auth = new Auth(client);
    	JSONObject json = auth.getUserInfo();
        
        assertTrue(json instanceof JSONObject);
	}
}