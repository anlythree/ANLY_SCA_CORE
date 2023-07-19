package com.anlythree.anlymysql.business.user.param;

import lombok.Data;

/**
 * @DATE: 2023/7/19
 * @USER: anlythree
 */
@Data
public class GetUserListParam {

    private Integer startVersion;

    private Integer endVersion;
}
