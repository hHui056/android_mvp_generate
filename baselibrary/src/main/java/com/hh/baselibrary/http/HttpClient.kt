package com.hh.baselibrary.http

import com.hh.baselibrary.util.file.FileUtils1
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * 支持Http GET/POST 方法，支持同步和异步([io.reactivex.Observable])操作。
 *
 * GET: Json、String、File
 *
 * POST: Form、Json、File&Params
 *
 * Created by hHui on 2017/8/3.
 *
 */
class HttpClient {

    fun getString(url: String): Observable<String> {
        val getMethod = HttpManager.createService(iCls = IGetMethod::class.java)
        return getMethod.getResponse(url).map {
            it.string()
        }.doOnSubscribe { HttpManager.manage(it) }
    }

    fun <T> getJson(url: String, type: Class<T>): Observable<T> {
        return getString(url).map {
            it.toModel(type)
        }.doOnSubscribe { HttpManager.manage(it) }
    }

    fun <T> getJson(url: String, map: Map<String, String>, type: Class<T>): Observable<T> {
        val urlSb = StringBuffer(url).append("?")

        map.forEach {
            urlSb.append("${it.key}=${it.value}&")
        }
        val realUrl = urlSb.toString().substring(0, urlSb.toString().length - 1)
        return getString(realUrl).map {
            it.toModel(type)
        }.doOnSubscribe { HttpManager.manage(it) }
    }

    /**
     * get方式下载文件
     * @param url 文件下载地址
     * @param targetFile 保存路径
     */
    fun getFile(url: String, targetFile: File): Observable<File> {
        val getMethod = HttpManager.createService(iCls = IGetMethod::class.java)
        return getMethod.getFile(url).map {
            FileUtils1.saveFile(it.byteStream(), targetFile)
            return@map targetFile
        }.doOnSubscribe { HttpManager.manage(it) }
    }

    fun <R> postJsonString(url: String, request: String, typeResponse: Class<R>): Observable<R> {
        val postMethod = HttpManager.createService(iCls = IPostMethod::class.java)
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json"), request)
        return postMethod.postJson(url, body).map {
            it.string().toModel(typeResponse)
        }.doOnSubscribe { HttpManager.manage(it) }
    }

    fun <T, R> postJson(url: String, request: T, typeResponse: Class<R>): Observable<R> {
        val postMethod = HttpManager.createService(iCls = IPostMethod::class.java)
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json"), request.toJsonString())
        return postMethod.postJson(url, body).map {
            it.string().toModel(typeResponse)
        }.doOnSubscribe { HttpManager.manage(it) }
    }

    fun <T> postString(url: String, request: T): Observable<String> {
        val postMethod = HttpManager.createService(iCls = IPostMethod::class.java)
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json"), request.toJsonString())
        return postMethod.postJson(url, body).map {
            it.string()
        }.doOnSubscribe { HttpManager.manage(it) }
    }

    /**
     * @param url url
     * @param fieldMap key—value
     * @param typeResponse 返回的对象的类型
     */
    fun <R> postForm(url: String, fieldMap: Map<String, String>, typeResponse: Class<R>): Observable<R> {
        val postMethod = HttpManager.createService(iCls = IPostMethod::class.java)
        return postMethod.postForm(url, fieldMap.toMutableMap()).map {
            it.string().toModel(typeResponse)
        }.doOnSubscribe { HttpManager.manage(it) }
    }

    fun <R> postFilesWithParams(url: String, paramMap: Map<String, String>, fileMap: Map<String, File>,
                                typeResponse: Class<R>): Observable<R> {
        val postMethod = HttpManager.createService(iCls = IPostMethod::class.java)
        val params = paramMap.mapValues { RequestBody.create(MultipartBody.FORM, it.value) }

        val files = fileMap.map {
            val contentFile = RequestBody.create(MediaType.parse("application/octet-stream"), it.value)
            val filePart = MultipartBody.Part.createFormData(it.key, it.value.name, contentFile)
            return@map filePart
        }
        return postMethod.postMultiPart(url, params.toMutableMap(), files).map {
            it.string().toModel(typeResponse)
        }.doOnSubscribe { HttpManager.manage(it) }
    }

}