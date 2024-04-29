package website.programming.androideatit.Database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import website.programming.androideatit.Model.Category;
import website.programming.androideatit.Model.User;

/**
 * Created by cokel on 3/25/2018.
 */

public class ParseJsonCategoryData {

    String parse_json;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<Category> CategoryArrayLists = new ArrayList<Category>();
    String Categoryid, name, image;
    int count=0;
    String jsondata;

    public ParseJsonCategoryData(String jsondata) {
        this.jsondata = jsondata;
    }

    public ArrayList<Category> getCategoryData() {
        try {
            jsonObject = new JSONObject(jsondata);
            count=0;
            jsonArray=jsonObject.getJSONArray("server_response");
          //  Log.i("jsonarray:",String.valueOf(jsonArray.length()));
            while(count<jsonArray.length()){

                JSONObject jo = jsonArray.getJSONObject(count);

                Categoryid = jo.getString("id");
                name = jo.getString("name");
                image = jo.getString("image");

                Category category = new Category(Categoryid, name, image);
                CategoryArrayLists.add(category);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return CategoryArrayLists;
    }

    public int getCategoryCount(){
        return count;
    }
}
