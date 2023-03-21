package com.example.demomonna;

import lombok.Getter;

/**
 * @Author： Monna
 * @CreateTime:2022-12-28 09:19:23
 * @Descrption:
 */

public class Param {
    private double count;

    private String s;

    @Override
    public String toString() {
        return "Param{" +
                "count=" + count +
                ", s='" + s + '\'' +
                '}';
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
