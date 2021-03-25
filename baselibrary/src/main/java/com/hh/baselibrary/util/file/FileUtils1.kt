package com.hh.baselibrary.util.file

import android.text.TextUtils
import android.util.Base64
import okio.Okio
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

/**
 *Create By hHui on 2018/10/11
 */
object FileUtils1 {
    /**
     * 保存一个输入流到文件中。
     * @param input 输入字节流
     * @param target 目标文件，如果文件不存在，自动创建；文件存在，覆盖。
     * @return true 操作成功
     */
    @JvmStatic
    fun saveFile(
        input: InputStream,
        target: File,
        onError: (Exception) -> Unit = { throw it }
    ): Boolean {
        return try {
            if (!target.exists()) {
                target.createNewFile()
            }
            val sink = Okio.buffer(Okio.sink(target))
            val source = Okio.buffer(Okio.source(input))
            sink.writeAll(source)
            sink.close()
            source.close()
            true
        } catch (e: Exception) {
            onError(e)
            false
        }
    }

    fun deleteFile(file: File?): Boolean {
        return file != null && (!file.exists() || file.isFile && file.delete())
    }

    @JvmStatic
    fun copySdcardFile(a: String, b: String, onError: (Exception) -> Unit = { throw it }): Boolean {
        return try {
            if (!File(b).exists()) {
                File(b).createNewFile()
            }
            val sink = Okio.buffer(Okio.sink(File(b)))
            val source = Okio.buffer(Okio.source(File(a)))
            sink.writeAll(source)
            sink.close()
            source.close()
            true
        } catch (e: Exception) {
            onError(e)
            false
        }
    }

    @JvmStatic
    fun saveString2File(
        content: String,
        target: File,
        onError: (Exception) -> Unit = { throw it }
    ): Boolean {
        return try {
            if (!target.exists()) {
                target.createNewFile()
            }
            val sink = Okio.buffer(Okio.sink(target))
            sink.writeString(content, Charset.defaultCharset())
            sink.close()
            true
        } catch (e: Exception) {
            onError(e)
            false
        }
    }

    @JvmStatic
    fun readFile2String(input: String, onError: (Exception) -> Unit = { throw it }): String {
        return try {
            val source = Okio.buffer(Okio.source(File(input)))
            String(source.readByteArray())
        } catch (e: Exception) {
            onError(e)
            ""
        }
    }

    @JvmStatic
    fun imageToBase64(path: String): String? {
        if (TextUtils.isEmpty(path)) {
            return null
        }
        var input: InputStream? = null
        var data: ByteArray? = null
        var result: String? = null
        try {
            input = FileInputStream(path)
            data = ByteArray(input.available())
            input.read(data)
            result = Base64.encodeToString(data, Base64.DEFAULT)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return result
    }

    /**
     * 文件转[ByteArray]
     */
    @JvmStatic
    fun fileToByteArray(file: File): ByteArray? {
        var input: InputStream? = null
        var data: ByteArray? = null
        try {
            input = FileInputStream(file)
            data = ByteArray(input.available())
            input.read(data)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return data
    }

}