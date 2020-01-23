package com.ccx.front.commons.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppUserDetailsDTO extends User {

    private Long userId;

    private String headImage;

    private String phone;

    private String email;

    private String openId;

    private Short isVip;

    public AppUserDetailsDTO(String nickName,
                             String password, Collection<? extends GrantedAuthority> authorities,
                             Long userId, String headImage, String phone,
                             String email, String openId, Short isVip) {
        super(nickName, password, authorities);
        this.userId = userId;
        this.headImage = headImage;
        this.phone = phone;
        this.email = email;
        this.openId = openId;
        this.isVip = isVip;
    }
}
