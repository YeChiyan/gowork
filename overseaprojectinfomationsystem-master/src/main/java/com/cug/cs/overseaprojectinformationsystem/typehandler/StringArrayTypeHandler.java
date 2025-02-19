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
 * @description: TODO String[]转换
 * @author: ShengHui
 * @date: 2023-07-21  16:45
 */
@MappedTypes(String[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class StringArrayTypeHandler implements TypeHandler<String[]> {
    ObjectMapper objectMapper = new ObjectMapper();
    
    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        String value = objectMapper.writeValueAsString(parameter);
        ps.setString(i, value);
    }
    
    @Override
    public String[] getResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        return transfer(string);
    }
    
    @Override
    public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        return transfer(string);
    }
    
    @Override
    public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        return transfer(string);
    }
    
    @SneakyThrows
    private String[] transfer(String s) {
        String[] array = new String[0];
        if (s == null || "".equals(s)) {
            return array;
        }
        array = objectMapper.readValue(s, String[].class);
        return array;
    }
}
