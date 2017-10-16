package majorproject.gpsgsm.com.api.Background;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import majorproject.gpsgsm.com.api.MainActivity;

/**
 * Created by Shailendra on 10/16/2017.
 */

public class LoginRegisterBackground extends AsyncTask<String,Void,String> {

public static String token="";
    Context context;


    public LoginRegisterBackground(Context context) {

        this.context = context;

    }

    @Override
    protected String doInBackground(String... params) {

        String username = params[0];
        String password = params[1];


        try {
            URL url = new URL("http://ideadeu.herokuapp.com/api/auth/token");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //   urlConnection.setRequestProperty ("Authorization",  "JWT eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMSwidXNlcm5hbWUiOiJtZXNzaSIsImV4cCI6MTUwODE0NDI5NSwiZW1haWwiOiJtZXNzaUByb25hbGRvLmNvbSJ9.b1C6JuUK7vKrJDwHte2X3d-YTje5eCB5qkDqvEWpDEc");

            urlConnection.connect();
            String postingData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                    URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");


            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            writer.write(postingData);

            writer.flush();


            String error = "";


            //from testing
            try {
                error= urlConnection.getErrorStream().toString();
                String line,result="";
                BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                while((line=reader.readLine())!=null) {

                    result += line;

                     Log.v("errorgotwas", "so this is the error " + error);

                }

                Log.v("errorgotwasoho", "so this is the error " + result);
            }catch (Exception e)
            {
                Log.v("errorgotwasException","exception"+e.getMessage());
                e.printStackTrace();




            }

//upto testing

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


            String line,result="";
            while ((line = reader.readLine()) != null) {

                result += line;


            }


            JSONObject jsonObject=new JSONObject(result);
            token= "JWT "+jsonObject.getString("token");
            Log.v("error&Finaltokenis ",token+"");

            Log.v("errorgotTokenget", result);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Intent intent= new Intent(context, MainActivity.class);
        context.startActivity(intent);




    }
}