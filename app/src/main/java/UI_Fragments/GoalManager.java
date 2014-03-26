package UI_Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.R;

import java.util.Random;

import GoalFragments.Goal;

/**
 * Created by Drew on 3/13/14.
 */
public class GoalManager extends Fragment {
    View rootView;
    LinearLayout leftGrid, rightGrid;
    boolean leftFirst;
    Button sortBy;
    Random r;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.goal_manager_layout, container, false);
        leftGrid = (LinearLayout) rootView.findViewById(R.id.goalsLeftGrid);
        rightGrid = (LinearLayout) rootView.findViewById(R.id.goalsRightGrid);
        sortBy = (Button) rootView.findViewById(R.id.sortByButton);
        leftFirst = true;

        r = new Random();

        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);
        addGoal(leftGrid, rightGrid);

        sortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftGrid.removeAllViews();
                rightGrid.removeAllViews();

                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
                addGoal(leftGrid, rightGrid);
            }
        });

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addGoal(LinearLayout left, LinearLayout right) {
        if(leftFirst)
        {
            getChildFragmentManager().beginTransaction().add(R.id.goalsLeftGrid, new Goal(r.nextInt(3))).commit();
            leftFirst = false;
        }
        else
        {
            getChildFragmentManager().beginTransaction().add(R.id.goalsRightGrid, new Goal(r.nextInt(3))).commit();
            leftFirst = true;
        }
    }
}
