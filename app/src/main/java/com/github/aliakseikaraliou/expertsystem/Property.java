package com.github.aliakseikaraliou.expertsystem;

import com.github.aliakseikaraliou.andromeda.utils.java.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Property implements Serializable {

    private final String mName;
    private boolean mChecked;

    public Property(final String pName) {
        mName = pName;
    }

    public String getName() {
        return mName;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(final boolean pChecked) {
        mChecked = pChecked;
    }

    public static List<Property> createFrom(final Iterable<String> mNames) {
        final List<Property> properties = new ArrayList<>();
        for (final String name : mNames) {
            properties.add(new Property(name));
        }
        return properties;
    }

    public static List<Integer> getResponse(final Iterable<Property> mProperties) {
        return (List<Integer>) CollectionUtils.map(mProperties, new CollectionUtils.CollectionMapper<Property, Integer>() {

            @Override
            public Integer map(final Property pProperty) {
                return pProperty.mChecked ? 1 : 0;
            }
        });
    }
}
