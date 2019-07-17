package com.example.frame.mvp.download;

import com.example.commonlibrary.bean.BaseBean;
import com.example.commonlibrary.mvp.model.DefaultModel;
import com.example.commonlibrary.mvp.presenter.BaseListPresenter;
import com.example.frame.bean.DownloadItemBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 项目名称:    and-incentive-sdk
 * 创建人:      陈锦军
 * 创建时间:    2019-07-01     20:49
 */
class DownLoadPresenter extends BaseListPresenter<DownloadItemBean,DefaultModel> {





    @Inject
    public DownLoadPresenter(DefaultModel baseModel) {
        super(baseModel);
    }

    @Override
    protected void getData(int page) {

        List<DownloadItemBean> apks = new ArrayList<>();
        DownloadItemBean apk1 = new DownloadItemBean();
        apk1.name = "爱奇艺";
        apk1.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/0c10c4c0155c9adf1282af008ed329378d54112ac";
        apk1.url = "http://wap.apk.anzhi.com/data5/apk/201906/19/0f47fb45613624d9f7070ac4935219c5_44031100.apk";
        apks.add(apk1);
        DownloadItemBean apk2 = new DownloadItemBean();
        apk2.name = "微信";
        apk2.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/00814b5dad9b54cc804466369c8cb18f23e23823f";
        apk2.url = "http://wap.apk.anzhi.com/data5/apk/201904/24/dbb6e433381352b49a345e549a12a724_80932900.apk";
        apks.add(apk2);
        DownloadItemBean apk3 = new DownloadItemBean();
        apk3.name = "新浪微博";
        apk3.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/01db44d7f809430661da4fff4d42e703007430f38";
        apk3.url = "http://wap.apk.anzhi.com/data5/apk/201906/26/66fd0feda3e642af25694fbedd2e2530_37464600.apk";
        apks.add(apk3);
        DownloadItemBean apk4 = new DownloadItemBean();
        apk4.name = "QQ";
        apk4.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/072725ca573700292b92e636ec126f51ba4429a50";
        apk4.url = "http://wap.apk.anzhi.com/data5/apk/201906/14/af9696f0d7c405fd254c0f1b5d82206c_11139200.apk";
        apks.add(apk4);
        DownloadItemBean apk5 = new DownloadItemBean();
        apk5.name = "陌陌";
        apk5.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/06006948e655c4dd11862d060bd055b4fd2b5c41b";
        apk5.url = "http://wap.apk.anzhi.com/data5/apk/201906/17/7940864ef36338e4639344aecafd3377_45457500.apk";
        apks.add(apk5);
        DownloadItemBean apk6 = new DownloadItemBean();
        apk6.name = "手机淘宝";
        apk6.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/017a859792d09d7394108e0a618411675ec43f220";
        apk6.url = "http://wap.apk.anzhi.com/data5/apk/201907/01/a2f52e7d1f0779e5fb10a61e87dd0250_74741100.apk";
        apks.add(apk6);
        DownloadItemBean apk7 = new DownloadItemBean();
        apk7.name = "酷狗音乐";
        apk7.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/0f2f050e21e42f75c7ecca55d01ac4e5e4e40ca8d";
        apk7.url = "http://wap.apk.anzhi.com/data5/apk/201906/25/67dc514042dcfc29a16ab233c8f7714e_03812700.apk";
        apks.add(apk7);
        DownloadItemBean apk9 = new DownloadItemBean();
        apk9.name = "ofo共享单车";
        apk9.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/0fe1a5c6092f3d9fa5c4c1e3158e6ff33f6418152";
        apk9.url = "http://wap.apk.anzhi.com/data5/apk/201906/24/so.ofo.labofo_12451100.apk";
        apks.add(apk9);
        DownloadItemBean apk10 = new DownloadItemBean();
        apk10.name = "摩拜单车";
        apk10.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/0863a058a811148a5174d9784b7be2f1114191f83";
        apk10.url = "http://wap.apk.anzhi.com/data5/apk/201906/28/com.mobike.mobikeapp_50581500.apk";
        apks.add(apk10);
        DownloadItemBean apk11 = new DownloadItemBean();
        apk11.name = "贪吃蛇大作战";
        apk11.iconUrl = "http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/09f7f5756d9d63bb149b7149b8bdde0769941f09b";
        apk11.url = "http://wap.apk.anzhi.com/data5/apk/201906/20/9fb8cfb181609de919900038e0f30c3c_77861800.apk";
        apks.add(apk11);
        BaseBean<List<DownloadItemBean>> baseBean=new BaseBean<>();
        baseBean.setData(apks);
        iView.updateData(baseBean);
        iView.hideLoading();
    }
}
