package website.programming.androideatit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Database.DatabaseInsert;
import website.programming.androideatit.Model.User;
import website.programming.androideatit.Database.DatabaseSelectServices;
import website.programming.androideatit.Database.ParseJsonUserData;

import website.programming.androideatit.jbcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    Button btnSignUp, BtnSignIn;
    TextView txtSlogan;

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


        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);


            btnSignUp = (Button) findViewById(R.id.btnSignUp);
            BtnSignIn = (Button) findViewById(R.id.btnSignIn);

            txtSlogan = (TextView) findViewById(R.id.txtSlogan);

            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
            txtSlogan.setTypeface(face);

            //Init Paper
            Paper.init(this);

            BtnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent signIn = new Intent(MainActivity.this, SignIn.class);
                    startActivity(signIn);
                }
            });

            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent signUp = new Intent(MainActivity.this, SignUp.class);
                    startActivity(signUp);
                }
            });

            //Check remember
            String user = Paper.book().read(Common.USER_KEY);
            String pwd = Paper.book().read(Common.PWD_KEY);
            if(user != null && pwd != null)
            {
                if(!user.isEmpty() && !pwd.isEmpty())
                    login(user,pwd);
            }

            //printKeyHash();
    }

    private void printKeyHash() {
        try{
            PackageInfo info = getPackageManager().getPackageInfo("website.programming.androideatit",
                    PackageManager.GET_SIGNATURES);

            for(android.content.pm.Signature signature:info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("keyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    private void login(final String phone,final String pwd) {

        int flags = Base64.NO_WRAP | Base64.URL_SAFE;

        String hashString = Base64.encodeToString(pwd.getBytes(), flags);


        if (Common.isConnectedToInternet(getBaseContext())) {
                    final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                    mDialog.setMessage("Please wait...");
                    mDialog.show();

                    DatabaseInsert sql = new DatabaseInsert(MainActivity.this);
                    ParseJsonUserData parseJsonUserData = null;
                    ArrayList<User> jsonUserData = new ArrayList<User>();

                    try {
                        String parse_json = sql.execute("signin", phone, hashString).get();

                        if (!TextUtils.isEmpty(parse_json)) {
                            parseJsonUserData = new ParseJsonUserData(parse_json);
                            jsonUserData = parseJsonUserData.getUserData();
                            mDialog.dismiss();
                            User user = jsonUserData.get(0);
                            user.setPhone(phone);
                            if (user.verifyPassword(user.getPassword(), hashString).equals(pwd)) {
                                Intent homeIntent = new Intent(MainActivity.this, Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Password!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "User Not Exists!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
        } else {
            Toast.makeText(MainActivity.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
