package com.study.app_room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    // 查詢所有 user
    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    // 新增(含多筆批次)
    @Insert
    fun insert(vararg user: User)
}