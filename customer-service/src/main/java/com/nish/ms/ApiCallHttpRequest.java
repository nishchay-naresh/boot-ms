package com.nish.ms;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCallHttpRequest {

    private static final String BASE_URL = "http://localhost:4040/api/v1/customers";

    // API endpoints need to up and running
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        testApiStatus(httpClient);
        getAllCustomers(httpClient);
        getCustomerById(httpClient, 205);
        createCustomer(httpClient);
        updateCustomer(httpClient, 204);
        patchCustomerSingleField(httpClient, 208);
        patchCustomerMultipleFields(httpClient, 208);
        deleteCustomer(httpClient, 205);
    }

    private static void testApiStatus(HttpClient httpClient) throws Exception {
        System.out.println("=== GET /api/status ===");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:4040/api/status"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println();
    }

    private static void getAllCustomers(HttpClient httpClient) throws Exception {
        System.out.println("=== GET /api/v1/customers ===");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println();
    }

    private static void getCustomerById(HttpClient httpClient, int customerId) throws Exception {
        System.out.println("=== GET /api/v1/customers/" + customerId + " ===");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + customerId))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println();
    }

    private static void createCustomer(HttpClient httpClient) throws Exception {
        System.out.println("=== POST /api/v1/customers ===");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("""
                    {
                        "customerId": 208,
                        "customerName": "Michel",
                        "email": "michel@abc.com",
                        "mobileNumber": 188888888
                    }
                """))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println();
    }

    private static void updateCustomer(HttpClient client, int customerId) throws Exception {
        System.out.println("=== PUT /api/v1/customers/" + customerId + " ===");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + customerId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("""
                    {
                        "customerId": 204,
                        "customerName": "Kevin P",
                        "email": "kevinp@abc.com",
                        "mobileNumber": 83333333
                    }
                """))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println();
    }

    private static void patchCustomerSingleField(HttpClient client, int customerId) throws Exception {
        System.out.println("=== PATCH /api/v1/customers/" + customerId + " (single field) ===");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + customerId))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString("""
                    {
                         "email": "michel_upd@abc.com"
                    }
                """))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println();
    }

    private static void patchCustomerMultipleFields(HttpClient client, int customerId) throws Exception {
        System.out.println("=== PATCH /api/v1/customers/" + customerId + " (multiple fields) ===");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + customerId))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString("""
                    {
                         	"email": "michel@abc.com",
                      		"mobileNumber": 19999999
                    }
                """))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println();
    }

    private static void deleteCustomer(HttpClient client, int customerId) throws Exception {
        System.out.println("=== DELETE /api/v1/customers/" + customerId + " ===");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + customerId))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
        System.out.println();
    }
}