package com.serge.nsn.qahub.configuration

import com.serge.nsn.qahub.data.entities.UserEntity
import lombok.Data
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors


@Data
class QAUserDetails(user: UserEntity) : UserDetails {
    private val userName: String
    private val password: String
    private val authorities: List<GrantedAuthority>

    init {
        userName = user.email
        password = user.password
        authorities = Arrays.stream(
            user.roles
                .split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        )
            .map { role: String? -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toList())
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}