package com.futuremind.recyclerviewfastscroll;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Michal on 05/08/16.
 */
public abstract class ScrollerViewProvider {

    private FastScroller scroller;
    private ViewVisibilityManager bubbleVisibilityManager;

    void setFastScroller(FastScroller scroller){
        this.scroller = scroller;
    }

    protected Context getContext(){
        return scroller.getContext();
    }

    protected FastScroller getScroller() {
        return scroller;
    }

    /**
     * @param container The container {@link FastScroller} for the view to inflate properly.
     * @return A view which will be by the {@link FastScroller} used as a handle.
     */
    public abstract View provideHandleView(ViewGroup container);

    /**
     * @param container The container {@link FastScroller} for the view to inflate properly.
     * @return A view which will be by the {@link FastScroller} used as a bubble.
     */
    public abstract View provideBubbleView(ViewGroup container);

    /**
     * Bubble view has to provide a {@link TextView} that will show the index title.
     * @return A {@link TextView} that will hold the index title.
     */
    public abstract TextView provideBubbleTextView();

    /**
     * To offset the position of the bubble relative to the handle. E.g. in {@link DefaultScrollerViewProvider}
     * the sharp corner of the bubble is aligned with the center of the handle.
     * @return the position of the bubble in relation to the handle (according to the orientation).
     */
    public abstract int getBubbleOffset();

    /**
     * @return {@link ViewVisibilityManager} responsible for showing and hiding bubble.
     */
    public abstract ViewVisibilityManager provideBubbleVisibilityManager();

    private ViewVisibilityManager getBubbleVisibilityManager(){
        if(bubbleVisibilityManager==null) bubbleVisibilityManager = provideBubbleVisibilityManager();
        return bubbleVisibilityManager;
    }

    public void handleGrabbed(){
        if(getBubbleVisibilityManager()!=null) getBubbleVisibilityManager().show();
    }

    public void handleReleased(){
        if(getBubbleVisibilityManager()!=null) getBubbleVisibilityManager().hide();
    }

}