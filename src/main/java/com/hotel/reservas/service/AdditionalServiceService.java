package com.hotel.reservas.service;

import com.hotel.reservas.model.Reservation;
import com.hotel.reservas.model.ServiceType;
import org.springframework.stereotype.Service;

@Service
public class AdditionalServiceService {

    public void addService(Reservation reservation, ServiceType serviceType) {
        reservation.addService(serviceType);
        System.out.printf("[AdditionalServiceService] Service '%s' ($%.2f) added to reservation %s%n",
                serviceType.getDescription(), serviceType.getCost(), reservation.getId());
    }

    public double calculateServicesTotal(Reservation reservation) {
        double total = reservation.getAdditionalServices().stream()
                .mapToDouble(service -> {
                    // Breakfast is charged per day
                    if (service == ServiceType.BREAKFAST) {
                        return service.getCost() * reservation.getNightsCount();
                    }
                    return service.getCost();
                })
                .sum();
        System.out.printf("[AdditionalServiceService] Total additional services reservation %s: $%.2f%n",
                reservation.getId(), total);
        return total;
    }

    public double calculateServiceCost(ServiceType serviceType, long nights) {
        if (serviceType == ServiceType.BREAKFAST) {
            return serviceType.getCost() * nights;
        }
        return serviceType.getCost();
    }
}
