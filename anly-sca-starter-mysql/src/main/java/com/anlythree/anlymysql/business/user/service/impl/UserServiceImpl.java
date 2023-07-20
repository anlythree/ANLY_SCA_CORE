package com.anlythree.anlymysql.business.user.service.impl;

import com.anlythree.anlymysql.business.user.entity.UserDO;
import com.anlythree.anlymysql.business.user.dao.UserDao;
import com.anlythree.anlymysql.business.user.param.GetUserListParam;
import com.anlythree.anlymysql.business.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DATE: 2023/7/19
 * @USER: anlythree
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {
    @Override
    public List<UserDO> getUserList(GetUserListParam param) {
        List<UserDO> userDOList = lambdaQuery()
//                .lt(UserDO::getVersion, param.getStartVersion())
//                .gt(UserDO::getVersion, param.getEndVersion())
                .orderByAsc(UserDO::getId)
                .list();
        log.info("【data from database,userDOList:】"+userDOList);
        return userDOList;
    }
}
