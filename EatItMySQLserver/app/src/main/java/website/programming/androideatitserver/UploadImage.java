package website.programming.androideatitserver;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by cokel on 3/30/2018.
 */

public class UploadImage extends AsyncTask<String,Void,String> {

    Bitmap image;
    String name;

    public UploadImage() {
    }

    @Override
    protected void onPostExecute(String result) {
        //return result;
    }

    @Override
    protected String doInBackground(String... params) {


        try {
            String method = params[0];
            String name = params[1];
            String SERVER_URL = params[2];
            String encodedImage = params[3];

            URL url= new URL(SERVER_URL);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os= httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

            String data= URLEncoder.encode("method", "UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                    URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                    URLEncoder.encode("image","UTF-8")+"="+URLEncoder.encode(encodedImage,"UTF-8");

            //   Log.i("url:", data);

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();


            os.close();
            InputStream IS= httpURLConnection.getInputStream();
            IS.close();
            return "Image Successfully Upload";
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
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
