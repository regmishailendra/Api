package majorproject.gpsgsm.com.api;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import majorproject.gpsgsm.com.api.Adapters.StoryAdapter;
import majorproject.gpsgsm.com.api.Background.BackgroundCreate;
import majorproject.gpsgsm.com.api.Background.BackgroundTask;
import majorproject.gpsgsm.com.api.models.Story;

import static majorproject.gpsgsm.com.api.Background.LoginRegisterBackground.token;

public class MainActivity extends AppCompatActivity
      //  implements BackgroundTask.MyCallBack
{
    TextView createLayout;
    RecyclerView recyclerView;
    ArrayList<Story> storyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        createLayout = (TextView) findViewById(R.id.createActivity);


        storyList = new ArrayList<Story>();


        BackgroundTask backgroundTask = new BackgroundTask(this);


        backgroundTask.execute("http://ideadeu.herokuapp.com/api/?format=json");



//        StoryAdapter storyAdapter=new StoryAdapter(storyList, MainActivity.this);
//        recyclerView.setAdapter(storyAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        createLayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, CreateStoryActivity.class);
                        startActivity(intent);

                    }
                }
        );


    }

//    @Override
//    public void updateRecyclerView(List<Story> storyList) {
//        StoryAdapter storyAdapter=new StoryAdapter(storyList, MainActivity.this);
//        recyclerView.setAdapter(storyAdapter);
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    this.finish();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout:

      token="";
                this.finish();
                startActivity(new Intent(MainActivity.this,LoginRegisterActivity.class));

                this.finish();


                break;

            case android.R.id.home:
                this.finish();
                break;


        }


        return true;
    }
















}