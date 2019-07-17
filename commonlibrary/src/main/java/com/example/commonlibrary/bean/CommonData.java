package com.example.commonlibrary.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名称:    FastFrame
 * 创建人:      陈锦军
 * 创建时间:    2019-05-30     14:56
 */
@Entity
public class CommonData {
    @Id
    private String id;
    private String data;

    @Generated(hash = 456985936)
    public CommonData(String id, String data) {
        this.id = id;
        this.data = data;
    }

    @Generated(hash = 1707498294)
    public CommonData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
