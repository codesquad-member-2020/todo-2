package com.codesquad.todo2.log;

import com.codesquad.todo2.api.ResponseBodyWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/projects/{projectId}")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    public ResponseEntity<ResponseBodyWrapper> viewLog(@PathVariable long projectId) throws JsonProcessingException {
        List<LogDto> logDtos = logService.mapProjectToLogDtos(projectId);
        LogsDto logsDto = new LogsDto(projectId);
        logsDto.setLogDtoList(logDtos);
        return ResponseEntity.ok(ResponseBodyWrapper.ok(logsDto));
    }
}
