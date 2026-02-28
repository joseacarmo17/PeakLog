package org.peaklog.repository.query.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.peaklog.model.domain.UserModelDomain;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserLoginRowMapper implements RowMapper<UserModelDomain> {

  @Override
  public UserModelDomain mapRow(ResultSet rs, int rowNum) throws SQLException {

    return UserModelDomain.builder().login(rs.getString("login")).build();
  }
}
