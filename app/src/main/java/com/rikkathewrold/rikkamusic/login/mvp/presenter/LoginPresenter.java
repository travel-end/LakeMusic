package com.rikkathewrold.rikkamusic.login.mvp.presenter;


import com.rikkathewrold.rikkamusic.login.bean.LoginBean;
import com.rikkathewrold.rikkamusic.login.mvp.contract.LoginContract;
import com.rikkathewrold.rikkamusic.login.mvp.model.LoginModel;
import com.rikkathewrold.rikkamusic.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        this.mModel = new LoginModel();
    }

    @Override
    public void login(String phone, String password) {
        LogUtil.d(TAG, "login");
        mModel.login(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(LoginBean bean) {
                        LogUtil.d(TAG, "onNext : " + bean);
                        mView.onLoginSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "onError : " + e.toString());
                        mView.onLoginFail(e.getMessage());

//                        LoginBean bean = new LoginBean();
//                        bean.setLoginType(1);
//                        bean.setCode(200);
//                        LoginBean.AccountBean b1 = new LoginBean.AccountBean();
//                        b1.setUserName("1_13272136221");
//                        b1.setType(1);
//                        b1.setSalt("[B@3c3d6a89");
//                        b1.setCreateTime(1486309512362L);
//                        LoginBean.ProfileBean b2 = new LoginBean.ProfileBean();
//                        b2.setUserId(415560926);
//                        b2.setAvatarUrl("https://p3.music.126.net/ULOn30612l-96hKgIy18tA==/18787355185828647.jpg");
//                        b2.setNickname("rikkatheworld");
//                        b2.setAvatarImgId(18787355185828650L);
//                        b2.setBackgroundUrl("https://p4.music.126.net/r4Ej-BqYiX-Al8AqRFeAUA==/109951163710677237.jpg");
//
//                        LoginBean.BindingsBean b3 = new LoginBean.BindingsBean();
//                        b3.setRefreshTime(1486309535);
//                        b3.setUserId(415560926);
//                        b3.setTokenJsonStr("{\"countrycode\":\"\",\"cellphone\":\"13272136221\",\"hasPassword\":true}");
//                        b3.setBindingTime(1486309535025L);
//                        List<LoginBean.BindingsBean> list = new ArrayList<>();
//                        list.add(b3);
//
//                        bean.setAccount(b1);
//                        bean.setProfile(b2);
//                        bean.setBindings(list);
//
//
//                        mView.onLoginSuccess(bean);
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d(TAG, "onComplete!");
                    }
                });
    }
}
