package UI_Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;

/**
 * Created by Drew on 3/27/14.
 */
public class CreateGoalSection extends Fragment {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_goal_section_layout, container, false);

        initializeFragment();

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initializeFragment() {
        getChildFragmentManager().beginTransaction().add(R.id.createGoalContainer, new CreateGoal(), "create goal").commit();
    }
}
