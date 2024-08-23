package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
   private int userNo;
    private String userName;
    private String userId;
    private String userPwd;
    private String userEmail;
    private String userPhone;
    private String userStatus;
    private String isAdmin;
    private Date userJoinDate;
}
