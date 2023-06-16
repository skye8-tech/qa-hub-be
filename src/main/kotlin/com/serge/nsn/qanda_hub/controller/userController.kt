package com.serge.nsn.qanda_hub.controller
import com.serge.nsn.qanda_hub.dto.AnswerDto
import com.serge.nsn.qanda_hub.dto.CommentDto
import com.serge.nsn.qanda_hub.dto.QuestionDto
import com.serge.nsn.qanda_hub.dto.UserDto
import com.serge.nsn.qanda_hub.entities.QuestionEntity
import com.serge.nsn.qanda_hub.entities.UserEntity
import com.serge.nsn.qanda_hub.services.AnswerService
import com.serge.nsn.qanda_hub.services.CommentService
import com.serge.nsn.qanda_hub.services.QuestionService
import com.serge.nsn.qanda_hub.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping
class UserController(
    private val userService: UserService,
    private val questionService: QuestionService,
    private val answerService: AnswerService,
    private val commentService: CommentService
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody dto: UserDto): ResponseEntity<UserDto> = ResponseEntity(userService.register_user(dto), HttpStatus.CREATED)
//
//    @PostMapping("/login")
//    fun userLogin(@RequestBody dto: LoginDto): ResponseEntity<UserDto>{
//        return userService.login()
//    }

    @PostMapping("/post-question")
    fun askQuestion(@RequestBody questionDto: QuestionDto) = questionService.askQuestion(questionDto)

    @PostMapping("/answer-question/{question_id}")
    fun answerQuestion(@RequestBody answerDto: AnswerDto, @PathVariable question_id: Long) = answerService.answerQuestion(answerDto, question_id)

    @GetMapping("/all-questions")
    fun getAllQuestions(): List<QuestionEntity> = questionService.getAllQuestions()

    @GetMapping("/question/{question_id}/comments")
    fun viewComments(@PathVariable question_id: Long) = commentService.getAllComments(question_id)

    @PostMapping("/question/{question_id}/comment")
    fun giveComment(commentDto: CommentDto) = commentService.comment(commentDto)

}
