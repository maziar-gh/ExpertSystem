package com.github.aliakseikaraliou.expertsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    private final String name;
    private final List<Integer> mProperties;

    public Item(final String pName) {
        name = pName;
        mProperties = new ArrayList<>();
    }

    public Item(final String pName, final List<Integer> pProperties) {
        name = pName;
        mProperties = new ArrayList<>(pProperties);
    }

    public String getName() {
        return name;
    }

    public List<Integer> getProperties() {
        return mProperties;
    }

    public static List<Item> createFrom(final List<String> mNames, final List<List<Integer>> mProperties) {
        if (mNames.size() != mProperties.size()) {
            throw new RuntimeException("Items in names don't equal expert lines");
        }

        final List<Item> items = new ArrayList<>();

        for (int i = 0; i < mNames.size(); i++) {
            final String name = mNames.get(i);
            final List<Integer> properties = mProperties.get(i);

            final Item item = new Item(name, properties);
            items.add(item);
        }

        return items;
    }
}
