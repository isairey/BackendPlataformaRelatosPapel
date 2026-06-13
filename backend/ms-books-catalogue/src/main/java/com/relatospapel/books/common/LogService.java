package com.relatospapel.books.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogService {

    private final ObjectMapper objectMapper;

    public void logAsJson(Object obj) {
        String json = objectMapper.writeValueAsString(obj);
        log.info("Payload: {}", json);
    }


}
