package website.programming.androideatit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Database.DatabaseSelectServices;
import website.programming.androideatit.Database.ParseJsonOrderData;
import website.programming.androideatit.Model.Request;
import website.programming.androideatit.ViewHolder.OrderAdapter;

public class OrderStatus extends AppCompatActivity {

    RecyclerView recylerview;
    RecyclerView.LayoutManager layoutManager;

    String CatergoryId="";
    OrderAdapter adapter;


    String parse_json = "",method="";
    ArrayList<Request> jsondata;
    String BufferNoData = "Nodata";
    DatabaseSelectServices databaseSelectServices = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Arkhip_font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_order_status);


        //Load Menu
        recylerview = (RecyclerView)findViewById(R.id.listOrders);
        recylerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recylerview.setLayoutManager(layoutManager);

        String strPhone = getIntent().getStringExtra("userPhone");

        if(strPhone==null)
            loadOrders(Common.currentUser.getPhone());
        else
            loadOrders(getIntent().getStringExtra("userPhone"));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void loadOrders(String phone) {

        if (databaseSelectServices == null) {
            try {
                method = "OrderStatus";
                DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
                parse_json = databaseSelectServices.execute(method,phone).get();
                //parse_json = databaseSelectServices.parse_json;
                Log.i("parse_json", parse_json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(parse_json.matches(BufferNoData)){
                Toast.makeText(OrderStatus.this, "No Order Information!", Toast.LENGTH_SHORT).show();
            }
            else {
                ParseJsonOrderData parseJsonOrderData= new ParseJsonOrderData(parse_json);
                jsondata = parseJsonOrderData.getOrderData();

                if (jsondata.size() > 0) {
                    adapter = new OrderAdapter(jsondata,this);
                } else {
                    Toast.makeText(OrderStatus.this, "Load Order Status Failure!", Toast.LENGTH_SHORT).show();
                }

            }
        }

        adapter.notifyDataSetChanged();
        recylerview.setAdapter(adapter);
    }

}
