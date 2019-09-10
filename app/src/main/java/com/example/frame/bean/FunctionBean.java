package com.example.frame.bean;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-08-02     14:50
 */
public class FunctionBean {
    private String title;
    private String fragmentTitle;


    public FunctionBean(String title, String fragmentTitle) {
        this.title = title;
        this.fragmentTitle = fragmentTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFragmentTitle() {
        return fragmentTitle;
    }

    public void setFragmentTitle(String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
    }
}
