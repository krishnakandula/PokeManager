package com.canvas.krish.pokemanager;

/**
 * Created by Krishna Chaitanya Kandula on 1/2/2017.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
