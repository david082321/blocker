package com.merxury.ifw;

import android.content.Context;
import android.content.pm.ComponentInfo;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.merxury.ifw.entity.ComponentType;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Mercury on 2018/4/19.
 */
public class IntentFirewallImplTest {
    private static final String TAG = "IntentFirewallImplTest";
    Context context;
    IntentFirewall firewall;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
        firewall = new IntentFirewallImpl(context, "com.weico");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addComponent() throws Exception {
        ComponentInfo componentInfo = new ComponentInfo();
        componentInfo.packageName = "com.android.camera2";
        componentInfo.name = "com.android.camera.settings.CameraSettingsActivity";
        ComponentInfo componentInfo1 = new ComponentInfo();
        componentInfo1.packageName = "com.android.camera2";
        componentInfo1.name = "com.android.camera.CaptureActivity";
        firewall.add(componentInfo, ComponentType.ACTIVITY);
        firewall.add(componentInfo1, ComponentType.ACTIVITY);
        ComponentInfo componentInfo3 = new ComponentInfo();
        componentInfo3.packageName = "com.android.camera2";
        componentInfo3.name = "com.android.camera.VideoCamera";
        ComponentInfo componentInfo4 = new ComponentInfo();
        componentInfo4.packageName = "com.example.android.architecture.blueprints.todomvp.mock";
        componentInfo4.name = "com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailActivity";
        firewall.add(componentInfo3, ComponentType.ACTIVITY);
        firewall.add(componentInfo4, ComponentType.ACTIVITY);
        ComponentInfo componentInfo5 = new ComponentInfo();
        componentInfo5.packageName = "com.example.android.architecture.blueprints.todomvp.mock";
        componentInfo5.name = "com.example.android.architecture.blueprints.todoapp.statistics.StatisticsActivity";
        ComponentInfo componentInfo6 = new ComponentInfo();
        componentInfo6.packageName = "com.android.settings";
        componentInfo6.name = ".Settings";
        firewall.add(componentInfo5, ComponentType.ACTIVITY);
        firewall.add(componentInfo6, ComponentType.ACTIVITY);
        String filePath = firewall.save();
        Log.i(TAG, filePath);
    }

    @Test
    public void removeComponent() throws Exception {
        ComponentInfo componentInfo = new ComponentInfo();
        componentInfo.packageName = "com.weico";
        componentInfo.name = "activity1";
        firewall.remove(componentInfo, ComponentType.ACTIVITY);
        String filePath = firewall.save();
        Log.i(TAG, filePath);
    }

    @Test
    public void getComponentEnableState() throws Exception {
        addComponent();
        removeComponent();
        ComponentInfo componentInfo = new ComponentInfo();
        componentInfo.packageName = "com.weico";
        componentInfo.name = "activity1";
        Assert.assertEquals(false, firewall.getComponentEnableState(componentInfo));
        ComponentInfo componentInfo1 = new ComponentInfo();
        componentInfo1.packageName = "com.weico";
        componentInfo1.name = "activity2";
        Assert.assertEquals(true, firewall.getComponentEnableState(componentInfo1));
        ComponentInfo componentInfo2 = new ComponentInfo();
        componentInfo2.packageName = "com.weico";
        componentInfo2.name = "broadcast1";
        Assert.assertEquals(true, firewall.getComponentEnableState(componentInfo2));
    }

    @Test
    public void removeRules() {
        String filename = "com.weico";
        firewall.clear(filename);
    }

}