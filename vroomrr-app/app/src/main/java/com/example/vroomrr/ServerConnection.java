package com.example.vroomrr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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
     *
     */
    public static void getCandidates(ServerCallback callback, Activity activity) {
        GetAsync task = new GetAsync(callback, activity);
        task.execute("candidates");
    }

    public static void rateCandidate(Opinion opinion, ServerCallback callback, Activity activity) {
        PostAsync task = new PostAsync(new Gson().toJson(opinion), callback, activity);
        task.execute("candidates/opinion");
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
     * @param image64 The base64 model for the image to add.
     * @param callback The callback to return results to
     * @param activity The activity to return results to
     */
    public static void addCarImage(Image64 image64, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(image64), callback, activity);
        task.execute("cars/image/add");
    }

    /**
     * Delete an image for a car.
     * @param carImage The image to delete for a car.
     * @param callback The callback to return results to
     * @param activity The activity to return results to
     */
    public static void deleteCarImage(CarImage carImage, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(carImage), callback, activity);
        task.execute("cars/image/delete");
    }

    /**
     * Get current filter of the User by Session ID
     * @param callback callback
     * @param activity activity
     */
    public static void getFilter(ServerCallback callback, Activity activity){
        GetAsync task = new GetAsync(callback, activity);
        task.execute("candidates/filter");
    }

    /**
     * Send a filter to the server.
     * @param filter filter to send
     * @param callback callback
     * @param activity activity
     */
    public static void updateFilter(SearchFilter filter, ServerCallback callback, Activity activity){
        PostAsync task = new PostAsync(new Gson().toJson(filter), callback, activity);
        task.execute("candidates/filter/update");
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
     * @param user User to get public key from
     * @param callback ServerCallback
     * @param activity Activity
     */
    public static void getPublicKey(User user, ServerCallback callback, Activity activity) {
        PostAsync task = new PostAsync(new Gson().toJson(user), callback, activity);
        task.execute("user");
    }

    /**
     * Encode an image to a Base64 to send it to the server.
     * @param bitmap , bitmap image to send
     * @param context , Context of where to get the resources.
     * @return , return a Base64 string.
     */
    public static String encodeToBase64(Bitmap bitmap, Context context){
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    /**
     * Return a bitmap of the base64 encoded image
     * @param string , Base64 encoded string
     * @param context , Context of the image.
     * @return , return Bitmap value.
     */
    public static Bitmap decodeToBitmap(String string, Context context){
        //decode base64 string to image
        byte[] imageBytes = Base64.decode(string, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public static class GetImageFromUrl extends AsyncTask<String, Void, Bitmap>{
        public GetImageFromUrl(){
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = "https://grolink.nl/cars/image/" + url[0];
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
                SharedPreferences SP = Cryptography.getEncryptedSharedPreferences(activity);

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
                        if(returnData.startsWith("{\"error\":")){
                            Toast.makeText(activity, returnData, Toast.LENGTH_LONG).show();
                        } else {
                            callback.completionHandler(returnData, strings[0]);
                        }
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
                        if(returnData.startsWith("{\"error\":")){
                            Toast.makeText(activity, returnData, Toast.LENGTH_LONG).show();
                        } else {
                            callback.completionHandler(returnData, strings[0]);
                        }
                    }
                });

            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}