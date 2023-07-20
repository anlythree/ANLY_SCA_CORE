package com.anlythree.anlymysql.business.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 * @DATE: 2023/7/19
 * @USER: anlythree
 */
@Data
@TableName("user_desc")
public class UserDO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String phone;

    private String version;

    private String remove;
}
