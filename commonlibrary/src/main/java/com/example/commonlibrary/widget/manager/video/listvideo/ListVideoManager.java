package com.example.commonlibrary.widget.manager.video.listvideo;

import com.example.commonlibrary.widget.manager.video.base.IVideoPlayer;
import com.example.commonlibrary.widget.manager.video.nativeplayer.DefaultVideoPlayer;

/**
 * 项目名称:    Update
 * 创建人:      陈锦军
 * 创建时间:    2018/11/26     9:23
 */
public class ListVideoManager {
    private static ListVideoManager sInstance;
    private IVideoPlayer currentPlayer;

    public static ListVideoManager getInstance() {
        if (sInstance == null) {
            sInstance = new ListVideoManager();
        }
        return sInstance;
    }


    public void setCurrentPlayer(IVideoPlayer currentPlayer) {
        if (currentPlayer!=null&&!currentPlayer.equals(this.currentPlayer)) {
            if (this.currentPlayer!=null) {
                this.currentPlayer.pause();
            }
            this.currentPlayer=currentPlayer;
        }

    }

    private ListVideoManager() {

    }


    public boolean onBackPressed() {
        if (currentPlayer != null && currentPlayer.getWindowState() == DefaultVideoPlayer.WINDOW_STATE_FULL) {
            currentPlayer.setWindowState(DefaultVideoPlayer.WINDOW_STATE_LIST);
            return true;
        }
        return false;
    }


    public void release() {
        if (currentPlayer != null) {
            currentPlayer.release();
            currentPlayer = null;
        }
    }

    public void error() {
        if (currentPlayer != null) {
            currentPlayer.getController().onPlayStateChanged(DefaultVideoPlayer.PLAY_STATE_ERROR);
        }
    }


    public void start(){
        if (currentPlayer != null) {
            currentPlayer.start();
        }
    }



    public IVideoPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void pause() {
        if (currentPlayer != null) {
            currentPlayer.pause();
        }
    }

    public void reset() {
        if (currentPlayer != null) {
            currentPlayer.reset();
        }
    }
}
