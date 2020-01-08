package com.zlk.swipetoloadlayout.head;

import android.content.Context;
import android.util.AttributeSet;

import com.zlk.swipetoloadlayout.SwipeRefreshHeaderLayout;

/**
 * Created by ice on 2017/5/22 0022.
 * this is a xxx for
 */

public class ProductHeader extends SwipeRefreshHeaderLayout {

    public static final String TAG = ProductHeader.class.getSimpleName();

    public ProductHeader(Context context) {
        this(context, null);
    }

    public ProductHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        Log.i(TAG, "onFinishInflate: ");
    }

    @Override
    public void onRefresh() {
//        Log.i(TAG, "onRefresh: ");
    }

    @Override
    public void onPrepare() {
//        Log.i(TAG, "onPrepare: ");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
//        if (!isComplete) {
//            Log.d(TAG, "onMove() called with: y = [" + y + "], isComplete = [" + isComplete + "], automatic = [" + automatic + "]");
//        }
    }

    @Override
    public void onRelease() {
//        Log.i(TAG, "onRelease: ");
    }

    @Override
    public void onComplete() {
//        Log.i(TAG, "onComplete: ");
    }

    @Override
    public void onReset() {
//        Log.i(TAG, "onReset: ");
    }
}
