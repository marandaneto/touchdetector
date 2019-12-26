package com.marandan.test;

import android.app.Activity;
import android.os.Build;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class AppWindowCallback implements Window.Callback {

    private final GestureDetector gestureDetector;
    private final Activity activity; // it needs to be weakreference

    public AppWindowCallback(Activity activity) {
        this.activity = activity;

        gestureDetector = new GestureDetector(activity.getApplicationContext(), new AppGestureListener(this.activity));
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return activity.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return activity.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        return activity.dispatchTouchEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return activity.dispatchTrackballEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);

        return activity.dispatchGenericMotionEvent(event);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return activity.dispatchPopulateAccessibilityEvent(event);
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        return activity.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        return activity.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, @Nullable View view, @NonNull Menu menu) {
        return activity.onPreparePanel(featureId, view, menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, @NonNull Menu menu) {
        return activity.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
        return activity.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
        activity.onWindowAttributesChanged(attrs);
    }

    @Override
    public void onContentChanged() {
        activity.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        activity.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onAttachedToWindow() {
        activity.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        activity.onDetachedFromWindow();
    }

    @Override
    public void onPanelClosed(int featureId, @NonNull Menu menu) {
        activity.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onSearchRequested() {
        return activity.onSearchRequested();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return activity.onSearchRequested(searchEvent);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return activity.onWindowStartingActionMode(callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return activity.onWindowStartingActionMode(callback, type);
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        activity.onActionModeStarted(mode);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        activity.onActionModeFinished(mode);
    }
}
