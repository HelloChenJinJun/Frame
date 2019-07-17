package com.example.commonlibrary.widget.manager.video.webplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.example.commonlibrary.widget.CustomWebView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.Set;

/**
 * 项目名称:    FastFrame
 * 创建人:      陈锦军
 * 创建时间:    2019-06-24     21:04
 */
public class WebVideoView extends CustomWebView {
    private Handler mainThreadHandler;
    private boolean isDestroyed=false;

    public static class State {
        public final static int UNSTARTED = -1;
        public final static int ENDED = 0;
        public final static int PLAYING = 1;
        public final static int PAUSED = 2;
        public final static int BUFFERING = 3;
        public final static int VIDEO_CUED = 5;

        @IntDef({UNSTARTED, ENDED, PLAYING, PAUSED, BUFFERING, VIDEO_CUED})
        @Retention(RetentionPolicy.SOURCE)
        public @interface YouTubePlayerState {
        }
    }

    public static class PlaybackQuality {
        public final static int SMALL = 0;
        public final static int MEDIUM = 1;
        public final static int LARGE = 2;
        public final static int HD720 = 3;
        public final static int HD1080 = 4;
        public final static int HIGH_RES = 5;
        public final static int DEFAULT = -1;

        @IntDef({SMALL, MEDIUM, LARGE, HD720, HD1080, HIGH_RES, DEFAULT})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Quality {
        }
    }

    public static class Error {
        public final static int INVALID_PARAMENTER_IN_REQUEST = 0;
        public final static int HTML_5_PLAYER = 1;
        public final static int VIDEO_NOT_FOUND = 2;
        public final static int VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER = 3;
        public final static int PLAYER_CREATE_FAILED = 4;

        @IntDef({INVALID_PARAMENTER_IN_REQUEST
                , HTML_5_PLAYER
                , VIDEO_NOT_FOUND
                , VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER
                , PLAYER_CREATE_FAILED})
        @Retention(RetentionPolicy.SOURCE)
        public @interface PlayerError {
        }
    }

    public interface YouTubeListener {
        void onReady();

        void onStateChange(@State.YouTubePlayerState int state);

        void onPlaybackQualityChange(@PlaybackQuality.Quality int playbackQuality);

        void onPlaybackRateChange(double rate);

        void onError(@Error.PlayerError int error);

        void onApiChange();

        void onCurrentSecond(float second);

        void onVideoDuration(float duration);

        void onLog(String log);

        void onVideoTitle(String videoTitle);

        void onVideoId(String videoId);
    }

    @NonNull
    private Set<YouTubeListener> youTubeListeners=new HashSet<>();

    @NonNull
    protected Set<YouTubeListener> getListeners() {
        return youTubeListeners;
    }

    protected boolean addListener(YouTubeListener listener) {
        return youTubeListeners.add(listener);
    }

    protected boolean removeListener(YouTubeListener listener) {
        return youTubeListeners.remove(listener);
    }


    public WebVideoView(Context context) {
        this(context,null);
    }

    public WebVideoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WebVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mainThreadHandler=new Handler(Looper.getMainLooper());
        initialize();
    }


    protected void initialize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(true);
        }
        WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        this.addJavascriptInterface(new WebVideoPlayerBridge(this), "YouTubePlayerBridge");

//        String videoPlayHtml =YouTubeUtil.getYTVideoHTML();
//        if (TextUtils.isEmpty(videoPlayHtml)) {
//            videoPlayHtml = YouTubeUtil.getVideoPlayerHTML(getResources().openRawResource(R.raw.player));
//        }


        this.loadDataWithBaseURL("https://www.youtube.com", null, "text/html", "utf-8", null);

        // apparently there's a bug in the ChromeClient
        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public Bitmap getDefaultVideoPoster() {
                Bitmap result = null;
                try {
                    result = super.getDefaultVideoPoster();
                } catch (Exception ignore) {
                }

                if (result == null)
                    return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
                else
                    return result;
            }
        });

        setWebViewClient(new WebViewClient());
    }



    /**
     * This function loads and plays the specified video.
     *
     * @param videoId
     * @param startSeconds the time from which the video should start playing
     */
    protected void loadVideo(final String videoId, final float startSeconds) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                safeLoadUrl("javascript:loadVideo('" + videoId + "', " + startSeconds + ")");
            }
        });
    }

    /**
     * This function loads the specified video's thumbnail and prepares the player to play the video.
     *
     * @param videoId
     * @param startSeconds the time from which the video should start playing
     */
    protected void cueVideo(final String videoId, final float startSeconds) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                safeLoadUrl("javascript:cueVideo('" + videoId + "', " + startSeconds + ")");
            }
        });
    }

    protected void realPLay() {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                safeLoadUrl("javascript:playVideo()");
            }
        });
    }

    protected void realPause() {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                safeLoadUrl("javascript:pauseVideo()");
            }
        });
    }

    protected void realStop() {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                safeLoadUrl("javascript:stopVideo()");
            }
        });
    }

    protected void changeEq(final int freq, final float gainPercentage) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                safeLoadUrl("javascript:changeFilterGain(" + freq + "," + gainPercentage + ");");
            }
        });
    }

    private void safeLoadUrl(String url) {
        try {
            loadUrl(url);
        } catch (NullPointerException e) {
            String msg = "WebView 'origin url is : " + getOriginalUrl();
        }
    }

    protected void seekTo(final int time) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                safeLoadUrl("javascript:seekTo(" + time + ")");
            }
        });
    }
    @Override
    public void destroy() {
        super.destroy();
    }









    private int videoHeight;
    private int videoWidth;

    public void adaptVideoSize(int videoWidth, int videoHeight) {
        if (this.videoWidth != videoWidth && this.videoHeight != videoHeight) {
            this.videoWidth = videoWidth;
            this.videoHeight = videoHeight;
            requestLayout();
        }
    }

    @Override
    public void setRotation(float rotation) {
        if (rotation != getRotation()) {
            super.setRotation(rotation);
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        float viewRotation = getRotation();

        // 如果判断成立，则说明显示的TextureView和本身的位置是有90度的旋转的，所以需要交换宽高参数。
        if (viewRotation == 90f || viewRotation == 270f) {
            int tempMeasureSpec = widthMeasureSpec;
            widthMeasureSpec = heightMeasureSpec;
            heightMeasureSpec = tempMeasureSpec;
        }

        int width = getDefaultSize(videoWidth, widthMeasureSpec);
        int height = getDefaultSize(videoHeight, heightMeasureSpec);
        if (videoWidth > 0 && videoHeight > 0) {

            int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

            if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
                // the size is fixed
                width = widthSpecSize;
                height = heightSpecSize;
                // for compatibility, we adjust size based on aspect ratio
                if (videoWidth * height < width * videoHeight) {
                    width = height * videoWidth / videoHeight;
                } else if (videoWidth * height > width * videoHeight) {
                    height = width * videoHeight / videoWidth;
                }
            } else if (widthSpecMode == MeasureSpec.EXACTLY) {
                // only the width is fixed, adjust the height to match aspect ratio if possible
                width = widthSpecSize;
                height = width * videoHeight / videoWidth;
                if (heightSpecMode == MeasureSpec.AT_MOST && height > heightSpecSize) {
                    // couldn't match aspect ratio within the constraints
                    height = heightSpecSize;
                    width = height * videoWidth / videoHeight;
                }
            } else if (heightSpecMode == MeasureSpec.EXACTLY) {
                // only the height is fixed, adjust the width to match aspect ratio if possible
                height = heightSpecSize;
                width = height * videoWidth / videoHeight;
                if (widthSpecMode == MeasureSpec.AT_MOST && width > widthSpecSize) {
                    // couldn't match aspect ratio within the constraints
                    width = widthSpecSize;
                    height = width * videoHeight / videoWidth;
                }
            } else {
                // neither the width nor the height are fixed, try to use actual video size
                width = videoWidth;
                height = videoHeight;
                if (heightSpecMode == MeasureSpec.AT_MOST && height > heightSpecSize) {
                    // too tall, decrease both width and height
                    height = heightSpecSize;
                    width = height * videoWidth / videoHeight;
                }
                if (widthSpecMode == MeasureSpec.AT_MOST && width > widthSpecSize) {
                    // too wide, decrease both width and height
                    width = widthSpecSize;
                    height = width * videoHeight / videoWidth;
                }
            }
        } else {
            // no size yet, just adopt the given spec sizes
        }
        setMeasuredDimension(width, height);
    }

}
