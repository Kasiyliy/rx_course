package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.LikeDto;
import kz.kasya.bitlab.RXCourse.models.entities.Like;
import kz.kasya.bitlab.RXCourse.models.mappers.LikeMapper;
import kz.kasya.bitlab.RXCourse.services.LikeService;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@AllArgsConstructor
public class LikeController extends BaseController {
    private LikeMapper likeMapper;
    private LikeService likeService;

    @GetMapping
    @ApiOperation("Получение всех лайков в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(likeMapper.toDtoList(likeService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение лайка по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(likeMapper.toDto(likeService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Добавление лайка")
    public ResponseEntity<?> add(@RequestBody LikeDto likeDto) throws ServiceException {
        Like like = likeMapper.toEntity(likeDto);
        return buildResponse(likeMapper.toDto(likeService.save(like)), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody LikeDto likeDto) throws ServiceException {
        Like like = likeService.update(likeMapper.toEntity(likeDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(likeMapper.toDto(like))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Удаление лайка")
    public ResponseEntity<?> delete(@RequestBody LikeDto likeDto) throws ServiceException{
        likeService.delete(likeMapper.toEntity(likeDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление лайка по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws ServiceException{
        likeService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }



}
