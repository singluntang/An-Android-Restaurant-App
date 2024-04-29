package website.programming.androideatit;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Database.Database;
import website.programming.androideatit.Database.DatabaseInsert;
import website.programming.androideatit.Database.DatabaseSelectServices;
import website.programming.androideatit.Database.ParseJsonFoodData;
import website.programming.androideatit.Database.ParseJsonRatingData;
import website.programming.androideatit.Model.Food;
import website.programming.androideatit.Model.Order;
import website.programming.androideatit.Model.Rating;
import website.programming.androideatit.ViewHolder.FoodAdapter;

public class FoodDetail extends AppCompatActivity implements RatingDialogListener{

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart, btnRating;
    ElegantNumberButton numberButton;
    RatingBar ratingBar;

    String foodId="";

    Food currentFood;


    String parse_json = "",method="";
    ArrayList<Rating> jsondata;
    ArrayList<Food> foodjsondata;
    String BufferNoData = "Nodata";
    DatabaseSelectServices databaseSelectServices = null;

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

        setContentView(R.layout.activity_food_detail);



        //Init View
        numberButton = (ElegantNumberButton)findViewById(R.id.nuber_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);
        btnRating = (FloatingActionButton)findViewById(R.id.btn_rating);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRatingDialog();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()
                ));

                Toast.makeText(FoodDetail.this,"Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        food_name = (TextView) findViewById(R.id.food_name);
        food_price = (TextView)findViewById(R.id.food_price);
        food_description = (TextView)findViewById(R.id.food_description);

        food_image = (ImageView) findViewById(R.id.img_food);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        if(getIntent() != null) {
            foodId = getIntent().getStringExtra("FoodId");
            //Toast.makeText(FoodDetail.this,foodId,Toast.LENGTH_SHORT).show();
        }
        if(!foodId.isEmpty() && foodId != null)
        {

            if (Common.isConnectedToInternet(getBaseContext())) {
                getDetailFood(foodId);
                getRatingFood(foodId);
            }
            else {
                Toast.makeText(FoodDetail.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void getRatingFood(String foodId) {

        int count=0, sum=0;

        if (databaseSelectServices == null) {
            try {
                method = "Rating";
                DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
                parse_json = databaseSelectServices.execute(method,foodId).get();
                //parse_json = databaseSelectServices.parse_json;
                Log.i("parse_json", parse_json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(parse_json.matches(BufferNoData)){
                Toast.makeText(FoodDetail.this, "No Rating!", Toast.LENGTH_SHORT).show();
            }
            else {
                ParseJsonRatingData parseJsonRatingData = new ParseJsonRatingData(parse_json);
                jsondata = parseJsonRatingData.getRatingData();

                if (jsondata.size() > 0) {

                    for(int i=0;i<jsondata.size();i++)
                    {
                        Rating item = jsondata.get(i);
                        sum+=Integer.parseInt(item.getRateValue());
                        count++;
                    }
                    if(count != 0) {
                        float average = sum / count;
                        ratingBar.setRating(average);
                    }
                } else {
                    Toast.makeText(FoodDetail.this, "Load Rating Failure!", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

    private void showRatingDialog() {
          new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNoteDescriptions(Arrays.asList("Very Bad","Not Good","Quite Ok","Very Good","Excellent"))
                .setDefaultRating(1)
                .setTitle("Rate this food")
                .setDescription("Please select some stars and give your feedback")
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Please write your comment here...")
                .setHintTextColor(R.color.colorAccent)
                .setCommentTextColor(android.R.color.white)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(FoodDetail.this)
                .show();
    }

    private void getDetailFood(String foodId) {
        if (databaseSelectServices == null) {
            try {
                method = "FoodDetail";
                DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
                parse_json = databaseSelectServices.execute(method,foodId).get();
                //parse_json = databaseSelectServices.parse_json;
                Log.i("parse_json", parse_json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(parse_json.matches(BufferNoData)){
                Toast.makeText(FoodDetail.this, "No Food!", Toast.LENGTH_SHORT).show();
            }
            else {
                ParseJsonFoodData parseJsonFoodData= new ParseJsonFoodData(parse_json);
                foodjsondata = parseJsonFoodData.getFoodData();

                if (foodjsondata.size() == 1) {
                    currentFood = foodjsondata.get(0);

                    Picasso.with(getBaseContext()).load(currentFood.getImage())
                            .into(food_image);

                    collapsingToolbarLayout.setTitle(currentFood.getName());

                    food_price.setText(currentFood.getPrice());
                    food_description.setText(currentFood.getDescription());
                    food_name.setText(currentFood.getName());
                } else {
                    Toast.makeText(FoodDetail.this, "Load FoodList Failure!", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

    @Override
    public void onPositiveButtonClicked(int value, String comments) {

        String userPhone, rateValue , comment;

        method = "Rating";

        try {
            DatabaseInsert DBinsert = new DatabaseInsert(FoodDetail.this);
            String result = DBinsert.execute(method,Common.currentUser.getPhone(),foodId, String.valueOf(value),comments).get();

            getRatingFood(foodId);
            Toast.makeText(FoodDetail.this,"Thank You for submit rating!!", Toast.LENGTH_SHORT).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
