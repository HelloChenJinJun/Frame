package com.example.commonlibrary.repository;


import org.greenrobot.greendao.AbstractDaoSession;

import retrofit2.Retrofit;

/**
 * 项目名称:    NewFastFrame
 * 创建人:      李晨
 * 创建时间:    2018/8/26     14:47
 */

public class DefaultRepositoryManager<D extends AbstractDaoSession> extends BaseRepositoryManager<D> {
    public DefaultRepositoryManager(Retrofit retrofit, D abstractDaoSession) {
        super(retrofit, abstractDaoSession);
    }
}
