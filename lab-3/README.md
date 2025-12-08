# Lab 3 - Circuit Breaker
In this lab, we'll add the circuit breaker pattern to the employee-api when it calls the payroll-api. This will allow us to gracefully handle situations such as the payroll-api being offline or otherwise unresponsive.

## Step 1 - Add the Resilience4J dependency
1. Open the employee-api and update the gradle build with this implementation: `
	implementation 'org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j`
2. Update the application.yaml with the circuit breaker settings as a subsetting of `spring`.
```yaml
  cloud:
    circuitbreaker:
      resilience4j:
        configs:
          default:
            slidingWindowSize: 20
            failureRateThreshold: 50
            waitDurationInOpenState: 30s
        instances:
          employeeService:
            baseConfig: default
```
- slidingWindowSize looks at the last <i>n</i> calls, in this case 20, to determine if the circuit state needs to switch to open.
- failureRateThreshold is the percentage of calls that need to fail for the circuit to open.
- waitDurationInOpenState is the number of seconds to wait before retrying the service.

## Step 2 - Use the Circuit Breaker
Since we are using an autoconfigured circuit breaker with our settings, we need to just received an injected circuit breaker factory to use it. Update your EmployeeController bean to take the factory.

```java
    private final CircuitBreaker employeeHoursCircuitBreaker;
```

With that declaration, update your constructor to accept a `CircuitBreakerFactory<?, ?>` type and assign it.

Then update `employeeHours` to wrap the discovery call into the circuit breaker.

```java
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
```



## Step 3 - Test the breaker
Build and start the`eurekaserver` and `payroll-api`. Verify payroll is registered. Then build and run employee-api.

Use Postman to verify you are receiving data.

Now, stop the payroll-api and try to run the employee-api. It will start responding slow, then speed up after the thresholds are met.

Finally, start the payroll-api back up and verify that the employee-api eventually starts to receive data again.
