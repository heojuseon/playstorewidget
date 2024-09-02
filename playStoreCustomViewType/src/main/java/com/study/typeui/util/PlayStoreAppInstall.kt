package com.study.typeui.util

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log

object PlayStoreAppInstall {
    /**
     * 주어진 패키지 명이 설치되어 있는지 확인하는 함수
     *
     * @param context Context 객체
     * @param packageName 확인하려는 패키지 명
     * @return 설치되어 있으면 true, 설치되어 있지 않으면 false
     */
    fun isPackageInstalled(context: Context, packageName: String): Boolean{
        Log.d("!@!@#!@#", "isPackageInstalled_packageName: $packageName")
        return try {
            val packageManager = context.packageManager
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}