package com.anlythree.anlymysql.business.user.service.impl;

import com.anlythree.anlymysql.business.user.DO.UserDO;
import com.anlythree.anlymysql.business.user.dao.UserDao;
import com.anlythree.anlymysql.business.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @DATE: 2023/7/19
 * @USER: anlythree
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {
}
