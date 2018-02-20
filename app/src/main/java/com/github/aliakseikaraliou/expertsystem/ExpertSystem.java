package com.github.aliakseikaraliou.expertsystem;

import com.github.aliakseikaraliou.andromeda.utils.java.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ExpertSystem {

    private List<String> mNames;
    private List<List<Integer>> mLists;
    private final List<String> mProperties;
    private String mResult;

    private int mMin;

    public ExpertSystem(final Iterable<Item> pLists, final List<String> pProperties) {
        mLists = new ArrayList<>();
        mNames = new ArrayList<>();

        for (final Item list : pLists) {
            mNames.add(list.getName());
            mLists.add(list.getProperties());
        }

        mProperties = new ArrayList<>(pProperties);
    }

    public boolean isFinish() {
        if (mLists.size() <= 1) {

            if (mLists.isEmpty()) {
                mResult = "None";
            } else {
                mResult = mNames.get(0);
            }

            return true;
        } else if (mLists.get(0).isEmpty()) {
            mResult = StringUtils.join(mNames, ",");
            return true;
        } else {
            return false;
        }
    }

    public String getQuestion() {
        final int[] sums = new int[mLists.get(0).size()];

        for (int i = 0; i < mLists.size(); i++) {
            final List<Integer> item = mLists.get(i);

            for (int j = 0; j < item.size(); j++) {
                sums[j] += item.get(j);
            }
        }

        mMin = 0;

        for (int i = 0; i < sums.length; i++) {
            final int sum = sums[i];

            if (sum == 0) {
                removeColumn(i);
            } else if (sums[mMin] > sums[i]) {
                mMin = i;
            }
        }

        return mProperties.get(mMin);
    }

    public void setAnswer(final boolean pAnswer) {

        final int answer = pAnswer ? 1 : 0;

        final List<List<Integer>> result = new ArrayList<>();
        final List<String> names = new ArrayList<>();

        for (int i = 0; i < mLists.size(); i++) {
            final List<Integer> list = mLists.get(i);
            final String name = mNames.get(i);

            if (list.get(mMin) == answer) {
                result.add(list);
                names.add(name);
            }
        }

        removeColumn(mMin);

        mLists = new ArrayList<>(result);
        mNames = new ArrayList<>(names);
    }

    private void removeColumn(final int pPosition) {
        for (final List<Integer> list : mLists) {
            list.remove(pPosition);
        }

        mProperties.remove(pPosition);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        for (final List<Integer> list : mLists) {
            for (final Integer integer : list) {
                stringBuilder.append(integer);
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public String getResult() {
        return mResult;
    }
}
