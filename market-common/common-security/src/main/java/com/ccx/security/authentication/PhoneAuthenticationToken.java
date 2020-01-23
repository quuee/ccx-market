package com.ccx.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PhoneAuthenticationToken extends AbstractAuthenticationToken {

    private String phone;

    private String code;

    public PhoneAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String phone, String code) {
        super(authorities);
        this.phone = phone;
        this.code = code;
    }

    public PhoneAuthenticationToken(String phone, String code) {
        super(null);
        this.phone = phone;
        this.code = code;
    }

    @Override
    public Object getCredentials() {
        return this.code;
    }

    @Override
    public Object getPrincipal() {
        return this.phone;
    }
}
