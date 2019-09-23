package com.example.job3.present;

import com.example.job3.bean.RootBeans;

import java.util.List;


public interface Presenter {
    void sendData(List<RootBeans.BodyBean.ResultBean> result);
}
