package com.example.commonlibrary.bean.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.commonlibrary.net.download.FileInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FILE_INFO".
*/
public class FileInfoDao extends AbstractDao<FileInfo, String> {

    public static final String TABLENAME = "FILE_INFO";

    /**
     * Properties of entity FileInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Url = new Property(0, String.class, "url", true, "URL");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property TotalBytes = new Property(2, int.class, "totalBytes", false, "TOTAL_BYTES");
        public final static Property LoadBytes = new Property(3, int.class, "loadBytes", false, "LOAD_BYTES");
        public final static Property Speed = new Property(4, int.class, "speed", false, "SPEED");
        public final static Property Status = new Property(5, int.class, "status", false, "STATUS");
        public final static Property Path = new Property(6, String.class, "path", false, "PATH");
    }


    public FileInfoDao(DaoConfig config) {
        super(config);
    }
    
    public FileInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FILE_INFO\" (" + //
                "\"URL\" TEXT PRIMARY KEY NOT NULL ," + // 0: url
                "\"NAME\" TEXT," + // 1: name
                "\"TOTAL_BYTES\" INTEGER NOT NULL ," + // 2: totalBytes
                "\"LOAD_BYTES\" INTEGER NOT NULL ," + // 3: loadBytes
                "\"SPEED\" INTEGER NOT NULL ," + // 4: speed
                "\"STATUS\" INTEGER NOT NULL ," + // 5: status
                "\"PATH\" TEXT);"); // 6: path
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FILE_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FileInfo entity) {
        stmt.clearBindings();
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(1, url);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getTotalBytes());
        stmt.bindLong(4, entity.getLoadBytes());
        stmt.bindLong(5, entity.getSpeed());
        stmt.bindLong(6, entity.getStatus());
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(7, path);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FileInfo entity) {
        stmt.clearBindings();
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(1, url);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getTotalBytes());
        stmt.bindLong(4, entity.getLoadBytes());
        stmt.bindLong(5, entity.getSpeed());
        stmt.bindLong(6, entity.getStatus());
 
        String path = entity.getPath();
        if (path != null) {
            stmt.bindString(7, path);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public FileInfo readEntity(Cursor cursor, int offset) {
        FileInfo entity = new FileInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // url
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.getInt(offset + 2), // totalBytes
            cursor.getInt(offset + 3), // loadBytes
            cursor.getInt(offset + 4), // speed
            cursor.getInt(offset + 5), // status
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // path
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FileInfo entity, int offset) {
        entity.setUrl(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTotalBytes(cursor.getInt(offset + 2));
        entity.setLoadBytes(cursor.getInt(offset + 3));
        entity.setSpeed(cursor.getInt(offset + 4));
        entity.setStatus(cursor.getInt(offset + 5));
        entity.setPath(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final String updateKeyAfterInsert(FileInfo entity, long rowId) {
        return entity.getUrl();
    }
    
    @Override
    public String getKey(FileInfo entity) {
        if(entity != null) {
            return entity.getUrl();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FileInfo entity) {
        return entity.getUrl() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
