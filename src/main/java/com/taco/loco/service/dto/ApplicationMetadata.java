package com.taco.loco.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"serviceVersion", "apiVersion"})
public class ApplicationMetadata implements Serializable {
    private String serviceVersion;
    private String apiVersion;

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public String toString() {
        return "ApplicationMetadata{" +
                "serviceVersion='" + serviceVersion + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                '}';
    }
}
