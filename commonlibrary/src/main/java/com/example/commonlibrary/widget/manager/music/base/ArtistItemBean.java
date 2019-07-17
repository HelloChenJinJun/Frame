package com.example.commonlibrary.widget.manager.music.base;

import androidx.annotation.Nullable;

/**
 * 项目名称:    and-incentive-sdk
 * 创建人:      陈锦军
 * 创建时间:    2019-07-01     16:02
 */
public class ArtistItemBean {
    private long artistId;
    private String name;
    private int count;


    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof ArtistItemBean&& ((ArtistItemBean) obj).getArtistId()==getArtistId();
    }
}
