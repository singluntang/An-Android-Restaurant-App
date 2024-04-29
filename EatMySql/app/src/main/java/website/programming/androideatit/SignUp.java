package website.programming.androideatit;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;
import java.util.concurrent.ExecutionException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Database.DatabaseSelectServices;
import website.programming.androideatit.Database.DatabaseInsert;
import website.programming.androideatit.Database.ParseJsonUserData;
import website.programming.androideatit.Model.User;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone, edtName, edtPassword, edtSecureCode;
    String name,phone,securecode,password;
    Button btnSignUp;
    String method;
    String parse_json = null;
    String BufferNoData = "Nodata";
    List<User> jsondata;
    DatabaseSelectServices databaseSelectServices = null;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void signup(View view){
        final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
        mDialog.setMessage("Please wait...");
        mDialog.show();

        if (Common.isConnectedToInternet(getBaseContext())) {
            method= "signup";

            if (databaseSelectServices == null) {
                try {
                    method= "signup";
                    DatabaseSelectServices databaseSelectServices= new DatabaseSelectServices();
                    parse_json =  databaseSelectServices.execute(method, edtPhone.getText().toString()).get();
                    //parse_json = databaseSelectServices.parse_json;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                if(parse_json.matches(BufferNoData)){
                    Log.i("JsonData", parse_json);
                    ParseJsonUserData ParseJsonUserData= new ParseJsonUserData(parse_json);
                    jsondata = ParseJsonUserData.getUserData();
                    mDialog.dismiss();
                    method= "signup";

                    try {

                        phone= edtPhone.getText().toString();
                        name= edtName.getText().toString();
                        password= edtPassword.getText().toString();
                        securecode= edtSecureCode.getText().toString();
                        DatabaseInsert DBinsert = new DatabaseInsert(SignUp.this);
                        String result = DBinsert.execute(method,phone,name, password,securecode).get();
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(SignUp.this, "Sign Up Successfully.", Toast.LENGTH_SHORT).show();

                }
                else {
                    mDialog.dismiss();
                    Toast.makeText(SignUp.this, "Phone Number already Registered.", Toast.LENGTH_SHORT).show();
                }

            }
        }
        else {
            Toast.makeText(SignUp.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Arkhip_font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());


        setContentView(R.layout.activity_sign_up);

        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtSecureCode = (MaterialEditText) findViewById(R.id.edtSecureCode);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);


        if(!Common.isConnectedToInternet(SignUp.this)) {
            Toast.makeText(SignUp.this,"Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
