package com.github.aliakseikaraliou.expertsystem.holders;

import android.content.Context;

import com.github.aliakseikaraliou.andromeda.utils.java.CollectionUtils;
import com.github.aliakseikaraliou.andromeda.utils.java.StringUtils;
import com.github.aliakseikaraliou.expertsystem.Item;
import com.github.aliakseikaraliou.expertsystem.helpers.InternalStorageHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemHolder {

    private static final ItemHolder ourInstance = new ItemHolder();
    private static final String ITEMS = "items";
    private static final String EXPERT = "expert";
    private static final String SEPARATOR = "-";

    public static ItemHolder getInstance() {
        return ourInstance;
    }

    private ItemHolder() {
    }

    public void add(final Context pContext, final Item pItem) {
        InternalStorageHelper.appendLine(pContext, ITEMS, pItem.getName());
        InternalStorageHelper.appendLine(pContext, EXPERT, StringUtils.join(pItem.getProperties(), SEPARATOR));
    }

    public void rewrite(final Context pContext, final Iterable<Item> pItems) {
        pContext.deleteFile(ITEMS);
        pContext.deleteFile(EXPERT);

        for (final Item item : pItems) {
            add(pContext, item);
        }
    }

    public List<Item> getAll(final Context pContext) {
        final List<String> lines = readByLines(pContext, ITEMS);
        final Collection<List<Integer>> properties = CollectionUtils.map(readByLines(pContext, EXPERT), new CollectionUtils.CollectionMapper<String, List<Integer>>() {

            @Override
            public List<Integer> map(final String pS) {
                return (List<Integer>) CollectionUtils.map(CollectionUtils.toModifiableList(pS.split(SEPARATOR)), new CollectionUtils.CollectionMapper<String, Integer>() {

                    @Override
                    public Integer map(final String pS) {
                        return Integer.parseInt(pS);
                    }
                });
            }
        });
        return Item.createFrom(lines, (List<List<Integer>>) properties);
    }

    public Item getItem(final Context pContext, final int pPosition) {
        final String name = InternalStorageHelper.readLine(pContext, ITEMS, pPosition);
        final Collection<Integer> properties = CollectionUtils.map(CollectionUtils.toModifiableList(InternalStorageHelper.readLine(pContext, ITEMS, pPosition).split(SEPARATOR)), new CollectionUtils.CollectionMapper<String, Integer>() {

            @Override
            public Integer map(final String pS) {
                return Integer.parseInt(pS);
            }
        });
        return new Item(name, (List<Integer>) properties);
    }

    public String getProperties(final Context pContext, final int pPosition) {
        return InternalStorageHelper.readLine(pContext, EXPERT, pPosition);
    }

    public void addProperty(final Context pContext, final List<Integer> pValues) {
        final List<Item> items = getAll(pContext);

        for (int i = 0; i < items.size(); i++) {
            final int value = pValues.get(i);
            items.get(i).getProperties().add(value);
        }

        rewrite(pContext, items);
    }

    private List<String> readByLines(final Context pContext, final String pFileName) {
        final String properties = InternalStorageHelper.readAll(pContext, pFileName);

        if (!StringUtils.isEmpty(properties)) {
            return CollectionUtils.toList(properties.split("\n"));
        } else {
            return new ArrayList<>();
        }
    }

}
