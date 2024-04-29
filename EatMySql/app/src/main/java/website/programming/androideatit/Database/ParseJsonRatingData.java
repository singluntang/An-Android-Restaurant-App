package website.programming.androideatit.Database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import website.programming.androideatit.Model.Category;
import website.programming.androideatit.Model.Food;
import website.programming.androideatit.Model.Rating;
import website.programming.androideatit.Model.User;

/**
 * Created by cokel on 3/25/2018.
 */

public class ParseJsonRatingData {

    String parse_json;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<Rating> RagingArrayLists = new ArrayList<Rating>();
    String  phone, ratevalue, foodid, comment;
    int count=0;
    String jsondata="";

    public ParseJsonRatingData(String jsondata) {
        this.jsondata = jsondata;
    }



    public ArrayList<Rating> getRatingData() {
        try {
            jsonObject = new JSONObject(jsondata);
            count=0;
            jsonArray=jsonObject.getJSONArray("server_response");
      //      Log.i("jsonarray:",String.valueOf(jsonArray.length()));
            while(count<jsonArray.length()){

                JSONObject jo = jsonArray.getJSONObject(count);

                phone = jo.getString("phone");
                ratevalue = jo.getString("ratevalue");
                foodid = jo.getString("foodid");
                comment = jo.getString("comment");
                Rating rating = new Rating(phone, foodid, ratevalue, comment);
                RagingArrayLists.add(rating);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return RagingArrayLists;
    }

    public int getFoodCount(){
        return count;
    }
}
