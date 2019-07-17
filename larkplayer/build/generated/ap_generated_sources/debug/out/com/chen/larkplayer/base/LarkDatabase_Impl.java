package com.chen.larkplayer.base;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.chen.larkplayer.bean.LibraryListBeanDAO;
import com.chen.larkplayer.bean.LibraryListBeanDAO_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LarkDatabase_Impl extends LarkDatabase {
  private volatile LibraryListBeanDAO _libraryListBeanDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FileInfo` (`url` TEXT NOT NULL, `name` TEXT, `fileDir` TEXT, `totalBytes` INTEGER NOT NULL, `loadBytes` INTEGER NOT NULL, `speed` INTEGER NOT NULL, `status` INTEGER NOT NULL, PRIMARY KEY(`url`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LibraryListBean` (`type` INTEGER NOT NULL, `icon` INTEGER NOT NULL, `title` TEXT, `count` INTEGER NOT NULL, `album` TEXT, PRIMARY KEY(`type`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '76aeb955d9d0c058b303fde6b71bd354')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `FileInfo`");
        _db.execSQL("DROP TABLE IF EXISTS `LibraryListBean`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFileInfo = new HashMap<String, TableInfo.Column>(7);
        _columnsFileInfo.put("url", new TableInfo.Column("url", "TEXT", true, 1));
        _columnsFileInfo.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsFileInfo.put("fileDir", new TableInfo.Column("fileDir", "TEXT", false, 0));
        _columnsFileInfo.put("totalBytes", new TableInfo.Column("totalBytes", "INTEGER", true, 0));
        _columnsFileInfo.put("loadBytes", new TableInfo.Column("loadBytes", "INTEGER", true, 0));
        _columnsFileInfo.put("speed", new TableInfo.Column("speed", "INTEGER", true, 0));
        _columnsFileInfo.put("status", new TableInfo.Column("status", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFileInfo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFileInfo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFileInfo = new TableInfo("FileInfo", _columnsFileInfo, _foreignKeysFileInfo, _indicesFileInfo);
        final TableInfo _existingFileInfo = TableInfo.read(_db, "FileInfo");
        if (! _infoFileInfo.equals(_existingFileInfo)) {
          throw new IllegalStateException("Migration didn't properly handle FileInfo(com.example.commonlibrary.net.download.FileInfo).\n"
                  + " Expected:\n" + _infoFileInfo + "\n"
                  + " Found:\n" + _existingFileInfo);
        }
        final HashMap<String, TableInfo.Column> _columnsLibraryListBean = new HashMap<String, TableInfo.Column>(5);
        _columnsLibraryListBean.put("type", new TableInfo.Column("type", "INTEGER", true, 1));
        _columnsLibraryListBean.put("icon", new TableInfo.Column("icon", "INTEGER", true, 0));
        _columnsLibraryListBean.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsLibraryListBean.put("count", new TableInfo.Column("count", "INTEGER", true, 0));
        _columnsLibraryListBean.put("album", new TableInfo.Column("album", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLibraryListBean = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLibraryListBean = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLibraryListBean = new TableInfo("LibraryListBean", _columnsLibraryListBean, _foreignKeysLibraryListBean, _indicesLibraryListBean);
        final TableInfo _existingLibraryListBean = TableInfo.read(_db, "LibraryListBean");
        if (! _infoLibraryListBean.equals(_existingLibraryListBean)) {
          throw new IllegalStateException("Migration didn't properly handle LibraryListBean(com.chen.larkplayer.bean.LibraryListBean).\n"
                  + " Expected:\n" + _infoLibraryListBean + "\n"
                  + " Found:\n" + _existingLibraryListBean);
        }
      }
    }, "76aeb955d9d0c058b303fde6b71bd354", "6706cb262affcf12452f2b875a9aa58e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "FileInfo","LibraryListBean");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `FileInfo`");
      _db.execSQL("DELETE FROM `LibraryListBean`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public LibraryListBeanDAO getLibraryListBeanDAO() {
    if (_libraryListBeanDAO != null) {
      return _libraryListBeanDAO;
    } else {
      synchronized(this) {
        if(_libraryListBeanDAO == null) {
          _libraryListBeanDAO = new LibraryListBeanDAO_Impl(this);
        }
        return _libraryListBeanDAO;
      }
    }
  }
}
