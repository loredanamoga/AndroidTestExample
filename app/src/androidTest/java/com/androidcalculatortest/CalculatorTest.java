package com.androidcalculatortest;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by loredanamoga on 8/30/2017.
 */

@RunWith(AndroidJUnit4.class)
public class CalculatorTest {
    private static final String BASIC_SAMPLE_PACKAGE = "com.android.calculator2";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice device;

    @Before
    public void startMainActivityFromHomeScreen() {

        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);


    }

    @After
    public void clear() {
        try {
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/clr")).click();

            assertEquals("", device.findObject(new UiSelector().resourceId("formula")).getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddition() {
        try {
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_4")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/op_add")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_7")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/eq")).click();

            assertEquals("11", device.findObject(new UiSelector().resourceId("formula")).getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSubtraction() {
        try {
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_7")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/op_sub")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_4")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/eq")).click();

            assertEquals("3", device.findObject(new UiSelector().resourceId("formula")).getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testMultiplication() {
        try {
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_6")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/op_mul")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_4")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/eq")).click();

            assertEquals("24", device.findObject(new UiSelector().resourceId("formula")).getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDivision() {
        try {
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_6")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_4")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/op_div")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/digit_8")).click();
            device.findObject(new UiSelector().resourceId("com.android.calculator2:id/eq")).click();

            assertEquals("8", device.findObject(new UiSelector().resourceId("formula")).getText());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }



}
