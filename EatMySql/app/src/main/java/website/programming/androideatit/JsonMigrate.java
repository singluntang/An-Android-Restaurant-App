package website.programming.androideatit;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Database.DatabaseInsert;
import website.programming.androideatit.Database.DatabaseSelectServices;
import website.programming.androideatit.Database.ParseJsonCategoryData;
import website.programming.androideatit.Database.ParseJsonFoodData;
import website.programming.androideatit.Model.Category;
import website.programming.androideatit.Model.Food;
import website.programming.androideatit.Model.Rating;
import website.programming.androideatit.ViewHolder.MenuAdapter;

/**
 * Created by ashu on 16-Apr-16.
 */


public class JsonMigrate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_to_mysql);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Migrate");
        //setSupportActionBar(toolbar);
    }

    public void startMigrate(View view){
            String table = "Food";
            String BufferNoData = "Nodata";
            ArrayList<Food> jsondata;
            final ArrayList<Food> Foodlist = new ArrayList<Food>();
            DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
            String parse_json = "";
            try {
                parse_json = databaseSelectServices.execute(table).get();
                Log.i("parse_json_SELECT", parse_json);


                if(parse_json.matches(BufferNoData)){
                    Toast.makeText(JsonMigrate.this, "No Item!", Toast.LENGTH_SHORT).show();
                }
                else {
                    ParseJsonFoodData ParseJsonFoodData= new ParseJsonFoodData(parse_json);
                    jsondata = ParseJsonFoodData.getFoodData();

                    if (jsondata.size() > 0) {
                        website.programming.androideatit.JsonToSql DBinsert = new website.programming.androideatit.JsonToSql(jsondata, JsonMigrate.this);
                        String result = DBinsert.execute().get();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
    }

}