package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.UserInfo;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;

    private RoleService roleService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService, RoleService roleService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
//    @Bean
//    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
//        daoAuthProvider.setPasswordEncoder(bCryptPasswordEncoder());
//        daoAuthProvider.setUserDetailsService(userService.listUsers());
//        return daoAuthProvider;
//    }


//     аутентификация inMemory
//@Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                UserInfo.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService uds = new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserInfo user = userService.getUserByLogin(username);

                if (user.getId() < 0) {
                    throw new UsernameNotFoundException("user not found");
                }
//                Role role = roleService.getRoleByID(user.getId());

                return user;

//                return null;
            }};

        auth.userDetailsService(uds);

    }

//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
//    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

//     аутентификация userDetailService
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
////        UserDetails user =
////                User.withDefaultPasswordEncoder()
////                        .username("user1")
////                        .password("user1")
////                        .roles("USER")
////                        .build();
////        UserDetails user1 =
////                User.withDefaultPasswordEncoder()
////                        .username("user")
////                        .password("user")
////                        .roles("USER")
////                        .build();
////        UserDetails user2 =
////                User.withDefaultPasswordEncoder()
////                        .username("admin")
////                        .password("admin")
////                        .roles("ADMIN")
////                        .build();
////        Map<String, UserDetails> userDetailsMap = new HashMap<>();
////        userDetailsMap.put(user.getUsername(), user);
////        userDetailsMap.put(user1.getUsername(), user1);
////        userDetailsMap.put(user2.getUsername(), user2);
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                UserInfo user = userService.getUserByLogin(username);
//
//                return new User(user.getUsername(), user.getPassword(), user.getRoles());
//            }
//        };
//    }
}