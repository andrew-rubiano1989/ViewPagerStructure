package UI_Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.R;

import java.util.ArrayList;

import CustomLibrary.FlowLayout;

/**
 * Created by Drew on 3/13/14.
 */
public class CreateGoal extends Fragment {

    LinearLayout goalName, goalHeader, tagsSection, create_tasks;
    ListView tagListView;
    FlowLayout tagsGrid;
    TextView tv_goalName, tv_end_date, tv_goal_type, newTag;
    ImageView iv_end_date, iv_goal_type;
    EditText et_goalName;
    View rootView;
    InputMethodManager imm;
    CalendarView calendarView;
    boolean firstTime;
    ArrayList<String> tags_unselected, tags_selected;
    ArrayList<TextView> tv_tags_list;
    ArrayAdapter<String> la_tags_selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_goal_layout, container, false);

        initalizeView();

        return rootView;
    }

    private void initalizeView() {
        firstTime = true;

        tags_unselected = new ArrayList<String>();
        tags_selected = new ArrayList<String>();

        la_tags_selected = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, tags_selected);

        imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);

        tagListView = (ListView) rootView.findViewById(R.id.tag_list);
        tagListView.setAdapter(la_tags_selected);

        tagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addTagBack(tags_selected.get(position).toString());
                tags_selected.remove(position);
                la_tags_selected.notifyDataSetChanged();

                showPublishIfNeeded();
            }
        });

        tagsGrid = (FlowLayout) rootView.findViewById(R.id.tags_container);

        goalName = (LinearLayout) rootView.findViewById(R.id.goal_name_edit_container);
        goalHeader = (LinearLayout) rootView.findViewById(R.id.goal_header);
        tagsSection = (LinearLayout) rootView.findViewById(R.id.tag_section);

        calendarView = (CalendarView) rootView.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                tv_end_date.setText(month+1 + "/" + dayOfMonth + "/" + year);
                //calendarView.setVisibility(View.GONE);
            }
        });

        tv_goalName = (TextView) rootView.findViewById(R.id.tv_goalName);
        tv_goalName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.GONE);
                goalHeader.setVisibility(View.GONE);
                tagsSection.setVisibility(View.GONE);
                goalName.setVisibility(View.VISIBLE);
                et_goalName.setFocusable(true);
                imm.showSoftInput(et_goalName, 1);

                showPublishIfNeeded();
            }
        });

        et_goalName = (EditText) rootView.findViewById(R.id.goal_name_edit_text);
        et_goalName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    imm.hideSoftInputFromWindow(et_goalName.getWindowToken(), 0);
                    tv_goalName.setText(et_goalName.getText());
                    goalName.setVisibility(View.GONE);
                    calendarView.setVisibility(View.GONE);
                    goalHeader.setVisibility(View.VISIBLE);

                    showPublishIfNeeded();

                    if(firstTime == true)
                        firstTime = false;
                    else
                        tagsSection.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        tv_end_date = (TextView) rootView.findViewById(R.id.tv_end_date);
        tv_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calendarView.getVisibility() != View.VISIBLE)
                {
                    tagsSection.setVisibility(View.GONE);
                    calendarView.setVisibility(View.VISIBLE);
                    showPublishIfNeeded();
                }
                else
                {
                    calendarView.setVisibility(View.GONE);
                    tagsSection.setVisibility(View.VISIBLE);
                    createTagsList();
                    showPublishIfNeeded();
                }
            }
        });

        tv_goal_type = (TextView) rootView.findViewById(R.id.tv_goal_type);
        tv_goal_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_goal_type.getText() == "Private Goal")
                    tv_goal_type.setText("Public Goal");
                else
                    tv_goal_type.setText("Private Goal");
            }
        });

        iv_end_date = (ImageView) rootView.findViewById(R.id.iv_end_date);
        iv_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calendarView.getVisibility() != View.VISIBLE)
                {
                    tagsSection.setVisibility(View.GONE);
                    calendarView.setVisibility(View.VISIBLE);
                    showPublishIfNeeded();
                }
                else
                {
                    calendarView.setVisibility(View.GONE);
                    tagsSection.setVisibility(View.VISIBLE);
                    createTagsList();
                    showPublishIfNeeded();
                }
            }
        });

        iv_goal_type = (ImageView) rootView.findViewById(R.id.iv_goal_type);
        iv_goal_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_goal_type.getText() == "Private Goal")
                    tv_goal_type.setText("Public Goal");
                else
                    tv_goal_type.setText("Private Goal");
            }
        });

        create_tasks = (LinearLayout) rootView.findViewById(R.id.create_tasks);
        create_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void createTagsList() {

        tags_unselected.add("hello");
        tags_unselected.add("goodbye");
        tags_unselected.add("bro");
        tags_unselected.add("dude");
        tags_unselected.add("music");
        tags_unselected.add("Beethoven");
        tags_unselected.add("Classical Music");
        tags_unselected.add("Music");
        tags_unselected.add("Art");
        tags_unselected.add("Guitar");
        tags_unselected.add("Electric Guitar");
        tags_unselected.add("Phones");
        tags_unselected.add("Android Phones");
        tags_unselected.add("EDM");
        tags_unselected.add("hello");
        tags_unselected.add("goodbye");
        tags_unselected.add("bro");
        tags_unselected.add("dude");
        tags_unselected.add("music");
        tags_unselected.add("Beethoven");
        tags_unselected.add("Classical Music");
        tags_unselected.add("Music");
        tags_unselected.add("Art");
        tags_unselected.add("Guitar");
        tags_unselected.add("Electric Guitar");
        tags_unselected.add("Phones");
        tags_unselected.add("Android Phones");
        tags_unselected.add("EDM");
        tags_unselected.add("hello");
        tags_unselected.add("goodbye");
        tags_unselected.add("bro");
        tags_unselected.add("dude");
        tags_unselected.add("music");
        tags_unselected.add("Beethoven");
        tags_unselected.add("Classical Music");
        tags_unselected.add("Music");
        tags_unselected.add("Art");
        tags_unselected.add("Guitar");
        tags_unselected.add("Electric Guitar");
        tags_unselected.add("Phones");
        tags_unselected.add("Android Phones");
        tags_unselected.add("EDM");
        tags_unselected.add("hello");
        tags_unselected.add("goodbye");
        tags_unselected.add("bro");
        tags_unselected.add("dude");
        tags_unselected.add("music");
        tags_unselected.add("Beethoven");
        tags_unselected.add("Classical Music");
        tags_unselected.add("Music");
        tags_unselected.add("Art");
        tags_unselected.add("Guitar");
        tags_unselected.add("Electric Guitar");
        tags_unselected.add("Phones");
        tags_unselected.add("Android Phones");
        tags_unselected.add("EDM");

        for(String tag: tags_unselected)
        {
            newTag = new TextView(getActivity().getApplicationContext());
            newTag.setText(tag);
            newTag.setTextColor(Color.parseColor("#0099cd"));
            newTag.setBackground(getResources().getDrawable(R.drawable.tag_background));
            newTag.setTextSize(18);
            newTag.setPadding(10,10,10,10);
            newTag.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            newTag.setOnClickListener(new textViewOnClickListener(newTag));
            tagsGrid.addView(newTag);
            showPublishIfNeeded();
        }
    }

    private void addTagBack(String tag)
    {
        newTag = new TextView(getActivity().getApplicationContext());
        newTag.setText(tag);
        newTag.setTextColor(Color.parseColor("#0099cd"));
        newTag.setBackground(getResources().getDrawable(R.drawable.tag_background));
        newTag.setTextSize(18);
        newTag.setPadding(10,10,10,10);
        newTag.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        newTag.setOnClickListener(new textViewOnClickListener(newTag));
        tagsGrid.addView(newTag);

        showPublishIfNeeded();
    }

    private class textViewOnClickListener implements View.OnClickListener
    {
        TextView textView;

        public textViewOnClickListener(TextView textView)
        {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            if (tagListView.getVisibility() == View.GONE)
                tagListView.setVisibility(View.VISIBLE);

            tags_selected.add(textView.getText().toString());
            tags_unselected.remove(tags_unselected.indexOf(textView.getText()));
            tagsGrid.removeView(v);
            la_tags_selected.notifyDataSetChanged();

            showPublishIfNeeded();
        }
    }

    private void showPublishIfNeeded()
    {
        if(tags_selected.size() > 0)
            create_tasks.setVisibility(View.VISIBLE);
        else
            create_tasks.setVisibility(View.GONE);

        if(tagsSection.getVisibility() == View.GONE || la_tags_selected.isEmpty())
            create_tasks.setVisibility(View.GONE);
    }
}
