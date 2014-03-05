package com.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Drew on 3/4/14.
 */
public class CustomActionBar extends Fragment{

    TextView Title;
    ImageButton homeIcon, searchIcon;
    EditText searchBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.actionbar, container, false);

        Title = (TextView) rootView.findViewById(R.id.Title);
        homeIcon = (ImageButton) rootView.findViewById(R.id.homeIcon);
        searchIcon = (ImageButton) rootView.findViewById(R.id.searchIcon);
        searchBar = (EditText) rootView.findViewById(R.id.searchBar);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBar.getVisibility() == View.VISIBLE)
                {
                    Title.setVisibility(View.VISIBLE);
                    homeIcon.setVisibility(View.VISIBLE);
                    searchBar.setVisibility(View.GONE);
                }
                else{
                    Title.setVisibility(View.GONE);
                    homeIcon.setVisibility(View.GONE);
                    searchBar.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootView;
    }

    public void setTitle(String title)
    {
        Title.setText(title);
    }
}
