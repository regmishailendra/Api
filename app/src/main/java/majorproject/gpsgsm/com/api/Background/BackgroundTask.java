package majorproject.gpsgsm.com.api.Background;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import majorproject.gpsgsm.com.api.Adapters.StoryAdapter;
import majorproject.gpsgsm.com.api.MainActivity;
import majorproject.gpsgsm.com.api.R;
import majorproject.gpsgsm.com.api.models.Story;

import static majorproject.gpsgsm.com.api.Background.LoginRegisterBackground.token;

/**
 * Created by Shailendra on 10/14/2017.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context context;


    public BackgroundTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    HttpURLConnection urlConnection;
    InputStream inputStream;

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];


        try {
            URL url = new URL(urlString);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Authorization", token);



            urlConnection.connect();


            //from testing
            try {
                String line, result = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));

                while ((line = reader.readLine()) != null) {

                    result += line;

                    // Log.v("errorgotwas", "so this is the error " + error);

                }

                Log.v("errorgotwasoho", "so this is the error " + result);
            } catch (Exception e) {
               // token="";

                Log.v("errorgotwasException", "exception" + e.getMessage());
                e.printStackTrace();


            }

//upto testing


            inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = "";
            String result = "";
            while ((line = reader.readLine()) != null) {

                result += line;


            }

            Log.v("resultman", result + "");


            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        List<Story> storyList = new ArrayList<>();
        String title = "";
        String content = "";
        String publish = "";
        int user;

        if (s == null) {
            return;
        }


        try {
            JSONArray jsonArray = new JSONArray(s);


            for (int i = 0; i < jsonArray.length(); i++) {

                Story story = new Story();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                title = jsonObject.get("title").toString();
                content = jsonObject.get("content").toString();
                //   user = Integer.parseInt(jsonObject.get("user").toString());
                user = 100;
                publish = jsonObject.get("publish").toString();


                story.setTitle(title);
                story.setContent(content);
                story.setUser("user's name");
                story.setPublish(publish);
                story.setId(user);
                storyList.add(story);

            }


            RecyclerView recyclerView = (RecyclerView) ((Activity) context).findViewById(R.id.recyclerView);
            StoryAdapter storyAdapter = new StoryAdapter(storyList, context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(storyAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }


//        MyCallBack mainActivity = new MainActivity();
//        mainActivity.updateRecyclerView(storyList);

    }

//    public interface MyCallBack {
//
//        void updateRecyclerView(List<Story> storyList);


}



