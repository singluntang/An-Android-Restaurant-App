package website.programming.androideatit.ViewHolder;

/**
 * Created by cokel on 3/26/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Database.Database;
import website.programming.androideatit.FoodDetail;
import website.programming.androideatit.FoodList;
import website.programming.androideatit.Interface.ItemClickListener;
import website.programming.androideatit.Model.Category;
import website.programming.androideatit.Model.Food;
import website.programming.androideatit.R;

/**
 * Created by cokel on 2/27/2018.
 */


class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView food_name, food_price;
    public ImageView food_image, fav_image, img_share;
    public ShareButton shareButton;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_name = (TextView)itemView.findViewById(R.id.food_name);
        food_image = (ImageView) itemView.findViewById(R.id.food_image);
        fav_image = (ImageView) itemView.findViewById(R.id.fav);
        img_share = (ImageView) itemView.findViewById(R.id.img_share);
        food_price = (TextView)itemView.findViewById(R.id.food_price);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }
}

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder>  {

    Context context;
    ArrayList<Food> listdata = new ArrayList<Food>();
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    Activity activity;
    Database localDB;
    static String strMessage;


    public FoodAdapter(ArrayList<Food> data, Context context, Activity activity) {
        this.listdata = data;
        this.context = context;
        this.activity = activity;
        localDB = new Database(context);
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.food_item, parent, false);
        return  new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FoodViewHolder holder, final int position) {


        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(activity);
        shareDialog.registerCallback(callbackManager, callback);


        holder.food_name.setText(listdata.get(position).getName());
        holder.food_price.setText(listdata.get(position).getPrice());

        Picasso.with(context).load(listdata.get(position).getImage())
                .into(holder.food_image);


        if(localDB.isFavourites(listdata.get(position).getId()))
            holder.fav_image.setImageResource(R.drawable.ic_favorite_black_24dp);


        holder.img_share.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Share  on Facebook custom button is clicked on
            @Override
            public void onClick(View view) {
                Log.i("FaceBook Share: ", listdata.get(position).getName().toString());
                Picasso.with(context).load(listdata.get(position).getImage())
                        .into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                SharePhoto photo = new SharePhoto.Builder()
                                        .setBitmap(bitmap)
                                        .build();
                                if (shareDialog.canShow(ShareLinkContent.class)) {
                                    SharePhotoContent content = new SharePhotoContent.Builder()
                                            .addPhoto(photo)
                                            .build();
                                    shareDialog.show(content);
                                }

                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }

                        });

            };

    });


    //Click to change state of Favourites
    holder.fav_image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!localDB.isFavourites(listdata.get(position).getId()))
            {
                localDB.addToFavourites(listdata.get(position).getId());
                holder.fav_image.setImageResource(R.drawable.ic_favorite_black_24dp);
                //strMessage = listdata.get(position).getName()+" was added to Favourites";
                Toast.makeText(context,""+listdata.get(position).getName()+" was added to Favourites",Toast.LENGTH_SHORT).show();
            }
            else {
                localDB.removeFavourites(listdata.get(position).getId());
                holder.fav_image.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                Toast.makeText(context,""+listdata.get(position).getName()+" was removed from Favourites",Toast.LENGTH_SHORT).show();
            }
        }
    });

    holder.setItemClickListener(new ItemClickListener() {
        @Override
        public void onClick(View view, int position, boolean isLongClick) {
            Intent foodDetail = new Intent(context, FoodDetail.class);
            foodDetail.putExtra("FoodId", listdata.get(position).getId());
            context.startActivity(foodDetail);
        }
    });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Call callbackManager.onActivityResult to pass login result to the LoginManager via callbackManager.
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private FacebookCallback<Sharer.Result> callback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onSuccess(Sharer.Result result) {
            Log.v("onSuccess", "Successfully posted");
            Toast.makeText(context, "Successfully posted",Toast.LENGTH_SHORT).show();
            // Write some code to do some operations when you shared content successfully.
        }

        @Override
        public void onCancel() {
            Log.v("onCancel", "Sharing cancelled");
            Toast.makeText(context, "Sharing cancelled",Toast.LENGTH_SHORT).show();
            // Write some code to do some operations when you cancel sharing content.
        }

        @Override
        public void onError(FacebookException error) {
            Log.v("onError", error.getMessage());
            Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
            // Write some code to do some operations when some error occurs while sharing content.
        }
    };

}
