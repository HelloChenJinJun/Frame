package com.example.commonlibrary.widget.manager.video.webplayer;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.commonlibrary.utils.CommonLogger;
import com.example.commonlibrary.utils.DensityUtil;
import com.example.commonlibrary.widget.manager.video.base.IVideoPlayer;
import com.example.commonlibrary.widget.manager.video.base.VideoController;
import com.example.commonlibrary.widget.manager.video.listvideo.ListVideoManager;
import com.example.commonlibrary.widget.manager.video.nativeplayer.DefaultVideoController;

import java.util.List;
import java.util.Map;

/**
 * 项目名称:    FastFrame
 * 创建人:      陈锦军
 * 创建时间:    2019-06-25     10:56
 */
public class WebVideoPlayer extends FrameLayout implements IVideoPlayer, WebVideoView.YouTubeListener {
    private int mWindowState = -1;

    //    播放未开始
    public static final int PLAY_STATE_IDLE = 0;
    //  资源准备中
    public static final int PLAY_STATE_PREPARING = 1;
    //    资源准备就绪
    public static final int PLAY_STATE_PREPARED = 2;
    //    播放中
    public static final int PLAY_STATE_PLAYING = 3;
    //    暂停中
    public static final int PLAY_STATE_PAUSE = 4;
    //    缓存播放中
    public static final int PLAY_STATE_BUFFERING_PLAYING = 5;
    //    缓存暂停中
    public static final int PLAY_STATE_BUFFERING_PAUSE = 6;
    //    播放完成
    public static final int PLAY_STATE_FINISH = 7;

    public static final int PLAY_STATE_ERROR = 8;


    //全屏模式
    public static final int WINDOW_STATE_FULL = 0;
    //    列表模式
    public static final int WINDOW_STATE_LIST = 1;

    //    小窗口tiny模式
    public static final int WINDOW_STATE_TINY = 2;
    private RelativeLayout container;
    private WebVideoView webView;
    private String url;
    private Map<String, String> headers;
    private AudioManager audioManager;
    private float duration;
    private String title;
    private float second;
    private int mState = PLAY_STATE_IDLE;

    public WebVideoPlayer(@NonNull Context context) {
        this(context,null);
    }

    public WebVideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WebVideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initWebView();
        initData();
    }

    private void initData() {
        if (audioManager == null) {
            audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) {
                audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            }
        }
    }

    private VideoController mVideoController;

    private void initView() {
        container = new RelativeLayout(getContext());
        setVideoController(new DefaultVideoController(getContext()));
        setWindowState(WINDOW_STATE_LIST);
    }


    public void setVideoController(VideoController videoController) {
        if (videoController != null && !videoController.equals(mVideoController)) {
            container.removeView(mVideoController);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(videoController, layoutParams);
            videoController.setIVideoPlayer(this);
            videoController.reset();
            mVideoController = videoController;
        }
    }


    private void initWebView() {
        if (webView != null) {
            container.removeView(webView);
        } else {
            webView = new WebVideoView(getContext());
        }
        webView.addListener(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        container.addView(webView, 0, layoutParams);
    }

    @Override
    public IVideoPlayer setUp(String url, Map<String, String> headers) {
        this.url = url;
        this.headers = headers;
        return this;
    }

    @Override
    public void setState(int state) {
            this.mState=state;
    }

    @Override
    public void start() {
        if (mState==PLAY_STATE_PLAYING|| mState == PLAY_STATE_BUFFERING_PLAYING){
            return;
        }
        if (mState == PLAY_STATE_PAUSE) {
            mState=PLAY_STATE_PLAYING;
            mVideoController.onPlayStateChanged(mState);
            webView.realPLay();
            return;
        }
        mState = PLAY_STATE_PREPARING;
        webView.loadVideo(url,0);
        CommonLogger.e("start");
        ListVideoManager.getInstance().setCurrentPlayer(this);
    }

    @Override
    public void pause() {
        webView.realPause();
    }

    @Override
    public void seekTo(int position) {
            webView.seekTo(position/1000);
    }

    @Override
    public IVideoPlayer setVolume(int volume) {
        if (audioManager != null) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        }
        return this;
    }

    @Override
    public int getMaxVolume() {
        return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    @Override
    public int getVolume() {
        return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    @Override
    public long getDuration() {
        return (long) duration;
    }

    @Override
    public void prepareAsync() {

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public long getPosition() {
        return (long) second;
    }

    @Override
    public int getBufferedPercentage() {
        return 0;
    }

    @Override
    public IVideoPlayer setTitle(String title) {
        this.title=title;
        return this;
    }

    @Override
    public IVideoPlayer setTotalLength(long length) {
        this.duration=length;
        return this;
    }

    @Override
    public int getCurrentState() {
        return mState;
    }

    @Override
    public int getWindowState() {
        return mWindowState;
    }

    @Override
    public IVideoPlayer setWindowState(int state) {
        if (state != mWindowState) {
            FrameLayout contentView = ((AppCompatActivity) getContext()).findViewById(android.R.id.content);
            if (contentView.indexOfChild(container) >= 0) {
                contentView.removeView(container);
            }
            if (indexOfChild(container) >= 0) {
                removeView(container);
            }
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mWindowState = state;
            if (mWindowState == WINDOW_STATE_FULL) {
                AppCompatActivity appCompatActivity = ((AppCompatActivity) getContext());
                ActionBar actionBar = appCompatActivity.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }
//                appCompatActivity.getWindow()
//                        .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                if (webView.getWidth() >= webView.getHeight()) {
                    appCompatActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                contentView.addView(container, layoutParams);
//                webView.setRotation(90);
            } else if (mWindowState == WINDOW_STATE_LIST) {
                AppCompatActivity appCompatActivity = ((AppCompatActivity) getContext());
//                appCompatActivity.getWindow()
//                        .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                appCompatActivity.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                ActionBar ab = appCompatActivity.getSupportActionBar();
                if (ab != null) {
                    ab.show();
                }
                appCompatActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                addView(container, layoutParams);
            } else if (mWindowState == WINDOW_STATE_TINY) {
                layoutParams.width = (int) (DensityUtil.getScreenWidth(getContext()) * 0.6f);
                layoutParams.height = (int) (DensityUtil.getScreenWidth(getContext()) * 0.6f * 9 / 16);
                layoutParams.bottomMargin = DensityUtil.toDp(7);
                layoutParams.rightMargin = DensityUtil.toDp(7);
                layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
                contentView.addView(container, layoutParams);
            }
            mVideoController.onWindowStateChanged(mWindowState);
        }
        return this;
    }

    @Override
    public void reset() {
//        webView.reload();
        mState=PLAY_STATE_IDLE;
        mVideoController.onPlayStateChanged(mState);
    }

    @Override
    public void release() {
//        webView.destroy();
        mState=PLAY_STATE_IDLE;
        mVideoController.onPlayStateChanged(mState);
    }

    @Override
    public IVideoPlayer setClarity(List<VideoController.Clarity> clarityList) {
        return this;
    }

    @Override
    public IVideoPlayer setImageCover(String imageUrl) {
        mVideoController.setImageCover(imageUrl);
        return this;
    }

    @Override
    public VideoController getController() {
        return mVideoController;
    }

    @Override
    public String getUrl() {
        return url;
    }











    @Override
    public void onReady() {
        CommonLogger.e("onReady");
        mState=PLAY_STATE_PREPARED;
        mVideoController.onPlayStateChanged(mState);
    }

    @Override
    public void onStateChange(int state) {
//        UNSTARTED, ENDED, PLAYING, PAUSED, BUFFERING, VIDEO_CUED
        if (state == WebVideoView.State.PLAYING) {
            mState=PLAY_STATE_PLAYING;
        }
        if (state == WebVideoView.State.ENDED) {
            mState=PLAY_STATE_FINISH;
        }
        if (state == WebVideoView.State.UNSTARTED) {
            mState=PLAY_STATE_PREPARING;
        }

        if (state == WebVideoView.State.PAUSED) {
            mState=PLAY_STATE_PAUSE;
        }
        if (state == WebVideoView.State.BUFFERING) {
            mState=PLAY_STATE_PREPARED;
        }
        mVideoController.onPlayStateChanged(mState);
        CommonLogger.e("onStateChange"+state);
    }

    @Override
    public void onPlaybackQualityChange(int playbackQuality) {
        CommonLogger.e("onPlaybackQualityChange"+playbackQuality);
    }

    @Override
    public void onPlaybackRateChange(double rate) {
        CommonLogger.e("onPlaybackRateChange"+rate);
    }

    @Override
    public void onError(int error) {
        mState=PLAY_STATE_ERROR;
        mVideoController.onPlayStateChanged(mState);
        CommonLogger.e("onError"+error);
    }

    @Override
    public void onApiChange() {
        CommonLogger.e("onApiChange");
    }

    @Override
    public void onCurrentSecond(float second) {
        CommonLogger.e("onCurrentSecond"+second);
            this.second=second*1000L;
    }

    @Override
    public void onVideoDuration(float duration) {
        CommonLogger.e("onVideoDuration"+duration);
        this.duration=duration*1000L;
    }

    @Override
    public void onLog(String log) {
        CommonLogger.e("onLog:"+log);
    }

    @Override
    public void onVideoTitle(String videoTitle) {
        CommonLogger.e("onVideoTitle:"+videoTitle);
            this.title=videoTitle;
    }

    @Override
    public void onVideoId(String videoId) {
        CommonLogger.e("videoId:"+videoId);
    }
}
