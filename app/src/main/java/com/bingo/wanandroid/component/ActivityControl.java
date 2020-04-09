package com.bingo.wanandroid.component;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * author bingo
 * date 2020/1/2
 * Activity 管理
 */
public class ActivityControl {

    private static ActivityControl activityControl;

    public synchronized static ActivityControl getInstance() {
        if (activityControl == null) {
            activityControl = new ActivityControl();
        }
        return activityControl;
    }

    private Set<Activity> allActivities;

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
