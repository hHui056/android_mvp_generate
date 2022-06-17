package com.hh.baselibrary.util.licence

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.annotation.JSONField
import java.util.*

/**
 * Create By hHui on 2022/6/17 0017 下午 14:09
 */
class Licence {
    var permanentValidity: Boolean = false

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    var endAt: Date? = null

    override fun toString(): String {
        return JSON.toJSONString(this)
    }
}