package com.cngame.gamesdk.V.login;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.cngame.gamesdk.P.login.LoginPresenter;
import com.cngame.gamesdk.utils.ToastUtils;
import com.cngame.gamesdk.utils.base_view.ViewController;

/**
 * Created by user on 2016/4/8.
 */
public class LoginViewController extends ViewController implements ILoginV
{
    private Button button;
    private LoginPresenter loginPresenter;

    public LoginViewController(Context context)
    {
        super(context);
        loginPresenter = LoginPresenter.getInstance();
        loginPresenter.addView(this);
    }

    @Override
    protected View getContentView()
    {
        LoginView view = new LoginView(context);

        button = (Button) view.findViewById(LoginView.id_bt);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loginPresenter.login();
            }
        });

        return view;
    }


    @Override
    public void onLoginSucceed()
    {
        ToastUtils.show(context, "LoginSucceed");
    }

    @Override
    public void onLoginFailed()
    {
        ToastUtils.show(context, "LoginFailed");
    }

    @Override
    protected void onViewDestroy()
    {
        super.onViewDestroy();
        loginPresenter.removeView(this);

    }
}
