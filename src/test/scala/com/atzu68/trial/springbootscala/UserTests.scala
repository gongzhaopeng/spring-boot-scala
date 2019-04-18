package com.atzu68.trial.springbootscala

import org.apache.tomcat.util.codec.binary.Base64
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.{HttpEntity, HttpHeaders, HttpStatus, MediaType}
import org.springframework.test.context.junit4.SpringRunner

import scala.collection.JavaConverters._
import com.atzu68.trial.springbootscala.model.User
import org.springframework.beans.factory.annotation.Autowired

@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserTests {

  @Autowired
  var restTemplate: TestRestTemplate = _

  @Test
  def testPostCreateUser() = {

    val headers = new HttpHeaders
    headers.add("Authorization",
      s"Basic ${Base64.encodeBase64String(("root" + ":" + "root").getBytes)}")
    headers.setContentType(MediaType.APPLICATION_JSON)
    headers.setAccept(List(MediaType.APPLICATION_JSON).asJava)

    val user = new User
    user.setUsername("Test")
    user.setPassword("Test")
    user.setEnabled(true)

    val entity = new HttpEntity(user, headers)
    val response = restTemplate.postForEntity("/api/users", entity, classOf[String])

    assert(response.getStatusCode == HttpStatus.CREATED)
  }
}
