package com.bingo.wanandroid.di.module;

import com.bingo.wanandroid.di.component.BaseFragmentComponent;
import com.bingo.wanandroid.ui.hierarchy.fragment.HierarchyDetailListFragment;
import com.bingo.wanandroid.ui.hierarchy.fragment.HierarchyFragment;
import com.bingo.wanandroid.ui.mainpager.fragment.MainPagerFragment;
import com.bingo.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.bingo.wanandroid.ui.project.fragment.ProjectFragment;
import com.bingo.wanandroid.ui.project.fragment.ProjectListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * author bingo
 * date 2020/1/5
 */

@Module(subcomponents = {BaseFragmentComponent.class})
public abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules = MainPagerFragmentModule.class)
    abstract MainPagerFragment contributesMainPagerFragmentInjector();

    @ContributesAndroidInjector(modules = HierarchyFragmentModule.class)
    abstract HierarchyFragment contributesHierarchyFragmentInjector();

    @ContributesAndroidInjector(modules = HierarchyDetailListFragmentModule.class)
    abstract HierarchyDetailListFragment contributesHierarchyDetailListFragmentInjector();

    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
    abstract NavigationFragment contributesNavigationFragmentInjector();

    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
    abstract ProjectFragment contributesProjectFragmentInjector();

    @ContributesAndroidInjector(modules = ProjectListFragmentModule.class)
    abstract ProjectListFragment contributesProjectListFragmentInjector();
}
