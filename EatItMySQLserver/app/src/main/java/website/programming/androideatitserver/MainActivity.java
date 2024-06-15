package website.programming.androideatitserver;

import android.content.Intent;
import android.graphics.Typeface;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import website.programming.androideatitserver.Model.Category;
import website.programming.androideatitserver.ViewHolder.MenuViewHolder;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button)findViewById(R.id.btnSignin);
        txtSlogan = (TextView)findViewById(R.id.txtSlogan);


//        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        Typeface typeface = ResourcesCompat.getFont(context, R.font.nabila);
        txtSlogan.setTypeface(typeface);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignIn.class));
                finish();
            }
        });


    }
}
