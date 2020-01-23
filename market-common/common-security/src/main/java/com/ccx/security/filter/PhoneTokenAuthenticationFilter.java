package com.ccx.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.ccx.market.emun.ResultEnum;
import com.ccx.security.authentication.PhoneAuthenticationToken;
import com.ccx.security.util.JwtUtils;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PhoneTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(PhoneTokenAuthenticationFilter.class);

    public static final String SPRING_SECURITY_RESTFUL_LOGIN_URL = "/phone/login";

    @Setter
    @Getter
    private JwtUtils jwtUtils;

    public PhoneTokenAuthenticationFilter() {
        super(new AntPathRequestMatcher(SPRING_SECURITY_RESTFUL_LOGIN_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (!httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String phone = httpServletRequest.getParameter("phone");
        String code = httpServletRequest.getParameter("code");

        logger.info("phone:{},code:{}",phone,code);

        PhoneAuthenticationToken authenticationToken =
                new PhoneAuthenticationToken(phone, code);
        authenticationToken.setDetails(authenticationDetailsSource.buildDetails(httpServletRequest));

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.error("Authentication does not pass");
        String token = jwtUtils.createToken(String.valueOf(authResult.getPrincipal()),authResult.getAuthorities());
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("msg", "Authentication success");
        resMap.put("code", ResultEnum.SuccessResult.getCode());
        resMap.put("token", token);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSONObject.toJSONString(resMap));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        logger.warn("Authentication does not pass");

        Map<String, Object> resMap = new HashMap<>();
//        resMap.put("msg", "Authentication does not pass");
        resMap.put("msg", failed.getMessage());
        resMap.put("code", ResultEnum.FailResult.getCode());

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSONObject.toJSONString(resMap));
    }
}
