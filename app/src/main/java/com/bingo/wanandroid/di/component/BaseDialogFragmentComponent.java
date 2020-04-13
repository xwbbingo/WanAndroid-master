package com.bingo.wanandroid.di.component;

import com.bingo.wanandroid.base.fragment.BaseDialogFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * author bingo
 * date 2020/1/5
 */

@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseDialogFragmentComponent extends AndroidInjector<BaseDialogFragment> {

    /**
     * 每一个继承于BaseDialogFragment的Fragment都继承于同一个子组件
     */
    @Subcomponent.Builder
    abstract class BaseBuilder extends Builder<BaseDialogFragment>{

    }
}
