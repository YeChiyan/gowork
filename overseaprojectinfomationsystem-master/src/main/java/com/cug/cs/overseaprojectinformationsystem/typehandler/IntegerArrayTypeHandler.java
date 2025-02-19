package com.cug.cs.overseaprojectinformationsystem.typehandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description: TODO  可能会用的数据库类型与Java数据类型的转换
 * @author: ShengHui
 * @date: 2023-07-21  16:10
 */
@MappedTypes(Integer[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class IntegerArrayTypeHandler implements TypeHandler<Integer[]> {
    ObjectMapper objectMapper = new ObjectMapper();
    
    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Integer[] integers, JdbcType jdbcType) {
        String value = objectMapper.writeValueAsString(integers);
        preparedStatement.setString(i, value);
    }
    
    @Override
    public Integer[] getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        return transfer(string);
    }
    
    @Override
    public Integer[] getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        return transfer(string);
    }
    
    @Override
    public Integer[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return transfer(string);
    }
    
    @SneakyThrows
    private Integer[] transfer(String s) {
        Integer[] array = new Integer[0];
        if (s == null || "".equals(s)) {
            return array;
        }
        array = objectMapper.readValue(s, Integer[].class);
        return array;
    }
}
