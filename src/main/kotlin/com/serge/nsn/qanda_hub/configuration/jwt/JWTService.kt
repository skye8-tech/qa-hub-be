package com.serge.nsn.qanda_hub.configuration.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function


@Service
@AllArgsConstructor
@NoArgsConstructor
class JWTService {
    @Value("\${spring.jwt.secret}")
    private lateinit var jwtSecret: String

    @Value("\${spring.jwt.expiration}")
    private val jwtExpiration: Long = 1

    fun generateToken(userName: String?): String {
        val claims: Map<String, Any?> = HashMap()
        return tokenCreator(claims, userName)
    }

    fun tokenCreator(claims: Map<String, Any?>?, userName: String?): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(signedKey, SignatureAlgorithm.HS256).compact()
    }

    fun extractUsernameFromToken(theToken: String?): String {
        return extractClaim(theToken) { obj: Claims -> obj.subject }
    }

    fun extractExpirationTimeFromToken(theToken: String?): Date {
        return extractClaim(theToken) { obj: Claims -> obj.expiration }
    }

    fun extractUsername(token: String?): String {
        return extractAllClaims(token).subject
    }

    fun validateToken(token: String): Boolean = !isTokenExpired(token)

    private fun <T> extractClaim(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(signedKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(theToken: String?): Boolean {
        return extractExpirationTimeFromToken(theToken).before(Date())
    }

    private val signedKey: Key
        get() {
            val keyByte = Decoders.BASE64.decode(jwtSecret)
            return Keys.hmacShaKeyFor(keyByte)
        }
}