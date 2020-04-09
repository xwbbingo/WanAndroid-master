package com.bingo.wanandroid.component;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * author bingo
 * date 2020/1/2
 */

public class RxBus {

    /**
     * 主题
      */
    private final FlowableProcessor<Object> bus;

    /**
     * PublishSubject只会把 在订阅发生的时间点之后 来自原始Flowable的数据 发射给观察者
     */
    private RxBus() {
        bus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getDefault() {
        return RxBusHolder.INSTANCE;
    }

    private static class RxBusHolder {
        private static final RxBus INSTANCE = new RxBus();
    }

    /**
     * 提供了一个新的事件
     *
     * @param o Object
     */
    public void post(Object o) {
        bus.onNext(o);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     *
     * @param eventType Event type
     * @param <T> 对应的Class类型
     * @return Flowable<T>
     */
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

}
