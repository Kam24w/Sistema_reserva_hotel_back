package com.hotel.reservas.service;

import com.hotel.reservas.model.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
public class PricingService {

    // High season months: December, January, July, August
    private static final double HIGH_SEASON_FACTOR = 1.5;
    private static final double LOW_SEASON_FACTOR = 1.0;

    public boolean isHighSeason(LocalDate date) {
        Month month = date.getMonth();
        return month == Month.DECEMBER || month == Month.JANUARY
                || month == Month.JULY || month == Month.AUGUST;
    }

    public double calculateNightPrice(Room room, LocalDate checkInDate) {
        double factor = isHighSeason(checkInDate) ? HIGH_SEASON_FACTOR : LOW_SEASON_FACTOR;
        double price = room.getBasePrice() * factor;
        System.out.printf("[PricingService] Room %s | Base price: $%.2f | Factor: %.1f | Night price: $%.2f%n",
                room.getNumber(), room.getBasePrice(), factor, price);
        return price;
    }

    public double calculateRoomTotal(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        long nights = checkInDate.until(checkOutDate).getDays();
        double nightPrice = calculateNightPrice(room, checkInDate);
        double total = nightPrice * nights;
        System.out.printf("[PricingService] Room total: $%.2f (%d nights x $%.2f)%n", total, nights, nightPrice);
        return total;
    }

    public double getSeasonFactor(LocalDate checkInDate) {
        return isHighSeason(checkInDate) ? HIGH_SEASON_FACTOR : LOW_SEASON_FACTOR;
    }
}
