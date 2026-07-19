package com.nish.ms.service;

import com.nish.ms.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerApiService {

    private static final String URI_CUSTOMERS = "http://customer-service/api/v1/customers";
    private final RestTemplate restTemplate;

    public CustomerApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void testApiCalls() {
        getCustomersObject();
        System.out.println("-------------------------------");
        getCustomersEntity();
    }

    private void getCustomersObject() {
        Customer[] customerArray = restTemplate.getForObject(URI_CUSTOMERS, Customer[].class);
        Arrays.stream(customerArray).forEach(System.out::println);

        Map<String, String> params = new HashMap<>();
        params.put("id", "201");
        Customer customer = restTemplate.getForObject(URI_CUSTOMERS + "/{id}", Customer.class, params);
        System.out.println("customer = " + customer);
    }

    private void getCustomersEntity() {
        ResponseEntity<Customer[]> responseArray = restTemplate.getForEntity(URI_CUSTOMERS, Customer[].class);

        if (responseArray.getStatusCode().equals(HttpStatus.OK)) {
            Arrays.stream(responseArray.getBody()).forEach(System.out::println);
        }

        Map<String, String> params = new HashMap<>();
        params.put("id", "201");
        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(URI_CUSTOMERS + "/{id}", Customer.class, params);

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            System.out.println("customer = " + responseEntity.getBody());
        }
    }
}
