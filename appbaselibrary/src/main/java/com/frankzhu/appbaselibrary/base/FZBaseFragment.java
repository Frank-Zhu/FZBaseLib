package com.frankzhu.appbaselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frankzhu.appbaselibrary.utils.FZSharedPreferencesHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      16/1/25  下午2:28.
 * Description: Fragment 基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/1/25        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class FZBaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    protected FZSharedPreferencesHelper mFZSharedPreferencesHelper;
    private Unbinder unbinder;

    protected abstract int getFragmentLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFZSharedPreferencesHelper = FZSharedPreferencesHelper.getInstance();
        if (getArguments() != null) {
            getArgumentsForFragmentIntent(getArguments());
        }
    }

    /**
     * 获取 Fragment 传递的数据
     *
     * @param bundle Fragment Bundle
     */
    protected void getArgumentsForFragmentIntent(Bundle bundle) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
