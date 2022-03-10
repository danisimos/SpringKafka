package com.orioninc.codegen.api;

import com.orioninc.codegen.model.Subscription;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-08T20:55:05.617916800+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.springKafkaUserEvents.base-path:}")
public class SubscriptionsApiController implements SubscriptionsApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SubscriptionsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<String> subscriptionsPost(Subscription subscription) {
        return null;
    }

}
