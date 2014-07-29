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
 *
 * @author: Maksym Novozhylov <mnovozhilov@odesk.com>
 */
package com.odesk.example_odeskapi;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Properties;

import com.oDesk.api.*;
import com.oDesk.api.Routers.Organization.Users;

import org.json.JSONException;
import org.json.JSONObject;

import oauth.signpost.OAuth;

public class MyActivity extends Activity {

    private final static String ODESK_CONSUMER_KEY = "REPLACE-WITH-YOUR-CONSUMER-KEY-FROM-ODESK";
    private final static String ODESK_CONSUMER_SECRET = "REPLACE-WITH-SECRET-FOR-THE-CONSUMER-KEY";

    private final static String OAUTH_CALLBACK_SCHEME = "x-oauthflow";
    private OAuthClient client;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        // Read the prefs to see if we have token
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String token = prefs.getString("token", null);
        String tokenSecret = prefs.getString("secret", null);

        if (token != null && tokenSecret != null && client != null) {
            client.setTokenWithSecret(token, tokenSecret);
        } else {
            if (client == null) {
                //NOTE: KEY/SECRET PAIR MUST BE STORED IN A SAFE PLACE
                //THIS PART OF ASSIGNING OF CONSUMER KEY IS AN EXAMPLE
                Properties props = new Properties();
                props.setProperty("consumerKey", ODESK_CONSUMER_KEY);
                props.setProperty("consumerSecret", ODESK_CONSUMER_SECRET);

                Config config = new Config(props);

                client = new OAuthClient(config);
            }
            // authorize
            new ODeskAuthorizeTask().execute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback once we are done with the authorization of this app
     * @param intent
     */
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Verify OAuth callback
        Uri uri = intent.getData();
        if (uri != null && uri.getScheme().equals(OAUTH_CALLBACK_SCHEME)) {
            String verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);

            new ODeskRetrieveAccessTokenTask().execute(verifier);
        }
    }

    public void onClickGetMyUID(View view) {
        String token = prefs.getString("token", null);
        if (client == null || token == null) {
            Toast.makeText(this, "Authenticating at first, wait", Toast.LENGTH_LONG).show();
            return;
        }

        new GetMyUIdTask().execute();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            return rootView;
        }
    }

    class ODeskAuthorizeTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(Void... params) {
            String authzUrl = client.getAuthorizationUrl();

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authzUrl)));

            return authzUrl;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                Toast.makeText(MyActivity.this, result, Toast.LENGTH_LONG).show();
            }
        }
    }

    class ODeskRetrieveAccessTokenTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String verifier = params[0];

            HashMap<String, String> token = client.getAccessTokenSet(verifier);
            client.setTokenWithSecret(token.get("token"), token.get("secret"));

            // Save token/secret in preferences
            prefs.edit().putString("token", token.get("token"))
                        .putString("secret", token.get("secret"))
                        .commit();

            return "ok - token is: " + prefs.getString("token", null);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                Toast.makeText(MyActivity.this, result, Toast.LENGTH_LONG).show();
            }
        }
    }

    class GetMyUIdTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            JSONObject json = null;
            String token = prefs.getString("token", null);
            Users users = new Users(client);

            String myId = "";
            try {
                json = users.getMyInfo();
                JSONObject user = json.getJSONObject("user");
                myId = user.getString("id");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            return "Your UID is " + myId;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                Toast.makeText(MyActivity.this, result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
