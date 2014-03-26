package UI_Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.app.R;

import AchievementStreamChildFragments.CardFragment;
import CustomLibrary.CustomAchievementsScrollView;
import CustomLibrary.ScrollViewListener;

/**
 * Created by Drew on 3/13/14.
 */
public class AchievementStream extends Fragment implements ScrollViewListener{

    FragmentManager childFragManager;
    CustomAchievementsScrollView cardScrollView;
    View rootView;
    LinearLayout cardContainer;
    int maxHeight, atBottom;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.achievement_stream_layout, container, false);

        childFragManager = getChildFragmentManager();
        cardScrollView = (CustomAchievementsScrollView) rootView.findViewById(R.id.achievementScrollView);
        cardContainer = (LinearLayout) rootView.findViewById(R.id.card_scroll_view);
        cardScrollView.setScrollViewListener(this);

        new addCard().execute(new CardFragment());
        new addCard().execute(new CardFragment());
        new addCard().execute(new CardFragment());
        new addCard().execute(new CardFragment());
        new addCard().execute(new CardFragment());
        new addCard().execute(new CardFragment());
        new addCard().execute(new CardFragment());

        return rootView;
    }

    @Override
    public void onScrollChanged(CustomAchievementsScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(y > maxHeight)
            maxHeight = y;
        else if(y == maxHeight)
        {
            atBottom++;
        }
        else if(atBottom > 1)
        {
            //here we look for new events
            new addCard().execute(new CardFragment());
            atBottom = 0;
            maxHeight = y-50;
        }
        else
        {
            atBottom = 0;
        }

        if(y > maxHeight)
        {
            Log.e("ScrollView", "y: " + y);
            Log.e("ScrollView", "maxheight: " + maxHeight);
        }

        Log.i("ScrollView", "ScrollView position: " + new Integer(y).toString());
        Log.i("ScrollView", "scrollView height: " + new Integer(maxHeight).toString());
        Log.i("ScrollView", "atBottom count: " + new Integer(atBottom).toString());

        /*if(oldy < y && y == scrollView.getMeasuredHeight())
        {
            new addCard().execute(new CardFragment());
        }*/
    }

    public class addCard extends AsyncTask<CardFragment, Integer, Long>
    {

        @Override
        protected Long doInBackground(CardFragment... params) {
            childFragManager.beginTransaction().setCustomAnimations(R.anim.enter_comment, R.anim.leave_comment).add(R.id.card_scroll_view, params[0]).commit();
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