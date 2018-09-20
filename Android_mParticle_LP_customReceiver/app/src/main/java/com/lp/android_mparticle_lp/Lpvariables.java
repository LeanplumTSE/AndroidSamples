package com.lp.android_mparticle_lp;

import com.leanplum.Var;
import com.leanplum.annotations.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fede on 9/20/18.
 */

public class Lpvariables {

    @Variable
    public static String welcomeString = "Welcome to Leanplum";


    @Variable
    public static Map<String, Object> powerup = new HashMap<String, Object>() {
        {
            put("name", "Turbo Boost");
        }
    };


    @Variable
    public static int testValue = 0;

    @Variable
    public static boolean testBool = true;

}
