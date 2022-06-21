package com.example.studyapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode
@Data
@TableName("m_user")
public class User implements Serializable {

    @TableId
    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String password;

    private int status;

    private Date created;

    @TableField("last_login")
    private Date lastLogin;

}
