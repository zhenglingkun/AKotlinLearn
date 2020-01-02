package com.zlk.akotlinlearn;

import com.zlk.akotlinlearn.common.BasePresenter;
import com.zlk.akotlinlearn.common.BaseView;

import org.jetbrains.annotations.NotNull;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void showSuite(@NotNull String suit);
    }

    interface Presenter extends BasePresenter {

    }

}
