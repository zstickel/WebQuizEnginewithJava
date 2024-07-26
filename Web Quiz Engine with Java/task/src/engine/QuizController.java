package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuizController {
    @Autowired
    private UserService userService;
    @Autowired QuizManagerService quizManagerService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    /*
    @GetMapping("/api/quiz")
    public Quiz getQuiz() {
        return quizManagerService.getQuizList().get(0);
    }
     */

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable("id") long id){
        Quiz quiz = quizManagerService.getQuizByiD(id);
        if (quiz != null){
            QuizResponse quizResponse = new QuizResponse(quiz);
            return new ResponseEntity<>(quizResponse, HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.ok("User registered successfully!");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/quizzes")
    public ResponseEntity<Page<QuizResponse>> getQuizzes(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<QuizResponse> quizPage = quizManagerService.getQuizList(pageable);
        return ResponseEntity.ok(quizPage);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseEntity<AnswerResponse> postAnswer(@PathVariable long id, @RequestBody AnswerRequest answerRequest,  @AuthenticationPrincipal UserDetails currentUser) {
        int [] answer = answerRequest.getAnswer().stream().mapToInt(Integer::intValue).toArray();
        if (!quizManagerService.quizIdExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean isCorrect = quizManagerService.checkQuizAnswer(id, answer);
        if (isCorrect) {
            User user = userRepository.findByEmail(currentUser.getUsername());
            Quiz quiz = quizRepository.findById(id).orElseThrow();
            quizManagerService.markQuizAsCompleted(user, quiz);
            return ResponseEntity.ok(new AnswerResponse(true, "Congratulations, you're right!"));
        } else {
            return ResponseEntity.ok(new AnswerResponse(false, "Wrong answer! Please, try again."));
        }
    }

    @GetMapping("/api/quizzes/completed")
    public ResponseEntity<Page<CompletedQuizDTO>> getCompletedQuizzes(@RequestParam(defaultValue = "0") int page, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userRepository.findByEmail(currentUser.getUsername());
        Pageable pageable = PageRequest.of(page, 10);
        Page<CompletedQuizDTO> completedQuizPage = quizManagerService.getCompletedQuizzes(user, pageable);
        return ResponseEntity.ok(completedQuizPage);
    }

    @PostMapping("api/quizzes")
    public ResponseEntity<QuizResponse> createQuiz(@RequestBody @Valid QuizRequest quizRequest,  @AuthenticationPrincipal UserDetails currentUser) {
        String username = currentUser.getUsername();
        Quiz newQuiz = quizManagerService.addQuiz(quizRequest, username);
        // Normally, you'd save the newQuiz to a database or perform some other processing here

        return ResponseEntity.ok(new QuizResponse(newQuiz));
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id,
                                           @AuthenticationPrincipal UserDetails currentUser) {
        Quiz quiz = quizManagerService.getQuizByiD(id);
        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
        }

        if (!quiz.getAuthor().equals(currentUser.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author of this quiz");
        }


        quizManagerService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    private boolean checkAnswer(int answer){
        return answer == 2;
    }

}
