package com.example.commonlibrary.net.download;

import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-07-31     17:46
 */
public class DownloadExecutors {


    private static DownloadExecutors sInstance;


    private static final int MAX_POOL_SIZE = 10;          //最大线程池的数量
    private static final int KEEP_ALIVE_TIME = 1;        //存活的时间
    private static final TimeUnit UNIT = TimeUnit.HOURS; //时间单位
    private final ThreadPoolExecutor threadPoolExecutor;
    private int corePoolSize = 10;

    public static DownloadExecutors getsInstance() {
        if (sInstance == null) {
            sInstance=new DownloadExecutors();
        }
        return sInstance;
    }


    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    private DownloadExecutors(){
        threadPoolExecutor=new ThreadPoolExecutor(corePoolSize, MAX_POOL_SIZE, KEEP_ALIVE_TIME, UNIT, //
                new PriorityBlockingQueue<Runnable>(),   //无限容量的缓冲队列
                Executors.defaultThreadFactory(),        //线程创建工厂
                new ThreadPoolExecutor.AbortPolicy());
    }
}
