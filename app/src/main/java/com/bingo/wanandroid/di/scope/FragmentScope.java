package com.bingo.wanandroid.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * author bingo
 * date 2020/1/5
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
