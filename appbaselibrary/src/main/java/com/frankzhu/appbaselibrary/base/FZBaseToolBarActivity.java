package com.frankzhu.appbaselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.frankzhu.appbaselibrary.R;
import com.frankzhu.appbaselibrary.utils.FZSharedPreferencesHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/25  上午11:28.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/25        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBaseToolBarActivity extends AppCompatActivity {
    protected String TAG = FZBaseToolBarActivity.class.getSimpleName();
    protected FZSharedPreferencesHelper mFZSharedPreferencesHelper;
    protected FrameLayout mFlContent;
    protected Toolbar mToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFZSharedPreferencesHelper = FZSharedPreferencesHelper.getInstance();
        setContentView(R.layout.activity_base_tool_bar);
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);
        mFlContent.addView(LayoutInflater.from(this).inflate(getContentViewLayoutRes(), null, false));
    }

    protected abstract int getContentViewLayoutRes();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
