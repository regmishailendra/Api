package majorproject.gpsgsm.com.api.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import majorproject.gpsgsm.com.api.DetailsActivity;
import majorproject.gpsgsm.com.api.R;


public class StoryFragment extends Fragment {

TextView contentTextView,titleTextView;

    LinearLayout fragmentClick;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_story,container,false);

        contentTextView=(TextView)getActivity().findViewById(R.id.contentTextView);
        titleTextView=(TextView)getActivity().findViewById(R.id.titleTextView);
        fragmentClick=(LinearLayout)v.findViewById(R.id.fragmentClick);


        fragmentClick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), DetailsActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("title","This is passed titleN");
                        bundle.putInt("id",11);
                        bundle.putString("publish","2016-15-06N");
                        bundle.putString("content","Lets talk about the content that exists here and how can they be related in being professional work we do.This content refers to everybody working  for their dreams. The dream job that exists for every perople is the main reason they live inLets talk about the content that exists here and how can they be related in being professional work we do.This content refers to everybody working  for their dreams. The dream job that exists for every perople is the main reason they live in.Lets talk about the content that exists here and how can they be related in being professional work we do.This content refers to everybody working  for their dreams. The dream job that exists for every perople is the main reason they live in. Lets talk about the content that exists here and how can they be related in being professional work we do.This content refers to everybody working  for their dreams. The dream job that exists for every perople is the main reason they live in.N.\"");
                        bundle.putString("user","ShailendraN");
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                        getActivity().finish();

                    }
                }
        );





return v;
    }









}
