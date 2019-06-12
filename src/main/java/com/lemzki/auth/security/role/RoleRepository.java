package com.lemzki.auth.security.role;


import com.lemzki.auth.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByUsers(User user);
}
