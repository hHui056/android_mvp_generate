package com.hh.androidbaselibrary.ui.objectBox.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Unique

/**
 * Create By hHui on 2023/2/27 0027 下午 15:28
 */
@Entity
data class User(
    @Id
    var id: Long = 0,

    var name: String? = null,

    var hobby: String? = null
)