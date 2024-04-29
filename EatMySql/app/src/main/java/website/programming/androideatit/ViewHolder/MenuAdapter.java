package website.programming.androideatit.ViewHolder;

/**
 * Created by cokel on 3/26/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import website.programming.androideatit.FoodDetail;
import website.programming.androideatit.FoodList;
import website.programming.androideatit.Interface.ItemClickListener;
import website.programming.androideatit.Model.Category;
import website.programming.androideatit.R;

/**
 * Created by cokel on 2/27/2018.
 */

class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);

        txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView) itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }
}

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder>  {

    Context context;
    ArrayList<Category> listdata = new ArrayList<Category>();

    public MenuAdapter(ArrayList<Category> data, Context context) {
        this.listdata = data;
        this.context = context;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.menu_item, parent, false);
        return  new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.txtMenuName.setText(listdata.get(position).getName());

        Picasso.with(context).load(listdata.get(position).getImage())
                .into(holder.imageView);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent foodlist = new Intent(context, FoodList.class);
                foodlist.putExtra("CategoryId", listdata.get(position).getCategoryid());
                context.startActivity(foodlist);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }



}
