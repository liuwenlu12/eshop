package com.oaec.eshop.util;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcDataSource {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "eshop";
    private static final String PASSWORD = "123456";

    private static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}
