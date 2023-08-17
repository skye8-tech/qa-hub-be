//package com.serge.nsn.qahub.services
//
//import com.serge.nsn.qahub.data.entities.VoteEntity
//import com.serge.nsn.qahub.data.repositories.QuestionRepository
//import com.serge.nsn.qahub.data.repositories.UserRepository
//import com.serge.nsn.qahub.data.repositories.VoteRepository
//import org.springframework.http.ResponseEntity
//import java.security.Principal
//
//class VoteService (
//    private val voteRepository: VoteRepository,
//    private val questionRepository: QuestionRepository,
//    private val userRepository: UserRepository
//        ) {
//    fun upVoteQuestion(qID: Long, principal: Principal): ResponseEntity<VoteEntity>{
//
//
//        val question = questionRepository.findById(qID).get()
//
//        voteRepository.save(
//            VoteEntity(
//                upVote = true,
//                user = userRepository.findByEmail(principal.name).get()
//            )
//        )
//
////        return voteRepository.findAll().count
//
//    }
//}