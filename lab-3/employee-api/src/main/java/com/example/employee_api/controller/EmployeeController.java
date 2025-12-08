package com.example.employee_api.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class EmployeeController {
    private final DiscoveryClient discoveryClient;
	private final RestClient restClient;
    private final CircuitBreaker employeeHoursCircuitBreaker;

	public EmployeeController(DiscoveryClient discoveryClient,
			RestClient.Builder restClientBuilder,
        	CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
		this.discoveryClient = discoveryClient;
		restClient = restClientBuilder.build();
        this.employeeHoursCircuitBreaker = circuitBreakerFactory.create("employeeHours");
	}

	@GetMapping("employeeHours")
    public String employeeHours() {
        return employeeHoursCircuitBreaker.run(
                () -> {
                    ServiceInstance serviceInstance =
                            discoveryClient.getInstances("payroll-api").get(0);

                    return restClient.get()
                            .uri(serviceInstance.getUri() + "/hours")
                            .retrieve()
                            .body(String.class);
                },
                this::employeeHoursFallback
        );
    }

    private String employeeHoursFallback(Throwable t) {
        return "Employee hours service is temporarily unavailable. Please try again later.";
    }
}
