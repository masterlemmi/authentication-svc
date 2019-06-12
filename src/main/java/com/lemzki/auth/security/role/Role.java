package com.lemzki.auth.security.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemzki.auth.security.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = {"users"})
public class Role {

    public static final Role USER = new Role("USER") ;
    public static final Role ADMIN = new Role("ADMIN") ;

    @Id
    @Column(unique = true)
    private String name;

    public Role(String name){
      this.name = name;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}
