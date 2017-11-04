package com.yarward.mvp1test.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Michelle_Hong on 2017/2/23 0023.
 *
 * @des
 */

public class ThreadPoolTools {
    public static final int THREADLIMITCOUNT = 10;
    public static ExecutorService SINGLE_TASK_EXECUTOR;		//单独一个线程
    public static ExecutorService LIMITED_TASK_EXECUTOR;	//固定数量的线程
    public static ExecutorService FULL_TASK_EXECUTOR;		//不限定数量的线程

    static {
		SINGLE_TASK_EXECUTOR = Executors.newSingleThreadExecutor();
        LIMITED_TASK_EXECUTOR = Executors.newFixedThreadPool(THREADLIMITCOUNT);
		FULL_TASK_EXECUTOR = Executors.newCachedThreadPool();
    }
}
