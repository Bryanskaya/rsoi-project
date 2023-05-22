package ru.bmstu.statisticsapp.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.bmstu.statisticsapp.models.ServiceAvg;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ServiceAvgRowMapper implements RowMapper<ServiceAvg> {
    public ServiceAvg mapRow(ResultSet rs, int rowNum) throws SQLException {
        ServiceAvg data = new ServiceAvg();

        data.num = BigInteger.valueOf(rs.getInt("num"));
        data.avgTime = rs.getDouble("avg_time");
        data.service = rs.getString("service");

        return data;
    }
}
