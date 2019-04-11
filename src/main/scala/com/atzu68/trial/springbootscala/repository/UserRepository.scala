package com.atzu68.trial.springbootscala.repository

import com.atzu68.trial.springbootscala.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
trait UserRepository extends CrudRepository[User, Long] {

  def findUserByUsername(username: String): User
}
