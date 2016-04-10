package com.cngame.gamesdk.utils.base_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public abstract class ViewController
        implements View.OnClickListener, View.OnTouchListener, ViewContainer.KeyEventHandler
{

    private WindowManager mWindowManager;
    protected Context context;
    private ViewContainer wholeView;
    private View contentView;
    private ViewDismissHandler mViewDismissHandler;

    public ViewController(Context context)
    {
        this.context = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public void setViewDismissHandler(ViewDismissHandler viewDismissHandler)
    {
        mViewDismissHandler = viewDismissHandler;
    }

    public void show()
    {
        wholeView = getWholeView();
        wholeView.setOnTouchListener(this);
        wholeView.setKeyEventHandler(this);

        int w = WindowManager.LayoutParams.MATCH_PARENT;
        int h = WindowManager.LayoutParams.MATCH_PARENT;

        int flags = 0;
        int type = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            type = WindowManager.LayoutParams.TYPE_TOAST;
        }
        else
        {
            type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        WindowManager.LayoutParams layoutParams =
                new WindowManager.LayoutParams(w, h, type, flags, PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.TOP;

        mWindowManager.addView(wholeView, layoutParams);
    }

    private ViewContainer getWholeView()
    {
        ViewContainer viewContainer = new ViewContainer(context);
        viewContainer.setLayoutParams(
                new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT)
                );
        viewContainer.setBackgroundColor(Color.parseColor("#77000000"));

        contentView = getContentView();
        viewContainer.addView(contentView);

        return viewContainer;
    }

    protected abstract View getContentView();

    @Override
    public void onClick(View v)
    {
        removePoppedViewAndClear();
    }

    protected void onViewDestroy()
    {

    }

    private void removePoppedViewAndClear()
    {
        onViewDestroy();

        if (mWindowManager != null && wholeView != null)
        {
            mWindowManager.removeView(wholeView);
        }

        if (mViewDismissHandler != null)
        {
            mViewDismissHandler.onViewDismiss();
        }

        wholeView.setOnTouchListener(null);
        wholeView.setKeyEventHandler(null);
    }

    /**
     * touch the outside of the content view, remove the popped view
     */
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Rect rect = new Rect();
        contentView.getGlobalVisibleRect(rect);
        if (!rect.contains(x, y))
        {
            removePoppedViewAndClear();
        }
        return false;
    }

    @Override
    public void onKeyEvent(KeyEvent event)
    {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
        {
            removePoppedViewAndClear();
        }
    }

    public interface ViewDismissHandler
    {
        void onViewDismiss();
    }

}

