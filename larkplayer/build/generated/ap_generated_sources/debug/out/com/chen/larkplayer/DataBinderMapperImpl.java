package com.chen.larkplayer;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.chen.larkplayer.databinding.ActivityLarkPlayerBindingImpl;
import com.chen.larkplayer.databinding.FragmentBottomBindingImpl;
import com.chen.larkplayer.databinding.ItemActivityLarkPlayerNavigationBindingImpl;
import com.chen.larkplayer.databinding.ItemFragmentDiscoverBannerBindingImpl;
import com.chen.larkplayer.databinding.ItemFragmentLibraryBindingImpl;
import com.chen.larkplayer.databinding.ItemFragmentLocalAlbumListBindingImpl;
import com.chen.larkplayer.databinding.ItemFragmentLocalArtistListBindingImpl;
import com.chen.larkplayer.databinding.ItemFragmentLocalMusicListBindingImpl;
import com.chen.larkplayer.databinding.ItemFragmentLocalVideoListBindingImpl;
import com.chen.larkplayer.databinding.ItemFragmentMusicDiscoverBannerBindingImpl;
import com.chen.larkplayer.databinding.ViewFragmentLibraryHeaderBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYLARKPLAYER = 1;

  private static final int LAYOUT_FRAGMENTBOTTOM = 2;

  private static final int LAYOUT_ITEMACTIVITYLARKPLAYERNAVIGATION = 3;

  private static final int LAYOUT_ITEMFRAGMENTDISCOVERBANNER = 4;

  private static final int LAYOUT_ITEMFRAGMENTLIBRARY = 5;

  private static final int LAYOUT_ITEMFRAGMENTLOCALALBUMLIST = 6;

  private static final int LAYOUT_ITEMFRAGMENTLOCALARTISTLIST = 7;

  private static final int LAYOUT_ITEMFRAGMENTLOCALMUSICLIST = 8;

  private static final int LAYOUT_ITEMFRAGMENTLOCALVIDEOLIST = 9;

  private static final int LAYOUT_ITEMFRAGMENTMUSICDISCOVERBANNER = 10;

  private static final int LAYOUT_VIEWFRAGMENTLIBRARYHEADER = 11;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(11);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.activity_lark_player, LAYOUT_ACTIVITYLARKPLAYER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.fragment_bottom, LAYOUT_FRAGMENTBOTTOM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.item_activity_lark_player_navigation, LAYOUT_ITEMACTIVITYLARKPLAYERNAVIGATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.item_fragment_discover_banner, LAYOUT_ITEMFRAGMENTDISCOVERBANNER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.item_fragment_library, LAYOUT_ITEMFRAGMENTLIBRARY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.item_fragment_local_album_list, LAYOUT_ITEMFRAGMENTLOCALALBUMLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.item_fragment_local_artist_list, LAYOUT_ITEMFRAGMENTLOCALARTISTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.item_fragment_local_music_list, LAYOUT_ITEMFRAGMENTLOCALMUSICLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.item_fragment_local_video_list, LAYOUT_ITEMFRAGMENTLOCALVIDEOLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.item_fragment_music_discover_banner, LAYOUT_ITEMFRAGMENTMUSICDISCOVERBANNER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.chen.larkplayer.R.layout.view_fragment_library_header, LAYOUT_VIEWFRAGMENTLIBRARYHEADER);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYLARKPLAYER: {
          if ("layout/activity_lark_player_0".equals(tag)) {
            return new ActivityLarkPlayerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_lark_player is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBOTTOM: {
          if ("layout/fragment_bottom_0".equals(tag)) {
            return new FragmentBottomBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_bottom is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMACTIVITYLARKPLAYERNAVIGATION: {
          if ("layout/item_activity_lark_player_navigation_0".equals(tag)) {
            return new ItemActivityLarkPlayerNavigationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_activity_lark_player_navigation is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFRAGMENTDISCOVERBANNER: {
          if ("layout/item_fragment_discover_banner_0".equals(tag)) {
            return new ItemFragmentDiscoverBannerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_fragment_discover_banner is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFRAGMENTLIBRARY: {
          if ("layout/item_fragment_library_0".equals(tag)) {
            return new ItemFragmentLibraryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_fragment_library is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFRAGMENTLOCALALBUMLIST: {
          if ("layout/item_fragment_local_album_list_0".equals(tag)) {
            return new ItemFragmentLocalAlbumListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_fragment_local_album_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFRAGMENTLOCALARTISTLIST: {
          if ("layout/item_fragment_local_artist_list_0".equals(tag)) {
            return new ItemFragmentLocalArtistListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_fragment_local_artist_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFRAGMENTLOCALMUSICLIST: {
          if ("layout/item_fragment_local_music_list_0".equals(tag)) {
            return new ItemFragmentLocalMusicListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_fragment_local_music_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFRAGMENTLOCALVIDEOLIST: {
          if ("layout/item_fragment_local_video_list_0".equals(tag)) {
            return new ItemFragmentLocalVideoListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_fragment_local_video_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFRAGMENTMUSICDISCOVERBANNER: {
          if ("layout/item_fragment_music_discover_banner_0".equals(tag)) {
            return new ItemFragmentMusicDiscoverBannerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_fragment_music_discover_banner is invalid. Received: " + tag);
        }
        case  LAYOUT_VIEWFRAGMENTLIBRARYHEADER: {
          if ("layout/view_fragment_library_header_0".equals(tag)) {
            return new ViewFragmentLibraryHeaderBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for view_fragment_library_header is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(2);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    result.add(new com.example.commonlibrary.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "holdViewModel");
      sKeys.put(2, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(11);

    static {
      sKeys.put("layout/activity_lark_player_0", com.chen.larkplayer.R.layout.activity_lark_player);
      sKeys.put("layout/fragment_bottom_0", com.chen.larkplayer.R.layout.fragment_bottom);
      sKeys.put("layout/item_activity_lark_player_navigation_0", com.chen.larkplayer.R.layout.item_activity_lark_player_navigation);
      sKeys.put("layout/item_fragment_discover_banner_0", com.chen.larkplayer.R.layout.item_fragment_discover_banner);
      sKeys.put("layout/item_fragment_library_0", com.chen.larkplayer.R.layout.item_fragment_library);
      sKeys.put("layout/item_fragment_local_album_list_0", com.chen.larkplayer.R.layout.item_fragment_local_album_list);
      sKeys.put("layout/item_fragment_local_artist_list_0", com.chen.larkplayer.R.layout.item_fragment_local_artist_list);
      sKeys.put("layout/item_fragment_local_music_list_0", com.chen.larkplayer.R.layout.item_fragment_local_music_list);
      sKeys.put("layout/item_fragment_local_video_list_0", com.chen.larkplayer.R.layout.item_fragment_local_video_list);
      sKeys.put("layout/item_fragment_music_discover_banner_0", com.chen.larkplayer.R.layout.item_fragment_music_discover_banner);
      sKeys.put("layout/view_fragment_library_header_0", com.chen.larkplayer.R.layout.view_fragment_library_header);
    }
  }
}
