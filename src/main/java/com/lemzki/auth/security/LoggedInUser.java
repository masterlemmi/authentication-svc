package com.lemzki.auth.security;

import com.lemzki.auth.security.user.User;


@FunctionalInterface
public interface LoggedInUser {
    User get();

     LoggedInUser ANONYMOUS = ()-> User.ANONYMOUS;

}
