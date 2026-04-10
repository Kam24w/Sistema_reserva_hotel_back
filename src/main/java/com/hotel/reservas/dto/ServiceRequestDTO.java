package com.hotel.reservas.dto;

import com.hotel.reservas.model.ServiceType;

public class ServiceRequestDTO {
    private ServiceType serviceType;

    public ServiceType getServiceType() { return serviceType; }
    public void setServiceType(ServiceType serviceType) { this.serviceType = serviceType; }
}
