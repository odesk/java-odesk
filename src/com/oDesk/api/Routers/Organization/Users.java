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

package com.oDesk.api.Routers.Organization;

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
public final class Users {
	
	final static String ENTRY_POINT = "api";
	
	private OAuthClient oClient = null;

	public Users(OAuthClient client) {
		oClient = client;
		oClient.setEntryPoint(ENTRY_POINT);
	}
	
	/** 
     * Get Auth User Info
     *
     * @throws	JSONException If error occurred
	 * @return	{@link JSONObject}
     */
    public JSONObject getMyInfo() throws JSONException {   
        return oClient.get("/hr/v2/users/me");
    }

}
