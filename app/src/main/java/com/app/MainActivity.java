package com.app;

import java.sql.CallableStatement;
import java.util.Locale;
import java.util.concurrent.Callable;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import CustomLibrary.CustomImageView;
import UI_Fragments.AchievementStream;
import UI_Fragments.CreateGoal;
import UI_Fragments.GoalManager;
import UI_Fragments.HallOfAwesome;
import UI_Fragments.ProgressTracker;

public class MainActivity extends Activity implements View.OnClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    ActionBar actionBar;
    LinearLayout stream, goalManager, createGoal, achievements, progressTracker;
    TextView Title;
    ImageView homeIcon, streamIcon, goalManagerIcon, searchIcon;
    EditText searchBar;
    Animation expand, collapse;
    ValueAnimator colorAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);

        actionBar = getActionBar();
        actionBar.hide();

        stream = (LinearLayout) findViewById(R.id.stream);
        stream.setOnClickListener(this);
        streamIcon = (ImageView) stream.findViewById(R.id.stream_icon);

        goalManager = (LinearLayout) findViewById(R.id.goalManager);
        goalManager.setOnClickListener(this);
        goalManagerIcon = (ImageView) findViewById(R.id.goal_manager_icon);

        createGoal = (LinearLayout) findViewById(R.id.createGoal);
        createGoal.setOnClickListener(this);

        achievements = (LinearLayout) findViewById(R.id.achievements);
        achievements.setOnClickListener(this);

        progressTracker = (LinearLayout) findViewById(R.id.progressTracker);
        progressTracker.setOnClickListener(this);

        stream.setBackgroundColor(Color.parseColor("#0099cb"));
        streamIcon.setImageResource(R.drawable.ic_stream_xxhdpi_white);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                if(v > .5)
                    Title.setText(mSectionsPagerAdapter.getPageTitle(i + 1));
                else
                    Title.setText(mSectionsPagerAdapter.getPageTitle(i));
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        Title = (TextView) findViewById(R.id.Title);
        homeIcon = (ImageView) findViewById(R.id.homeIcon);
        searchIcon = (ImageView) findViewById(R.id.searchIcon);
        searchBar = (EditText) findViewById(R.id.searchBar);

        Drawable myDrawable = getResources().getDrawable(R.drawable.person_icon);
        Bitmap icon = ((BitmapDrawable) myDrawable).getBitmap();
        homeIcon.setImageBitmap(getRoundedCornerBitmap(icon, 48));

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchBar.getVisibility() == View.VISIBLE)
                {
                    collapse = new ScaleAnimation(1, 0, 1, 1);
                    collapse.setDuration(250);

                    searchIcon.setImageResource(R.drawable.ic_search_xxxhdpi_light_grey);

                    searchBar.setAnimation(collapse);
                    searchBar.setVisibility(View.GONE);
                    homeIcon.setVisibility(View.VISIBLE);
                    Title.setVisibility(View.VISIBLE);
                }
                else{
                    searchBar.setVisibility(View.VISIBLE);

                    expand = new ScaleAnimation(0, 1, 1, 1);
                    expand.setDuration(250);

                    searchIcon.setImageResource(R.drawable.ic_search_xxxhdpi_white);

                    searchBar.setAnimation(expand);

                    homeIcon.setVisibility(View.GONE);
                    Title.setVisibility(View.GONE);

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.stream:
                mViewPager.setCurrentItem(0);

                changeColorAsync(stream, Color.parseColor("#0099cd"));
                changeColorAsync(goalManager, Color.parseColor("#333333"));
                changeColorAsync(createGoal, Color.parseColor("#333333"));
                changeColorAsync(achievements, Color.parseColor("#333333"));
                changeColorAsync(progressTracker, Color.parseColor("#333333"));

                streamIcon.setImageResource(R.drawable.ic_stream_xxhdpi_white);
                goalManagerIcon.setImageResource(R.drawable.ic_goal_manager_xxxhpdi_light_grey);
                break;
            case R.id.goalManager:
                mViewPager.setCurrentItem(1);

                changeColorAsync(stream, Color.parseColor("#333333"));
                changeColorAsync(goalManager, Color.parseColor("#0099cd"));
                changeColorAsync(createGoal, Color.parseColor("#333333"));
                changeColorAsync(achievements, Color.parseColor("#333333"));
                changeColorAsync(progressTracker, Color.parseColor("#333333"));

                streamIcon.setImageResource(R.drawable.ic_stream_xxhdpi_light_grey);
                goalManagerIcon.setImageResource(R.drawable.ic_goal_manager_xxxhpd_white);
                break;
            case R.id.createGoal:
                mViewPager.setCurrentItem(2);

                changeColorAsync(stream, Color.parseColor("#333333"));
                changeColorAsync(goalManager, Color.parseColor("#333333"));
                changeColorAsync(createGoal, Color.parseColor("#0099cd"));
                changeColorAsync(achievements, Color.parseColor("#333333"));
                changeColorAsync(progressTracker, Color.parseColor("#333333"));

                streamIcon.setImageResource(R.drawable.ic_stream_xxhdpi_light_grey);
                goalManagerIcon.setImageResource(R.drawable.ic_goal_manager_xxxhpdi_light_grey);

                break;
            case R.id.achievements:
                mViewPager.setCurrentItem(3);

                changeColorAsync(stream, Color.parseColor("#333333"));
                changeColorAsync(goalManager, Color.parseColor("#333333"));
                changeColorAsync(createGoal, Color.parseColor("#333333"));
                changeColorAsync(achievements, Color.parseColor("#0099cd"));
                changeColorAsync(progressTracker, Color.parseColor("#333333"));

                streamIcon.setImageResource(R.drawable.ic_stream_xxhdpi_light_grey);
                goalManagerIcon.setImageResource(R.drawable.ic_goal_manager_xxxhpdi_light_grey);
                break;
            case R.id.progressTracker:
                mViewPager.setCurrentItem(4);

                changeColorAsync(stream, Color.parseColor("#333333"));
                changeColorAsync(goalManager, Color.parseColor("#333333"));
                changeColorAsync(createGoal, Color.parseColor("#333333"));
                changeColorAsync(achievements, Color.parseColor("#333333"));
                changeColorAsync(progressTracker, Color.parseColor("#0099cd"));

                streamIcon.setImageResource(R.drawable.ic_stream_xxhdpi_light_grey);
                goalManagerIcon.setImageResource(R.drawable.ic_goal_manager_xxxhpdi_light_grey);
                break;
        }
    }

    private void changeColorAsync(final LinearLayout view, final int color)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                view.setBackgroundColor(color);
            }
        };
        runnable.run();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        AchievementStream aStream;
        GoalManager gManager;
        CreateGoal cGoal;
        HallOfAwesome hoAwesome;
        ProgressTracker pTracker;

        public void createNew()
        {
            if(aStream == null)
                aStream = new AchievementStream();
            if(gManager == null)
                gManager = new GoalManager();
            if(cGoal == null)
                cGoal = new CreateGoal();
            if(hoAwesome == null)
                hoAwesome = new HallOfAwesome();
            if(pTracker == null)
                pTracker = new ProgressTracker();

        }

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            createNew();
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 0:
                    return aStream;
                case 1:
                    return gManager;
                case 2:
                    return cGoal;
                case 3:
                    return hoAwesome;
                case 4:
                    return pTracker;
                default:
                    return PlaceholderFragment.newInstance(position);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section2);
                case 2:
                    return getString(R.string.title_section3);
                case 3:
                    return getString(R.string.title_section4);
                case 4:
                    return getString(R.string.title_section5);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
