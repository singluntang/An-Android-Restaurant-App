package website.programming.androideatit.Database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import website.programming.androideatit.Model.Category;
import website.programming.androideatit.Model.Food;
import website.programming.androideatit.Model.Order;
import website.programming.androideatit.Model.Request;
import website.programming.androideatit.Model.User;

/**
 * Created by cokel on 3/25/2018.
 */

public class ParseJsonOrderData {

    String parse_json;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<Request> OrderArrayLists = new ArrayList<Request>();
    String id, phone, name,address,total,status,Comment, paymentState, foods;
    int count=0;
    String jsondata="";

    public ParseJsonOrderData(String jsondata) {
        this.jsondata = jsondata;
    }

    public ArrayList<Request> getOrderData() {
        try {
            jsonObject = new JSONObject(jsondata);
            count=0;
            jsonArray=jsonObject.getJSONArray("server_response");
           // Log.i("jsonarray:",String.valueOf(jsonArray.length()));
            while(count<jsonArray.length()){

                JSONObject jo = jsonArray.getJSONObject(count);

                id = jo.getString("order_id");
                phone = jo.getString("phone");
                name = jo.getString("name");
                address = jo.getString("address");
                total = jo.getString("total");
                status = jo.getString("status");
                Comment = jo.getString("Comment");
                paymentState = jo.getString("paymentState");
                //foods = jo.getString("foods");

                Request order = new Request(id, phone, name, address, total,status,Comment,paymentState,foods );
                OrderArrayLists.add(order);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return OrderArrayLists;
    }

    public int getOrderCount(){
        return count;
    }
}
