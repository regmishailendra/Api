package majorproject.gpsgsm.com.api;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import majorproject.gpsgsm.com.api.fragments.StoryFragment;

public class DetailsActivity extends AppCompatActivity {
    String title, publish, content, user;
    int id;
    private TextView titleTextView, contentTextView, userTextView, publishTextView, idTextView;

    private LinearLayout linearLayout, fragmentLinear;
Bitmap imageBitmap;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getSupportActionBar().isShowing()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Bundle bundle = getIntent().getExtras();

        title = bundle.getString("title");
        id = bundle.getInt("id");
        publish = bundle.getString("publish");
        content = bundle.getString("content");
        user = bundle.getString("user");
        imageBitmap=(Bitmap)getIntent().getExtras().get("image");
        imageView=(ImageView)findViewById(R.id.imageView);


        fragmentLinear = (LinearLayout) findViewById(R.id.fragmentLinear);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        contentTextView = (TextView) findViewById(R.id.contentTextView);
        userTextView = (TextView) findViewById(R.id.userTextView);
        publishTextView = (TextView) findViewById(R.id.publishTextView);
        idTextView = (TextView) findViewById(R.id.idTextView);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);


        titleTextView.setText(title);
        idTextView.setText(id + "");
        publishTextView.setText(publish);
        contentTextView.setText(content);
        userTextView.setText(user + "");
        imageView.setImageBitmap(imageBitmap);




        Fragment storyFragment = new StoryFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLinear, storyFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:

                this.supportFinishAfterTransition();
                return true;


        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.supportFinishAfterTransition();
        super.onBackPressed();
    }
}
