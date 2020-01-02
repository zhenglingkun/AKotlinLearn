package com.zlk.akotlinlearn.common;

public interface BaseView<T> {
    void setPresenter(T presenter);

    boolean isActive();
}
