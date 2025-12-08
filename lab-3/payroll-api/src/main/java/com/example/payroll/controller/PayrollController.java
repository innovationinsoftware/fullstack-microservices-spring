package com.example.payroll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.payroll.model.HoursWorkedBean;
import java.util.List;

@RestController
public class PayrollController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from PayrollController";
    }

    @GetMapping("/hours")
    public List<HoursWorkedBean> getHoursWorked() {
        return HoursWorkedBean.getSampleEmployees();
    }

}
