package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.HomeworkAnswerDto;
import kz.kasya.bitlab.RXCourse.models.entities.HomeworkAnswer;
import kz.kasya.bitlab.RXCourse.models.mappers.HomeworkAnswerMapper;
import kz.kasya.bitlab.RXCourse.repositories.HomeworkAnswerRepository;
import kz.kasya.bitlab.RXCourse.services.HomeworkAnswerService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homework_answers")
@AllArgsConstructor
public class HomeworkAnswerController extends BaseController{
    private HomeworkAnswerService homeworkAnswerService;
    private HomeworkAnswerMapper homeworkAnswerMapper;

    @GetMapping
    @ApiOperation("Получение всех домашних заданий в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(homeworkAnswerMapper.toDtoList(homeworkAnswerService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение домашних заданий по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(homeworkAnswerMapper.toDto(homeworkAnswerService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление домашнего задания")
    public ResponseEntity<?> add(@RequestBody HomeworkAnswerDto homeworkAnswerDto) throws ServiceException {
        HomeworkAnswer homeworkAnswer = homeworkAnswerMapper.toEntity(homeworkAnswerDto);
        return buildResponse(homeworkAnswerMapper.toDto(homeworkAnswerService.save(homeworkAnswer)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody HomeworkAnswerDto homeworkAnswerDto) throws ServiceException {
        HomeworkAnswer homeworkAnswer = homeworkAnswerService.update(homeworkAnswerMapper.toEntity(homeworkAnswerDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(homeworkAnswerMapper.toDto(homeworkAnswer))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Удаление домашнего задания")
    public ResponseEntity<?> delete(@RequestBody HomeworkAnswerDto homeworkAnswerDto) throws ServiceException{
        homeworkAnswerService.delete(homeworkAnswerMapper.toEntity(homeworkAnswerDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление домашнего задания по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        homeworkAnswerService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }
}
