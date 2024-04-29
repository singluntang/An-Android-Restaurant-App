package website.programming.androideatit;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Common.Config;
import website.programming.androideatit.Database.Database;
import website.programming.androideatit.Database.DatabaseInsert;
import website.programming.androideatit.Model.MyResponse;
import website.programming.androideatit.Model.Notification;
import website.programming.androideatit.Model.Order;
import website.programming.androideatit.Model.Request;
import website.programming.androideatit.Model.Sender;
import website.programming.androideatit.Model.Token;
import website.programming.androideatit.Remote.APIService;
import website.programming.androideatit.ViewHolder.CartAdapter;

public class Cart extends AppCompatActivity {

    private static final int PAYPAL_REQUEST_CODE = 9999 ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    String totalamount;
    Button btnPlace;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;

    APIService mServices;

    String method,phone, name, home, total, status, address, comment, paymentState, foods;;

    //Paypal payment
    static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)//user Sandbox because we test, change it later if you going to production
            .clientId(Config.PAYPAL_CLIENT_ID);

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

        setContentView(R.layout.activity_cart);

        //Init paypal
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);



        //Init Services
        mServices = Common.getFCMService();

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        txtTotalPrice = (TextView)findViewById(R.id.total);

        btnPlace = (Button)findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cart.size() > 0)
                    showAlertDialog();
                else
                    Toast.makeText(Cart.this,"The Cart is Empty!!",Toast.LENGTH_SHORT).show();
            }
        });
        loadListFood();

    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address: ");

        LayoutInflater inflater = this.getLayoutInflater();
        View order_address_comment = inflater.inflate(R.layout.order_address_comment,null);

        final MaterialEditText edtAddress = (MaterialEditText)order_address_comment.findViewById(R.id.edtAddress);
        final MaterialEditText edtComment = (MaterialEditText)order_address_comment.findViewById(R.id.edtComment);

        alertDialog.setView(order_address_comment);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String order_number=String.valueOf(System.currentTimeMillis());
                //requests.child(order_number)
                  //      .setValue(request);

                ConvertToJson convertToJson = new ConvertToJson(cart);
                String OrderedFood = convertToJson.Convert();

                        try {
                            method="Order";
                            phone= Common.currentUser.getPhone();
                            name= Common.currentUser.getName();
                            home= edtAddress.getText().toString();
                            total= txtTotalPrice.getText().toString();
                            status= "0";
                            comment= edtComment.getText().toString();
                            paymentState= "";
                            foods= OrderedFood;
                            Log.i("Food with double quotes", foods);
                            DatabaseInsert DBinsert = new DatabaseInsert(Cart.this);
                            String result = DBinsert.execute(method,order_number, phone, name, home, total, status, comment, paymentState,foods).get();
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }


                //Delete CART
                new Database(getBaseContext()).cleanCart();
                loadListFood();
                //sendNotificationOrder(order_number);
                Toast.makeText(Cart.this, "Thank You", Toast.LENGTH_SHORT).show();
                //finish();

                //fist , get Address and comment from Alet Dialog
                address = edtAddress.getText().toString();
                comment = edtComment.getText().toString();

                //String amount = txtTotalPrice.getText().toString()
                //                        .replace("$","")
                //                        .replace(",","");
                //float amount = Float.parseFloat("1.00");

                String totalamount = "0.1";

                PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal((String.valueOf(totalamount))),
                        "HKD",
                        "Eat It App Order",
                        PayPalPayment.PAYMENT_INTENT_SALE);

                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
                startActivityForResult(intent, PAYPAL_REQUEST_CODE);

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

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("requestCode",String.valueOf(requestCode));
        Log.i("resultCode",String.valueOf(resultCode));
        Toast.makeText(this, "Payment Received",Toast.LENGTH_LONG).show();
        if (requestCode == PAYPAL_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null)
                {
                    try {
                            String paymentDetail = confirmation.toJSONObject().toString(4);
                            JSONObject jsonObject = new JSONObject(paymentDetail);
                            Log.i("PayPal Status",jsonObject.getJSONObject("response").getString("state"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                       /* try {
                            String paymentDetail = confirmation.toJSONObject().toString(4);
                            JSONObject jsonObject = new JSONObject(paymentDetail);

                            //Create New Request
                            Request request = new Request(
                                    Common.currentUser.getPhone(),
                                    Common.currentUser.getName(),
                                    address,
                                    txtTotalPrice.getText().toString(),
                                    "0",
                                    comment,
                                    jsonObject.getJSONObject("response").getString("state"),
                                    cart);

                            String order_number=String.valueOf(System.currentTimeMillis());
                            requests.child(order_number)
                                    .setValue(request);

                            //Delete CART
                            new Database(getBaseContext()).cleanCart();
                            loadListFood();
                            //sendNotificationOrder(order_number);

                             Toast.makeText(Cart.this, "Thank You", Toast.LENGTH_SHORT).show();
                             finish();

                        } catch (JSONException e) {
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                }

            }
            else if(requestCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Payment Cancel", Toast.LENGTH_SHORT).show();
            else if(requestCode == PaymentActivity.RESULT_EXTRAS_INVALID)
                Toast.makeText(this, "Invalid Payment", Toast.LENGTH_SHORT).show();
        }

    }

    private void sendNotificationOrder(final String order_number) {
        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
        Query data = tokens.orderByChild("serverSide").equalTo(true);
        //Toast.makeText(Cart.this, "NO SnapSHOT", Toast.LENGTH_SHORT).show();
         data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                {

                    //Toast.makeText(Cart.this, "have SnapSHOT", Toast.LENGTH_SHORT).show();

                    Token serverToken = postSnapShot.getValue(Token.class);
                    //Create raw payload to send
                    Notification notification = new Notification("Eat It","You have new order "+order_number);
                    Sender content = new Sender(serverToken.getToken(),notification);

                    mServices.sendNotification(content)
                            .enqueue(new Callback<MyResponse>() {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {

                                    if (response.code() == 200) {

                                        if (response.body().success == 1) {
                                            Toast.makeText(Cart.this, "Thank You Notification has been sent", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(Cart.this, "Failed !!!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MyResponse> call, Throwable t) {
                                    Log.e("ERROR", t.getMessage());
                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void loadListFood() {
        cart = new Database(this).getCarts();

        Log.i("Load List Food:",String.valueOf(cart.size()).toString());


        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);

        int total = 0;
        for (Order order : cart) {
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
        }
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));
        totalamount = String.valueOf(total);


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals(Common.DELETE))
            deleteCart(item.getOrder());

        loadListFood();

        return true;
    }

    private void deleteCart(int position) {
        cart.remove(position);
        //delete sqllite database
        new Database(this).cleanCart();
        for(Order item:cart)
            new Database(this).addToCart(item);
    }
}
