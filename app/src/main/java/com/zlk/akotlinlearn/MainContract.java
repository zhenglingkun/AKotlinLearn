package com.zlk.akotlinlearn;

import com.zlk.akotlinlearn.common.BasePresenter;
import com.zlk.akotlinlearn.common.BaseView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void showSuite(@NotNull String suit);

        void showWeekday(@NotNull String weekday);

        void showHoliday(@NotNull String holiday, String desc);

        void showDate(@NotNull List<String> dates);

        void hideHoliday();

        void showAvoid(@NotNull String avoid);

        void showLunar(@NotNull String lunar);

        void showLunarYear(@NotNull String lunarYear);

        void showAnimalsYear(@NotNull String animalsYear);
    }

    interface Presenter extends BasePresenter {

    }

}
