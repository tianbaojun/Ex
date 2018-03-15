package com.tabjin.ex.bean;

import java.io.Serializable;

/**
 * Created by tabjin on 18-3-15.
 */

public class Function implements Serializable {

    public String name;
    public Class activityName;

    public Function(String name, Class activityName) {
        this.name = name;
        this.activityName = activityName;
    }
}
