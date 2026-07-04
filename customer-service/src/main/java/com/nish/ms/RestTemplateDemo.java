package com.nish.ms;

import com.nish.ms.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
* https://www.baeldung.com/rest-template
* */
public class RestTemplateDemo {

    private static final String URI_CUSTOMERS = "http://localhost:4040/api/v1/customers";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        getCustomersObject(restTemplate);
        System.out.println("-------------------------------");
        getCustomersEntity(restTemplate);
    }

    /*
     * getForEntity() - Get Plain JSON
     *
     * */
    private static void getCustomersObject(RestTemplate restTemplate) {

        // "/api/v1/customers"
        Customer[] customerArray = restTemplate.getForObject(URI_CUSTOMERS, Customer[].class);
        Arrays.stream(customerArray).forEach(System.out::println);

        // "/api/v1/customers/{id}"
        Map<String, String> params = new HashMap<>();
        params.put("id", "201");
        Customer customer = restTemplate.getForObject(URI_CUSTOMERS + "/{id}", Customer.class, params);

        System.out.println("customer = " + customer);

    }

    /*
    * getForObject() - Retrieving POJO Instead of JSON
    *
    * */
    private static void getCustomersEntity(RestTemplate restTemplate) {

        // "/api/v1/customers"
        ResponseEntity<Customer[]> responseArray = restTemplate.getForEntity(URI_CUSTOMERS, Customer[].class);

        if(responseArray.getStatusCode().equals(HttpStatus.OK)) {
            Arrays.stream(responseArray.getBody()).forEach(System.out::println);
        }

        // "/api/v1/customers/{id}"
        Map<String, String> params = new HashMap<>();
        params.put("id", "201");
        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(URI_CUSTOMERS + "/{id}", Customer.class, params);

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            System.out.println("customer = " + responseEntity.getBody());
        }
    }

}
