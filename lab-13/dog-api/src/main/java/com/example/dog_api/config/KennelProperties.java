package com.example.dog_api.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kennel")
public class KennelProperties {

    private String name;
    private String address;
    private int capacity;
    private List<String> sections;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getSections() {
        return sections;
    }

    public void setSections(List<String> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "KennelProperties{name='" + name + '\'' + ", address='" + address + '\'' + ", capacity=" + capacity + ", sections=" + sections + '}';
    }
}
