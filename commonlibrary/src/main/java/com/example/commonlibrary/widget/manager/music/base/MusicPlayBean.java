package com.example.commonlibrary.widget.manager.music.base;

import androidx.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by COOTEK on 2017/8/18.
 */
@Entity
public class MusicPlayBean implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    private long songId;
    private long albumId;
    private String artistId;
    private String songName;
    private String albumName;
    private String artistName;
    private String albumUrl;
    private String lrcUrl;
    private String songUrl;
    private long duration;
    private boolean isLocal;


    public boolean isLocal() {
        return isLocal;
    }


    public void setLocal(boolean local) {
        isLocal = local;
    }

    @Generated(hash = 112788813)
    public MusicPlayBean(long songId, long albumId, String artistId,
                         String songName, String albumName, String artistName, String albumUrl,
                         String lrcUrl, String songUrl, long duration, boolean isLocal) {
        this.songId = songId;
        this.albumId = albumId;
        this.artistId = artistId;
        this.songName = songName;
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumUrl = albumUrl;
        this.lrcUrl = lrcUrl;
        this.songUrl = songUrl;
        this.duration = duration;
        this.isLocal = isLocal;
    }


    @Generated(hash = 1105425544)
    public MusicPlayBean() {
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof MusicPlayBean
                && ((MusicPlayBean) obj).getSongId() == getSongId();
    }




    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public String getLrcUrl() {
        return lrcUrl;
    }

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public boolean getIsLocal() {
        return this.isLocal;
    }


    public void setIsLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }
}