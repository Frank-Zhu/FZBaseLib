package com.frankzhu.appnetworklibrary.api;

import com.frankzhu.appbaselibrary.log.FZLogUtils;
import com.frankzhu.appbaselibrary.utils.FZStringHelper;
import com.frankzhu.appnetworklibrary.bean.base.HttpResult;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/5/3 上午11:09
 * Description: 网络返回拦截器
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/5/3      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class RedirectResponseTransformer<T> implements Observable.Transformer<HttpResult<T>, T> {
    //对应HTTP的状态码
    private static final int REQUEST_TIMEOUT = 408;
    private static final int GATEWAY_TIMEOUT = 504;

    @Override
    public Observable<T> call(Observable<HttpResult<T>> HttpResultObservable) {
        return HttpResultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .lift(new Observable.Operator<T, HttpResult<T>>() {
                    @Override
                    public Subscriber<? super HttpResult<T>> call(
                            final Subscriber<? super T> subscriber) {
                        return new Subscriber<HttpResult<T>>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Throwable throwable = e;
                                //获取最根源的异常
                                while (throwable.getCause() != null) {
                                    e = throwable;
                                    throwable = throwable.getCause();
                                }
                                if (e instanceof HttpException) {
                                    HttpException httpException = (HttpException) e;
                                    switch (httpException.code()) {
                                        case REQUEST_TIMEOUT:
                                        case GATEWAY_TIMEOUT: {
                                            ApiException apiException = new ApiException(HttpApiMethods.NETWORK_TIMEOUT_ERROR_MSG);
                                            apiException.setErrorCode(ApiException.TIME_OUT_ERROR_CODE);
                                            subscriber.onError(apiException);
                                        }
                                        break;
                                        default:
                                            subscriber.onError(new ApiException(HttpApiMethods.DEFAULT_ERROR_MSG));
                                            break;
                                    }
                                } else if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
                                    ApiException apiException = new ApiException(HttpApiMethods.NETWORK_TIMEOUT_ERROR_MSG);
                                    apiException.setErrorCode(ApiException.TIME_OUT_ERROR_CODE);
                                    subscriber.onError(apiException);
                                } else {
                                    subscriber.onError(e);
                                }
                            }

                            @Override
                            public void onNext(HttpResult<T> httpResult) {
                                FZLogUtils.d("result", httpResult.toJson());
                                if (httpResult.isSuccess()) {
                                    subscriber.onNext(httpResult.getData());
                                } else {
                                    String msg = HttpApiMethods.DEFAULT_ERROR_MSG;
                                    if (FZStringHelper.notEmpty(httpResult.getMsg())) {
                                        msg = httpResult.getMsg();
                                    }
                                    subscriber.onError(new ApiException(msg, httpResult.getErrorCode()));
                                }
                            }
                        };
                    }
                });
    }
}
