package website.programming.androideatit;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import website.programming.androideatit.Common.Common;
import website.programming.androideatit.Model.Category;
import website.programming.androideatit.Model.Food;

/**
 * Created by ashu on 16-Apr-16.
 */
public class JsonToSql extends AsyncTask<String,Void,String> {

    ArrayList<Food> datalist;
    Context ctx;
    public JsonToSql(ArrayList<Food> datalist, Context ctx){
        this.ctx=ctx;
        this.datalist=datalist;
    }


    protected String doInBackground(String... params) {

        String reg_url= Common.SERVER_URL + "Migrate.php";

        for (int i=0;i<datalist.size();i++) {
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(datalist.get(i).getName(), "UTF-8") + "&" +
                        URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(datalist.get(i).getImage(), "UTF-8") + "&" +
                        URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(datalist.get(i).getDescription(), "UTF-8") + "&" +
                        URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(datalist.get(i).getPrice(), "UTF-8") + "&" +
                        URLEncoder.encode("discount", "UTF-8") + "=" + URLEncoder.encode(datalist.get(i).getDiscount(), "UTF-8") + "&" +
                        URLEncoder.encode("menuid", "UTF-8") + "=" + URLEncoder.encode(datalist.get(i).getMenuId(), "UTF-8");

                Log.i("url:", data);

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "Migrate Successfully.";
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}