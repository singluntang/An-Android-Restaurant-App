package website.programming.androideatit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Database.DatabaseInsert;
import website.programming.androideatit.Database.DatabaseSelectServices;
import website.programming.androideatit.Database.ParseJsonUserData;
import website.programming.androideatit.Model.User;

public class SignIn extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSignIn;
    CheckBox ckbRemember;
    TextView txtForgotPwd;
    FirebaseDatabase database;
    DatabaseReference table_user;
    String method;
    String parse_json = null;
    List<User> jsondata;
    String BufferNoData = "Nodata";
    DatabaseSelectServices databaseSelectServices = null;
    DatabaseInsert databaseInsert = null;

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


        setContentView(R.layout.activity_sign_in);

            edtPhone = (EditText) findViewById(R.id.edtPhone);
            edtPassword = (EditText) findViewById(R.id.edtPassword);
            btnSignIn = (Button) findViewById(R.id.btnSignIn);
            ckbRemember = (CheckBox) findViewById(R.id.ckbRemember);
            txtForgotPwd = (TextView) findViewById(R.id.txtForgotPwd);


            //Init Paper
            Paper.init(this);


            //Init Firebase
            //database = FirebaseDatabase.getInstance();
           // table_user = database.getReference("User");

            txtForgotPwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showForgotPwdDialog();
                }
            });

            btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                    mDialog.setMessage("Please wait...");
                    mDialog.show();

                    if (Common.isConnectedToInternet(getBaseContext())) {

                        //Save user & password
                        if(ckbRemember.isChecked())
                        {
                            Paper.book().write(Common.USER_KEY, edtPhone.getText().toString());
                            Paper.book().write(Common.PWD_KEY,edtPassword.getText().toString());
                        }

                        int flags = Base64.NO_WRAP | Base64.URL_SAFE;

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

                                String hashString = Base64.encodeToString(edtPassword.getText().toString().getBytes(), flags);

                                DatabaseInsert sql = new DatabaseInsert(SignIn.this);
                                ParseJsonUserData parseJsonUserData = null;
                                ArrayList<User> jsonUserData = new ArrayList<User>();

                                try {
                                    String parse_json = sql.execute("signin", edtPhone.getText().toString(), hashString).get();

                                    if (!TextUtils.isEmpty(parse_json)) {
                                        parseJsonUserData = new ParseJsonUserData(parse_json);
                                        jsonUserData = parseJsonUserData.getUserData();
                                        mDialog.dismiss();
                                        User user = jsonUserData.get(0);
                                        user.setPhone(edtPhone.getText().toString());
                                        if (user.verifyPassword(user.getPassword(), hashString).equals(edtPassword.getText().toString())) {
                                            Intent homeIntent = new Intent(SignIn.this, Home.class);
                                            Common.currentUser = user;
                                            startActivity(homeIntent);
                                            finish();
                                        } else {
                                            Toast.makeText(SignIn.this, "Wrong Password!!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else
                                    {
                                        mDialog.dismiss();
                                        Toast.makeText(SignIn.this, "User Not Exists!", Toast.LENGTH_SHORT).show();
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
            });
        }

    private void showForgotPwdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Password");
        builder.setMessage("Enter your secured code.");

        LayoutInflater inflater = this.getLayoutInflater();
        View forgot_view = inflater.inflate(R.layout.forgot_password_layout,null);

        builder.setIcon(R.drawable.ic_security_black_24dp);

        builder.setView(forgot_view);


        final MaterialEditText edtPhone = (MaterialEditText)forgot_view.findViewById(R.id.edtPhone);
        final MaterialEditText edtSecureCode = (MaterialEditText)forgot_view.findViewById(R.id.edtSecureCode);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    method= "forgetpwd";
                    DatabaseSelectServices databaseSelectServices= new DatabaseSelectServices();
                    parse_json =  databaseSelectServices.execute(method, edtPhone.getText().toString(), edtSecureCode.getText().toString()).get();
                    databaseSelectServices = null;
                    //parse_json = databaseSelectServices.parse_json;
                    Log.i("parse_json", parse_json);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(parse_json.matches(BufferNoData)){
                    Toast.makeText(SignIn.this, "Wrong Secure code!",Toast.LENGTH_LONG).show();
                }
                else {
                    ParseJsonUserData ParseJsonUserData= new ParseJsonUserData(parse_json);
                    jsondata = ParseJsonUserData.getUserData();

                    if (jsondata.size() > 0) {
                        Toast.makeText(SignIn.this, "Your Password : " + jsondata.get(0).getPassword(),Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignIn.this, "Process Failure!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();



    }

}

