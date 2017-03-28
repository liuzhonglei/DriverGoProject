package com.driver.go.wap;

import android.content.Context;

import com.driver.go.base.DriverGoApplication;
import com.driver.go.base.Profile;
import com.driver.go.utils.ToastManager;
import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

import cn.waps.AppConnect;

/**
 * Created by malijie on 2017/3/23.
 */

public class WapManager {
    public static WapManager sWapManager = null;
    private Context mContext = null;
    private AppConnect mAppConnect = null;
    private PayConnect mPayConnect = null;

    private WapManager(Context context){
        mContext = context;
        mAppConnect = mAppConnect.getInstance(WapProfile.WAP_APP_ID,WapProfile.WAP_APP_PID,mContext);
        mPayConnect = PayConnect.getInstance(WapProfile.WAP_APP_ID, WapProfile.WAP_APP_PID, context);

        mAppConnect.initUninstallAd(mContext);
    }

    public static WapManager getInstance(Context context){
        if(sWapManager == null){
            synchronized (WapManager.class){
                if(sWapManager == null){
                    sWapManager = new WapManager(context);
                }
            }
        }
        return sWapManager;
    }


    public PayConnect getPayConnect(){
        return mPayConnect;
    }

    public void close(){
        mAppConnect.close();
    }

    public void feedbackApp(){
        mAppConnect.showFeedback(mContext);
    }


    public void updateApp(){
        mAppConnect.checkUpdate(mContext);
    }

    public String getUserId(){
       return mPayConnect.getDeviceId(mContext);
    }

    //PayConnect.getInstance(context).pay(context,
    //orderId, userId, price, goodsName, goodsDesc, notifyUrl, new MyPayResultListener());
    public void payForExam(float price,String goodsName,String goodsDesc,PayResultListener resultListener){
        ToastManager.showShortMsg("payForExam");
        mPayConnect  = PayConnect.getInstance(WapProfile.WAP_APP_ID, WapProfile.WAP_APP_PID, mContext);
        mPayConnect.pay(mContext, getOrderId(), DriverGoApplication.mDeviceId, price, goodsName, goodsDesc, "" ,resultListener);
    }

    private String getOrderId(){
        return String.valueOf(System.currentTimeMillis() );
    }

}
