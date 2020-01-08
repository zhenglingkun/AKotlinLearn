package com.zlk.akotlinlearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.frag_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle?.text = getString(R.string.app_name)
        swipeToLoadLayout?.setOnRefreshListener {
            mPresenter.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onStart()
    }

    override fun refreshComplete() {
        swipeToLoadLayout?.isRefreshing = false
    }

    override fun showHoliday(holiday: String, desc: String) {
        val s: String = getString(R.string.stub_holiday)
        tvHoliday?.text = String.format(s, holiday, desc)
        tvHoliday?.visibility = View.VISIBLE
    }

    override fun hideHoliday() {
        tvHoliday?.text = ""
        tvHoliday?.visibility = View.GONE
    }

    override fun showWeekday(weekday: String) {
        tvWeekday?.text = weekday
    }

    override fun showDate(dates: MutableList<String>) {
        val s: String = getString(R.string.stub_date)
        tvDate?.text = String.format(s, dates[0], dates[1], dates[2])
    }

    override fun showLunar(lunar: String) {
        val s: String = getString(R.string.stub_lunar)
        tvLunar?.text = String.format(s, lunar)
    }

    override fun showLunarYear(lunarYear: String) {
        tvLunarYear?.text = lunarYear
    }

    override fun showAnimalsYear(animalsYear: String) {
        tvAnimalsYear?.text = animalsYear
    }

    override fun showSuite(suit: String) {
        val s: String = getString(R.string.stub_suite)
        tvSuite?.text = String.format(s, suit)
    }

    override fun showAvoid(avoid: String) {
        val s: String = getString(R.string.stub_avoid)
        tvAvoid?.text = String.format(s, avoid)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    override fun isActive(): Boolean {
        return isAdded
    }
}