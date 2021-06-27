package com.dy.entity;

/**
 * @author: zjd-DY
 * @date: 2021/6/17 21:10
 * @description:
 */
public class GradeList {
    int context;
    int innovate;
    int defence;
    int time;
    int grade;

    @Override
    public String toString() {
        return "GradeList{" +
                "context=" + context +
                ", innovate=" + innovate +
                ", defence=" + defence +
                ", time=" + time +
                ", grade=" + grade +
                '}';
    }

    public GradeList() {
        super();
    }

    public GradeList(int context, int innovate, int defence, int time, int grade) {
        this.context = context;
        this.innovate = innovate;
        this.defence = defence;
        this.time = time;
        this.grade = grade;
    }

    public int getContext() {
        return context;
    }

    public void setContext(int context) {
        this.context = context;
    }

    public int getInnovate() {
        return innovate;
    }

    public void setInnovate(int innovate) {
        this.innovate = innovate;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
