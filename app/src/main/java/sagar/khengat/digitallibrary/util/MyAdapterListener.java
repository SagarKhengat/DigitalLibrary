package sagar.khengat.digitallibrary.util;

import android.view.View;


public interface MyAdapterListener {
    void issueOnClick(View v, int position);
    void rejectOnClick(View v, int position);
}
