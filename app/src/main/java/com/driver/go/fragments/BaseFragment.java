package com.driver.go.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.driver.go.activity.DriverBaseActivity;
import com.driver.go.db.DBConstants;
import com.driver.go.db.SQLiteManager;
import com.driver.go.entity.QuestionItem;

/**
 * Created by malijie on 2017/3/15.
 */

public class BaseFragment extends Fragment {
    protected SQLiteManager mSQLiteManager = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSQLiteManager = SQLiteManager.getInstance();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public boolean isDownloadSubject1DB(){
        if(mSQLiteManager != null){
            return mSQLiteManager.queryOrderQuestionById(DriverBaseActivity.SUBJECT_TYPE_1,1).moveToFirst();
        }
        return false;
    }


    public void addOrderQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.SUBJECT1_ORDER_EXAM_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }
}