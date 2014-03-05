package com.app;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

public class MyCustomAnimation extends Animation {

    public final static int COLLAPSE = 1;
    public final static int EXPAND = 0;

    private View mView;
    private int mEndWidth;
    private int mType;
    private LinearLayout.LayoutParams mLayoutParams;

    public MyCustomAnimation(View view, int duration, int type) {

        setDuration(duration);
        mView = view;
        mEndWidth = mView.getWidth();
        mLayoutParams = ((LinearLayout.LayoutParams) view.getLayoutParams());
        mType = type;
        if(mType == EXPAND) {
            mLayoutParams.width = 0;
        } else {
            mLayoutParams.width = LayoutParams.MATCH_PARENT;
        }
        view.setVisibility(View.VISIBLE);
    }

    public int getWidth(){
        return mView.getWidth();
    }

    public void setWidth(int width){
        mEndWidth = width;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime < 1.0f) {
            if(mType == EXPAND) {
                mLayoutParams.width =  (int)(mEndWidth * interpolatedTime);
            } else {
                mLayoutParams.width = (int) (mEndWidth * (1 - interpolatedTime));
            }
            mView.requestLayout();
        } else {
            if(mType == EXPAND) {
                mLayoutParams.width = LayoutParams.WRAP_CONTENT;
                mView.requestLayout();
            }else{
                mView.setVisibility(View.GONE);
            }
        }
    }
}