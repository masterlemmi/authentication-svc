package com.lemzki.auth.security.role;

import com.lemzki.auth.security.user.User;

import java.util.Set;

public interface RoleService {
    Set<Role> findByUsers(User user);

}
