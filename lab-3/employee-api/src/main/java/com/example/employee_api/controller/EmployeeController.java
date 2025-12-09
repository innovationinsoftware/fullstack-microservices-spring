package com.example.employee_api.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class EmployeeController {
    private final DiscoveryClient discoveryClient;
	private final RestClient restClient;

	public EmployeeController(DiscoveryClient discoveryClient,
			RestClient.Builder restClientBuilder) {
		this.discoveryClient = discoveryClient;
		restClient = restClientBuilder.build();
	}

	@GetMapping("employeeHours")
    public String employeeHours() {
		ServiceInstance serviceInstance =
				discoveryClient.getInstances("payroll-api").get(0);

		return restClient.get()
				.uri(serviceInstance.getUri() + "/hours")
				.retrieve()
				.body(String.class);
    }
}
