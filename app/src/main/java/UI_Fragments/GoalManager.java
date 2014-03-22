package UI_Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.R;

import java.util.Random;

import GoalFragments.Goal;

/**
 * Created by Drew on 3/13/14.
 */
public class GoalManager extends Fragment {
    View rootView;
    LinearLayout leftGrid, rightGrid;
    Button sortBy;
    Random r;
    int leftHeight, rightHeight, rand;
    boolean left;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.goal_manager_layout, container, false);
        leftGrid = (LinearLayout) rootView.findViewById(R.id.goalsLeftGrid);
        rightGrid = (LinearLayout) rootView.findViewById(R.id.goalsRightGrid);
        sortBy = (Button) rootView.findViewById(R.id.sortByButton);

        leftHeight = 0;
        rightHeight = 0;
        left = true;

        r = new Random();

        addGoal();
        addGoal();
        addGoal();
        addGoal();
        addGoal();
        addGoal();
        addGoal();

        sortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftGrid.removeAllViews();
                rightGrid.removeAllViews();
                leftHeight = 0;
                rightHeight = 0;

                addGoal();
                addGoal();
                addGoal();
                addGoal();
                addGoal();
                addGoal();
            }
        });

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addGoal() {
        new addGoal().execute(getChildFragmentManager());
    }

    private class addGoal extends AsyncTask<FragmentManager,Integer, Long>
    {
        @Override
        protected Long doInBackground(FragmentManager... fm) {
            rand = r.nextInt(3);
            if(left)
            {
                fm[0].beginTransaction().add(R.id.goalsLeftGrid, new Goal(rand)).setCustomAnimations(R.anim.enter_comment, R.anim.leave_comment).commit();
                switch(rand)
                {
                    case 0:
                        leftHeight += 150;
                        break;
                    case 1:
                        leftHeight += 200;
                        break;
                    case 2:
                        leftHeight += 250;
                        break;
                }
                left = false;
            }
            else
            {
                fm[0].beginTransaction().add(R.id.goalsRightGrid, new Goal(rand)).setCustomAnimations(R.anim.enter_comment, R.anim.leave_comment).commit();
                switch(rand)
                {
                    case 0:
                        rightHeight += 150;
                        break;
                    case 1:
                        rightHeight += 200;
                        break;
                    case 2:
                        rightHeight += 250;
                        break;
                }
                left = true;
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... progress) {
        }
        @Override
        protected void onPostExecute(Long result) {
        }
    }
}
