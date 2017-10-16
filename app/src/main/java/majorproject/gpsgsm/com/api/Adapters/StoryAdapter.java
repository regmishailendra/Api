package majorproject.gpsgsm.com.api.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import majorproject.gpsgsm.com.api.DetailsActivity;
import majorproject.gpsgsm.com.api.R;
import majorproject.gpsgsm.com.api.models.Story;

/**
 * Created by Shailendra on 10/13/2017.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {

    public List<Story> storyList;
    public Context context;
 Bitmap threadImage;


    public StoryAdapter(List<Story> storyList, Context context) {
        this.storyList = storyList;
        this.context = context;
        Log.v("titlehuhu", storyList.size() + "whats the value");


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, content, user, publish, id;
        private LinearLayout linearLayout;
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);


            title = (TextView) itemView.findViewById(R.id.titleTextView);
            content = (TextView) itemView.findViewById(R.id.contentTextView);
            user = (TextView) itemView.findViewById(R.id.userTextView);
            publish = (TextView) itemView.findViewById(R.id.publishTextView);
            id = (TextView) itemView.findViewById(R.id.idTextView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

            image = (ImageView) itemView.findViewById(R.id.image);


        }


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories_custom, parent, false);


        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        final Thread imageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.100.6:8000/media/seen.jpg");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.connect();

                    InputStream inputStream = connection.getInputStream();


               threadImage = BitmapFactory.decodeStream(inputStream);


                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder.image.setImageBitmap(threadImage);
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        imageThread.start();


        final Story story = storyList.get(position);


        holder.title.setText(story.getTitle());
        holder.content.setText(story.getContent());
        holder.user.setText(story.getUser() + "");
        holder.publish.setText(story.getPublish());
//        holder.id.setText(story.getId());
        //get image url too


        holder.linearLayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", story.getTitle());
                        bundle.putInt("id", 11);    //see here
                        bundle.putString("publish", story.getPublish());
                        bundle.putString("content", story.getContent());
                        bundle.putString("user", story.getUser());
                        intent.putExtra("image",threadImage);

                        intent.putExtras(bundle);

                        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, holder.image, "imageTransition");


                        context.startActivity(intent, optionsCompat.toBundle());


                    }
                }
        );


    }


    @Override
    public int getItemCount() {
        return storyList.size();

    }
}
