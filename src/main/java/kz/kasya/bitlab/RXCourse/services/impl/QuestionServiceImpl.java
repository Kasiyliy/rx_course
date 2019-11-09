package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Question;
import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;
import kz.kasya.bitlab.RXCourse.models.entities.Test;
import kz.kasya.bitlab.RXCourse.models.requests.OptionRequest;
import kz.kasya.bitlab.RXCourse.models.requests.QuestionRequest;
import kz.kasya.bitlab.RXCourse.repositories.QuestionRepository;
import kz.kasya.bitlab.RXCourse.services.QuestionOptionService;
import kz.kasya.bitlab.RXCourse.services.QuestionService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionOptionService questionOptionService;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Question findById(Long id) throws ServiceException {
        Optional<Question> courseOptional = questionRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public Question save(Question question) throws ServiceException {
        if(question.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  questionRepository.save(question);
    }

    @Override
    public Question update(Question question) throws ServiceException {
        if(question.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return questionRepository.save(question);
    }

    @Override
    public void delete(Question question) throws ServiceException {
        if(question.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        question = findById(question.getId());
        question.setDeletedAt(new Date());
        questionRepository.save(question);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Question question = findById(id);
        question.setDeletedAt(new Date());
        questionRepository.save(question);
    }

    @Override
    public List<Question> findAllByTestId(Long id) throws ServiceException {
        return questionRepository.findAllByDeletedAtNullAndTest_Id(id);
    }

    @Override
    public void addQuestions(List<QuestionRequest> questionRequests, Long testId) throws ServiceException{
        Test test = new Test();
        test.setId(testId);
        for (QuestionRequest request: questionRequests) {
            Question question;
            if(request.getId() == null) {
                 question = new Question();
            }
            else {
                 question = questionRepository.getOne(request.getId());
            }
            question.setQuestion(request.getQuestion());
            question.setScore(request.getScore());
            question.setTest(test);
            questionRepository.save(question);
            for(OptionRequest optionRequest: request.getOptions()){
                QuestionOption questionOption;
                if(optionRequest.getId() == null){
                     questionOption = new QuestionOption();
                }
                else {
                    questionOption = questionOptionService.findById(optionRequest.getId());
                }
                questionOption.setQuestion(question);
                questionOption.setAnswer(optionRequest.getOptionText());
                questionOption.setRightAnswer(optionRequest.getRightAnswer());
                questionOptionService.save(questionOption);
            }
        }
    }
}
