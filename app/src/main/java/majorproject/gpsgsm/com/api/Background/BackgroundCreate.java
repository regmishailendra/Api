package majorproject.gpsgsm.com.api.Background;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static majorproject.gpsgsm.com.api.Background.LoginRegisterBackground.token;

/**
 * Created by Shailendra on 10/14/2017.
 */

public class BackgroundCreate extends AsyncTask<String, Void, String> {
String title,content;

    @Override
    protected String doInBackground(String... params) {
        Log.v("notokenyar",token+"");

title=params[0];
        content=params[1];
        try {
            URL url = new URL("http://ideadeu.herokuapp.com/api/create/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty ("Authorization",token);

            Log.v("notokenyar",token+"what is wrong");

urlConnection.connect();



            String postingData= URLEncoder.encode("title","UTF-8")+"="+URLEncoder.encode(title,"UTF-8")+"&"+
                 URLEncoder.encode("content","UTF-8")+"="+URLEncoder.encode(content,"UTF-8")+"&"+
                   URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode("1","UTF-8");


            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            writer.write(postingData);

            writer.flush();


            String error="";


            //from testing
try {
   error= urlConnection.getErrorStream().toString();
    String line,result="";
    BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
    while((line=reader.readLine())!=null) {

        result += line;

       // Log.v("errorgotwas", "so this is the error " + error);

    }

    Log.v("errorgotwasoho", "so this is the error " + result);
}catch (Exception e)
{
    Log.v("errorgotwasException","exception"+e.getMessage());
            e.printStackTrace();

    Log.v("notokenyar",token+"");



}

//upto testing
            BufferedReader reader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));






            String line,result="";
            while((line=reader.readLine())!=null) {

                result+=line;


            }

Log.v("errorstream",result+"welcome");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


}
