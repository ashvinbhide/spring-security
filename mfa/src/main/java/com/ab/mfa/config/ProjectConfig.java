package com.ab.mfa.config;

import com.ab.mfa.security.filters.TokenFilter;
import com.ab.mfa.security.filters.UsernamePasswordFilter;
import com.ab.mfa.security.providers.OtpProvider;
import com.ab.mfa.security.providers.TokenProvider;
import com.ab.mfa.security.providers.UsernamePasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final UsernamePasswordProvider usernamePasswordProvider;

    private final OtpProvider otpProvider;

    final
    TokenProvider TokenProvider;

    public ProjectConfig(OtpProvider otpProvider, UsernamePasswordProvider usernamePasswordProvider
            , TokenProvider TokenProvider) {
        this.otpProvider = otpProvider;
        this.usernamePasswordProvider = usernamePasswordProvider;
        this.TokenProvider = TokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.authenticationProvider(usernamePasswordProvider)
                .authenticationProvider(otpProvider)
                .authenticationProvider(TokenProvider);
    }

    @Override
    protected void configure(HttpSecurity http) {
        http.addFilterAt(usernamePasswordFilter(),
                BasicAuthenticationFilter.class)
             .addFilterAfter(tokenFilter(),
                BasicAuthenticationFilter.class );
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }


    @Bean
    public UsernamePasswordFilter usernamePasswordFilter() {
        return new UsernamePasswordFilter();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
