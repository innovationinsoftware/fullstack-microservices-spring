package com.example.payroll.model;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class HoursWorkedBean {
    private String employeeId;
    
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    private int hoursWorked;

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    // Initialize sample employee hours
    private static final List<HoursWorkedBean> SAMPLE_EMPLOYEES = new ArrayList<>();

    static {
        HoursWorkedBean emp1 = new HoursWorkedBean();
        emp1.setEmployeeId("E001");
        emp1.setHoursWorked(40);
        SAMPLE_EMPLOYEES.add(emp1);

        HoursWorkedBean emp2 = new HoursWorkedBean();
        emp2.setEmployeeId("E002");
        emp2.setHoursWorked(35);
        SAMPLE_EMPLOYEES.add(emp2);

        HoursWorkedBean emp3 = new HoursWorkedBean();
        emp3.setEmployeeId("E003");
        emp3.setHoursWorked(42);
        SAMPLE_EMPLOYEES.add(emp3);
    }

    public static List<HoursWorkedBean> getSampleEmployees() {
        return new ArrayList<>(SAMPLE_EMPLOYEES);
    }
}