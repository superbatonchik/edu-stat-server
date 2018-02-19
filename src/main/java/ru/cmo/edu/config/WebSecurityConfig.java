package ru.cmo.edu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.cmo.edu.data.repository.CredentialsRepository;
import ru.cmo.edu.data.repository.EduRepository;
import ru.cmo.edu.data.repository.MunicipalityRepository;
import ru.cmo.edu.data.repository.RegionRepository;
import ru.cmo.edu.rest.security.JwtAuthenticationTokenFilter;
import ru.cmo.edu.service.JpaUserDetailsService;
import ru.cmo.edu.service.JwtService;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    private CredentialsRepository credentialsRepository;
    private final EduRepository eduRepository;
    private final MunicipalityRepository municipalityRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public WebSecurityConfig(JwtService jwtService, UserDetailsService userDetailsService,
                             CredentialsRepository credentialsRepository,
                             EduRepository eduRepository,
                             MunicipalityRepository municipalityRepository,
                             RegionRepository regionRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.credentialsRepository = credentialsRepository;
        this.eduRepository = eduRepository;
        this.municipalityRepository = municipalityRepository;
        this.regionRepository = regionRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JpaUserDetailsService(credentialsRepository, eduRepository, municipalityRepository, regionRepository);
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService());
                //.passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter(jwtService, userDetailsService);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            logger.info("401", e);
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        };
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/*").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();

        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // disable page caching
        httpSecurity.headers().cacheControl().disable();
    }
}
