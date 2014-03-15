package GoalFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.R;

import java.util.Random;

/**
 * Created by Drew on 3/14/14.
 */
public class Goal extends Fragment {

    View rootView;
    LinearLayout container;
    int minHeight;

    public Goal(int size)
    {
        minHeight = size;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        switch(minHeight){
            case 0:
                rootView = inflater.inflate(R.layout.goal_display_layout1, container, false);
                break;
            case 1:
                rootView = inflater.inflate(R.layout.goal_display_layout2, container, false);
                break;
            case 2:
                rootView = inflater.inflate(R.layout.goal_display_layout3, container, false);
                break;
        }

        return rootView;
    }
}
