package com.dy.entity;

import java.util.Arrays;

/**
 * @author: zjd-DY
 * @date: 2021/6/17 21:15
 * @description:
 */
public class Teacher {
    int id;
    String name;
    int tno;
    String sex;
    Group group;
    String status;
    String isChecked;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tno=" + tno +
                ", sex='" + sex + '\'' +
                ", group=" + group +
                ", status='" + status + '\'' +
                ", isChecked='" + isChecked + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTno() {
        return tno;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public Teacher(int id, String name, int tno, String sex, Group group, String status, String isChecked) {
        this.id = id;
        this.name = name;
        this.tno = tno;
        this.sex = sex;
        this.group = group;
        this.status = status;
        this.isChecked = isChecked;
    }

    public Teacher() {
    }
}
