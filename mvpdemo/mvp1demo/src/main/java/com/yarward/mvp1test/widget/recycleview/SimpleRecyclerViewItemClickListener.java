package com.yarward.mvp1test.widget.recycleview;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Michelle_Hong on 2017/9/6 0006.
 *
 * @des RecyclerView的Item点击时间监
 */

public class SimpleRecyclerViewItemClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private OnItemClickListener onItemClickListener;
    private GestureDetectorCompat gestureDetectorCompat;

    public SimpleRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public SimpleRecyclerViewItemClickListener() {
        super();
    }


    private void initGestureDetector(final RecyclerView recyclerView){
        gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(),new GestureDetector.SimpleOnGestureListener(){
            //可以根据需要，重写onGestureListener和 onDoubleTapListener接口的方法

            /**
             * 单击确认，即很快按下并抬起，但不连击点击第二下
             * @param e
             * @return
             */
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //单击事件
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && onItemClickListener != null){
                    onItemClickListener.onItemClick(childView,recyclerView.getChildLayoutPosition(childView));
                    return true;
                }
                return false;
            }

            /**
             * 长按，触摸屏按下后即不抬起也不移动，一段时间
             * @param e
             */
            @Override
            public void onLongPress(MotionEvent e) {
                //长按事件
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && onItemClickListener != null){
                    onItemClickListener.onItemLongClick(childView,recyclerView.getChildLayoutPosition(childView));
                }
            }

            /**
             * 滚动，触摸屏按下后移动
             * @param e1
             * @param e2
             * @param distanceX
             * @param distanceY
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            /**
             * 滑动，触摸屏按下后快速移动并抬起，会触发滚动手势，紧接着触发一个滑动手势
             * @param e1
             * @param e2
             * @param velocityX
             * @param velocityY
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            /**
             * 短按，触摸屏按下后片刻抬起，会触发这个手势，如果迅速抬起则不会
             * @param e
             */
            @Override
            public void onShowPress(MotionEvent e) {
                super.onShowPress(e);
            }

            /**
             * 单击，触摸屏按下即立刻触发
             * @param e
             * @return
             */
            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }

            /**
             * 双击，手指在触摸屏上迅速点击第二下即触发
             * @param e
             * @return
             */
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //双击事件
                int action = e.getAction();
                if(action == MotionEvent.ACTION_UP){
                    View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(childView != null && onItemClickListener != null){
                        onItemClickListener.onItemDoubleClick(childView,recyclerView.getChildLayoutPosition(childView));
                        return true;
                    }
                }
                return false;
            }

            /**
             * 双击的按下跟抬起各触发一次
             * @param e
             * @return
             */
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return super.onDoubleTapEvent(e);
            }

            /**
             * 单击确认，即很快的按下并太极，但不连续点击第二下
             * @param e
             * @return
             */
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public boolean onContextClick(MotionEvent e) {
                return super.onContextClick(e);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (gestureDetectorCompat == null){
            initGestureDetector(rv);
        }
        if(gestureDetectorCompat.onTouchEvent(e)){//把事件交给GestureDetector处理
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        super.onTouchEvent(rv, e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.onRequestDisallowInterceptTouchEvent(disallowIntercept);
    }

    /**
     * RecyclerView的Item点击事件监听
     */
    public interface OnItemClickListener{

        /**
         * 当ItemView的单击事件触发时调用
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);

        /**
         * 当ItemView的长按事件触发时调用
         * @param view
         * @param position
         */
        void onItemLongClick(View view, int position);

        /**
         * 当ItemView的双击事件触发时调用
         * @param view
         * @param postion
         */
        void onItemDoubleClick(View view, int postion);
    }
}
