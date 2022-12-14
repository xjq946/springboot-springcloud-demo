package com.example.manualrpc.rpc.service;

public class ServiceConfig {

    private String serviceName;
    private Class serviceInterfaceClass;
    private Class serviceClass;

    public ServiceConfig(String serviceName, Class serviceInterfaceClass, Class serviceClass) {
        this.serviceName = serviceName;
        this.serviceInterfaceClass = serviceInterfaceClass;
        this.serviceClass = serviceClass;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Class getServiceInterfaceClass() {
        return serviceInterfaceClass;
    }

    public void setServiceInterfaceClass(Class serviceInterfaceClass) {
        this.serviceInterfaceClass = serviceInterfaceClass;
    }

    public Class getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public String toString() {
        return "ServiceConfig{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceInterfaceClass=" + serviceInterfaceClass +
                ", serviceClass=" + serviceClass +
                '}';
    }
}
