package com.hh.baselibrary.util

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * Created by hHui on 2019/6/24 0024.
 */
class RxTimerUtil {
    private var mDisposable: Disposable? = null

    /**
     * @param milliseconds 毫秒
     * 每隔milliseconds毫秒后执行next操作
     */
    fun interval(milliseconds: Long, next: IRxNext) {
        Observable.interval(milliseconds, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onError(e: Throwable) = Unit
                    override fun onComplete() = Unit
                    override fun onSubscribe(d: Disposable) {
                        mDisposable = d
                    }

                    override fun onNext(t: Long) {
                        next.doNext(t)
                    }
                })
    }

    /** 取消定时器 **/
    fun cancel() {
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
            mDisposable = null
        }
    }

    interface IRxNext {
        fun doNext(number: Long)
    }
}