package org.peaklog.service;

import java.util.List;
import org.peaklog.model.domain.UserModelDomain;

public interface UserService {
  List<UserModelDomain> findAllUsers();

  UserModelDomain findUserById(Integer id);
}
