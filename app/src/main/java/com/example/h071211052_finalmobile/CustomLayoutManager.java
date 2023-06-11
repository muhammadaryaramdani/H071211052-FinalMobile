package com.example.h071211052_finalmobile;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomLayoutManager extends GridLayoutManager {

    public CustomLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public int getSpanSize(int position) {
        // Set the span size for each item based on the position
        if (position % 2 == 0) {
            return 1; // Every even position takes 1 span (full width)
        } else {
            return getSpanCount(); // Every odd position takes all spans
        }
    }
}
