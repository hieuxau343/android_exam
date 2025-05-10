package com.example.finalexam

import android.content.Context
import android.content.SharedPreferences
import com.example.finalexam.data.model.User
import com.google.gson.Gson

class SharedPrefsHelper(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("music_app", Context.MODE_PRIVATE)
    private val gson = Gson()


    // Lưu thông tin User
    fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        prefs.edit().putString("user_info", userJson).apply()
    }

    // Lấy thông tin User
    fun getUser(): User? {
        val userJson = prefs.getString("user_info", null) ?: return null
        return gson.fromJson(userJson, User::class.java)
    }

    // Xóa thông tin User
    fun clearUser() {
        prefs.edit().remove("user_info").apply()
    }
}