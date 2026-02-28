package org.peaklog.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.peaklog.exception.ErrorCode;
import org.peaklog.exception.ServiceException;
import org.peaklog.model.domain.UserModelDomain;
import org.peaklog.repository.query.UserQueryRepository;
import org.peaklog.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserQueryRepository userQueryRepository;

  @Override
  public List<UserModelDomain> findAllUsers() {

    return this.userQueryRepository.findAllUsers();
  }

  @Override
  public UserModelDomain findUserById(Integer id) {
    Optional<UserModelDomain> user = this.userQueryRepository.findByLogin(id.toString());
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new ServiceException(ErrorCode.USER_NOT_FOUND);
    }
  }
}
