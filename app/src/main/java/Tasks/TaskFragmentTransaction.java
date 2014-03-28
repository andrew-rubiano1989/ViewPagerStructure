package Tasks;

import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by Drew on 3/27/14.
 */
public class TaskFragmentTransaction extends FragmentTransaction {
    @Override
    public FragmentTransaction add(Fragment fragment, String tag) {
        return null;
    }

    @Override
    public FragmentTransaction add(int containerViewId, Fragment fragment) {
        return null;
    }

    @Override
    public FragmentTransaction add(int containerViewId, Fragment fragment, String tag) {
        return null;
    }

    @Override
    public FragmentTransaction replace(int containerViewId, Fragment fragment) {
        return null;
    }

    @Override
    public FragmentTransaction replace(int containerViewId, Fragment fragment, String tag) {
        return null;
    }

    @Override
    public FragmentTransaction remove(Fragment fragment) {
        return null;
    }

    @Override
    public FragmentTransaction hide(Fragment fragment) {
        return null;
    }

    @Override
    public FragmentTransaction show(Fragment fragment) {
        return null;
    }

    @Override
    public FragmentTransaction detach(Fragment fragment) {
        return null;
    }

    @Override
    public FragmentTransaction attach(Fragment fragment) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public FragmentTransaction setCustomAnimations(int enter, int exit) {
        return null;
    }

    @Override
    public FragmentTransaction setCustomAnimations(int enter, int exit, int popEnter, int popExit) {
        return null;
    }

    @Override
    public FragmentTransaction setTransition(int transit) {
        return null;
    }

    @Override
    public FragmentTransaction setTransitionStyle(int styleRes) {
        return null;
    }

    @Override
    public FragmentTransaction addToBackStack(String name) {
        return null;
    }

    @Override
    public boolean isAddToBackStackAllowed() {
        return false;
    }

    @Override
    public FragmentTransaction disallowAddToBackStack() {
        return null;
    }

    @Override
    public FragmentTransaction setBreadCrumbTitle(int res) {
        return null;
    }

    @Override
    public FragmentTransaction setBreadCrumbTitle(CharSequence text) {
        return null;
    }

    @Override
    public FragmentTransaction setBreadCrumbShortTitle(int res) {
        return null;
    }

    @Override
    public FragmentTransaction setBreadCrumbShortTitle(CharSequence text) {
        return null;
    }

    @Override
    public int commit() {
        return 0;
    }

    @Override
    public int commitAllowingStateLoss() {
        return 0;
    }
}
