package com.example.commonlibrary.repository;


/**
 * Created by COOTEK on 2017/7/31.
 */

public interface IRepositoryManager<D> {
    <T> T getApi(Class<T> retrofitClass);


    <T> void clearApi(Class<T> retrofitClass);



    D getDaoSession();




    void clearAllCache();


//    DaoSession getDaoSession();



}
