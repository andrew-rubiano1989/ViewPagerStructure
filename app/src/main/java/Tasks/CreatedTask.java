package Tasks;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.R;

/**
 * Created by Drew on 3/27/14.
 */
public class CreatedTask extends Task {

    View rootView;
    int position;
    String taskName;
    TextView tv_position, tv_taskName;

    public CreatedTask(int position, String taskName)
    {
        this.position = position;
        this.taskName = taskName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.created_task_layout, container, false);

        initializeTask();

        return rootView;
    }

    private void initializeTask() {

        tv_position = (TextView) rootView.findViewById(R.id.placement);
        tv_taskName = (TextView) rootView.findViewById(R.id.task_name);

        tv_position.setText(new Integer(position).toString());
        tv_taskName.setText(taskName.toString());
    }

    public String getTaskName()
    {
        return taskName;
    }
}
