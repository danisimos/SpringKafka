package com.orioninc.codegen.api;

import com.orioninc.codegen.model.ProcessedIntervalSubscriptions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-08T20:55:05.860912200+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.springKafkaUserEventsDataBase.base-path:}")
public class UsersApiController implements UsersApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> usersGet(Integer id, String firstName, String lastName, Integer age) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> usersIdGet(Integer id) {
        return null;
    }
}
