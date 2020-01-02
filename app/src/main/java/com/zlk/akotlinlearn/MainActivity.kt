package com.zlk.akotlinlearn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zlk.akotlinlearn.common.ActivityUtils

const val TAG_FLAG_MAIN: String = "main"

class MainActivity : AppCompatActivity() {

    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mainFragment =
            supportFragmentManager.findFragmentByTag(TAG_FLAG_MAIN) as MainFragment?
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance()
            ActivityUtils.addFragment2Activity(
                supportFragmentManager,
                mainFragment,
                R.id.container,
                TAG_FLAG_MAIN
            )
        }

        mPresenter = MainPresenter(mainFragment)
    }
}
