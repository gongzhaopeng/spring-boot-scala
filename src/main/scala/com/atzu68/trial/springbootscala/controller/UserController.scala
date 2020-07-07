package com.atzu68.trial.springbootscala.controller

import java.lang.{Iterable => JIterable}

import com.atzu68.trial.springbootscala.model.User
import com.atzu68.trial.springbootscala.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(path = Array("/api"))
class UserController(@Autowired private val userService: UserService) {

  @GetMapping(path = Array("/users"))
  def getAllUsers: JIterable[User] = {
    userService.listUsers
  }

  @GetMapping(path = Array("/users/{id}"))
  def getUser(@PathVariable id: Long): User = {
    userService.getUser(id).orElse(null)
  }

  @PostMapping(path = Array("/users"))
  def createUser(@RequestBody user: User): ResponseEntity[Long] = {
    val id = userService.createUser(user)
    new ResponseEntity(id, null, HttpStatus.CREATED)
  }
}
