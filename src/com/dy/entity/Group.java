package com.dy.entity;

import java.util.Date;

/**
 * @author: zjd-DY
 * @date: 2021/6/17 21:14
 * @description:
 */
public class Group {
    int id;
    String name;
    Date defencetime;
    Teacher leader;
    Check check;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", defencetime=" + defencetime +
                ", leader=" + leader +
                ", check=" + check +
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

    public Date getDefencetime() {
        return defencetime;
    }

    public void setDefencetime(Date defencetime) {
        this.defencetime = defencetime;
    }

    public Teacher getLeader() {
        return leader;
    }

    public void setLeader(Teacher leader) {
        this.leader = leader;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Group(int id, String name, Date defencetime, Teacher leader, Check check) {
        this.id = id;
        this.name = name;
        this.defencetime = defencetime;
        this.leader = leader;
        this.check = check;
    }

    public Group() {
    }
}
