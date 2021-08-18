package com.springCrudV2.demo.config;

import com.springCrudV2.demo.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private final DefaultTokenServices tokenServices;

    @Autowired
    public ResourceServerConfig(DefaultTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").hasAnyAuthority(Permission.DEVELOPERS_READ.getPermission())
                .antMatchers(HttpMethod.PUT, "/**").hasAnyAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/**").hasAnyAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/**").hasAnyAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .anyRequest().authenticated();
    }
}