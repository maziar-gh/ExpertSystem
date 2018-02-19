package com.github.aliakseikaraliou.expertsystem.holders;

import android.content.Context;

import com.github.aliakseikaraliou.andromeda.utils.java.StringUtils;
import com.github.aliakseikaraliou.expertsystem.helpers.InternalStorageHelper;

import java.util.Arrays;
import java.util.List;

public class PropertyHolder {

    private static final PropertyHolder ourInstance = new PropertyHolder();
    private static final String PROPERTIES = "properties";

    public static PropertyHolder getInstance() {
        return ourInstance;
    }

    private PropertyHolder() {
    }

    public void add(final Context pContext, final String pName) {
        InternalStorageHelper.appendLine(pContext, PROPERTIES, pName);
    }

    public List<String> getAll(final Context pContext) {
        final String properties = InternalStorageHelper.readAll(pContext, PROPERTIES);
        return Arrays.asList(properties.split("\n"));
    }

    public String get(final Context pContext, final int pPosition) {
        return InternalStorageHelper.readLine(pContext, PROPERTIES, pPosition);
    }
}
