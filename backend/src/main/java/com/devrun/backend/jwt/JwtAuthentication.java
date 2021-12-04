package com.devrun.backend.jwt;

import lombok.Getter;

@Getter
public class JwtAuthentication {

  public final String token;

  public final String username;

  JwtAuthentication(String token, String username) {
//    checkArgument(isNotEmpty(token), "token must be provided.");
//    checkArgument(isNotEmpty(username), "username must be provided.");

    this.token = token;
    this.username = username;
  }

}
