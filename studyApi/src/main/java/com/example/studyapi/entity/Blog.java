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
public class Blog implements Serializable {

    @TableId
    private Long id;

    @TableField("user_id")
    private Long userId;

    private String title;

    private String description;

    private String content;

    private Date create;

    private int status;



}
