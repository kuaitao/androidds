package com.bashapplication.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class ThreadPoolManager
{
	private static ThreadPoolManager tpm;

	private final static int CORE_POOL_SIZE = 13;

	private final static int MAX_POOL_SIZE = 15;

	private final static int TASK_QOS_PERIOD = 120;
	private boolean isPaused;

	private BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();

	
	private PausableThreadPoolExecutor threadPool;

	
	private ThreadPoolManager() {
		taskQueue = new LinkedBlockingQueue<Runnable>();
		threadPool = new PausableThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
				TASK_QOS_PERIOD, TimeUnit.SECONDS, taskQueue);
	}

	
	public static ThreadPoolManager getInstance() {
		if (null == tpm)
			tpm = new ThreadPoolManager();
		return tpm;
	}

	public boolean isPause(){
		return isPaused;
	}
	
	public static boolean isInit() {
		if (null == tpm)
			return false;
		return true;
	}

	public void remove(Runnable task)
	{
		if(task!=null && threadPool != null)
		{
			threadPool.remove(task);
		}
	}

	public void addExecuteTask(Runnable task) {
		if (task != null) {
			threadPool.execute(task);
		}
	}

	public void pauseThreadPool() {
		if (null != tpm && null != threadPool) {
			threadPool.pause();
			isPaused = true;
		}
	}
	
	public void clearThreadPool() {
		if (null != tpm && null != threadPool) {
			taskQueue.clear();
		}
	}

	public void resumeThreadPool() {
		if(null != tpm && null != threadPool) {
			threadPool.resume();
			isPaused = false;
		}
	}

	public void destoryThreadPool() {
		clearThreadPool();
		threadPool.shutdown();
		taskQueue = null;
		threadPool = null;
		tpm = null;
	}
}

