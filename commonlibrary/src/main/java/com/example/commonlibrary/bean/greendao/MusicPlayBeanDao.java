package com.example.commonlibrary.bean.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.commonlibrary.widget.manager.music.base.MusicPlayBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MUSIC_PLAY_BEAN".
*/
public class MusicPlayBeanDao extends AbstractDao<MusicPlayBean, Long> {

    public static final String TABLENAME = "MUSIC_PLAY_BEAN";

    /**
     * Properties of entity MusicPlayBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property SongId = new Property(0, long.class, "songId", true, "_id");
        public final static Property AlbumId = new Property(1, long.class, "albumId", false, "ALBUM_ID");
        public final static Property ArtistId = new Property(2, String.class, "artistId", false, "ARTIST_ID");
        public final static Property SongName = new Property(3, String.class, "songName", false, "SONG_NAME");
        public final static Property AlbumName = new Property(4, String.class, "albumName", false, "ALBUM_NAME");
        public final static Property ArtistName = new Property(5, String.class, "artistName", false, "ARTIST_NAME");
        public final static Property AlbumUrl = new Property(6, String.class, "albumUrl", false, "ALBUM_URL");
        public final static Property LrcUrl = new Property(7, String.class, "lrcUrl", false, "LRC_URL");
        public final static Property SongUrl = new Property(8, String.class, "songUrl", false, "SONG_URL");
        public final static Property Duration = new Property(9, long.class, "duration", false, "DURATION");
        public final static Property IsLocal = new Property(10, boolean.class, "isLocal", false, "IS_LOCAL");
    }


    public MusicPlayBeanDao(DaoConfig config) {
        super(config);
    }
    
    public MusicPlayBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MUSIC_PLAY_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: songId
                "\"ALBUM_ID\" INTEGER NOT NULL ," + // 1: albumId
                "\"ARTIST_ID\" TEXT," + // 2: artistId
                "\"SONG_NAME\" TEXT," + // 3: songName
                "\"ALBUM_NAME\" TEXT," + // 4: albumName
                "\"ARTIST_NAME\" TEXT," + // 5: artistName
                "\"ALBUM_URL\" TEXT," + // 6: albumUrl
                "\"LRC_URL\" TEXT," + // 7: lrcUrl
                "\"SONG_URL\" TEXT," + // 8: songUrl
                "\"DURATION\" INTEGER NOT NULL ," + // 9: duration
                "\"IS_LOCAL\" INTEGER NOT NULL );"); // 10: isLocal
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MUSIC_PLAY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MusicPlayBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getSongId());
        stmt.bindLong(2, entity.getAlbumId());
 
        String artistId = entity.getArtistId();
        if (artistId != null) {
            stmt.bindString(3, artistId);
        }
 
        String songName = entity.getSongName();
        if (songName != null) {
            stmt.bindString(4, songName);
        }
 
        String albumName = entity.getAlbumName();
        if (albumName != null) {
            stmt.bindString(5, albumName);
        }
 
        String artistName = entity.getArtistName();
        if (artistName != null) {
            stmt.bindString(6, artistName);
        }
 
        String albumUrl = entity.getAlbumUrl();
        if (albumUrl != null) {
            stmt.bindString(7, albumUrl);
        }
 
        String lrcUrl = entity.getLrcUrl();
        if (lrcUrl != null) {
            stmt.bindString(8, lrcUrl);
        }
 
        String songUrl = entity.getSongUrl();
        if (songUrl != null) {
            stmt.bindString(9, songUrl);
        }
        stmt.bindLong(10, entity.getDuration());
        stmt.bindLong(11, entity.getIsLocal() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MusicPlayBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getSongId());
        stmt.bindLong(2, entity.getAlbumId());
 
        String artistId = entity.getArtistId();
        if (artistId != null) {
            stmt.bindString(3, artistId);
        }
 
        String songName = entity.getSongName();
        if (songName != null) {
            stmt.bindString(4, songName);
        }
 
        String albumName = entity.getAlbumName();
        if (albumName != null) {
            stmt.bindString(5, albumName);
        }
 
        String artistName = entity.getArtistName();
        if (artistName != null) {
            stmt.bindString(6, artistName);
        }
 
        String albumUrl = entity.getAlbumUrl();
        if (albumUrl != null) {
            stmt.bindString(7, albumUrl);
        }
 
        String lrcUrl = entity.getLrcUrl();
        if (lrcUrl != null) {
            stmt.bindString(8, lrcUrl);
        }
 
        String songUrl = entity.getSongUrl();
        if (songUrl != null) {
            stmt.bindString(9, songUrl);
        }
        stmt.bindLong(10, entity.getDuration());
        stmt.bindLong(11, entity.getIsLocal() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public MusicPlayBean readEntity(Cursor cursor, int offset) {
        MusicPlayBean entity = new MusicPlayBean( //
            cursor.getLong(offset + 0), // songId
            cursor.getLong(offset + 1), // albumId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // artistId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // songName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // albumName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // artistName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // albumUrl
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // lrcUrl
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // songUrl
            cursor.getLong(offset + 9), // duration
            cursor.getShort(offset + 10) != 0 // isLocal
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MusicPlayBean entity, int offset) {
        entity.setSongId(cursor.getLong(offset + 0));
        entity.setAlbumId(cursor.getLong(offset + 1));
        entity.setArtistId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSongName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAlbumName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setArtistName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAlbumUrl(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLrcUrl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSongUrl(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setDuration(cursor.getLong(offset + 9));
        entity.setIsLocal(cursor.getShort(offset + 10) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MusicPlayBean entity, long rowId) {
        entity.setSongId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MusicPlayBean entity) {
        if(entity != null) {
            return entity.getSongId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MusicPlayBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}