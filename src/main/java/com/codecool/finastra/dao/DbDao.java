package com.codecool.finastra.dao;

import com.codecool.finastra.util.ConnUtil;

import java.sql.Connection;

class DbDao {
    //Create connection with DB 'testjob' schema
    final Connection connection;

    DbDao() {
        connection = ConnUtil.getConnection("testjob");
        //TODO assertnull 'n' exceptions
    }
}
