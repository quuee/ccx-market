package com.ccx.mngt.config;


import com.ccx.security.handler.MyAuthExceptionEntryPoint;
import com.ccx.security.filter.TokenAuthenticationFilter;
import com.ccx.security.authentication.TokenAuthenticationProvider;
import com.ccx.security.filter.TokenAuthorizationFilter;
import com.ccx.security.prop.SecurityProperties;
import com.ccx.security.util.JwtUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private SecurityProperties properties;



    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http
                .cors()//允许跨域资源共享
                .and()
                .csrf().disable()//跨站请求伪造
                .exceptionHandling() // 异常处理设置
//                .accessDeniedHandler(new MyAccessDeniedHandler())
                .authenticationEntryPoint(new MyAuthExceptionEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/user/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(tokenAuthorizationFilter(), TokenAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//不使用session

    }


    /**
     * 静态资源 放行
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers(
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/webjars/**"
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception{
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter();
        tokenAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        tokenAuthenticationFilter.setJwtUtils(jwtUtils);
        return tokenAuthenticationFilter;
    }

    @Bean
    public TokenAuthorizationFilter tokenAuthorizationFilter() throws Exception{
        TokenAuthorizationFilter tokenAuthorizationFilter = new TokenAuthorizationFilter(authenticationManagerBean());
        tokenAuthorizationFilter.setJwtUtils(jwtUtils);
        tokenAuthorizationFilter.setProperties(properties);
        return  tokenAuthorizationFilter;
    }
}
