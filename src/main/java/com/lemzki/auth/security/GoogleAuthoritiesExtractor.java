package com.lemzki.auth.security;

import com.lemzki.auth.security.role.Role;
import com.lemzki.auth.security.role.RoleService;
import com.lemzki.auth.security.user.User;
import com.lemzki.auth.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GoogleAuthoritiesExtractor implements AuthoritiesExtractor {
  @Autowired
  public UserService userService;

  @Autowired
  RoleService roleService;

  @Override
  public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
    String id = (String) map.get("id");
    Optional<User> user = userService.findByGoogleId(id);

    //user not registered to our DB so no authorities given
    if (!user.isPresent()) {
      return Collections.emptyList();
    }

    Set<Role> roleSet = roleService.findByUsers(user.get());
    String[] roles = roleSet.stream()
        .map(role -> "ROLE_"+ role.getName())
        .toArray(String[]::new);

    return AuthorityUtils.createAuthorityList(roles);
  }
}
