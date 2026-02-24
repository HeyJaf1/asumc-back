package kz.asumc.api.controller;

import kz.asumc.core.service.RequestService;
import kz.asumc.dto.RequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public RequestDto createRequest(@RequestBody RequestDto requestDto) {
        return requestService.createRequest(requestDto);
    }

    @GetMapping("/hello")
    public String hello() {
        return requestService.hello();
    }
}