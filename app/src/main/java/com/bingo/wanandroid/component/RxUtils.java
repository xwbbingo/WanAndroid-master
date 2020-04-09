package com.bingo.wanandroid.component;

import com.bingo.wanandroid.core.bean.BaseResponse;
import com.bingo.wanandroid.core.bean.main.login.LoginData;
import com.bingo.wanandroid.core.http.exception.OtherException;
import com.bingo.wanandroid.utils.CommonUtil;
import com.bingo.wanandroid.utils.NetWorkUtil;

import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author bingo
 * date 2020/1/5
 */
public class RxUtils {

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> rxFlSchedulerHelper() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> observable) {
//                return observable.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
    }


    /**
     * 统一返回结果处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseResponse<T>, Observable<T>>) baseResponse -> {
                    if(baseResponse.getErrorCode() == BaseResponse.SUCCESS
                            && baseResponse.getData() != null
                            && NetWorkUtil.isNetworkConnected()) {
                        return createData(baseResponse.getData());
                    } else {
                        return Observable.error(new Exception());
                    }
                });
//        return new ObservableTransformer<BaseResponse<T>, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<BaseResponse<T>> httpResponseObservable) {
//                return httpResponseObservable.flatMap(new Function<BaseResponse<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> apply(BaseResponse<T> baseResponse) throws Exception {
//                        if (baseResponse.getErrorCode() == BaseResponse.SUCCESS
//                                && baseResponse.getData() != null
//                                && NetWorkUtil.isNetworkConnected()) {
//                            return createData(baseResponse.getData());
//                        } else {
//                            return Observable.error(new Exception());
//                        }
//                    }
//                });
//            }
//        };
    }


    /**
     * 得到 Observable
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
//        return Observable.create(new ObservableOnSubscribe<T>() {
//            @Override
//            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
//                try {
//                    emitter.onNext(t);
//                    emitter.onComplete();
//                } catch (Exception e) {
//                    emitter.onError(e);
//                }
//            }
//        });
    }

    /**
     * 退出登录返回结果处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>,T> handleLogoutResult(){
        return httpResponseObservable ->
                httpResponseObservable.flatMap(new Function<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        if(tBaseResponse.getErrorCode() == BaseResponse.SUCCESS
                                && tBaseResponse.getData() != null
                                && NetWorkUtil.isNetworkConnected()) {
                            //创建一个非空数据源，避免onNext()传入null
                            return createData(CommonUtil.cast(new LoginData()));
                        } else {
                            return Observable.error(new OtherException());
                        }
                    }
                });

    }
}
