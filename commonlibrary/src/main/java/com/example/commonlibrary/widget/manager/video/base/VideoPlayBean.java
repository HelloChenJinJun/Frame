package com.example.commonlibrary.widget.manager.video.base;

import androidx.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 项目名称:    FastFrame
 * 创建人:      陈锦军
 * 创建时间:    2019-06-06     10:55
 */

@Entity
public class VideoPlayBean {

    @Id
    private String path;
    private String thumbPath;
    private long duration;
    private String resolution;
    private String title;
    private String provider;
    private long updateTime;
    private boolean isFavorite;
    private String albumName;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public boolean isFavorite() {
        return isFavorite;
    }


    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public long getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Generated(hash = 145536821)
    public VideoPlayBean(String path, String thumbPath, long duration,
                         String resolution, String title, String provider, long updateTime,
                         boolean isFavorite, String albumName) {
        this.path = path;
        this.thumbPath = thumbPath;
        this.duration = duration;
        this.resolution = resolution;
        this.title = title;
        this.provider = provider;
        this.updateTime = updateTime;
        this.isFavorite = isFavorite;
        this.albumName = albumName;
    }

    @Generated(hash = 1502599342)
    public VideoPlayBean() {
    }

    public String getProvider() {
        return provider;
    }


    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof VideoPlayBean
                && ((VideoPlayBean) obj)
                .getPath().equals(getPath());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResolution() {
        return resolution;
    }


    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public boolean getIsFavorite() {
        return this.isFavorite;
    }


    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }





}
