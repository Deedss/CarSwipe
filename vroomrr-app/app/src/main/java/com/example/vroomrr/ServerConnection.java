package com.example.vroomrr;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

final public class ServerConnection {
    // URL information for Server Connections
    private final String user_agent = "";
    //todo Add official master_server
//    private final String master_server = "grolink.nl/Vroomrr/";
    private final String master_server = "localhost:5000";

    // Cookies handler
    private final CookieManager cookieManager;

    /**
     * Constructor
     */
    public ServerConnection() {
        cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

//    public String httpGET(String file, Context context){
//
//        StringBuffer data = new StringBuffer("");
//
//        try{
//            SharedPreferences SP = context.getSharedPreferences("Cookies", MODE_PRIVATE);
//            String cookie = SP.getString("Cookie","");
//
//            URL url = new URL(master_server + file);
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            connection.setRequestProperty("User-Agent", user_agent);
//            connection.setRequestProperty("Cookie", cookie);
//            connection.setRequestMethod("GET");
//            connection.connect();
//
//            InputStream inputStream = connection.getInputStream();
//
//            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                data.append(line);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try{
//            final SharedPreferences.Editor SP = context.getSharedPreferences("WebCache", MODE_PRIVATE).edit();
//            SP.putString(file, data.toString());
//            SP.apply();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return data.toString();
//    }

    private String httpPOST(String urlIn, String urlParameters, Context context){

        StringBuffer data = new StringBuffer("");

        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        try{

            URL url = new URL(urlIn);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", user_agent);
            connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            connection.setRequestProperty( "Accept", "*/*");
            try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                wr.write( postData );
            }

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                data.append(line);
            }

        } catch (IOException e) {
            // writing exception to log
            Log.d("Error",e.toString());
        }

        return data.toString();
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
    public void register(User user){

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
}
