package ru.bmstu.statisticsapp.models.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.bmstu.statisticsapp.models.QueryServiceAvg;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryAvgRowMapper implements RowMapper<QueryServiceAvg> {
    public QueryServiceAvg mapRow(ResultSet rs, int rowNum) throws SQLException {
        QueryServiceAvg data = new QueryServiceAvg();

        data.num = BigInteger.valueOf(rs.getInt("num"));
        data.avgTime = rs.getDouble("avg_time");
        data.service = rs.getString("service");
        data.action = rs.getString("action");

        return data;
    }
}
