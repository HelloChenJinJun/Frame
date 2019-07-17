package com.example.commonlibrary.widget.manager.video.webplayer;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import androidx.annotation.NonNull;

/**
 * WEB TO APP bridge
 */
public class WebVideoPlayerBridge {

    @NonNull private final WebVideoView webVideoPlayer;
    @NonNull private final Handler mainThreadHandler;

    public WebVideoPlayerBridge(@NonNull WebVideoView webVideoPlayer) {
        this.webVideoPlayer = webVideoPlayer;
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @JavascriptInterface
    public void onReady() {
      mainThreadHandler.post(new Runnable() {
        @Override
        public void run() {
          for (WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners()) {
            listener.onReady();
          }
        }
      });
    }

    @JavascriptInterface
    public void onStateChange(final String state) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                    if ("UNSTARTED".equalsIgnoreCase(state))
                        listener.onStateChange(WebVideoView.State.UNSTARTED);
                    else if ("ENDED".equalsIgnoreCase(state))
                        listener.onStateChange(WebVideoView.State.ENDED);
                    else if ("PLAYING".equalsIgnoreCase(state))
                        listener.onStateChange(WebVideoView.State.PLAYING);
                    else if ("PAUSED".equalsIgnoreCase(state))
                        listener.onStateChange(WebVideoView.State.PAUSED);
                    else if ("BUFFERING".equalsIgnoreCase(state))
                        listener.onStateChange(WebVideoView.State.BUFFERING);
                    else if ("CUED".equalsIgnoreCase(state))
                        listener.onStateChange(WebVideoView.State.VIDEO_CUED);
            }
        });
    }

    @JavascriptInterface
    public void onPlaybackQualityChange(final String quality) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                    if ("small".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(WebVideoView.PlaybackQuality.SMALL);
                    else if ("medium".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(WebVideoView.PlaybackQuality.MEDIUM);
                    else if ("large".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(WebVideoView.PlaybackQuality.LARGE);
                    else if ("hd720".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(WebVideoView.PlaybackQuality.HD720);
                    else if ("hd1080".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(WebVideoView.PlaybackQuality.HD1080);
                    else if ("highres".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(WebVideoView.PlaybackQuality.HIGH_RES);
                    else if ("default".equalsIgnoreCase(quality))
                        listener.onPlaybackQualityChange(WebVideoView.PlaybackQuality.DEFAULT);
            }
        });
    }

    /**
     *  The default playback rate is 1, which indicates that the video is playing at normal speed. Playback rates may include values like 0.25, 0.5, 1, 1.5, and 2.
     *  <br/><br/>TODO add constants
     * @param rate 0.25, 0.5, 1, 1.5, 2
     */
    @JavascriptInterface
    public void onPlaybackRateChange(final String rate) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    double dRate = Double.parseDouble(rate);
                    for (WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                        listener.onPlaybackRateChange(dRate);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @JavascriptInterface
    public void onError(final String error) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners()) {
                    if ("2".equalsIgnoreCase(error))
                        listener.onError(WebVideoView.Error.INVALID_PARAMENTER_IN_REQUEST);
                    else if ("5".equalsIgnoreCase(error))
                        listener.onError(WebVideoView.Error.HTML_5_PLAYER);
                    else if ("100".equalsIgnoreCase(error))
                        listener.onError(WebVideoView.Error.VIDEO_NOT_FOUND);
                    else if ("101".equalsIgnoreCase(error))
                        listener.onError(WebVideoView.Error.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER);
                    else if ("150".equalsIgnoreCase(error))
                        listener.onError(WebVideoView.Error.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER);
                }
            }
        });
    }

    @JavascriptInterface
    public void onApiChange() {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                    listener.onApiChange();
            }
        });
    }

    @JavascriptInterface
    public void currentSeconds(final String seconds) {
        final float fSeconds;
        try {
            fSeconds = Float.parseFloat(seconds);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                    listener.onCurrentSecond(fSeconds);
            }
        });
    }

    @JavascriptInterface
    public void onVideoTitle(final String videoTitle) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                    listener.onVideoTitle(videoTitle);
            }
        });
    }

    @JavascriptInterface
    public void onVideoId(final String videoId) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                    listener.onVideoId(videoId);
            }
        });
    }

    @JavascriptInterface
    public void onVideoDuration(final String seconds) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    String finalSeconds = TextUtils.isEmpty(seconds) ? "0" : seconds;
                    float videoDuration = Float.parseFloat(finalSeconds);
                    for (WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                        listener.onVideoDuration(videoDuration);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @JavascriptInterface
    public void onLog(final String message) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for(WebVideoView.YouTubeListener listener : webVideoPlayer.getListeners())
                    listener.onLog(message);
            }
        });
    }
}
