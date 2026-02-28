package org.peaklog.repository.query.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.peaklog.model.domain.UserModelDomain;
import org.peaklog.repository.query.UserQueryRepository;
import org.peaklog.repository.query.mapper.UserLoginRowMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueryRepositoryImpl implements UserQueryRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final UserLoginRowMapper userRowMapper;
  private final String findAllLogins;
  private final String findLoginById;

  public UserQueryRepositoryImpl(
      NamedParameterJdbcTemplate jdbcTemplate,
      UserLoginRowMapper userRowMapper,
      @Value("${user.findAllLogins}") String findAllLogins,
      @Value("${user.findLoginById}") String findLoginById) {
    this.jdbcTemplate = jdbcTemplate;
    this.userRowMapper = userRowMapper;
    this.findAllLogins = findAllLogins;
    this.findLoginById = findLoginById;
  }

  @Override
  public List<UserModelDomain> findAllUsers() {
    return jdbcTemplate.query(findAllLogins, userRowMapper);
  }

  @Override
  public Optional<UserModelDomain> findByLogin(String login) {

    List<UserModelDomain> result =
        jdbcTemplate.query(findLoginById, Map.of("login", login), userRowMapper);

    return result.stream().findFirst();
  }
}
