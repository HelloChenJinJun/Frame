package com.example.commonlibrary.widget.manager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import androidx.collection.LongSparseArray;

import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.R;
import com.example.commonlibrary.utils.CommonLogger;
import com.example.commonlibrary.widget.manager.music.base.AlbumItemBean;
import com.example.commonlibrary.widget.manager.music.base.ArtistItemBean;
import com.example.commonlibrary.widget.manager.music.base.MusicPlayBean;
import com.example.commonlibrary.widget.manager.music.base.MusicUtil;
import com.example.commonlibrary.widget.manager.video.base.VideoPlayBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by COOTEK on 2017/8/10.
 */

public class LocalInfoProvider {

    public static Cursor getSongCursor(Context context, String selection, String[] selectionArgs, String sortOrder) {
        String baseSelection = "is_music=1 AND title!= ''";
        if (!TextUtils.isEmpty(selection)) {
            baseSelection = selection + " AND " + baseSelection;
        }
        return context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{
                MediaStore.Audio.AudioColumns._ID, MediaStore.Audio.AudioColumns.TITLE
                , MediaStore.Audio.AudioColumns.ARTIST, MediaStore.Audio.AudioColumns.ARTIST_ID,
                MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.AudioColumns.ALBUM_ID,
                MediaStore.Audio.AudioColumns.TRACK, MediaStore.Audio.AudioColumns.DURATION,
                MediaStore.Audio.AudioColumns.DATA
        }, baseSelection, selectionArgs, sortOrder);
    }


    public static Observable<List<MusicPlayBean>> searchMusic(Context context, String searchString) {
        return getMusicForCursor(getSongCursor(context, "title LIKE ? or artist LIKE ? or album LIKE ? ",
                new String[]{"%" + searchString + "%", "%" + searchString + "%", "%" + searchString + "%"}));
    }

    public static Observable<List<MusicPlayBean>> getAllMusic() {
        return getMusicForCursor(getSongCursor(BaseApplication.getInstance(), null, null));

    }


    public static Observable<List<MusicPlayBean>> getMusicForPage(Context context, int num, int pageSize) {
        return getMusicForCursor(getSongCursor(context, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER + " desc limit " + pageSize + " offset " + num));
    }

    public static Observable<List<MusicPlayBean>> getMusicForPage(int num, int pageSize) {
        return getMusicForPage(BaseApplication.getInstance(), num, pageSize);
    }


    private static Observable<List<MusicPlayBean>> getMusicForCursor(final Cursor cursor) {
        return Observable.create(e -> {
            List<MusicPlayBean> list = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    MusicPlayBean music = new MusicPlayBean();
                    CommonLogger.e("data", cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)));
                    music.setSongId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID)));
                    music.setSongUrl(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)));
                    music.setSongName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)));
                    music.setArtistId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST_ID)) + "");
                    music.setArtistName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)));
                    music.setAlbumId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM_ID)));
                    music.setAlbumName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM)));
                    music.setAlbumUrl(MusicUtil.getAlbumArt(music.getAlbumId()));
                    music.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DURATION)));
                    music.setIsLocal(true);
                    list.add(music);
                } while (cursor.moveToNext());
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            e.onNext(list);
            e.onComplete();
        });
    }


    public static Cursor getSongCursor(Context context, String selection, String[] selectionArgs) {
        StringBuilder select = new StringBuilder(" 1=1 and title != ''");
        select.append(" and " + MediaStore.Audio.Media.SIZE + " > " + 1048576);
        select.append(" and " + MediaStore.Audio.Media.DURATION + " > " + 6000);
        if (!TextUtils.isEmpty(selection)) {
            select.append(" and ").append(selection);
        }
        return context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{
                MediaStore.Audio.AudioColumns._ID, MediaStore.Audio.AudioColumns.TITLE
                , MediaStore.Audio.AudioColumns.ARTIST, MediaStore.Audio.AudioColumns.ARTIST_ID,
                MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.AudioColumns.ALBUM_ID,
                MediaStore.Audio.AudioColumns.TRACK, MediaStore.Audio.AudioColumns.DURATION,
                MediaStore.Audio.AudioColumns.DATA
        }, select.toString(), selectionArgs, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }


    public static List<ArtistItemBean> getAllLocalArtistData(Context context) {
        Cursor cursor=getSongCursor(context,null,null);
        LongSparseArray<ArtistItemBean> sparseArray= new LongSparseArray<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long artistId=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST_ID));
               ArtistItemBean artistItemBean=sparseArray.get(artistId);
                if (artistItemBean == null) {
                    artistItemBean=new ArtistItemBean();
                    artistItemBean.setArtistId(artistId);
                    artistItemBean.setCount(1);
                    artistItemBean.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)));
                    sparseArray.put(artistId,artistItemBean);
                }else {
                    artistItemBean.setCount(artistItemBean.getCount()+1);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        List<ArtistItemBean> result=new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            result.add(sparseArray.valueAt(i));
        }
        return result;
    }


    public static List<AlbumItemBean> getAllLocalAlbumData(Context context) {
        Cursor cursor=getSongCursor(context,null,null);
        LongSparseArray<AlbumItemBean> sparseArray= new LongSparseArray<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long albumId=cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM_ID));
                AlbumItemBean albumItemBean=sparseArray.get(albumId);
                if (albumItemBean == null) {
                    albumItemBean=new AlbumItemBean();
                    albumItemBean.setAlbumId(albumId);
                    albumItemBean.setCount(1);
                    albumItemBean.setCover(MusicUtil.getAlbumArt(albumId));
                    albumItemBean.setAlbumName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM)));
                    sparseArray.put(albumId,albumItemBean);
                }else {
                    albumItemBean.setCount(albumItemBean.getCount()+1);
                }

            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        List<AlbumItemBean> result=new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            result.add(sparseArray.valueAt(i));
        }
        return result;
    }





    public static int getSongListCount(){
        StringBuilder select = new StringBuilder(" 1=1 and title != ''");
        select.append(" and " + MediaStore.Audio.Media.SIZE + " > " + 1048576);
        select.append(" and " + MediaStore.Audio.Media.DURATION + " > " + 6000);
        Cursor cursor= BaseApplication.getInstance().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{
                MediaStore.Audio.AudioColumns._ID, MediaStore.Audio.AudioColumns.TITLE
                , MediaStore.Audio.AudioColumns.ARTIST, MediaStore.Audio.AudioColumns.ARTIST_ID,
                MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.AudioColumns.ALBUM_ID,
                MediaStore.Audio.AudioColumns.TRACK, MediaStore.Audio.AudioColumns.DURATION,
                MediaStore.Audio.AudioColumns.DATA
        }, select.toString(), null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        return cursor==null?0:cursor.getCount();
    }


    /**
     * 根据专辑ID获取专辑封面图
     *
     * @param album_id 专辑ID
     * @return
     */
    public static Bitmap getAlbumsBitmap(long album_id) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = BaseApplication.getInstance().getContentResolver().query(Uri.parse(mUriAlbums + "/" + album_id), projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        Bitmap bm = null;
        if (album_art != null) {
            bm = BitmapFactory.decodeFile(album_art);
        } else {
            bm = BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(), R.drawable.ic_default_album_cover);
        }
        return bm;
    }



    public static List<VideoPlayBean> getVideoData(Context context) {
        List<VideoPlayBean> sysVideoList = new ArrayList<>();
        // MediaStore.Video.Thumbnails.DATA:视频缩略图的文件路径
        String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.Thumbnails.VIDEO_ID};
        // 视频其他信息的查询条件
        String[] mediaColumns = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA, MediaStore.Video.Media.DURATION
        ,MediaStore.Video.Media.RESOLUTION,MediaStore.Video.Media.TITLE};

        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media
                        .EXTERNAL_CONTENT_URI,
                mediaColumns, null, null, null);

        if (cursor == null) {
            return sysVideoList;
        }
        if (cursor.moveToFirst()) {
            do {
                VideoPlayBean info = new VideoPlayBean();
                int id = cursor.getInt(cursor
                        .getColumnIndex(MediaStore.Video.Media._ID));
                Cursor thumbCursor = context.getContentResolver().query(
                        MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                        thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID
                                + "=" + id, null, null);
                if (thumbCursor!=null) {
                    if (thumbCursor.moveToFirst()) {
                        info.setThumbPath(thumbCursor.getString(thumbCursor
                                .getColumnIndex(MediaStore.Video.Thumbnails.DATA)));
                    }
                    thumbCursor.close();
                }
                info.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media
                        .DATA)));
                info.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video
                        .Media.DURATION)));
                info.setResolution(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video
                        .Media.RESOLUTION)));
                info.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video
                        .Media.TITLE)));
                sysVideoList.add(info);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return sysVideoList;
    }


    public static int getVideoListCount(){
        // 视频其他信息的查询条件
        String[] mediaColumns = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA, MediaStore.Video.Media.DURATION};
        Cursor cursor = BaseApplication.getInstance().getContentResolver().query(MediaStore.Video.Media
                        .EXTERNAL_CONTENT_URI,
                mediaColumns, null, null, null);
        return cursor==null?0:cursor.getCount();
    }



}
