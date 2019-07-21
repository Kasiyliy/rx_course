package kz.kasya.bitlab.RXCourse.models.mappers;

import kz.kasya.bitlab.RXCourse.models.dtos.TestResultDto;
import kz.kasya.bitlab.RXCourse.models.entities.TestResult;
import kz.kasya.bitlab.RXCourse.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestResultMapper extends AbstractModelMapper<TestResult, TestResultDto> {
    private ModelMapper modelMapper;
    private UserMapper userMapper;
    private TestMapper testMapper;

    public TestResultMapper(ModelMapper modelMapper, UserMapper userMapper, TestMapper testMapper) {
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
        this.testMapper = testMapper;
    }

    @Override
    public TestResultDto toDto(TestResult testResult) {
        TestResultDto testResultDto = modelMapper.map(testResult, TestResultDto.class);
        if (testResult.getUser() != null) {
            testResultDto.setUserDto(userMapper.toDto(testResult.getUser()));
        }
        if (testResult.getTest() != null) {
            testResultDto.setTestDto(testMapper.toDto(testResult.getTest()));
        }
        return testResultDto;
    }

    @Override
    public TestResult toEntity(TestResultDto testResultDto) {
        TestResult test = modelMapper.map(testResultDto, TestResult.class);
        if (testResultDto.getUserDto() != null) {
            test.setUser(userMapper.toEntity(testResultDto.getUserDto()));
        }

        if (testResultDto.getTestDto() != null) {
            test.setTest(testMapper.toEntity(testResultDto.getTestDto()));
        }
        return test;
    }

    @Override
    public List<TestResultDto> toDtoList(List<TestResult> testResults) {
        return testResults.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestResult> toEntityList(List<TestResultDto> testResultDtos) {
        return testResultDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
