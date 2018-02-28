package com.along.image.base;

import com.along.image.model.TableModel;

import io.reactivex.Observable;

/**
 * Created by mip on 2018/2/28.
 */

public interface BaseContract {

    abstract class Presenter extends BasePresenter{

    }

    interface Model extends BaseMode{
        Observable<TableModel> getTableList(String cateid,String p);
    }

    interface View extends BaseView{
        void returnTableList(TableModel tableModel);
    }

}
