package com.example.infood.Listener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infood.Interface.RecyclerViewClickListener;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private RecyclerViewClickListener clickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerViewClickListener clickListener){
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
    }

    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
            clickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e){}

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept){}
}
