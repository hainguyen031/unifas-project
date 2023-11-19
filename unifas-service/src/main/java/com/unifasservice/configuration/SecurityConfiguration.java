package com.unifasservice.configuration;


import com.unifasservice.security.JwtRequestFilter;
import com.unifasservice.security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Lazy
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/auth/login",
                        "/auth/register",
                        "/category").permitAll()



                // all other requests need to be authenticated
//                .antMatchers(HttpMethod.GET,
//                        "/api/admin/")
//                .hasAnyRole(ERole.ADMIN.toString()).

//             antMatchers(HttpMethod.GET, "/home").hasAnyRole(ERole.USER.toString(),ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.GET, "/api/profile").hasAnyRole(ERole.USER.toString(),ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.PUT, "/api/edit").hasAnyRole(ERole.USER.toString(),ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.DELETE, "/api/delete").hasAnyRole(ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.POST, "/api/transferMoneyToMomo").hasAnyRole(ERole.USER.toString(),ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.POST, "/api/transferMoneyToBank").hasAnyRole(ERole.USER.toString(),ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.GET, "/api/admin/getAll").hasAnyRole(ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.GET, "/api/admin/getAllUsers").hasAnyRole(ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.GET, "/api/admin/viewUserInfo/**").hasAnyRole(ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.PUT, "/api/admin/edit/**").hasAnyRole(ERole.ADMIN.toString()).
//                antMatchers(HttpMethod.DELETE, "/api/admin/delete/**").hasAnyRole(ERole.ADMIN.toString()).

//                anyRequest().hasAnyRole(ERole.ADMIN.toString())
                .and()

                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Configure remember me (save token in database)
//        httpSecurity.authorizeHttpRequests()
//                .and().rememberMe()
//                .tokenRepository(this.persistentTokenRepository())
//                .tokenValiditySeconds(24 * 60 * 60);//24 hours

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


//    public PersistentTokenRepository persistentTokenRepository() {
//        return new InMemoryTokenRepositoryImpl();
//    }

}

