package com.codesquad.todo2.log;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonTypeName("projectLogs")
public class LogsDto {
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    private Long projectId;
    @JsonProperty("logs")
    private List<LogDto> logDtoList;

    public LogsDto(Long projectId) {
        this.projectId = projectId;
    }

    public List<LogDto> getLogDtoList() {
        return logDtoList;
    }

    public void setLogDtoList(List<LogDto> logDtoList) {
        this.logDtoList = logDtoList;
    }
}
