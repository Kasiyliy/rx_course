package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.StudentCourse;
import kz.kasya.bitlab.RXCourse.models.entities.Test;
import kz.kasya.bitlab.RXCourse.repositories.StudentCourseRepository;
import kz.kasya.bitlab.RXCourse.repositories.TestResultRepository;
import kz.kasya.bitlab.RXCourse.services.StudentCourseService;
import kz.kasya.bitlab.RXCourse.services.TestService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {
    private StudentCourseRepository studentCourseRepository;
    private TestService testService;

    @Override
    public StudentCourse save(StudentCourse studentCourse) throws ServiceException {
        if(studentCourse.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Student course already exists")
                    .build();
        }
        return  studentCourseRepository.save(studentCourse);    }

    @Override
    public List<StudentCourse> findByUserId(Long id) {
        return studentCourseRepository.findByUser_IdAndDeletedAtIsNull(id);
    }

    @Override
    public Page<StudentCourse> findAllPageableByUserId(Optional<Integer> page, Optional<Integer> size, Optional<String[]> sortBy, Long userId) {
        Sort sort = null;
        if(sortBy.isPresent()){
            String[] sorters = sortBy.get();
            List<Sort.Order> sorts = Arrays.stream(sorters)
                    .map(s -> s.split("-")[0].trim().equalsIgnoreCase("asc")
                            ? Sort.Order.asc( s.split("-")[1]) : Sort.Order.desc( s.split("-")[1]))
                    .collect(Collectors.toList());
            sort = Sort.by(sorts);
        }else{
            sort = Sort.by("id");
        }
        return studentCourseRepository.findAllPageableByUser_idAndDeletedAtIsNull(PageRequest.of(page.orElse(0),size.orElse(5),sort), userId);
}

    @Override
    public List<Test> getTests(Long userId) throws ServiceException {
        List<StudentCourse> studentCourses = studentCourseRepository.findByUser_IdAndDeletedAtIsNull(userId);
        List<Test> tests = new ArrayList<>();
        for (StudentCourse studentCourse: studentCourses) {
            tests.addAll(testService.findAllByCourseId(studentCourse.getCourse().getId()));
        }
        return tests;
    }
}
