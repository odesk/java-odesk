/**
 * Copyright 2014 oDesk
 *
 * Licensed under the oDesk's API Terms of Use;
 * you may not use this file except in compliance with the Terms.
 * You may obtain a copy of the Terms at
 * 
 *    http://developers.odesk.com/API-Terms-of-Use
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oDesk.api.Routers.Activities;

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
public final class Team {
	
	final static String ENTRY_POINT = "api";
	
	private OAuthClient oClient = null;

	public Team(OAuthClient client) {
		oClient = client;
		oClient.setEntryPoint(ENTRY_POINT);
	}
	
	/**
     * Get by type
     *
     * @param   company Company ID
     * @param	team Team ID
     * @param   code (Optional) Code(s)
     * @param   isFull (Optional) Full list option
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    private JSONObject _getByType(String company, String team, String code, Boolean isFull) throws JSONException {
        String url = "";
        if (isFull) {
            url = "/full_list";
        } else if (code != null) {
            url = "/" + code;
        }

        return oClient.get("/otask/v1/tasks/companies/" + company + "/teams/" + team + "/tasks" + url);
    }

    /**
     * List all oTask/Activity records within a Company
     *
     * @param   company Company ID
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject getList(String company, String team) throws JSONException {
        return _getByType(company, team, null, false);
    }
    
    /**
     * List all oTask/Activity records within a Company with additional info
     *
     * @param   company Company ID
     * @param	team Team ID
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject getFullList(String company, String team) throws JSONException {
        return _getByType(company, team, null, true);
    }

    /**
     * List all oTask/Activity records within a Company by specified code(s)
     *
     * @param   company Company ID
     * @param	team Team ID
     * @param   code Specific code(s)
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject getSpecificList(String company, String team, String code) throws JSONException {
        return _getByType(company, team, code, false);
    }
    
    /**
     * Create an oTask/Activity record within a company
     *
     * @param   company Company ID
     * @param	team Team ID
     * @param   params Parameters
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject addActivity(String company, String team, HashMap<String, String> params) throws JSONException {
        return oClient.post("/otask/v1/tasks/companies/" + company + "/teams/" + team + "/tasks", params);
    }

    /**
     * Update specific oTask/Activity record within a company
     *
     * @param   company Company ID
     * @param	team Team ID
     * @param   code Specific code
     * @param   params Parameters
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject updateActivity(String company, String team, String code, HashMap<String, String> params) throws JSONException {
        return oClient.put("/otask/v1/tasks/companies/" + company + "/teams/" + team + "/tasks/" + code, params);
    }
    
    /**
     * Delete specific oTask/Activity record within a company
     *
     * @param   company Company ID
     * @param	team Team ID
     * @param   code Specific code(s)
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject deleteActivities(String company, String team, String code) throws JSONException {
        return oClient.delete("/otask/v1/tasks/companies/" + company + "/teams/" + team + "/tasks/" + code);
    }
    
    /**
     * Delete all oTask/Activity records within a company
     *
     * @param   company Company ID
     * @param	team Team ID
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject deleteAllActivities(String company, String team) throws JSONException {
        return oClient.delete("/otask/v1/tasks/companies/" + company + "/teams/" + team + "/tasks/all_tasks");
    }

}
