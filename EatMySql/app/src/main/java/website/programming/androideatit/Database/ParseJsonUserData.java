package website.programming.androideatit.Database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import website.programming.androideatit.Model.User;

/**
 * Created by cokel on 3/25/2018.
 */

public class ParseJsonUserData {

    String parse_json;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<User> UserArrayLists = new ArrayList<User>();
    String name, phone,  securecode, password;
    int count=0;
    String jsondata;

    public ParseJsonUserData(String jsondata) {
        this.jsondata = jsondata;
    }

    public ArrayList<User> getUserData() {
        try {
            jsonObject = new JSONObject(jsondata);
            count=0;
//          jsonArray=jsonObject.getJSONArray("server_response");
            phone = jsonObject.getString("phone");
            name = jsonObject.getString("name");
            password = jsonObject.getString("password_hash");
            securecode = jsonObject.getString("secureCode");

            User user = new User(name, password,securecode);
            user.setPhone(phone);
            UserArrayLists.add(user);
            count++;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return UserArrayLists;
    }

    public int getUserCount(){
        return count;
    }
}
