package com.bingo.wanandroid.di.module;

import com.bingo.wanandroid.di.component.BaseDialogFragmentComponent;
import com.bingo.wanandroid.di.component.BaseFragmentComponent;
import com.bingo.wanandroid.ui.main.fragment.HotSearchDialogFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * author bingo
 * date 2020/1/5
 */

@Module(subcomponents = {BaseDialogFragmentComponent.class})
public abstract class AbstractAllDialogFragmentModule {

    @ContributesAndroidInjector(modules = HotSearchDialogFragmentModule.class)
    abstract HotSearchDialogFragment contributesHotSearchDialogFragmentInjector();


}
