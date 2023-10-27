package com.serge.nsn.qahub.configuration

import com.serge.nsn.qahub.configuration.jwt.JWTAuthenticationFilter
import com.serge.nsn.qahub.configuration.jwt.JwtAuthenticationEntryPoint
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig(
    private val authenticationFilter: JWTAuthenticationFilter,
    private val userDetailsService: QAUserDetailsService,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
) {
    companion object {
        val UN_SECURED_URL = arrayOf("/api/auth/**", "/api/auth/login")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.httpBasic().and().csrf().disable().userDetailsService(userDetailsService)
            .cors { cors -> cors.configurationSource(corsConfigSource()) }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(*UN_SECURED_URL).permitAll()
                    .anyRequest().fullyAuthenticated()
            }.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
            .exceptionHandling { e: ExceptionHandlingConfigurer<HttpSecurity?> ->
                e.authenticationEntryPoint(
                    jwtAuthenticationEntryPoint
                )
            }
            .addFilterBefore(authenticationFilter, BasicAuthenticationFilter::class.java)
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }

    fun corsConfigSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.addAllowedOrigin("http://192.168.2.99:4200")
        config.addAllowedOrigin("http://localhost:4200")
        config.addAllowedMethod("POST")
        config.addAllowedMethod("GET")
        config.allowedMethods = listOf("POST", "GET", "DELETE")
        config.addAllowedHeader("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

    @Bean
    fun corsConfig(): CorsFilter {
        return CorsFilter(corsConfigSource())
    }

}