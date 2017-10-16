package majorproject.gpsgsm.com.api;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import majorproject.gpsgsm.com.api.Background.BackgroundCreate;

import static majorproject.gpsgsm.com.api.Background.LoginRegisterBackground.token;

public class CreateStoryActivity extends AppCompatActivity {

    boolean isOpen;
    FloatingActionButton mainFab;
    FloatingActionButton cameraFab;
    FloatingActionButton gallaryfab;
    Animation fabc, faba;
    ImageView image;
    int CAMERA_REQUEST_CODE = 7001;
    int GALLARY_REQUEST_CODE = 7002;
    String imageLocation;
    TextView altText;

    EditText titleEditText,contentEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        isOpen = false;
        altText = (TextView) findViewById(R.id.alttext);
        mainFab = (FloatingActionButton) findViewById(R.id.mainFAB);
        cameraFab = (FloatingActionButton) findViewById(R.id.cameraFab);
        gallaryfab = (FloatingActionButton) findViewById(R.id.gallaryFab);
        fabc = AnimationUtils.loadAnimation(CreateStoryActivity.this, R.anim.clockwise_rotate);
        faba = AnimationUtils.loadAnimation(CreateStoryActivity.this, R.anim.anticlockwise_roate);
        image = (ImageView) findViewById(R.id.image);

        titleEditText=(EditText)findViewById(R.id.titleEditText);
        contentEditText=(EditText)findViewById(R.id.contentEditText);












        mainFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (isOpen) {

                            closeFab();

                        } else {
                            openFab();
                        }
                    }
                }
        );


        cameraFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CreateStoryActivity.this, "Camera Fab", Toast.LENGTH_LONG).show();
                        closeFab();


                        //camera image take


                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        Uri imageUri = null;
                        try {
                            imageUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName(), createTempFile());


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);


                        startActivityForResult(intent, CAMERA_REQUEST_CODE);


                    }
                }
        );


        gallaryfab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CreateStoryActivity.this, "Gallary Fab", Toast.LENGTH_LONG).show();
                        closeFab();

                        Intent intent = new Intent(Intent.ACTION_PICK).setType("image/*");

                        startActivityForResult(intent, GALLARY_REQUEST_CODE);


                    }
                }
        );


    }

    private File createTempFile() throws IOException {

        String fileName = "image_" + new SimpleDateFormat("hhMMss").format(new Date());

        File dictory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(fileName, ".jpeg", dictory);

        imageLocation = imageFile.getAbsolutePath();


        //should learn exif interface and all.. now kam chalau for rotate aeu sajilo


        return imageFile;
    }


    private void openFab() {
        cameraFab.setVisibility(View.VISIBLE);
        gallaryfab.setVisibility(View.VISIBLE);
        mainFab.startAnimation(fabc);
        isOpen = true;

    }

    private void closeFab() {
        cameraFab.setVisibility(View.INVISIBLE);
        gallaryfab.setVisibility(View.INVISIBLE);
        isOpen = false;
        mainFab.startAnimation(faba);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            //Bundle bundle= data.getExtras();
            //Bitmap imageBitmap= (Bitmap) bundle.get("data");
//            image.setImageBitmap(BitmapFactory.decodeFile(imageLocation));


            int imageViewWidth = image.getWidth();
            int imageViewHeight = image.getHeight();


            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imageLocation, bmOptions);
            int cameraWidth = bmOptions.outWidth;
            int cameraHeightt = bmOptions.outHeight;
            int scaleFactor = Math.min(cameraWidth / imageViewWidth, cameraHeightt / imageViewHeight);
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inJustDecodeBounds = false;
            image.setImageBitmap(BitmapFactory.decodeFile(imageLocation, bmOptions));

            Log.v("pathman", "and previous is " + imageLocation);

            altText.setVisibility(View.GONE);

        } else if (requestCode == GALLARY_REQUEST_CODE && resultCode == RESULT_OK) {


            Uri selectedImage = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImage);


               int imageWidth=image.getWidth();
               int imageHeight= image.getHeight();
                BitmapFactory.Options bmOptions= new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds=true;
                BitmapFactory.decodeStream(inputStream,null,bmOptions);
                int cameraWidth=  bmOptions.outWidth;
                int cameraHeight= bmOptions.outHeight;
                int scaleFactor=Math.min(cameraWidth/imageWidth,cameraHeight/imageHeight);
                bmOptions.inSampleSize=1;
                bmOptions.inJustDecodeBounds=false;

                inputStream = getContentResolver().openInputStream(selectedImage);                  //may be the first input stream was used for bmoptions
                image.setImageBitmap(BitmapFactory.decodeStream(inputStream,null,bmOptions));






            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


///external/images/media/56649

            Log.v("pathman", selectedImage.getPath());


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.post_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.post:

                Toast.makeText(CreateStoryActivity.this, "Posted!", Toast.LENGTH_LONG).show();

                String title = titleEditText.getText().toString();
                String content= contentEditText.getText().toString();
                Log.v("notokenyar",token+"what is wrong");

                new BackgroundCreate().execute(title,content);

                this.finish();


                break;

            case android.R.id.home:
                this.finish();
                break;


        }


        return true;
    }


    @Override
    public void onBackPressed() {
        Log.v("fab", isOpen + "");

        if (isOpen) {
            closeFab();
        } else {
            super.onBackPressed();
        }
    }
}
