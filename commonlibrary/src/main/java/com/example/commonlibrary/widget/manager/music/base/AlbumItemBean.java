package com.example.commonlibrary.widget.manager.music.base;

/**
 * 项目名称:    and-incentive-sdk
 * 创建人:      陈锦军
 * 创建时间:    2019-07-01     17:00
 */
public class AlbumItemBean {
    private long albumId;
    private String albumName;
    private long count;

    private String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
