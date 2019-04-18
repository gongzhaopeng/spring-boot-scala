package com.atzu68.trial.springbootscala.configuration

import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(@Autowired val dataSource: DataSource)
  extends WebSecurityConfigurerAdapter {

  override def configure(http: HttpSecurity): Unit = {

    http.authorizeRequests().antMatchers(
      "/",
      "/h2-console/**",
      "/swagger-ui.html",
      "/**/*.css",
      "/**/*.js",
      "/**/*.png",
      "/**/*.woff*",
      "/configuration/**",
      "/swagger-resources/**",
      "/v2/**",
      "/csrf/**").permitAll()

    http.authorizeRequests().anyRequest().authenticated()
    http.csrf().disable()
    http.headers().frameOptions().disable()
    http.httpBasic()
  }

  override def configure(auth: AuthenticationManagerBuilder): Unit =
    auth.userDetailsService(userDetailsService()).
      passwordEncoder(passwordEncoder())

  @Bean
  override def userDetailsService(): UserDetailsService = {

    val manager = new JdbcDaoImpl
    manager.setDataSource(dataSource)
    manager
  }

  @Bean
  def passwordEncoder(): PasswordEncoder =
    PasswordEncoderFactories.createDelegatingPasswordEncoder()
}
