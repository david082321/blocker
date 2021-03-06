package com.merxury.blocker.core

import android.content.ComponentName

/**
 * Created by Mercury on 2018/1/13.
 * An Interface that defines what controller should do
 */

interface IController {

    /**
     * a method to change a component's state
     *
     * @param packageName   package name
     * @param componentName component name
     * @param state         PackageManager.COMPONENT_ENABLED_STATE_ENABLED: enable component
     * COMPONENT_ENABLED_STATE_DISABLED: disable component
     * @return true : changed component state successfully
     * false: cannot disable component
     */
    fun switchComponent(packageName: String, componentName: String, state: Int): Boolean

    fun enable(packageName: String, componentName: String): Boolean

    fun disable(packageName: String, componentName: String): Boolean

    fun batchEnable(componentList: List<ComponentName>): Int

    fun batchDisable(componentList: List<ComponentName>): Int

    fun checkComponentEnableState(packageName: String, componentName: String): Boolean
}
