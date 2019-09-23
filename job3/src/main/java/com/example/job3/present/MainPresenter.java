package com.example.job3.present;

import com.example.job3.bean.RootBeans;
import com.example.job3.model.MainModel;
import com.example.job3.model.Model;

import java.util.List;




public class MainPresenter {

    public Presenter p;
    private int id;

    public MainPresenter(Presenter p,int id) {
        this.p = p;
        this.id = id;
    }

    public MainPresenter(Presenter p) {
        this.p = p;
    }

    public boolean isAttach() {
        return p != null;
    }

    public void getModel() {
        MainModel mainModel = new MainModel(id);
        mainModel.getData(new Model() {
            @Override
            public void sendData(List<RootBeans.BodyBean.ResultBean> result) {
                if (isAttach()){
                    p.sendData(result);
                }
            }

        });
    }

    public void detach(){
        p = null;
    }
}
