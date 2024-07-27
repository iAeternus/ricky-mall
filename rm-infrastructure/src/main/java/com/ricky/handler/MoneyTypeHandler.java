package com.ricky.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricky.types.common.Money;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyTypeHandler implements TypeHandler<Money> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (IOException e) {
            throw new SQLException("Error serializing Money object to JSON", e);
        }
    }

    @Override
    public Money getResult(ResultSet rs, String columnName) throws SQLException {
        try {
            String json = rs.getString(columnName);
            return json != null ? objectMapper.readValue(json, Money.class) : null;
        } catch (IOException e) {
            throw new SQLException("Error deserializing Money JSON to object", e);
        }
    }

    @Override
    public Money getResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            String json = rs.getString(columnIndex);
            return json != null ? objectMapper.readValue(json, Money.class) : null;
        } catch (IOException e) {
            throw new SQLException("Error deserializing Money JSON to object", e);
        }
    }

    @Override
    public Money getResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            String json = cs.getString(columnIndex);
            return json != null ? objectMapper.readValue(json, Money.class) : null;
        } catch (IOException e) {
            throw new SQLException("Error deserializing Money JSON to object", e);
        }
    }

}