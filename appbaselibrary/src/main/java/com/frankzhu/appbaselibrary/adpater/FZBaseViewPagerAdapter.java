package com.frankzhu.appbaselibrary.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.frankzhu.appbaselibrary.base.FZBaseViewPagerFragment;

import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/27  上午11:09.
 * Description: View Pager Base Adapter
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/27        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FZBaseViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<FZBaseViewPagerFragment> mFragments;
    private String[] mTitles;

    public FZBaseViewPagerAdapter(FragmentManager fm, ArrayList<FZBaseViewPagerFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    public FZBaseViewPagerAdapter(FragmentManager fm, ArrayList<FZBaseViewPagerFragment> fragments, String[] titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    public void setTitles(String[] titles) {
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "" : mTitles[position];
    }
}
