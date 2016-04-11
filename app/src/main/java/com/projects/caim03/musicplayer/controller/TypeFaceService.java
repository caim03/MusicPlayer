package com.projects.caim03.musicplayer.controller;


import android.content.Context;
import android.graphics.Typeface;

public class TypeFaceService {
    private final static String RobotoBlack = "fonts/Roboto-Black.ttf";
    private final static String RobotoBlackItalic = "fonts/Roboto-BlackItalic.ttf";
    private final static String RobotoBold = "fonts/Roboto-Bold.ttf";
    private final static String RobotoBoldItalic = "fonts/Roboto-BoldItalic.ttf";
    private final static String RobotoItalic = "fonts/Roboto-Italic.ttf";
    private final static String RobotoLight = "fonts/Roboto-Light.ttf";
    private final static String RobotoLightItalic = "fonts/Roboto-LightItalic.ttf";
    private final static String RobotoMedium = "fonts/Roboto-Medium.ttf";
    private final static String RobotoMediumItalic = "fonts/Roboto-MediumItalic.ttf";
    private final static String RobotoRegular = "fonts/Roboto-Regular.ttf";
    private final static String RobotoThin = "fonts/Roboto-Thin.ttf";
    private final static String RobotoThinItalic = "fonts/Roboto-ThinItalic.ttf";

    public static Typeface getRobotoBlack(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoBlack);
    }

    public static Typeface getRobotoBlackItalic(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoBlackItalic);
    }

    public static Typeface getRobotoBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoBold);
    }

    public static Typeface getRobotoBoldItalic(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoBoldItalic);
    }

    public static Typeface getRobotoLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoLight);
    }

    public static Typeface getRobotoItalic(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoItalic);
    }

    public static Typeface getRobotoLightItalic(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoLightItalic);
    }

    public static Typeface getRobotoMedium(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoMedium);
    }

    public static Typeface getRobotoMediumItalic(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoMediumItalic);
    }

    public static Typeface getRobotoThin(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoThin);
    }

    public static Typeface getRobotoRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoRegular);
    }

    public static Typeface getRobotoThinItalic(Context context) {
        return Typeface.createFromAsset(context.getAssets(), RobotoThinItalic);
    }
}
