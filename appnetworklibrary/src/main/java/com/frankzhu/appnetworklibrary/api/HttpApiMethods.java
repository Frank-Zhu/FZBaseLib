package com.frankzhu.appnetworklibrary.api;

import com.frankzhu.appbaselibrary.app.FZBaseApplication;
import com.frankzhu.appbaselibrary.log.FZLogUtils;
import com.frankzhu.appbaselibrary.utils.FZNetworkInfoHelper;

import java.io.File;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/4/27 下午4:53
 * Description: APP api 请求工具类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/4/27      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class HttpApiMethods {
    private static final String TAG = HttpApiMethods.class.getSimpleName();
    static volatile HttpApiMethods singleton = null;
    private AppApiService appApiService;
    public static final String DEFAULT_ERROR_MSG = "Whoops！网络不给力\n快找个信号满满的地方再刷新一下吧";
    public static final String NETWORK_ERROR_MSG = "没有网络连接,请打开你的网络连接";
    public static final String NETWORK_TIMEOUT_ERROR_MSG = "网络通信出现问题,请确认您的网络状况良好后重试";
    public static final String USER_LOGOUT_ERROR_MSG = "请重新登录";

    //构造方法私有
    private HttpApiMethods(AppApiService appApiService) {
        this.appApiService = appApiService;
    }

    //在访问HttpMethods时创建单例
    public static HttpApiMethods with(AppApiService appApiService) {
        if (singleton == null) {
            synchronized (HttpApiMethods.class) {
                if (singleton == null) {
                    singleton = new Builder(appApiService).build();
                }
            }
        }
        return singleton;
    }

    public static class Builder {
        private AppApiService appApiService;

        public Builder(AppApiService appApiService) {
            if (appApiService == null) {
                throw new IllegalArgumentException("AppApiService must not be null.");
            }
            this.appApiService = appApiService;
        }

        public HttpApiMethods build() {
            return new HttpApiMethods(appApiService);
        }
    }

    public static String genRandomPwd() {
        final int maxNum = 36;
        int i;
        int count = 0;
        int pwdLength = 8;
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuilder pwd = new StringBuilder("");
        Random r = new Random();
        while (count < pwdLength) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    private static final Observable.Transformer ioTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return (Observable.Transformer<T, T>) ioTransformer;
    }

    private void toSubscribe(Observable<?> observable, Subscriber subscriber) {
        if (checkNetwork(subscriber)) {
            observable.compose(applyIoSchedulers())
                    .retryWhen(new RetryWithDelay(3, 3000))//总共重试3次，重试间隔3000毫秒
                    .subscribe(subscriber);
        }
    }

    /**
     * 检查是否有网络连接
     *
     * @param subscriber 订阅事件
     * @return true 有网络连接
     */
    protected boolean checkNetwork(Subscriber subscriber) {
        if (!FZNetworkInfoHelper.isOnline(FZBaseApplication.getContext())) {
            subscriber.onError(new ApiException(NETWORK_ERROR_MSG));
            return false;
        }
        return true;
    }

    /**
     * 创建文件类型的 RequestBody
     *
     * @param path 文件全路经
     */
    private MultipartBody.Part createFilePart(String path) {
        FZLogUtils.d(TAG, path);
        File file = new File(path);
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse(AppApiContact.HTTP_FILE_TYPE), file);
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData("files", file.getName(), requestFile);
    }

    /**
     * 创建文本类型的 RequestBody
     *
     * @param json 文本数据
     */
    private RequestBody createRequestBody(String json) {
        FZLogUtils.d(TAG, json);
        return RequestBody.create(MediaType.parse(AppApiContact.HTTP_INPUT_TYPE), json);
    }

    /**
     * 创建文本流类型的 RequestBody
     *
     * @param json 文本数据
     */
    private RequestBody createRequestBody(byte[] json) {
        return RequestBody.create(MediaType.parse(AppApiContact.HTTP_INPUT_TYPE), json);
    }
}
