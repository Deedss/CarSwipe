package com.example.vroomrr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
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

    public static void login(User user, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(user), callback, activity);
        task.execute("login");
    }

    /**
     * returns an arraylist with all chats specific to the user.
     *
     * @return Returns an arraylist of Chats.
     */
    public static void getChats(ServerCallback callback, Activity activity) {
        GetAsync task = new GetAsync(callback, activity);
        task.execute("chats");
    }
    //TODO: Add encryption
    public static void sendMessageTMP(ServerCallback callback, Activity activity, ChatMessage msg) {
        PostAsync task = new PostAsync(new Gson().toJson(msg),callback, activity);
        task.execute("chat/send");
    }

    /**
     * Returns a list of all messages in a chat.
     *
     * @param chat the chat for which to get the messages
     * @return List<String>
     */
    public static void getChatMsgs(ServerCallback callback, Activity activity, Chat chat) {
        PostAsync task = new PostAsync(new Gson().toJson(chat),callback, activity);
        task.execute("chat/messages");
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
     * Get all cars from a specific user
     * @param callback The callback to return results to
     * @param activity The activity to return results to
     */
    public static void getCars(User user, ServerCallback callback, Activity activity) {
        PostAsync task = new PostAsync(new Gson().toJson(user), callback, activity);
        task.execute("cars");
    }

    /**
     * Add a car for a specific user
     * @param licenseplate The licenseplate for which to add a car.
     * @param callback The callback to return results to
     * @param activity The activity to return results to
     */
    public static void addCar(String licenseplate, ServerCallback callback, Activity activity){
        GetAsync task = new GetAsync(callback, activity);
        task.execute("cars/add/" + licenseplate);
    }

    public static void deleteCar(Car car, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(car), callback, activity);
        task.execute("cars/delete");
    }

    /**
     * Update all information of a car object.
     * @param car  car to update info on
     * @param callback Callback implementation
     * @param activity the activity from where function is called.
     */
    public static void updateCar(Car car, ServerCallback callback, Activity activity) {
        PostAsync task = new PostAsync(new Gson().toJson(car), callback, activity);
        task.execute("cars/update");
    }

    /**
     * Get a specific car Image for a car.
     * @param car The car to get an image for.
     * @param callback The callback to return results to
     * @param activity The activity to return results to
     */
    public static void getCarImage(Car car, ServerCallback callback, Activity activity){
        GetAsync task = new GetAsync(callback, activity);
        task.execute("cars/image/" + new Gson().toJson(car));
    }

    /**
     * Get all images for a car
     * @param car The car to get all images for
     * @param callback The callback to return results to
     * @param activity The activity to return results to
     */
    public static void getCarImages(Car car, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(car) ,callback, activity);
        task.execute("cars/images");
    }

    /**
     * Add a new image to a specific car.
     * @param car The car to add new image for
     * @param callback The callback to return results to
     * @param activity The activity to return results to
     */
    public static void addCarImage(Car car, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(car), callback, activity);
        task.execute("cars/image/add");
    }

    /**
     * Delete an image for a car.
     * @param car The car to delete image for
     * @param callback The callback to return results to
     * @param activity The activity to return results to
     */
    public static void deleteCarImage(Car car, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(car), callback, activity);
        task.execute("cars/image/delete");
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

    public static class GetImageFromUrl extends AsyncTask<String, Void, Bitmap>{
        public GetImageFromUrl(){
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = url[0];
            Bitmap bitmap = null;
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    public static class GetAsync extends AsyncTask<String, Void, Void> {
        ServerCallback callback;
        @SuppressLint("StaticFieldLeak")
        Activity activity;

        // URL information for Server Connections
        private final String master_server = "https://grolink.nl/";
//        private final String master_server = "http://10.0.2.2:5000/";

        // This is a constructor that allows you to pass in the JSON body
        public GetAsync(ServerCallback callback, Activity activity) {
            this.callback = callback;
            this.activity = activity;
        }

        @Override
        protected Void doInBackground(final String... strings) {
            StringBuffer data = new StringBuffer("");

            try {
                //Cryptography.addToSharedPreferences(activity, String.valueOf(R.string.SessionId), "05480a28-74ae-40e5-a912-731564c232bf");
                //Cryptography.updateSharedPreferences(activity, String.valueOf(R.string.SessionId), "df6ad070-3529-4567-a460-c234e0ead214");
                SharedPreferences SP = Cryptography.getEncryptedSharedPreferences(activity);

                //System.out.println(Cryptography.getFromSharedPreferences(activity, String.valueOf(R.string.SessionId)));
                // Setup URL connection.
                String newUrl = master_server + strings[0];
                URL url = new URL(newUrl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Session-Id", SP.getString(String.valueOf(R.string.SessionId),""));
                connection.connect();

                InputStream inputStream = connection.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    data.append(line);
                }

                final String returnData = data.toString();

                // Do the callback
                activity.runOnUiThread(new Runnable() {
                    public void run(){
                        callback.completionHandler(returnData, strings[0]);
                    }
                });

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
        private final String master_server = "https://grolink.nl/";
//        private final String master_server = "http://10.0.2.2:5000/";

        // This is a constructor that allows you to pass in the JSON body
        public PostAsync(String postData, ServerCallback callback, Activity activity) {
            if (postData != null) {
                this.postData = postData;
                this.callback = callback;
                this.activity = activity;
            }
        }

        @Override
        protected Void doInBackground(final String... strings) {
            StringBuffer data = new StringBuffer("");

            try {
                //Cryptography.addToSharedPreferences(activity, String.valueOf(R.string.SessionId), "05480a28-74ae-40e5-a912-731564c232bf");
                //Cryptography.updateSharedPreferences(activity, String.valueOf(R.string.SessionId), "df6ad070-3529-4567-a460-c234e0ead214");
                SharedPreferences SP = Cryptography.getEncryptedSharedPreferences(activity);

                // Setup URL connection.
                String newUrl = master_server + strings[0];
                URL url = new URL(newUrl);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                if(SP.contains(String.valueOf(R.string.SessionId))){
                    connection.setRequestProperty("Session-Id", SP.getString(String.valueOf(R.string.SessionId), ""));
                }
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

                final String returnData = data.toString();

                // Do the callback
                activity.runOnUiThread(new Runnable() {
                    public void run(){
                        callback.completionHandler(returnData, strings[0]);
                    }
                });

            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}