package com.sgu.tourism.entity;


import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {


    /**
     * 用户id;
     */
    private Integer userId;
    /***
     * 用户的登录名
     */
    private String userName;


    /***
     * 用户的真正的名字；
     */
    private String userRealName;
    /**
     * 用户密码
     */
    private String password;
    /**
     *  用户电话
     */
    private String phone;
    /**
     * 用户性别
     */
    private Integer sex;
    /**
     *  用户创建时间；
     */
    private String createTime;
    /**
     * 用户的角色id  引用
     */
    private Integer roleType;
    /**
     *  头像
     */
    private String imgFileName;


    /**
     * 用户是否删除     0 正常
     *                 1已经删除
     */
    private int del;

}
