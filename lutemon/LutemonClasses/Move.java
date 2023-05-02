package com.olio.lutemon.LutemonClasses;

import java.io.Serializable;

public class Move implements Serializable {
    protected String name;
    protected String type;

    public Move(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
