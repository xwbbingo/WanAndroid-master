package com.bingo.wanandroid.core.event;

/**
 * author bingo
 * date 2020/1/6
 */
public class LoginEvent {
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public LoginEvent(boolean isLogin) {

        this.isLogin = isLogin;
    }
}
