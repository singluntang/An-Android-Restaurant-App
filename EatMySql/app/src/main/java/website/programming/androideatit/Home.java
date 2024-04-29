package website.programming.androideatit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Database.DatabaseInsert;
import website.programming.androideatit.Database.DatabaseSelectServices;
import website.programming.androideatit.Database.ParseJsonCategoryData;
import website.programming.androideatit.Model.Category;
import website.programming.androideatit.Model.Token;
import website.programming.androideatit.ViewHolder.MenuAdapter;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference category;

    TextView txtFullName;

    RecyclerView recyler_menu;
    RecyclerView.LayoutManager layoutManager;

    SwipeRefreshLayout swipeRefreshLayout;

    String name,phone,securecode,password;

    MenuAdapter adapter;
    String parse_json = null, method;
    ArrayList<Category> jsondata;
    String BufferNoData = "Nodata";
    DatabaseSelectServices databaseSelectServices = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Arkhip_font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu For Client");
        setSupportActionBar(toolbar);


        //View
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark
                );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Common.isConnectedToInternet(getBaseContext())) {
                    loadMenu();
                }
                else {
                    Toast.makeText(getBaseContext(), "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (Common.isConnectedToInternet(getBaseContext())) {
                    loadMenu();
                }
                else {
                    Toast.makeText(getBaseContext(), "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


        //Init Firebase
        //database = FirebaseDatabase.getInstance();
        //category = database.getReference("Category");

        Paper.init(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(Home.this, Cart.class);
                startActivity(cartIntent);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set Name for User
        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentUser.getName());

        //Load Menu
        recyler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recyler_menu.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(this);
        //recyler_menu.setLayoutManager(layoutManager);
        recyler_menu.setLayoutManager(new GridLayoutManager(this,2));

        updateToken(FirebaseInstanceId.getInstance().getToken());

    }

    private void updateToken(String token) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference tokens = db.getReference("Tokens");
        Token data = new Token(token,false);//false because this token send from client app
        tokens.child(Common.currentUser.getPhone()).setValue(data);
    }

    private void loadMenu() {

        if (databaseSelectServices == null) {
            try {
                method = "Category";
                DatabaseSelectServices databaseSelectServices = new DatabaseSelectServices();
                parse_json = databaseSelectServices.execute(method).get();
                //parse_json = databaseSelectServices.parse_json;
                Log.i("parse_json", parse_json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(parse_json.matches(BufferNoData)){
                Toast.makeText(Home.this, "No Category!", Toast.LENGTH_SHORT).show();
            }
            else {
                ParseJsonCategoryData parseJsonCategoryData= new ParseJsonCategoryData(parse_json);
                jsondata = parseJsonCategoryData.getCategoryData();

                if (jsondata.size() > 0) {
                    adapter = new MenuAdapter(jsondata,this);
                } else {
                    Toast.makeText(Home.this, "Load Category Failure!", Toast.LENGTH_SHORT).show();
                }

            }
        }
        adapter.notifyDataSetChanged();
        recyler_menu.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            loadMenu();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       int id = item.getItemId();

        if (id == R.id.nav_menu) {
            // Handle the camera action
        } else if (id == R.id.nav_cart) {
            Intent cartIntent = new Intent(Home.this, Cart.class);
            startActivity(cartIntent);

        } else if (id == R.id.nav_orders) {
            Intent OrderIntent = new Intent(Home.this, OrderStatus.class);
            startActivity(OrderIntent);
        } else if (id == R.id.nav_log_out) {
            Paper.book().destroy();
            Intent signIn = new Intent(Home.this,SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signIn);
        }
        else if(id== R.id.nav_change_pwd){
            Paper.book().destroy();
            Paper.init(this);
            showChangePasswordDialog();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
        alertDialog.setTitle("CHANGE PASSWORD");
        alertDialog.setMessage("Please fill all information");

        LayoutInflater inflater = LayoutInflater.from(this);
        View layout_pwd = inflater.inflate(R.layout.change_password_layout, null);

        final MaterialEditText edtPassword = (MaterialEditText)layout_pwd.findViewById(R.id.edtPassword);
        final MaterialEditText edtNewPassword = (MaterialEditText)layout_pwd.findViewById(R.id.edtNewPassword);
        final MaterialEditText edtRepeatPassword = (MaterialEditText)layout_pwd.findViewById(R.id.edtRepeatPassword);
        alertDialog.setIcon(R.drawable.ic_security_black_24dp);
        alertDialog.setView(layout_pwd);

        //Button
        alertDialog.setPositiveButton("CHANGE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //For use SpotsDialog , please use AlertDialog from android.app, not from v7 like above AlertDialog
                final android.app.AlertDialog waitingDialog = new SpotsDialog(Home.this);

                if(edtPassword.getText().toString().equals(Common.currentUser.getPassword()))
                {
                    if(edtNewPassword.getText().toString().matches(edtRepeatPassword.getText().toString()))
                    {
                        waitingDialog.dismiss();
                        method= "updatepwd";

                        try {
                            phone= Common.currentUser.getPhone();
                            password= edtNewPassword.getText().toString();
                            DatabaseInsert DBinsert = new DatabaseInsert(Home.this);
                            String result = DBinsert.execute(method,phone, password).get();

                            Common.currentUser.setPassword(edtNewPassword.getText().toString());
                            //Check remember
                            String user = Paper.book().read(Common.USER_KEY);
                            String pwd = Paper.book().read(Common.PWD_KEY);
                            if(user != null && pwd != null)
                            {
                                if(!user.isEmpty() && !pwd.isEmpty()) {
                                    Paper.book().write(Common.USER_KEY, user);
                                    Paper.book().write(Common.PWD_KEY, edtNewPassword.getText().toString());

                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        waitingDialog.dismiss();
                        Toast.makeText(Home.this, "New Password and Old Password must be the same", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    waitingDialog.dismiss();
                    Toast.makeText(Home.this, "Wrong Old Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }
}