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

package com.oDesk.api.Routers.Freelancers;

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
public final class Profile {

	final static String ENTRY_POINT = "api";

	private OAuthClient oClient = null;

	public Profile(OAuthClient client) {
		oClient = client;
		oClient.setEntryPoint(ENTRY_POINT);
	}

	/**
     * Get specific Freelancer's Profile
     *
     * @param   key Profile key
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject getSpecific(String key) throws JSONException {
        return oClient.get("/profiles/v1/providers/" + key);
    }

	/**
     * Get brief info for the specific Freelancer's Profile
     *
     * @param   key Profile key
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject getSpecificBrief(String key) throws JSONException {
        return oClient.get("/profiles/v1/providers/" + key + "/brief");
    }
}
