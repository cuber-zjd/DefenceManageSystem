package com.dy.entity;

/**
 * @author: zjd-DY
 * @date: 2021/6/17 21:10
 * @description:
 */
public class Student {
    int id;
    String name;
    int sno;
    String sex;

    Group group;
    GradeList grade;
    String paperUrl;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sno=" + sno +
                ", sex='" + sex + '\'' +

                ", group=" + group +
                ", grade=" + grade +
                ", paperUrl='" + paperUrl + '\'' +
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

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
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

    public GradeList getGrade() {
        return grade;
    }

    public void setGrade(GradeList grade) {
        this.grade = grade;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    public Student(int id, String name, int sno, String sex, Group group, GradeList grade, String paperUrl) {
        this.id = id;
        this.name = name;
        this.sno = sno;
        this.sex = sex;
        this.group = group;
        this.grade = grade;
        this.paperUrl = paperUrl;
    }
}