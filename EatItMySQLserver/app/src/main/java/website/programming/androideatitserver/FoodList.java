package website.programming.androideatitserver;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

//import android.support.annotation.NonNull;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;





import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import website.programming.androideatitserver.Common.Common;
import website.programming.androideatitserver.Database.DatabaseInsert;
import website.programming.androideatitserver.Database.DatabaseSelectServices;
import website.programming.androideatitserver.Database.ParseJsonCategoryData;
import website.programming.androideatitserver.Database.ParseJsonFoodData;
import website.programming.androideatitserver.Interface.ItemClickListener;
import website.programming.androideatitserver.Model.Category;
import website.programming.androideatitserver.Model.Food;
import website.programming.androideatitserver.ViewHolder.FoodViewHolder;
import website.programming.androideatitserver.ViewHolder.FoodAdapter_new;
import website.programming.androideatitserver.ViewHolder.MenuAdapter_new;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;

    String categoryId="";

    String strFoodId = "";
    Integer intFoodId;

    Food newFood;

    TextView txtFullName;
    EditText edtName, edtDescription, edtPrice, edtDiscount;
    Button btnSelect, btnUpLoad;

//    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

    DrawerLayout drawer;

    private final int PICK_IMAGE_REQUEST = 71;

    Uri saveUri;
    Bitmap imagebitmap;
    FoodAdapter_new adapter_new;
    String parse_json = null, method;
    ArrayList<Food> jsondata;
    ArrayList<Food> Emptyjsondata = null;
    String BufferNoData = "Nodata";
    DatabaseSelectServices databaseSelectServices = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code Late
                showAddFoodDialog();
            }
        });

        if(getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty())
            loadListFood(categoryId);
        //createId();
    }

    private void showAddFoodDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FoodList.this);
        alertDialog.setTitle("Add new Food");
        alertDialog.setMessage("Please Fill in the Details");

        LayoutInflater inflater = this.getLayoutInflater();
        View add_food_layout = inflater.inflate(R.layout.add_new_food_layout,null);

        edtName = add_food_layout.findViewById(R.id.edtName);
        edtDescription = add_food_layout.findViewById(R.id.edtDescription);
        edtPrice = add_food_layout.findViewById(R.id.edtPrice);
        edtDiscount = add_food_layout.findViewById(R.id.edtDiscount);
        btnSelect = add_food_layout.findViewById(R.id.btnSelect);
        btnUpLoad = add_food_layout.findViewById(R.id.btnUpLoad);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        btnUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLoadImage();
            }
        });

        alertDialog.setView(add_food_layout);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        //Set Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

                try {
                    method="Food";
                    DatabaseInsert DBinsert = new DatabaseInsert(FoodList.this);

                   /*Log.i("Method", method);
                    Log.i("ID", newFood.getId());
                    Log.i("Name", newFood.getName());
                    Log.i("Image", newFood.getImage());
                    Log.i("Description", newFood.getDescription());
                    Log.i("Discount", newFood.getDiscount());
                    Log.i("MenuId", newFood.getMenuId());*/
                    String result = DBinsert.execute(method,newFood.getId(),newFood.getName(),newFood.getImage(),newFood.getDescription(),newFood.getPrice(),newFood.getDiscount(),newFood.getMenuId()).get();
                    //finish();
                    loadListFood(newFood.getMenuId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Toast.makeText(FoodList.this, "New Food " + newFood.getName()+" was added.",Toast.LENGTH_LONG)
                        .show();

            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void createId() {

        try {
            method = "AllFood";
            DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
            parse_json = databaseSelectServices.execute(method).get();
            //parse_json = databaseSelectServices.parse_json;
            Log.i("parse_json", parse_json);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(parse_json.matches(BufferNoData)){
            strFoodId = "0";
        }
        else {
            ParseJsonFoodData parseJsonFoodData= new ParseJsonFoodData(parse_json);
            jsondata = parseJsonFoodData.getFoodData();
            if (jsondata.size() > 0) {
                for(Food food:jsondata)
                {
                    strFoodId = food.getId().toString();
                }
                intFoodId = Integer.parseInt(strFoodId) +1;
                strFoodId = String.valueOf(intFoodId);
                Log.i("Latest FoodId = " , strFoodId);
            } else {
                Toast.makeText(FoodList.this, "Load Food Failure!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {

            saveUri = data.getData();

            try {

                imagebitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), saveUri);

                btnSelect.setText("Image Selected !");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private void upLoadImage() {

        if(saveUri != null)
        {
            final ProgressDialog mDialog = new ProgressDialog(this);
            mDialog.setMessage("Uploading.....");
            mDialog.show();

            String imageName = UUID.randomUUID().toString();
            //String imageName=String.valueOf(System.currentTimeMillis());
            String PHP_URL = Common.SERVER_URL + "SavePicture.php";
            String IMAGE_URL = Common.SERVER_URL + "Foods/" + imageName + ".JPG";

            Log.i("PHP URL:" , PHP_URL);
            Log.i("Image URL:" , IMAGE_URL);

            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imagebitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] ImageByteArray = baos.toByteArray();
                String encodedImage = android.util.Base64.encodeToString(ImageByteArray, android.util.Base64.DEFAULT);
                method = "Food";
                String result = new UploadImage().execute(method,imageName,PHP_URL,encodedImage).get();
                mDialog.dismiss();
                Toast.makeText(FoodList.this, result, Toast.LENGTH_SHORT).show();
                createId();
                newFood = new Food( strFoodId,edtName.getText().toString(),
                        IMAGE_URL,
                        edtDescription.getText().toString(),
                        edtPrice.getText().toString(),
                        edtDiscount.getText().toString(),
                        categoryId
                );

                Log.i("Upload Result", result);

                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(FoodList.this, "Please Select the Image First.", Toast.LENGTH_SHORT).show();
        }
    }

    private void chooseImage() {
        //Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //startActivityForResult(intent, 1);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }

    private void loadListFood(String categoryId) {
        Log.i("Load List Food 1","Entered");
      //  if (databaseSelectServices == null) {
            try {
                method = "Food";
                DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
                parse_json = databaseSelectServices.execute(method, categoryId).get();
                //parse_json = databaseSelectServices.parse_json;
                Log.i("parse_json", parse_json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(parse_json.matches(BufferNoData)){
                adapter_new = new FoodAdapter_new(Emptyjsondata,this);
                adapter_new.notifyDataSetChanged();
                recyclerView.invalidate();
                recyclerView.setAdapter(adapter_new);
                Toast.makeText(FoodList.this, "No Food For Under this Category!", Toast.LENGTH_SHORT).show();
            }
            else {
                ParseJsonFoodData parseJsonFoodData= new ParseJsonFoodData(parse_json);
                jsondata = parseJsonFoodData.getFoodData();

                if (jsondata.size() > 0) {
                    adapter_new = new FoodAdapter_new(jsondata,this);
                    adapter_new.notifyDataSetChanged();
                    recyclerView.invalidate();
                    recyclerView.setAdapter(adapter_new);
                } else {
                    Toast.makeText(FoodList.this, "Load Food Failure!", Toast.LENGTH_SHORT).show();
                }

            }
        //}
       // adapter_new.notifyDataSetChanged();
       // recyclerView.setAdapter(adapter_new);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle().equals(Common.UPDATE))
        {
            //showUpdateDialog(adapter.getRef(item.getOrder()).getKey(),adapter.getItem(item.getOrder()));
            showUpdateDialog(adapter_new.getListdata().get(item.getOrder()));

        }
        else if(item.getTitle().equals(Common.DELETE))
        {
            deleteFood(adapter_new.getListdata().get(item.getOrder()).getId());
            loadListFood(categoryId);

        }

        return super.onContextItemSelected(item);
    }


    private void deleteFood(String key) {
        try {
            method="DeleteFood";
            DatabaseInsert DBinsert = new DatabaseInsert(FoodList.this);
            String result = DBinsert.execute(method, key).get();
            //finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"Item deleted!!!", Toast.LENGTH_SHORT).show();
    }

    private void showUpdateDialog(final Food item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FoodList.this);
        alertDialog.setTitle("Update new Food");
        alertDialog.setMessage("Please Fill in the Details");

        LayoutInflater inflater = this.getLayoutInflater();
        View add_food_layout = inflater.inflate(R.layout.add_new_food_layout,null);

        edtName = add_food_layout.findViewById(R.id.edtName);
        edtDescription = add_food_layout.findViewById(R.id.edtDescription);
        edtPrice = add_food_layout.findViewById(R.id.edtPrice);
        edtDiscount = add_food_layout.findViewById(R.id.edtDiscount);
        btnSelect = add_food_layout.findViewById(R.id.btnSelect);
        btnUpLoad = add_food_layout.findViewById(R.id.btnUpLoad);

        edtName.setText(item.getName());
        edtDescription.setText(item.getDescription());
        edtPrice.setText(item.getPrice());
        edtDiscount.setText(item.getDiscount());

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        btnUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeImage(item);
            }
        });

        alertDialog.setView(add_food_layout);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        //Set Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                item.setName(edtName.getText().toString());
                item.setDescription(edtDescription.getText().toString());
                item.setDiscount(edtDiscount.getText().toString());
                item.setPrice(edtPrice.getText().toString());
                try {
                    method="FoodUpdate";

                  /*  Log.i("Method", method);
                    Log.i("ID", item.getId());
                    Log.i("Name", item.getName());
                    Log.i("Image", item.getImage());
                    Log.i("Description", item.getDescription());
                    Log.i("Discount", item.getDiscount());
                    Log.i("MenuId", item.getMenuId());*/

                    DatabaseInsert DBinsert = new DatabaseInsert(FoodList.this);
                    String result = DBinsert.execute(method,item.getId(),item.getName(),item.getImage(),item.getDescription(),item.getPrice(),item.getDiscount(),item.getMenuId()).get();
                    loadListFood(item.getMenuId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void ChangeImage(final Food item) {
        if(saveUri != null)
        {
            final ProgressDialog mDialog = new ProgressDialog(this);
            mDialog.setMessage("Uploading.....");
            mDialog.show();

            String imageName = UUID.randomUUID().toString();
            //String imageName=String.valueOf(System.currentTimeMillis());
            String PHP_URL = Common.SERVER_URL + "SavePicture.php";
            String IMAGE_URL = Common.SERVER_URL + "Foods/" + imageName + ".JPG";

            Log.i("Image Url", IMAGE_URL);

            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imagebitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] ImageByteArray = baos.toByteArray();
                String encodedImage = android.util.Base64.encodeToString(ImageByteArray, android.util.Base64.DEFAULT);
                method="Food";
                String result = new UploadImage().execute(method,imageName,PHP_URL,encodedImage).get();
                mDialog.dismiss();
                Toast.makeText(FoodList.this, result, Toast.LENGTH_SHORT).show();
                Log.i("Upload Result", result);
                item.setImage(IMAGE_URL);
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

}