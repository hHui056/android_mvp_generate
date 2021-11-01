/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hh.baselibrary.log

import android.annotation.SuppressLint
import android.widget.Toast
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetSocketAddress
import java.text.SimpleDateFormat
import java.util.*

/**
 * Create By hHui on 2021/9/30 0030 上午 11:26
 */
class NetLog(private val host: String, private val port: Int) : ILog {
    private val xmlLogString = "<log level=\"%s\" app=\"%s\" thread=\"%s\" time=\"%s\" logger=\"%s\"><![CDATA[%s]]></log>"

    override fun d(tag: String, msg: String, t: Throwable?) {
        print("DEBUG", tag, msg)
    }

    override fun i(tag: String, msg: String, t: Throwable?) {
        print("INFO", tag, msg)
    }

    override fun w(tag: String, msg: String, t: Throwable?) {
        print("WARN", tag, msg)
    }

    override fun e(tag: String, msg: String, t: Throwable?) {
        print("ERROR", tag, msg)
    }

    override fun v(tag: String, msg: String, t: Throwable?) {
        print("INFO", tag, msg)
    }

    override fun dispose() {
    }

    @SuppressLint("CheckResult")
    private fun print(level: String, tag: String, msg: String) {
        Flowable.create(FlowableOnSubscribe<Boolean> {
            val sendMsg = String.format(xmlLogString, level, "android_base_library", Thread.currentThread().id.toString(),
                    Date().toShowStr(), tag, msg)
            val ds = DatagramSocket()
            val sendBytes = sendMsg.toByteArray()
            val dp = DatagramPacket(sendBytes, sendBytes.size, InetSocketAddress(host, port))
            ds.send(dp)
            ds.close()
            it.onNext(true)
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe({

        }, {

        })
    }


    private fun Date.toShowStr(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this)
    }
}