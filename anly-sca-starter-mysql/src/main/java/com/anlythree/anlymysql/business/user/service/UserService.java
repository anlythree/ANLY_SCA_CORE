package com.anlythree.anlymysql.business.user.service;

import com.anlythree.anlymysql.business.user.entity.UserDO;
import com.anlythree.anlymysql.business.user.param.GetUserListParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @DATE: 2023/7/19
 * @USER: anlythree
 */
public interface UserService extends IService<UserDO> {

    List<UserDO> getUserList(GetUserListParam param);
}
