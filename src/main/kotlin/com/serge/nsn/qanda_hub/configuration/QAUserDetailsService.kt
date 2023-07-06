package com.serge.nsn.qanda_hub.configuration

import com.serge.nsn.qanda_hub.data.repositories.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class QAUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)
            .map { user -> QAUserDetails(user) }
            .orElseThrow { UsernameNotFoundException("No user found") }
    }
}