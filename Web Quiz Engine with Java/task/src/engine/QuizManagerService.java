package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizManagerService {

    //private IdCounter idCounter = new IdCounter();

    @Autowired QuizRepository quizRepository;
    @Autowired
    private CompletedQuizRepository completedQuizRepository;


    public Quiz addQuiz(QuizRequest quizRequest, String username){
        Quiz newQuiz = new Quiz(
                username,
                quizRequest.getTitle(),
                quizRequest.getText(),
                quizRequest.getOptions() != null ? quizRequest.getOptions() : null,
                quizRequest.getAnswer() != null ? quizRequest.getAnswer() : null
        );
        quizRepository.save(newQuiz);
        return newQuiz;
    }
    public void printQuizzes(){
        quizRepository.findAll().forEach(quiz -> System.out.println(quiz.toString()));
    }

    public Quiz getQuizByiD(long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElse(null);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public boolean quizIdExists(long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
        Quiz quizOrNull = quiz.orElse(null);
        return quizOrNull != null;
    }

    public Page<QuizResponse> getQuizList(Pageable pageable){
        Page<Quiz> quizPage = quizRepository.findAll(pageable);
        List<QuizResponse> quizDTOs = quizPage.stream().map(QuizResponse::new).collect(Collectors.toList());
        return new PageImpl<>(quizDTOs, pageable, quizPage.getTotalElements());
    }


    public Page<CompletedQuizDTO> getCompletedQuizzes(User user, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "completedAt"));
        Page<CompletedQuiz> completedQuizzes = completedQuizRepository.findByUser(user, sortedPageable);
        return completedQuizzes.map(CompletedQuizDTO::new);
    }

    public void markQuizAsCompleted(User user, Quiz quiz) {
        CompletedQuiz completedQuiz = new CompletedQuiz(user, quiz, LocalDateTime.now());
        completedQuizRepository.save(completedQuiz);
    }

    /*
    public List<QuizResponse> getQuizResponseList() {
        List<QuizResponse> quizResponses = new ArrayList<>();
        List<Quiz> quizzes = quizRepository.findAll();
        for(Quiz quiz : quizzes){
            QuizResponse quizResponse = new QuizResponse(quiz);
            quizResponses.add(quizResponse);
        }
        return quizResponses;
    }

     */

    public boolean checkQuizAnswer(long quizId, int[] answer){
        Quiz quiz = getQuizByiD(quizId);
        if (quiz == null) {
            return false;
        }
        if (quiz.getAnswer() == null){
            return answer.length == 0;
        }
        if (answer.length != quiz.getAnswer().size()){
            return false;
        }

        for (int i=0; i<answer.length; i++){
            if (answer[i] != quiz.getAnswer().get(i)){
                return false;
            }
        }
        return true;

    }



}
