package com.example.myappday01.Presenter;


import com.example.myappday01.Views.MainView;
import com.example.myappday01.bean.User;
import com.example.myappday01.molder.MolderMain;

public class MainPresenter implements MainView,PresenterMain {
    private MainView mainView;
    private MolderMain molderMain;

    public MainPresenter(MainView mainView, MolderMain molderMain) {
        this.mainView = mainView;
        this.molderMain = molderMain;
    }

    @Override
    public void onData(User user) {
        mainView.onData(user);
    }

    @Override
    public void getSecr() {
        molderMain.getDate(this);
    }
}
