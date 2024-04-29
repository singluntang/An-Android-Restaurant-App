package website.programming.androideatitserver.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import website.programming.androideatitserver.Common.Common;
import website.programming.androideatitserver.Interface.ItemClickListener;
import website.programming.androideatitserver.Model.Food;
import website.programming.androideatitserver.Model.Order;
import website.programming.androideatitserver.Model.Request;
import website.programming.androideatitserver.OrderDetail;
import website.programming.androideatitserver.OrderStatus;
import website.programming.androideatitserver.R;
import website.programming.androideatitserver.TrackingOrder;

/**
 * Created by cokel on 4/1/2018.
 */
class OrderViewHolder_new extends RecyclerView.ViewHolder {

    public TextView txtOrderId,txtOrderStatus, txtOrderPhone, txtOrderAddress;

    public Button btnEdit,btnRemove ,btnDetail ,btnDirection;

    private ItemClickListener itemClickListener;

    public OrderViewHolder_new(View itemView) {
        super(itemView);

        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderAddress = (TextView)itemView.findViewById(R.id.order_address);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);

        btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
        btnRemove = (Button)itemView.findViewById(R.id.btnRemove);
        btnDetail = (Button)itemView.findViewById(R.id.btnDetail);
        btnDirection = (Button)itemView.findViewById(R.id.btnDirection);
    }
}

public class OrderAdapter_new extends RecyclerView.Adapter<OrderViewHolder_new>  {

    Context context;
    ArrayList<Request> listdata = new ArrayList<Request>();
    Request RequestDel;
    //Activity activity;

    public OrderAdapter_new(ArrayList<Request> data, Context context) {
        this.listdata = data;
        this.context = context;
        //this.activity = activity;
    }

    @Override
    public OrderViewHolder_new onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.order_layout, parent, false);
        return  new OrderViewHolder_new(itemView);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder_new holder,final int position) {
        RequestDel = listdata.get(position);
        holder.txtOrderId.setText(listdata.get(position).getId());
        holder.txtOrderStatus.setText(Common.convertCodeToStatus(listdata.get(position).getStatus()));
        holder.txtOrderAddress.setText(listdata.get(position).getAddress());
        holder.txtOrderPhone.setText(listdata.get(position).getPhone());

        //New event
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showUpdateDialog(listdata.get(position).getId(),
                //       listdata);
            }
        });

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestDel.DelOrder(listdata.get(position).getId());
                listdata.remove(position);
            }
        });

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderDetail = new Intent(context,OrderDetail.class);
                orderDetail.putExtra("OrderId",listdata.get(position).getId());
                context.startActivity(orderDetail);
                Log.i("OrderId",listdata.get(position).getId());
            }
        });

        holder.btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //just implement it to fix crash when click to this item
                Intent trackingOrder = new Intent(context,TrackingOrder.class);
                context.startActivity(trackingOrder);
            }
        });

    }

    public  ArrayList<Request> getListdata(){
        return listdata;
    }


    @Override
    public int getItemCount() {
        if (listdata!=null)
            return listdata.size();
        else
            return 0;
    }
}
