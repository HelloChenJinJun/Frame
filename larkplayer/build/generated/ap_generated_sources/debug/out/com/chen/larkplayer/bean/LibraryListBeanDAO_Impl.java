package com.chen.larkplayer.bean;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LibraryListBeanDAO_Impl implements LibraryListBeanDAO {
  private final RoomDatabase __db;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfLibraryListBean;

  public LibraryListBeanDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__updateAdapterOfLibraryListBean = new EntityDeletionOrUpdateAdapter<LibraryListBean>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `LibraryListBean` SET `type` = ?,`icon` = ?,`title` = ?,`count` = ?,`album` = ? WHERE `type` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LibraryListBean value) {
        stmt.bindLong(1, value.type);
        stmt.bindLong(2, value.icon);
        if (value.title == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.title);
        }
        stmt.bindLong(4, value.count);
        if (value.album == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.album);
        }
        stmt.bindLong(6, value.type);
      }
    };
  }

  @Override
  public void update(final LibraryListBean libraryListBean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfLibraryListBean.handle(libraryListBean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
