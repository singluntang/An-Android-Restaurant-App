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

import website.programming.androideatit.Common.Common;
import website.programming.androideatit.FoodDetail;
import website.programming.androideatit.FoodList;
import website.programming.androideatit.Interface.ItemClickListener;
import website.programming.androideatit.Model.Category;
import website.programming.androideatit.Model.Order;
import website.programming.androideatit.Model.Request;
import website.programming.androideatit.R;

import static website.programming.androideatit.Common.Common.convertCodeToStatus;

/**
 * Created by cokel on 2/27/2018.
 */

class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView txtOrderId,txtOrderStatus, txtOrderPhone, txtOrderAddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderAddress = (TextView)itemView.findViewById(R.id.order_address);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);

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


public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder>  {

    Context context;
    ArrayList<Request> listdata = new ArrayList<Request>();

    public OrderAdapter(ArrayList<Request> data, Context context) {
        this.listdata = data;
        this.context = context;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.order_layout, parent, false);
        return  new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

        holder.txtOrderId.setText(listdata.get(position).getId());
        holder.txtOrderAddress.setText(listdata.get(position).getAddress());
        holder.txtOrderStatus.setText(convertCodeToStatus(listdata.get(position).getStatus()));
        holder.txtOrderPhone.setText(listdata.get(position).getPhone());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }



}
