package com.ccx.mngt.commons.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MyUserDetails extends User {

    private Long userId;

    private Boolean superUser;

    private Set<MenuRouteButtonVO> menuRouteButtonVOSet;

    public MyUserDetails(String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities,
                         Long userId,
                         Boolean superUser) {
        super(username, password, authorities);
        this.userId = userId;
        this.superUser=superUser;
    }

    public MyUserDetails(String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities,
                         Set<MenuRouteButtonVO> menuRouteButtonVOSet,
                         Long userId,
                         Boolean superUser) {
        super(username, password, authorities);
        this.userId = userId;
        this.superUser=superUser;
        this.menuRouteButtonVOSet=menuRouteButtonVOSet;
    }

    public MyUserDetails(String username,
                         Collection<? extends GrantedAuthority> authorities,
                         Long userId,
                         Boolean superUser) {
        super(username, null,authorities);
        this.userId = userId;
        this.superUser=superUser;
    }
}
