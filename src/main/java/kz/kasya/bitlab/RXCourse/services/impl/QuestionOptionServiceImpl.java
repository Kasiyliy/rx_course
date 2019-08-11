package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.QuestionOption;
import kz.kasya.bitlab.RXCourse.repositories.QuestionOptionRepository;
import kz.kasya.bitlab.RXCourse.services.QuestionOptionService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private QuestionOptionRepository questionOptionRepository;

    @Autowired
    public QuestionOptionServiceImpl(QuestionOptionRepository questionOptionRepository) {
        this.questionOptionRepository = questionOptionRepository;
    }

    @Override
    public List<QuestionOption> findAll() {
        return questionOptionRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public QuestionOption findById(Long id) throws ServiceException {
        Optional<QuestionOption> courseOptional = questionOptionRepository.findById(id);
        return courseOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("lesson not found")
                .build());
    }

    @Override
    public QuestionOption save(QuestionOption questionOption) throws ServiceException {
        if(questionOption.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("lesson already exists")
                    .build();
        }
        return  questionOptionRepository.save(questionOption);
    }

    @Override
    public QuestionOption update(QuestionOption questionOption) throws ServiceException {
        if(questionOption.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        return questionOptionRepository.save(questionOption);
    }

    @Override
    public void delete(QuestionOption questionOption) throws ServiceException {
        if(questionOption.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("lesson is null")
                    .build();
        }
        questionOption = findById(questionOption.getId());
        questionOption.setDeletedAt(new Date());
        questionOptionRepository.save(questionOption);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        QuestionOption questionOption = findById(id);
        questionOption.setDeletedAt(new Date());
        questionOptionRepository.save(questionOption);
    }

    @Override
    public List<QuestionOption> findAllByQuestionIds(List<Long> ids) throws ServiceException {
        return questionOptionRepository.findAllByDeletedAtNullAndQuestion_IdIn(ids);
    }
}
