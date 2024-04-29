package website.programming.androideatit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Database.Database;
import website.programming.androideatit.Database.DatabaseSelectServices;
import website.programming.androideatit.Database.ParseJsonFoodData;
import website.programming.androideatit.Model.Food;
import website.programming.androideatit.ViewHolder.FoodAdapter;

public class FoodList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference foodList;

    RecyclerView recyler_food;
    RecyclerView.LayoutManager layoutManager;

    String CatergoryId="";
    List<String> suggestList = new ArrayList<>();
    MaterialSearchBar materialSearchBar;

    //Favourites
    Database localDB;

    //Facebook Share
    CallbackManager callbackManager;
    ShareDialog shareDialog;


    SwipeRefreshLayout swipeRefreshLayout;

    String parse_json = "",method="";
    ArrayList<Food> jsondata;
    String BufferNoData = "Nodata";
    DatabaseSelectServices databaseSelectServices = null;

    FoodAdapter adapter, searchdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Arkhip_font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_food_list);

        //Local DB
        //localDB = new Database(this);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark
        );


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(getIntent() != null) {
                    CatergoryId = getIntent().getStringExtra("CategoryId");
                }
                if(!CatergoryId.isEmpty() && CatergoryId != null)
                {

                    if (Common.isConnectedToInternet(FoodList.this)) {
                        loadListFood(CatergoryId);
                        suggestList.clear();
                        loadSuggest(CatergoryId);
                    }
                    else {
                        Toast.makeText(FoodList.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });


        //Default Run for the first time
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                if(getIntent() != null) {
                    CatergoryId = getIntent().getStringExtra("CategoryId");
                }
                if(!CatergoryId.isEmpty() && CatergoryId != null)
                {

                    if (Common.isConnectedToInternet(FoodList.this)) {
                        loadListFood(CatergoryId);
                        suggestList.clear();
                        loadSuggest(CatergoryId);
                    }
                    else {
                        Toast.makeText(FoodList.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

            //Load Menu
        recyler_food = (RecyclerView) findViewById(R.id.recycler_food);
        recyler_food.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyler_food.setLayoutManager(layoutManager);
        //loadMenu();


        //Search
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchBar);
        materialSearchBar.setHint("Enter your food");
        materialSearchBar.setLastSuggestions(suggestList);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<String>();
                for(String search:suggestList)
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recyler_food.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Log.i("Search Comfirmed", text.toString());
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                //Log.i("Search clicked", Integer.toString(buttonCode));
            }
        });
    }

    private void loadSuggest(String CatergoryId) {

        if (databaseSelectServices == null) {
            try {
                method = "Food";
                DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
                parse_json = databaseSelectServices.execute(method,CatergoryId).get();
                //parse_json = databaseSelectServices.parse_json;
                Log.i("parse_json", parse_json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(parse_json.matches(BufferNoData)){
                Toast.makeText(FoodList.this, "No Category!", Toast.LENGTH_SHORT).show();
            }
            else {
                ParseJsonFoodData parseJsonFoodData= new ParseJsonFoodData(parse_json);
                jsondata = parseJsonFoodData.getFoodData();

                if (jsondata.size() > 0) {

                    for(int i=0;i< jsondata.size();i++)
                    {
                        suggestList.add(jsondata.get(i).getName());
                    }


                } else {
                    Toast.makeText(FoodList.this, "Load Suggest List Failure!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void startSearch(CharSequence text) {
        if (databaseSelectServices == null) {
            try {
                method = "FoodItem";
                DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
                parse_json = databaseSelectServices.execute(method,text.toString()).get();
                //parse_json = databaseSelectServices.parse_json;
                Log.i("parse_json_food", parse_json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(parse_json.matches(BufferNoData)){
                Toast.makeText(FoodList.this, "No Food!", Toast.LENGTH_SHORT).show();
            }
            else {
                ParseJsonFoodData parseJsonFoodData= new ParseJsonFoodData(parse_json);
                jsondata = parseJsonFoodData.getFoodData();

                if (jsondata.size() > 0) {
                    searchdapter = new FoodAdapter(jsondata,this, this);
                } else {
                    Toast.makeText(FoodList.this, "Load FoodList Failure!", Toast.LENGTH_SHORT).show();
                }

            }
            searchdapter.notifyDataSetChanged();
            recyler_food.setAdapter(searchdapter);
        }

    }

    private void loadListFood(String CatergoryId) {


        if (databaseSelectServices == null) {
            try {
                method = "Food";
                DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
                parse_json = databaseSelectServices.execute(method,CatergoryId).get();
                //parse_json = databaseSelectServices.parse_json;
                Log.i("parse_json", parse_json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(parse_json.matches(BufferNoData)){
                Toast.makeText(FoodList.this, "No Food!", Toast.LENGTH_SHORT).show();
            }
            else {
                ParseJsonFoodData parseJsonFoodData= new ParseJsonFoodData(parse_json);
                jsondata = parseJsonFoodData.getFoodData();

                if (jsondata.size() > 0) {
                    adapter = new FoodAdapter(jsondata,this, this);
                } else {
                    Toast.makeText(FoodList.this, "Load FoodList Failure!", Toast.LENGTH_SHORT).show();
                }

            }
            adapter.notifyDataSetChanged();
            recyler_food.setAdapter(adapter);
            swipeRefreshLayout.setRefreshing(false);
        }

    }
}