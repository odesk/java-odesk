/**
 * Copyright 2014 oDesk
 *
 * Licensed under the oDesk's API Terms of Use;
 * you may not use this file except in compliance with the Terms.
 * You may obtain a copy of the Terms at
 * 
 *    https://developers.odesk.com/api-tos.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oDesk.api.Routers;

import java.util.HashMap;

import com.oDesk.ClassPreamble;
import com.oDesk.api.OAuthClient;

import org.json.JSONException;
import org.json.JSONObject;

@ClassPreamble (
	author = "Maksym Novozhylov <mnovozhilov@odesk.com>",
	date = "6/4/2014",
	currentRevision = 1,
	lastModified = "6/4/2014",
	lastModifiedBy = "Maksym Novozhylov",
	reviewers = {"Yiota Tsakiri"}
)
public final class Snapshot {
	
	final static String ENTRY_POINT = "api";
	
	private OAuthClient oClient = null;

	public Snapshot(OAuthClient client) {
		oClient = client;
		oClient.setEntryPoint(ENTRY_POINT);
	}
	
	/**
     * Get snapshot info
     *
     * @param   company Company
     * @param   username Username
     * @param   ts Timestamp
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject get(String company, String username, String ts) throws JSONException {   
        return oClient.get("/team/v1/snapshots/" + company + "/" + username + "/" + ts);
    }   

    /** 
     * Update snapshot
     *
     * @param   company Company
     * @param   username Username
     * @param   ts Timestamp
     * @param   params Parameters
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject update(String company, String username, String ts, HashMap<String, String> params) throws JSONException {   
        return oClient.put("/team/v1/snapshots/" + company + "/" + username + "/" + ts, params);
    }
    
    /**
     * Delete snapshot
     *
     * @param   company Company
     * @param   username Username
     * @param   ts Timestamp
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject delete(String company, String username, String ts) throws JSONException {
        return oClient.delete("/team/v1/snapshots/" + company + "/" + username + "/" + ts);
    }

}
