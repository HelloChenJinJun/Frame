package com.example.commonlibrary.widget.manager.music.base;

import android.database.Cursor;
import android.net.Uri;

import com.example.commonlibrary.BaseApplication;

/**
 * 项目名称:    FastFrame
 * 创建人:      陈锦军
 * 创建时间:    2019-06-06     10:34
 */
public class MusicUtil {
    public static final String RECENT_SONG_URL_LIST = "recent_song_url_list";
    public static final String SEEK = "seek";
    public static final String MUSIC_POSITION = "music_position";
    private static int sRandomId=-1;

    public static String getAlbumArt(long album_id) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = BaseApplication.getInstance().getContentResolver().query(Uri.parse(mUriAlbums + "/" + album_id), projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        return album_art;
    }
}
