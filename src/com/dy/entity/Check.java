package com.dy.entity;

/**
 * @author: zjd-DY
 * @date: 2021/6/20 20:20
 * @description:
 */
public class Check {
    int start;
    int end;

    public Check() {
    }

    @Override
    public String toString() {
        return "Check{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Check(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
