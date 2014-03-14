package UI_Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.R;

import java.util.Random;

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

        addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
        addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
        addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
        addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
        addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
        addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
        addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
        addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);

        sortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftGrid.removeAllViews();
                rightGrid.removeAllViews();

                addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
                addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
                addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
                addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
                addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
                addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
                addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
                addGoal(createBlock(r.nextInt(3)), leftGrid, rightGrid);
            }
        });

        return rootView;
    }

    private void addGoal(LinearLayout block, LinearLayout left, LinearLayout right) {
        if(leftFirst)
        {
            left.addView(block);
            leftFirst = false;
        }
        else
        {
            right.addView(block);
            leftFirst = true;
        }
    }

    private LinearLayout createBlock(int size)
    {
        while(size == 0)
            size = r.nextInt(3);

        DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
        LinearLayout lb = new LinearLayout(getActivity().getApplicationContext());
        lb.setOrientation(LinearLayout.VERTICAL);
        lb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Math.round(metrics.heightPixels/5)*size));
        lb.setBackgroundColor(Color.BLACK);

        TextView lt = new TextView(getActivity().getApplicationContext());
        lt.setText("block of size: " + size);

        lb.addView(lt);

        return lb;
    }
}
