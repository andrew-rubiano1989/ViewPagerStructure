package UI_Fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.R;

import java.util.ArrayList;
import Tasks.CreatedTask;
import Tasks.RecommendedTask;

/**
 * Created by Drew on 3/27/14.
 */
public class CreateTasks extends Fragment {

    View rootView;
    ArrayList<RecommendedTask> RecommendedTaskList;
    ArrayList<CreatedTask> CreatedTaskList;
    CreatedTask createdTask;
    ImageView addButton;
    EditText et_addTask;
    InputMethodManager imm;
    Activity main;
    LinearLayout sorting;
    Button recommended, created;
    boolean b_recommended, b_created;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_tasks_layout, container, false);

        initalizeView();

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initalizeView() {

        sorting = (LinearLayout) rootView.findViewById(R.id.sorting_menu);
        b_recommended = true;
        b_created = true;

        main = getActivity();

        imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);

        addButton = (ImageView) getActivity().findViewById(R.id.searchIcon);

        et_addTask = (EditText) getActivity().findViewById(R.id.searchBar);
        et_addTask.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et_addTask.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (v.getText() != "" && actionId == EditorInfo.IME_ACTION_DONE) {
                    imm.hideSoftInputFromWindow(et_addTask.getWindowToken(), 0);
                    addCreatedTask(v.getText().toString());
                    et_addTask.setText("");
                    et_addTask.clearFocus();
                    et_addTask.setVisibility(View.GONE);

                }
                return true;
            }
        });

        RecommendedTaskList = new ArrayList<RecommendedTask>();
        CreatedTaskList = new ArrayList<CreatedTask>();
        addRecommendedTasks();

        for(RecommendedTask task: RecommendedTaskList)
            getChildFragmentManager().beginTransaction().add(R.id.tasks_window, task, task.getTaskName()).setCustomAnimations(R.anim.enter_comment, R.anim.leave_comment).commit();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addCreatedTask(String text) {
        createdTask = new CreatedTask(1, text);
        CreatedTaskList.add(createdTask);
        getChildFragmentManager().beginTransaction().add(R.id.tasks_window, createdTask, createdTask.getTaskName()).setCustomAnimations(R.anim.enter_comment, R.anim.leave_comment).commit();

        if(sorting.getVisibility() == View.GONE)
        {
            sorting.setVisibility(View.VISIBLE);
            recommended = (Button) rootView.findViewById(R.id.recommended_button);
            recommended.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b_recommended == true)
                    {
                        hideRecommendedTasks();
                        b_recommended = false;
                    }
                    else
                    {
                        showRecommendedTasks();
                        b_recommended = true;
                    }
                }
            });
            created = (Button) rootView.findViewById(R.id.created_button);
            created.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(b_created == true)
                    {
                        hideCreatededTasks();
                        b_created = false;
                    }
                    else
                    {
                        showCreatedTasks();
                        b_created = true;
                    }
                }
            });
        }
    }

    private void addRecommendedTasks() {
        RecommendedTask rTask;

        for(int i = 1; i < 10; i++)
        {
            rTask = new RecommendedTask("Recommended Task " + i);
            RecommendedTaskList.add(rTask);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void hideRecommendedTasks()
    {
        for(int i = 0; i < RecommendedTaskList.size(); i++)
            getChildFragmentManager().beginTransaction().hide(getChildFragmentManager().findFragmentByTag(RecommendedTaskList.get(i).getTaskName())).commit();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showRecommendedTasks()
    {
        for(int i = 0; i < RecommendedTaskList.size(); i++)
            getChildFragmentManager().beginTransaction().show(getChildFragmentManager().findFragmentByTag(RecommendedTaskList.get(i).getTaskName())).commit();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void hideCreatededTasks()
    {
        for(int i = 0; i < CreatedTaskList.size(); i++)
            getChildFragmentManager().beginTransaction().hide(getChildFragmentManager().findFragmentByTag(CreatedTaskList.get(i).getTaskName())).commit();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showCreatedTasks()
    {
        for(int i = 0; i < CreatedTaskList.size(); i++)
            getChildFragmentManager().beginTransaction().show(getChildFragmentManager().findFragmentByTag(CreatedTaskList.get(i).getTaskName())).commit();
    }
}
