package website.programming.androideatit;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Model.Order;
import website.programming.androideatit.Model.Request;

/**
 * Created by cokel on 3/28/2018.
 */

public class ConvertToJson {

    Text json_data;
    List<Order> cart = new ArrayList<>();

    public ConvertToJson(List<Order> cart) {
        this.cart = cart;
    }


    public String Convert() {

        JSONObject firstrequestobj = new JSONObject();
        JSONObject requestobj = new JSONObject();
        JSONArray jsonOrderArray = new JSONArray();
        JSONArray jsonOrderLastArray = new JSONArray();



        for (Order order : cart) {
            try {
                JSONObject objectOrder = new JSONObject();
                objectOrder.put("productId", order.getProductId());
                objectOrder.put("productName", order.getProductName());
                objectOrder.put("quantity", order.getQuantity());
                objectOrder.put("price", order.getPrice());
                objectOrder.put("discount", order.getDiscount());
                jsonOrderArray.put(objectOrder);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            if(cart.size()>0) {
                requestobj.put("foods", jsonOrderArray);
                jsonOrderLastArray.put(requestobj);
                firstrequestobj.put("server_response", jsonOrderLastArray);
                Log.i("Json Order Data",firstrequestobj.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return firstrequestobj.toString();
    }

}
