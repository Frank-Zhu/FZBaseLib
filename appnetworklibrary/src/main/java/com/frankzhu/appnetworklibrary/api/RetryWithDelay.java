package com.frankzhu.appnetworklibrary.api;

import com.frankzhu.appbaselibrary.log.FZLogUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/8/18 上午10:30
 * Description: 重试机制转换
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/8/18      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class RetryWithDelay implements Func1<Observable<? extends Throwable>, Observable<?>> {
    private final int mMaxRetries;//重试次数
    private final int mRetryDelayMillis;//每次重试延时时间
    private int mRetryCount;//当前重试次数

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        mMaxRetries = maxRetries;
        mRetryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {
        return observable
                .flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        Throwable e = throwable;
                        //获取最根源的异常
                        while (e.getCause() != null) {
                            throwable = e;
                            e = e.getCause();
                        }
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            if (apiException.isTimeOutError()) {
                                if (++mRetryCount <= mMaxRetries) {
                                    // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                                    FZLogUtils.d("RetryWithDelay", "服务器超时错误,将在 " + mRetryDelayMillis + " 秒之后重试, 当前重试次数: " + mRetryCount);
                                    return Observable.timer(mRetryDelayMillis, TimeUnit.MILLISECONDS);
                                }
                            }
                        }
                        // Max retries hit. Just pass the error along.
                        return Observable.error(throwable);
                    }
                });
    }
}
