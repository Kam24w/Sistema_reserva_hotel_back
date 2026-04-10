package com.hotel.reservas.service;

import com.hotel.reservas.dto.InvoiceDTO;
import com.hotel.reservas.model.Reservation;
import com.hotel.reservas.model.ServiceType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InvoicingService {

    public InvoiceDTO generateInvoice(Reservation reservation, double servicesTotal) {
        InvoiceDTO invoice = new InvoiceDTO();

        invoice.setInvoiceNumber("INV-" + reservation.getId().substring(0, 8).toUpperCase());
        invoice.setReservationId(reservation.getId());
        invoice.setEmissionDate(LocalDateTime.now());
        invoice.setGuestName(reservation.getGuestName());
        invoice.setGuestEmail(reservation.getGuestEmail());
        invoice.setRoomNumber(reservation.getRoom().getNumber());
        invoice.setRoomType(reservation.getRoom().getType().name());
        invoice.setCheckInDate(reservation.getCheckInDate());
        invoice.setCheckOutDate(reservation.getCheckOutDate());
        invoice.setNightsCount(reservation.getNightsCount());
        invoice.setHighSeason(reservation.isHighSeason());
        invoice.setBaseNightPrice(reservation.getRoom().getBasePrice());
        invoice.setSeasonFactor(reservation.isHighSeason() ? 1.5 : 1.0);
        invoice.setRoomSubtotal(reservation.getBaseTotal());

        // Services detail
        List<Map<String, Object>> servicesDetail = new ArrayList<>();
        for (ServiceType service : reservation.getAdditionalServices()) {
            Map<String, Object> detail = new LinkedHashMap<>();
            detail.put("service", service.name());
            detail.put("description", service.getDescription());
            double cost = service == ServiceType.BREAKFAST
                    ? service.getCost() * reservation.getNightsCount()
                    : service.getCost();
            detail.put("cost", cost);
            if (service == ServiceType.BREAKFAST) {
                detail.put("note", "$" + service.getCost() + " x " + reservation.getNightsCount() + " nights");
            }
            servicesDetail.add(detail);
        }
        invoice.setServicesDetail(servicesDetail);
        invoice.setServicesTotal(servicesTotal);
        invoice.setFinalTotal(reservation.getBaseTotal() + servicesTotal);
        invoice.setDigitalKey(reservation.getDigitalKey());

        System.out.printf("[InvoicingService] Invoice %s generated. Total: $%.2f%n",
                invoice.getInvoiceNumber(), invoice.getFinalTotal());

        return invoice;
    }
}
