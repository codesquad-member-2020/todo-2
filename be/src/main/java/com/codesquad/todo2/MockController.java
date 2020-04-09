package com.codesquad.todo2;

import com.codesquad.todo2.service.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class MockController {
    private static final Logger log = LoggerFactory.getLogger(MockController.class);

    @PostMapping(value = "/mock/projects/{projectId}/categories/{categoryId}/cards")
    public ResponseEntity<ResponseResult> mockAddCard(@PathVariable long projectId, @PathVariable long categoryId, @RequestBody String title, @RequestBody(required=false) String content) {
        log.debug("[*] rojectId : {}, categoryId : {}, cardId : {}, title : {}, content : {}", projectId, categoryId, title, content);
        ResponseResult resultData = ResponseResult.succeed();
        return ResponseEntity.status(HttpStatus.OK)
                .body(resultData);
    }

    @PutMapping("/mock/projects/{projectId}/categories/{categoryId}/cards/{cardId}")
    public ResponseEntity<ResponseResult> mockEditCard(@PathVariable long projectId, @PathVariable long categoryId, @PathVariable long cardId, @RequestBody String title, @RequestBody(required=false) String content) {
        log.debug("[*] projectId : {}, categoryId : {}, cardId : {}, title : {}, content : {}", projectId, categoryId, cardId, title, content);
        ResponseResult resultData = ResponseResult.succeed();
        return ResponseEntity.status(HttpStatus.OK)
                .body(resultData);
    }

    @DeleteMapping("/mock/projects/{projectId}/categories/{categoryId}/cards/{cardId}")
    public ResponseEntity<ResponseResult> mockDeleteCard(@PathVariable long projectId, @PathVariable long categoryId, @PathVariable long cardId) {
        log.debug("[*] projectId : {}, categoryId : {}, cardId : {}", projectId, categoryId, cardId);
        ResponseResult resultData = ResponseResult.succeed();
        return ResponseEntity.status(HttpStatus.OK)
                .body(resultData);
    }

    @PutMapping("/mock/projects/{projectId}/categories/{categoryId}/cards")
    public ResponseEntity<ResponseResult> mockReorderCard(@PathVariable long projectId, @PathVariable long categoryId, @RequestBody long cardId, @RequestBody(required = false) long previousCardId) {
        log.debug("[*] projectId : {}, categoryId : {}, cardId : {}, previous_card_id : {}", projectId, categoryId, cardId, previousCardId);
        ResponseResult resultData = ResponseResult.succeed();
        return ResponseEntity.status(HttpStatus.OK)
                .body(resultData);
    }

    @GetMapping("/mock/projects/{projectId}")
    public String mockviewProject() {
//    public ResponseEntity<ResponseResult> viewProject() {
        log.debug("[*] GET REQUEST --- /projects/1");
        String mockData = "{\n" +
                "   \"result\":\"true\",\n" +
                "   \"data\":[\n" +
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
                "         \"category_id\":\"2\",\n" +
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
                "         \"category_id\":\"3\",\n" +
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
        return mockData;
    }
}
