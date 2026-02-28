package org.peaklog.repository.query;

import java.util.List;
import java.util.Optional;
import org.peaklog.model.domain.UserModelDomain;

public interface UserQueryRepository {
  List<UserModelDomain> findAllUsers();

  Optional<UserModelDomain> findByLogin(String login);
}
