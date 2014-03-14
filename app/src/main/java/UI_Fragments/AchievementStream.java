package UI_Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;

import AchievementStreamChildFragments.CardFragment;

/**
 * Created by Drew on 3/13/14.
 */
public class AchievementStream extends Fragment {

    FragmentManager childFragManager;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.achievement_stream_layout, container, false);

        childFragManager = getChildFragmentManager();

        childFragManager.beginTransaction().add(R.id.card_scroll_view, new CardFragment()).commit();
        childFragManager.beginTransaction().add(R.id.card_scroll_view, new CardFragment()).commit();
        childFragManager.beginTransaction().add(R.id.card_scroll_view, new CardFragment()).commit();
        childFragManager.beginTransaction().add(R.id.card_scroll_view, new CardFragment()).commit();
        childFragManager.beginTransaction().add(R.id.card_scroll_view, new CardFragment()).commit();
        childFragManager.beginTransaction().add(R.id.card_scroll_view, new CardFragment()).commit();

        return rootView;
    }

}