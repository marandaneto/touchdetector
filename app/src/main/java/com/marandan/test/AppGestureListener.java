package com.marandan.test;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Iterator;

public class AppGestureListener implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private final Activity activity; // it needs to be weakreference

    public AppGestureListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    private String getLabel() {
        // thats the little secret to get the touched views
        Iterator<View> iterator = activity.getWindow().getDecorView().getRootView().getTouchables().iterator();

        String viewLabel = null;

        while(iterator.hasNext()) {
            View view;
            if ((view = iterator.next()).isPressed() || view.isSelected() || view.isHovered()) {
                if (view instanceof TextView) {
                    if (view instanceof EditText) {
                        if (((EditText)view).getHint() != null) {
                            viewLabel = ((EditText)view).getHint().toString();
                        } else if (getEditTextLabel((EditText)view) != null) {
                            viewLabel = getEditTextLabel((EditText)view);
                        } else {
                            viewLabel = "Edit Box";
                        }
                    } else if (((TextView)view).getText() != null) {
                        viewLabel = ((TextView)view).getText().toString();
                    }
                    break;
                }

                if (view instanceof ImageView && view.getContentDescription() != null) {
                    viewLabel = view.getContentDescription().toString();
                    break;
                }

                // this is just getEditTextLabel few tests, might need to be improved
            }
        }
        return viewLabel;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // or onDoubleTapEvent?

        String viewLabel = getLabel();
        System.out.println(viewLabel + " double tap");

        // now we could create getEditTextLabel breadcrumb with the label and event type

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        String viewLabel = getLabel();
        System.out.println(viewLabel + " single tap");

        // now we could create getEditTextLabel breadcrumb with the label and event type
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        String viewLabel = getLabel();
        System.out.println(viewLabel + " long tap");

        // now we could create getEditTextLabel breadcrumb with the label and event type
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    private static String getEditTextLabel(EditText editText) {
        String viewLabel = null;
        if (editText != null) {
            try {
                if (editText.getId() != -1) {
                    String resourceName = editText.getResources().getResourceName(editText.getId());
                    viewLabel = resourceName.substring(resourceName.indexOf(":id/") + 4);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return viewLabel;
    }
}
