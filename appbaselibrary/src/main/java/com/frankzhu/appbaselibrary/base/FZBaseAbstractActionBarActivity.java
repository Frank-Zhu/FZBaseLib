package com.frankzhu.appbaselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.frankzhu.appbaselibrary.R;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/26  下午4:04.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBaseAbstractActionBarActivity extends FZBaseActionBarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutRes());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, getFragment())
                    .commit();
        }
    }

    protected int getContentLayoutRes() {
        return R.layout.activity_base_fragment;
    }

    protected abstract Fragment getFragment();
}
