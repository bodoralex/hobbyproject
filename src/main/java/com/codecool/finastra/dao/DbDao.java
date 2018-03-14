package com.codecool.finastra.dao;

import com.codecool.finastra.util.ConnUtil;

import java.sql.Connection;

class DbDao {
    //Create connection with DB 'testjob' schema
    final Connection connection = ConnUtil.getConnection("testjob");
}
