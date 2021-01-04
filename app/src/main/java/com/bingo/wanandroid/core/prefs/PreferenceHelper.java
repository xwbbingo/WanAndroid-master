package com.bingo.wanandroid.core.prefs;

/**
 * author bingo
 * date 2020/1/2
 */
public interface PreferenceHelper {

    /**
     * Set night mode state
     */
    void setNightModeState(boolean nightModeState);

    /**
     * Get night mode state
     *
     * @return if is night mode
     */
    boolean getNightModeState();

    /**
     * Set login status
     *
     * @param loginStatus login status
     */
    void setLoginStatus(boolean loginStatus);

    /**
     * Get login status
     *
     * @return if is login status
     */
    boolean getLoginStatus();

    /**
     * Set login account
     *
     * @param account account
     */
    void setLoginAccount(String account);

    /**
     * Get login account
     *
     * @return login account
     */
    String getLoginAccount();

    /**
     * Set login password
     *
     * @param password password
     */
    void setLoginPassword(String password);

    /**
     * Get login password
     *
     * @return login password
     */
    String getLoginPassword();

    /**
     * Set current page
     *
     */
    void setCurrentPage(int position);

    /**
     * Get current page
     *
     * @return current page
     */
    int getCurrentPage();

    /**
     * Set auto cache state
     *
     * @param b current auto cache state
     */
    void setAutoCacheState(boolean b);

    /**
     * Get auto cache state
     *
     * @return if auto cache state
     */
    boolean getAutoCacheState();

    /**
     * Set no image state
     *
     * @param b current no image state
     */
    void setNoImageState(boolean b);

    /**
     * Get no image state
     *
     * @return if has image state
     */
    boolean getNoImageState();

}
