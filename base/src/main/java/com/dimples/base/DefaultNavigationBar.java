package com.dimples.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.dimples.framwork.widget.navigationbar.AbsNavigationBar;

/**
 * 默认的NavigationBar效果实现类
 *
 * @author zhongyj
 * @date 2019/6/3
 */
public class DefaultNavigationBar<T extends DefaultNavigationBar.Builder.DefaultNavigationParams> extends
        AbsNavigationBar<DefaultNavigationBar.Builder.DefaultNavigationParams> {

    private DefaultNavigationBar(DefaultNavigationBar.Builder.DefaultNavigationParams params) {
        super(params);
    }

    @Override
    public int getNavigationBarLayout() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        // 绑定效果
        setText(R.id.title, getParams().mTitle);
        setText(R.id.right_text, getParams().mRightText);

        setOnClickListener(R.id.right_text, getParams().mRightClickListener);
        // 左边 要写一个默认的  finishActivity
        setOnClickListener(R.id.back, getParams().mLeftClickListener);
    }


    /**
     * NavigationBar的构造器
     *
     * @author zhongyj
     * @date 2019/6/3
     */
    public static class Builder extends AbsNavigationBar.Builder {

        DefaultNavigationParams P;

        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            P = new DefaultNavigationParams(context, parent);
        }

        public Builder(Context context) {
            super(context, null);
            P = new DefaultNavigationParams(context, null);
        }

        @Override
        public DefaultNavigationBar builder() {
            return new DefaultNavigationBar(P);
        }

        /**
         * 设置居中文本
         *
         * @param title String
         * @return DefaultNavigationBar.Builder
         */
        public DefaultNavigationBar.Builder setTitle(String title) {
            P.mTitle = title;
            return this;
        }

        /**
         * @param rightText String
         * @return DefaultNavigationBar.Builder
         */
        public DefaultNavigationBar.Builder setRightText(String rightText) {
            P.mRightText = rightText;
            return this;
        }

        /**
         * 设置右边的点击事件
         *
         * @param rightListener View.OnClickListener
         * @return DefaultNavigationBar.Builder
         */
        public DefaultNavigationBar.Builder setRightClickListener(View.OnClickListener rightListener) {
            P.mRightClickListener = rightListener;
            return this;
        }

        /**
         * 设置左边的点击事件
         *
         * @param rightListener View.OnClickListener
         * @return DefaultNavigationBar.Builder
         */
        public DefaultNavigationBar.Builder setLeftClickListener(View.OnClickListener rightListener) {
            P.mLeftClickListener = rightListener;
            return this;
        }

        /**
         * 设置右边的图片
         *
         * @param rightRes int
         * @return DefaultNavigationBar.Builder
         */
        public DefaultNavigationBar.Builder setRightIcon(int rightRes) {

            return this;
        }

        /**
         * NavigationBar的参数设置
         * 所有的效果设置
         *
         * @author zhongyj
         * @date 2019/6/3
         */
        static class DefaultNavigationParams extends AbsNavigationBar.Builder.AbsNavigationParams {

            String mTitle;

            String mRightText;

            View.OnClickListener mRightClickListener;

            View.OnClickListener mLeftClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 关闭当前Activity
                    ((Activity) mContext).finish();
                }
            };

            DefaultNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}