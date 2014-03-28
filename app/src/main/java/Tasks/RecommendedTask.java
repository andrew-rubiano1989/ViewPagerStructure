package Tasks;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.R;

import java.util.Random;

/**
 * Created by Drew on 3/27/14.
 */
public class RecommendedTask extends Task {
    View rootView;
    String taskName;
    TextView tv_taskName, tv_peopleWhoUsedTask;
    Random random;

    public RecommendedTask(String taskName)
    {
        this.taskName = taskName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recommended_task_layout, container, false);

        initializeTask();

        return rootView;
    }

    private void initializeTask() {
        random = new Random();

        tv_taskName = (TextView) rootView.findViewById(R.id.task_name);
        tv_peopleWhoUsedTask = (TextView) rootView.findViewById(R.id.peopleWhoUsedTask);

        tv_taskName.setText(taskName.toString());
        tv_peopleWhoUsedTask.setText(new Integer(random.nextInt(1000000)).toString());
    }

    public String getTaskName()
    {
        return taskName;
    }

}
