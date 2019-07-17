package com.example.commonlibrary.net.download;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-07-31     17:24
 */
public class DownloadBean implements Serializable {

    private String filePath;
    private String url;
    private List<DownloadSubItem> subItemList;
    private long totalSize;
    private long loadedSize;
    private int status;
    private long speed;


    @Override
    public String toString() {
        return "DownloadBean{" +
                "filePath='" + filePath + '\'' +
                ", url='" + url + '\'' +
                ", subItemList=" + subItemList +
                ", totalSize=" + totalSize +
                ", loadedSize=" + loadedSize +
                ", status=" + status +
                ", speed=" + speed +
                '}';
    }

    public DownloadBean(String filePath, String url, @DownloadTask.Status.DStatus int status) {
        this.filePath = filePath;
        this.url = url;
        this.status = status;
    }


    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }


    public void setLoadedSize(long loadedSize) {
        this.loadedSize = loadedSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public long getLoadedSize() {
        return loadedSize;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSubItemList(List<DownloadSubItem> subItemList) {
        this.subItemList = subItemList;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getUrl() {
        return url;
    }

    public List<DownloadSubItem> getSubItemList() {
        return subItemList;
    }

    public int getStatus() {
        return status;
    }

    public static class DownloadSubItem {
        private volatile long startPosition;
        private volatile long endPosition;
        private volatile long currentPosition;
        private long speed;
        private DownloadProgressListener listener;


        @Override
        public String toString() {
            return "DownloadSubItem{" +
                    "startPosition=" + startPosition +
                    ", endPosition=" + endPosition +
                    ", currentPosition=" + currentPosition +
                    ", speed=" + speed +
                    '}';
        }

        public DownloadSubItem(long startPosition, long endPosition) {
            this.startPosition = startPosition;
            this.endPosition = endPosition;
        }


        public void setDownloadProgressListener(DownloadProgressListener downloadProgressListener) {
            this.listener = downloadProgressListener;
        }

        public DownloadProgressListener getDownloadProgressListener() {
            return listener;
        }

        public void setSpeed(long speed) {
            this.speed = speed;
        }

        public long getSpeed() {
            return speed;
        }

        public void setCurrentPosition(long currentPosition) {
            this.currentPosition = currentPosition;
        }


        public long getCurrentPosition() {
            return currentPosition;
        }

        public long getEndPosition() {
            return endPosition;
        }

        public long getStartPosition() {
            return startPosition;
        }
    }
}
