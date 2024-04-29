package website.programming.androideatit.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

import retrofit2.Retrofit;
import website.programming.androideatit.Model.User;
import website.programming.androideatit.Remote.APIService;
import website.programming.androideatit.Remote.RetrofitClient;

/**
 * Created by cokel on 2/27/2018.
 */

public class Common {
    public static User currentUser;

    private static final String BASE_URL = "https://fcm.googleapis.com/";
    public static final String SERVER_URL = "http://192.168.86.26/EatIt/";
    public static final String AWS_SERVER_API = "https://d8vea6sltb.execute-api.us-east-2.amazonaws.com/dev";
   // public static final String SERVER_URL = "http://programming.website/EatIt/";

    public static APIService getFCMService()
    {
        //return RetrofitClient.getClient(BASE_URL).create(APIService.class);
        return RetrofitClient.getClient(AWS_SERVER_API).create(APIService.class);
    }

    public static final String DELETE = "Delete";
    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Password";


    public static String convertCodeToStatus(String status) {

        if(status.equals("0"))
            return "Placed";
        else if(status.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null)
        {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if(info != null) {
                for (int i = 0; i < info.length; i++)
                {
                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }

        }
        return false;
    }
}
