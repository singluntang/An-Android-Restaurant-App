package website.programming.androideatitserver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;
import java.util.concurrent.ExecutionException;

import website.programming.androideatitserver.Common.Common;
import website.programming.androideatitserver.Database.DatabaseSelectServices;
import website.programming.androideatitserver.Database.ParseJsonUserData;
import website.programming.androideatitserver.Model.User;

public class SignIn extends AppCompatActivity {

    MaterialEditText edtPhone, edtPassword;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;

    String method;
    String parse_json = null;
    List<User> jsondata;
    String BufferNoData = "Nodata";
    DatabaseSelectServices databaseSelectServices = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        //db = FirebaseDatabase.getInstance();
        //users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singnInUser(edtPhone.getText().toString(),edtPassword.getText().toString());
            }
        });
    }

    private void singnInUser(String phone, String password) {
        final String localPhone = phone;
        final String localPassword = password;

        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please wait...");
        mDialog.show();

        if (Common.isConnectedToInternet(getBaseContext())) {


            if (databaseSelectServices == null) {
                try {
                    method= "signup";
                    DatabaseSelectServices databaseSelectServices= new DatabaseSelectServices();
                    parse_json =  databaseSelectServices.execute(method, edtPhone.getText().toString()).get();
                    databaseSelectServices = null;
                    //parse_json = databaseSelectServices.parse_json;
                    Log.i("parse_json", parse_json);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(parse_json.matches(BufferNoData)){
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "User Not Exists!", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        method= "signin";
                        DatabaseSelectServices databaseSelectServices= new DatabaseSelectServices();
                        parse_json =  databaseSelectServices.execute(method,edtPhone.getText().toString(),edtPassword.getText().toString()).get();
                        //parse_json = databaseSelectServices.parse_json;
                        if(parse_json.matches(BufferNoData)){
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            ParseJsonUserData ParseJsonUserData= new ParseJsonUserData(parse_json);
                            jsondata = ParseJsonUserData.getUserData();

                            if (jsondata.size() > 0) {
                                mDialog.dismiss();
                                Intent homeIntent = new Intent(SignIn.this, Home.class);
                                Common.currentUser = jsondata.get(0);
                                startActivity(homeIntent);
                                finish();
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(SignIn.this, "SignIn Failure!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Toast.makeText(SignIn.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
