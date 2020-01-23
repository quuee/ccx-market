package com.ccx.security.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "market.security")
public class SecurityProperties {

    private Long expirationDate;

    private String appletSecret;

    private String tokenHeader;

    private String bearer;

    private String authorities;

    private Long appletForbidRefreshDate;

    private String needRefresh;
}
