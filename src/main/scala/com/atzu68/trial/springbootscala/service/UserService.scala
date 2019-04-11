package com.atzu68.trial.springbootscala.service

import java.lang.Iterable
import java.util.Optional

import com.atzu68.trial.springbootscala.model.User
import com.atzu68.trial.springbootscala.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

  def listUsers: Iterable[User] = {
    userRepository.findAll
  }

  def getUser(id: Long): Optional[User] = {
    userRepository.findById(id)
  }

  def createUser(user: User): Long = {
    userRepository.save(user)
    user.id
  }
}
