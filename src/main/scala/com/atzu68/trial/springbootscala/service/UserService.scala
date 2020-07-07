package com.atzu68.trial.springbootscala.service

import java.util.Optional

import com.atzu68.trial.springbootscala.model.User
import com.atzu68.trial.springbootscala.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.{PostAuthorize, PreAuthorize}
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

  @PreAuthorize("hasRole('admin')")
  def listUsers: Iterable[User] = {
    import scala.collection.JavaConverters._
    userRepository.findAll.asScala
  }

  @PreAuthorize("hasRole('user')")
  @PostAuthorize("returnObject.username==principal.username || hasRole('admin')")
  def getUser(id: Long): Optional[User] = {
    userRepository.findById(id)
  }

  @PreAuthorize("hasRole('admin')")
  def createUser(user: User): Long = {
    userRepository.save(user)
    user.id
  }
}