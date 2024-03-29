package com.example.commonlibrary.skin;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterFactory;

import com.example.commonlibrary.skin.attr.BackgroundAttr;
import com.example.commonlibrary.skin.attr.DrawableTopAttr;
import com.example.commonlibrary.skin.attr.SkinAttr;
import com.example.commonlibrary.skin.attr.SkinItem;
import com.example.commonlibrary.skin.attr.SrcAttr;
import com.example.commonlibrary.skin.attr.TextColorAttr;
import com.example.commonlibrary.skin.attr.ThumbAttr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by COOTEK on 2017/9/3.
 */

public class SkinLayoutInflaterFactory implements LayoutInflaterFactory {

    private static final String STYLE = "style";
    private static final String TEXT_COLOR = "textColor";
    private static final String BACKGROUND = "background";
    private static final String SRC = "src";
    private static final String THUMB = "thumb";
    private static final String SKIN_FLAG = "sk_";
    private AppCompatActivity appCompatActivity;
    private static List<String> supportAttrsName = new ArrayList<>();
    private Map<View, SkinItem> viewSkinItemMap = new HashMap<>();

    private static final String DRAWABLE_TOP = "drawableTop";

    static {
        supportAttrsName.add(DRAWABLE_TOP);
        supportAttrsName.add(TEXT_COLOR);
        supportAttrsName.add(BACKGROUND);
        supportAttrsName.add(SRC);
        supportAttrsName.add(THUMB);
    }


    public SkinLayoutInflaterFactory(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        //        获取是否应用换肤操作
        boolean hasApplySkin=false;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            if (hasSkin(context,attrs.getAttributeValue(i),attrs.getAttributeName(i))) {
                hasApplySkin=true;
                break;
            }
        }
        View view = appCompatActivity.getDelegate().createView(parent, name, context, attrs);
        if (view == null) {
            view = ViewProducer.createViewFromTag(context, name, attrs);
        }
        if (hasApplySkin) {
            return applySkin(context, view, attrs);
        }
        return view;
    }



    private boolean hasSkin(Context context, String attributeValue, String attributeName) {
        String idValue;
       if (supportAttrsName.contains(attributeName)) {
            if (attributeValue.startsWith("?") || attributeValue.startsWith("@")) {
                idValue=attributeValue.substring(1);
                return context.getResources().getResourceName(Integer.parseInt(idValue)).startsWith(SKIN_FLAG);
            }
        }
        return false;
    }

    private View applySkin(Context context, View view, AttributeSet attrs) {
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            try {
                if (attrName.equals(STYLE)) {
                    String styleName = attrValue.substring(attrValue.indexOf("/") + 1);
                    int styleId = context.getResources().getIdentifier(styleName, STYLE, context.getPackageName());
                    int[] styleAttrs = new int[]{android.R.attr.textColor, android.R.attr.background
                    };
                    TypedArray typedArray = context.getTheme().obtainStyledAttributes(styleId, styleAttrs);
                    int textColorResId = typedArray.getResourceId(0, 0);
                    int backgroundResId = typedArray.getResourceId(1, 0);
                    if (textColorResId != 0) {
                        createSkinFromAttrName(TEXT_COLOR, textColorResId, view);
                    }
                    if (backgroundResId != 0) {
                        createSkinFromAttrName(BACKGROUND, backgroundResId, view);
                    }
                } else if (supportAttrsName.contains(attrName)) {
                    //                只有引用类型才可以换肤
                    if (attrValue.startsWith("?") || attrValue.startsWith("@")) {
                        int id = Integer.parseInt(attrValue.substring(1));
                        if (id != 0&&context.getResources().getResourceName(id).startsWith(SKIN_FLAG)) {
                            createSkinFromAttrName(attrName, id, view);
                        }
                    }
                }
            } catch (Resources.NotFoundException | NumberFormatException e) {
                e.printStackTrace();
            }
            if (viewSkinItemMap.get(view) != null) {
                viewSkinItemMap.get(view).apply();
            }
        }
        return view;
    }


    public void applyAllViewSkin() {
        if (viewSkinItemMap != null && viewSkinItemMap.size() > 0) {
            for (SkinItem skinItem :
                    viewSkinItemMap.values()) {
                skinItem.apply();
            }
        }
    }


    public SkinAttr createSkinFromAttrName(String attrName, int resId, View view) {
        SkinAttr skinAttr = null;
        switch (attrName) {
            case BACKGROUND:
                skinAttr = new BackgroundAttr();
                break;
            case SRC:
                skinAttr = new SrcAttr();
                break;
            case TEXT_COLOR:
                skinAttr = new TextColorAttr();
                break;
            case THUMB:
                skinAttr = new ThumbAttr();
                break;
            case DRAWABLE_TOP:
                skinAttr = new DrawableTopAttr();
            default:
                break;
        }
        skinAttr.update(attrName, resId, view.getContext());
        if (viewSkinItemMap.get(view) == null) {
            SkinItem skinItem = new SkinItem();
            List<SkinAttr> list = new ArrayList<>();
            list.add(skinAttr);
            skinItem.setView(view);
            skinItem.setSkinAttrs(list);
            viewSkinItemMap.put(view, skinItem);
        } else {
            viewSkinItemMap.get(view).getSkinAttrs().add(skinAttr);
        }
        return skinAttr;
    }


    public void clear() {
        viewSkinItemMap.clear();
        viewSkinItemMap = null;
    }
}
