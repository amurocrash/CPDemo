package com.cngame.gamesdk.V.billing;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cngame.gamesdk.utils.base_view.ViewController;

/**
 * Created by user on 2016/4/8.
 */
public class BillingViewController extends ViewController
{
    public BillingViewController(Context context)
    {
        super(context);
    }

    @Override
    protected View getContentView()
    {
        LinearLayout linearLayout = new LinearLayout(context);
        FrameLayout.LayoutParams llParms = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParms.gravity = Gravity.CENTER;
        linearLayout.setLayoutParams(llParms);
        linearLayout.setBackgroundColor(Color.WHITE);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("This view for billing");
        textView.setPadding(40, 40, 40, 40);
        textView.setTextColor(Color.DKGRAY);

        linearLayout.addView(textView);
        return linearLayout;
    }
}
