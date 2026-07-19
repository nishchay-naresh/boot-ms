package com.nish.ms.controller;

import com.nish.ms.service.CustomerApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class ApiTestController {

    private final CustomerApiService customerApiService;

    public ApiTestController(CustomerApiService customerApiService) {
        this.customerApiService = customerApiService;
    }

    @GetMapping("/customers")
    public String testCustomerApiCalls() {
        customerApiService.testApiCalls();
        return "API calls executed. Check console for output.";
    }
}
