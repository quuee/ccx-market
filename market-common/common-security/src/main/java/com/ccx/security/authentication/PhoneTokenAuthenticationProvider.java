package com.ccx.security.authentication;

import com.ccx.security.conts.RedisKey;
import com.ccx.security.util.RedisUtil;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public class PhoneTokenAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(PhoneTokenAuthenticationProvider.class);

//    @Autowired
//    @Qualifier("AppPhoneDetailService")
    @Setter
    @Getter
    private UserDetailsService userDetailsService;

    @Setter
    @Getter
    private RedisUtil redisUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PhoneAuthenticationToken usernamePasswordAuthenticationToken=
                (PhoneAuthenticationToken)authentication;

        Object phone = usernamePasswordAuthenticationToken.getPrincipal();//phone
        Object code = usernamePasswordAuthenticationToken.getCredentials();//code

        Object redisPhoneCode = redisUtil.get(RedisKey.APP_USER_PHONE + String.valueOf(phone));
        logger.info("phone:{},code:{},redisPhoneCode:{}",phone,code,redisPhoneCode);
        if(!String.valueOf(code).equals(String.valueOf(redisPhoneCode))){
            throw new BadCredentialsException("验证码错误");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(String.valueOf(phone));
//        UsernamePasswordAuthenticationToken result =
//                new UsernamePasswordAuthenticationToken(phone,null,userDetails.getAuthorities());
//        result.setDetails(userDetails);
        PhoneAuthenticationToken phoneAuthenticationToken = new PhoneAuthenticationToken(userDetails.getUsername(), null);
        phoneAuthenticationToken.setDetails(userDetails);
        return phoneAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PhoneAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
