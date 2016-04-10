package com.cngame.gamesdk.V.login;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2016/4/9.
 */
public class LoginView extends LinearLayout
{
    public static final int id_bt = 0x00000001;

    public LoginView(Context context)
    {
        super(context);
        init(context);
    }

    public LoginView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        FrameLayout.LayoutParams llParms = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParms.gravity = Gravity.CENTER;
        this.setLayoutParams(llParms);
        this.setBackgroundColor(Color.WHITE);
        this.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("This view for login");
        textView.setPadding(40, 40, 40, 40);
        textView.setTextColor(Color.DKGRAY);

        Button button = new Button(context);
        button.setId(id_bt);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText("login");
        button.setPadding(40, 40, 40, 40);
        button.setTextColor(Color.BLACK);

        this.addView(textView);
        this.addView(button);
    }
}
