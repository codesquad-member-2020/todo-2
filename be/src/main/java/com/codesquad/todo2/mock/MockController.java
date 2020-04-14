package com.codesquad.todo2.mock;

import com.codesquad.todo2.domain.card.CardIds;
import com.codesquad.todo2.domain.card.CardTitleContent;
import com.codesquad.todo2.api.ResponseBodyWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class MockController {

    private static final Logger log = LoggerFactory.getLogger(MockController.class);

    @GetMapping("/mock/projects/{projectId}")
    public ResponseEntity<ResponseBodyWrapper> mockViewProject(@PathVariable Long projectId) throws JsonProcessingException {
        String mockData = "{\n" +
                "   \"project\":[\n" +
                "      {\n" +
                "         \"category_id\":1,\n" +
                "         \"title\":\"To Do\",\n" +
                "         \"cards\":[\n" +
                "            {\n" +
                "               \"cardId\":1,\n" +
                "               \"user_name\":\"조승우\",\n" +
                "               \"title\":\"제목입니다.\",\n" +
                "               \"content\":\"내용입니다.\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"cardId\":2,\n" +
                "               \"user_name\":\"조승우\",\n" +
                "               \"title\":\"제목입니다.(2)\",\n" +
                "               \"content\":\"내용입니다.(2)\"\n" +
                "            }\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"category_id\":2,\n" +
                "         \"title\":\"Doing\",\n" +
                "         \"cards\":[\n" +
                "            {\n" +
                "               \"cardId\":4,\n" +
                "               \"user_name\":\"조승우\",\n" +
                "               \"title\":\"제목입니다.(4)\",\n" +
                "               \"content\":\"내용입니다.(4)\"\n" +
                "            }\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"category_id\":3,\n" +
                "         \"title\":\"Done\",\n" +
                "         \"cards\":[\n" +
                "            {\n" +
                "               \"cardId\":3,\n" +
                "               \"user_name\":\"조승우\",\n" +
                "               \"title\":\"제목입니다.(3)\",\n" +
                "               \"content\":\"내용입니다.(3)\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode mockDataJson = mapper.readTree(mockData);
        return ResponseEntity.ok(ResponseBodyWrapper.ok(mockDataJson));
    }

    @PostMapping("/mock/projects/{projectId}/categories/{categoryId}/cards")
    public ResponseEntity<ResponseBodyWrapper> mockAddCard(@PathVariable long projectId,
                                                           @PathVariable long categoryId,
                                                           @RequestBody CardTitleContent requestBody) {
        return ResponseEntity.ok(ResponseBodyWrapper.ok());
    }

    @PutMapping("/mock/projects/{projectId}/categories/{categoryId}/cards/{cardId}")
    public ResponseEntity<ResponseBodyWrapper> mockEditCard(@PathVariable long projectId,
                                                            @PathVariable long categoryId,
                                                            @PathVariable long cardId,
                                                            @RequestBody CardTitleContent requestBody) {
        return ResponseEntity.ok(ResponseBodyWrapper.ok());
    }

    @DeleteMapping("/mock/projects/{projectId}/categories/{categoryId}/cards/{cardId}")
    public ResponseEntity<ResponseBodyWrapper> mockDeleteCard(@PathVariable long projectId,
                                                              @PathVariable long categoryId,
                                                              @PathVariable long cardId) {
        return ResponseEntity.ok(ResponseBodyWrapper.ok());
    }

    @PutMapping("/mock/projects/{projectId}/categories/{categoryId}/cards")
    public ResponseEntity<ResponseBodyWrapper> mockReorderCard(@PathVariable long projectId,
                                                               @PathVariable long categoryId,
                                                               @RequestBody CardIds requestBody) {
        return ResponseEntity.ok(ResponseBodyWrapper.ok());
    }
}
