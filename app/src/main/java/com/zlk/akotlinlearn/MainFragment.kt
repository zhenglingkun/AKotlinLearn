package com.zlk.akotlinlearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_main.*

class MainFragment : Fragment(), MainContract.View {

    lateinit var mPresenter: MainContract.Presenter

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun setPresenter(presenter: MainContract.Presenter?) {
        mPresenter = checkNotNull(presenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onStart()
    }

    override fun showSuite(suit: String) {
        tvSuite?.text = suit
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    override fun isActive(): Boolean {
        return isAdded
    }
}