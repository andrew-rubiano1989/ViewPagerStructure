package UI_Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.R;

/**
 * Created by Drew on 3/13/14.
 */
public class GoalManager extends Fragment {
    View rootView;
    LinearLayout grid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.goal_manager_layout, container, false);
        grid = (LinearLayout) rootView.findViewById(R.id.goalsGrid);
        grid.addView(createLargeBlock());
        grid.addView(createTwoMediumBlockRow());
        grid.addView(createOneMediumTwoSmallBlockRow());
        grid.addView(createThreeSmallBlockRow());

        return rootView;
    }

    private LinearLayout createLargeBlock()
    {
        DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
        LinearLayout lb = new LinearLayout(getActivity().getApplicationContext());
        lb.setOrientation(LinearLayout.VERTICAL);
        lb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, Math.round(metrics.heightPixels/3)));
        lb.setBackgroundColor(Color.BLACK);

        TextView lt = new TextView(getActivity().getApplicationContext());
        lt.setText("large block");

        lb.addView(lt);

        return lb;
    }
    private LinearLayout createMediumBlock(int weight)
    {
        DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
        LinearLayout lb = new LinearLayout(getActivity().getApplicationContext());
        lb.setOrientation(LinearLayout.VERTICAL);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)Math.round(metrics.widthPixels*.64), Math.round(metrics.heightPixels/3));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.FILL_PARENT);
        params.weight = weight;
        lb.setLayoutParams(params);
        lb.setBackgroundColor(Color.BLUE);

        TextView lt = new TextView(getActivity().getApplicationContext());
        lt.setText("medium block");

        lb.addView(lt);

        return lb;
    }
    private LinearLayout createSmallBlock(int weight)
    {
        DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
        LinearLayout lb = new LinearLayout(getActivity().getApplicationContext());
        lb.setOrientation(LinearLayout.VERTICAL);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)Math.round(metrics.widthPixels*.335), Math.round(metrics.heightPixels/3)));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.FILL_PARENT);
        params.weight = weight;
        lb.setLayoutParams(params);
        lb.setBackgroundColor(Color.GREEN);

        TextView lt = new TextView(getActivity().getApplicationContext());
        lt.setText("small block");

        lb.addView(lt);

        return lb;
    }

    private LinearLayout createTwoMediumBlockRow()
    {
        DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
        LinearLayout lb = new LinearLayout(getActivity().getApplicationContext());
        lb.setOrientation(LinearLayout.HORIZONTAL);
        lb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, Math.round(metrics.heightPixels/3)));

        lb.addView(createMediumBlock(1));
        lb.addView(createMediumBlock(1));

        return lb;
    }

    private LinearLayout createOneMediumTwoSmallBlockRow()
    {
        DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
        LinearLayout lb = new LinearLayout(getActivity().getApplicationContext());
        lb.setOrientation(LinearLayout.HORIZONTAL);
        lb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, Math.round(metrics.heightPixels/3)));
        lb.setWeightSum(7);

        lb.addView(createMediumBlock(3));
        lb.addView(createSmallBlock(2));
        lb.addView(createSmallBlock(2));

        return lb;
    }

    private LinearLayout createThreeSmallBlockRow()
    {
        DisplayMetrics metrics = getActivity().getApplicationContext().getResources().getDisplayMetrics();
        LinearLayout lb = new LinearLayout(getActivity().getApplicationContext());
        lb.setOrientation(LinearLayout.HORIZONTAL);
        lb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, Math.round(metrics.heightPixels/3)));

        lb.addView(createSmallBlock(1));
        lb.addView(createSmallBlock(1));
        lb.addView(createSmallBlock(1));

        return lb;
    }
}
