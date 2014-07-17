package com.oDesk.api.Routers.Hr;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.oDesk.api.Routers.Helper;
import com.oDesk.api.Routers.Hr.Contracts;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    Contracts.class
})
public class ContractsTest extends Helper {
	@Test public void endContract() throws Exception {
		Contracts contracts = new Contracts(client);
    	JSONObject json = contracts.endContract("1234", new HashMap<String, String>());
        
        assertTrue(json instanceof JSONObject);
	}
}