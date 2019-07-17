package com.example.commonlibrary.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 项目名称:    zhuayu_android
 * 创建人:      陈锦军
 * 创建时间:    2018/10/15     21:09
 */
public class TimeUtil {

    /**
     * 将毫秒数格式化为"##:##"的时间
     *
     * @param milliseconds 毫秒数
     * @return ##:##
     */
    public static String formatTime(long milliseconds) {
        if (milliseconds <= 0 || milliseconds >= 24 * 60 * 60 * 1000) {
            return "00:00";
        }
        long totalSeconds = milliseconds / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        StringBuilder stringBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(stringBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    public static String getTime(long time, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(time);
    }


    public static String getTime(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }


    public static Disposable countDown(int time, Consumer<Long> consumer) {
        return countDown(time, consumer, null);
    }


    public static Disposable countDown(long time, Consumer<Long> consumer, Consumer<Long> complete) {
        return Flowable.intervalRange(0, time + 1, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterNext(aLong -> {
                    if (consumer!=null) {
                        consumer.accept(aLong);
                    }
                    if (complete != null && time - aLong == 0) {
                        complete.accept(aLong);
                    }
                }).subscribe();
    }


    public static long HOUR_LEN_IN_SECOND = 60 * 60;
    public static long MIN_LEN_IN_SECOND = 60;
    public static long SECOND_LEN_IN_SECOND = 1;
    public static long parseFormatTimeForSeconds(String formatTime) {

        if (TextUtils.isEmpty(formatTime)) {
            return -1;
        }
        String[] parts = formatTime.split(":");
        int size = parts.length;
        if (size > 3 || size == 1) {
            return -1;
        }
        for (String part : parts) {
            if (TextUtils.isEmpty(part)) {
                return -1;
            }
        }

        long hour = 0, min = 0, second = 0;
        try {
            if (size == 3) {
                hour = Long.parseLong(parts[0]);
                min = Long.parseLong(parts[1]);
                second = Long.parseLong(parts[2]);
            }
            if (size == 2) {
                min = Long.parseLong(parts[0]);
                second = Long.parseLong(parts[1]);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
            return -1;
        }
        return hour * HOUR_LEN_IN_SECOND + min * MIN_LEN_IN_SECOND + second * SECOND_LEN_IN_SECOND;
    }
}
