package com.jurisdiction.common.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
public class SysTable {
    //表的名称
    private String tableName;
    //表的备注
    private String comments;
    //表的主键
    private SysColumn pk;
    //表的列名(不包含主键)
    private List<SysColumn> sysColumns;

    //类名(第一个字母大写)，如：sys_user => SysUser
    private String className;
    //类名(第一个字母小写)，如：sys_user => sysUser
    private String classname;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public SysColumn getPk() {
        return pk;
    }

    public void setPk(SysColumn pk) {
        this.pk = pk;
    }

    public List<SysColumn> getSysColumns() {
        return sysColumns;
    }

    public void setSysColumns(List<SysColumn> sysColumns) {
        this.sysColumns = sysColumns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "SysTable{" +
                "tableName='" + tableName + '\'' +
                ", comments='" + comments + '\'' +
                ", pk=" + pk +
                ", sysColumns=" + sysColumns +
                ", className='" + className + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
