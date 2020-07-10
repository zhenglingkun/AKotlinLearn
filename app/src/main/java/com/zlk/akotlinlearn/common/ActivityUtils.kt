package com.zlk.akotlinlearn.common

import android.text.TextUtils
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class ActivityUtils {
    companion object {
        fun addFragment2Activity(
            @NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int,
            flag: String
        ) {
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            if (TextUtils.isEmpty(flag)) {
                transaction.add(frameId, fragment)
            } else {
                transaction.add(frameId, fragment, flag)
            }
            transaction.commit()
        }
    }
}