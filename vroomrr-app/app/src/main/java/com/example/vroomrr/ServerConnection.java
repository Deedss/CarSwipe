package com.example.vroomrr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

final public class ServerConnection {
    // Cookies handler
    private final CookieManager cookieManager;

    /**
     * Constructor
     */
    public ServerConnection() {
        cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

    /**
     * Returns user information from the server.
     *
     * @param userId, the userId/username
     * @return User object
     */
    public User getUser(String userId) {
        User user = null;

        return user;
    }

    /**
     * Register a new User
     * @param user User to add
     */
    public static void register(User user, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(user), callback, activity);
        task.execute("register");
    }

    public static void login(User user, ServerCallback callback, Activity activity) {
        PostAsync task = new PostAsync(new Gson().toJson(user), callback, activity);
        task.execute("login");
    }

    /**
     * returns an arraylist with all chats specific to the user.
     *
     * @param user The User for which to import all chats.
     * @return Returns an arraylist of Chats.
     */
    public ArrayList<Chat> getChats(User user) {
        ArrayList<Chat> chats = null;

        return chats;
    }

    /**
     * Returns a list of all messages in a chat.
     *
     * @param chat the chat for which to get the messages
     * @return List<String>
     */
    public List<String> getChatMsgs(Chat chat) {
        List<String> list = null;

        return list;
    }

    /**
     * Returns all possible candidates depending on the user search filter.
     *
     * @param filter The filter to define the results by.
     * @return ArrayList<User>
     */
    public ArrayList<User> getCandidates(SearchFilter filter) {
        ArrayList<User> users = null;

        return users;
    }

    /**
     * Get the information from your car via the RDW api
     *
     * @param licenseplate the licensplate of the
     * @return returns the
     */
    public Car getCar(String licenseplate) {
        Car car = null;

        return car;
    }

    /**
     * Update info on specific car
     *
     * @param car  car to update info on
     * @param info info to add in database
     */
    public void updateInfo(Car car, String info) {

    }

    /**
     * Returns a list of possible searchfilters.
     *
     * @return ArrayList<SearchFilter>
     */
    public ArrayList<SearchFilter> getFilterOptions() {
        ArrayList<SearchFilter> filters = null;

        return filters;
    }

    /**
     * Send a message to a specific User
     *
     * @param user    User to send message to
     * @param message message to send
     */
    public void sendMessage(User user, String message) {
        /// TODO: 11-12-20 Encrypt message with RSAPublicKey
    }

    /**
     * Get the public key from the database for specific user.
     *
     * @param user User to get public key from
     * @return publickey
     */
    public static PublicKey getPublicKey(User user) {
        PublicKey publicKey = null;

        return publicKey;
    }

    public static class GetAsync extends AsyncTask<String, Void, Void> {
        JSONObject postData;
        ServerCallback callback;
        Activity activity;

        // URL information for Server Connections
        //todo Add official master_server
//    private final String master_server = "grolink.nl/Vroomrr/";
        private final String master_server = "http://10.0.2.2:5000/";

        // This is a constructor that allows you to pass in the JSON body
        public GetAsync(JSONObject postData, ServerCallback callback, Activity activity) {
            if (postData != null) {
                this.postData = postData;
                this.callback = callback;
                this.activity = activity;
            }
        }

        @Override
        protected Void doInBackground(String... strings) {
            StringBuffer data = new StringBuffer("");

            try {
                // Setup URL connection.
                String newUrl = master_server + strings[0];
                URL url = new URL(newUrl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //todo Check if SessionId in SharedPreferences
                connection.setRequestProperty("session_id", "");

                // Try to write to server.
                try( OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream())) {
                    wr.write(postData.toString());
                    wr.flush();
                }

                // Check on successful response
                if(connection.getResponseCode() == 200) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        data.append(line);
                    }

                    final StringBuffer returnData = data;

                    // Do the callback
                    activity.runOnUiThread(new Runnable() {
                        public void run(){
                            callback.completionHandler(true, returnData);
                        }
                    });
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class PostAsync extends AsyncTask<String, Void, Void> {
        String postData;
        ServerCallback callback;
        @SuppressLint("StaticFieldLeak")
        Activity activity;

        // URL information for Server Connections
        //todo Add official master_server
        private final String master_server = "https://grolink.nl/";

        // This is a constructor that allows you to pass in the JSON body
        public PostAsync(String postData, ServerCallback callback, Activity activity) {
            if (postData != null) {
                this.postData = postData;
                this.callback = callback;
                this.activity = activity;
            }
        }

        @Override
        protected Void doInBackground(String... strings) {
            StringBuffer data = new StringBuffer("");

            try {
                // Setup URL connection.
                String newUrl = master_server + strings[0];
                URL url = new URL(newUrl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //todo Check if SessionId in SharedPreferences
                connection.setRequestProperty("session_id", "");

                // Try to write to server.
                try( OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream())) {
                    wr.write(postData);
                    wr.flush();
                }

                // Check on successful response
                InputStream inputStream = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    data.append(line);
                }

                final StringBuffer returnData = data;

                System.out.println(returnData);

                // Do the callback
                activity.runOnUiThread(new Runnable() {
                    public void run(){
                        callback.completionHandler(true, returnData);
                    }
                });
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}