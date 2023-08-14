package com.anlythree.anlyes.entity;

import com.anlythree.common.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2020/7/8 4:57 下午
 * @Version: 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseDto {

    private String name;
    private String description;
    private int age;

}
