package pl.gregorymartin.touristapp.trip;

import pl.gregorymartin.touristapp.trip.dto.BookingReadModel;
import pl.gregorymartin.touristapp.trip.dto.BookingUserPanel;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.user.AppUserFactory;

import java.util.List;
import java.util.stream.Collectors;

public class BookingFactory {
    public static List<Booking> toEntity(List<BookingWriteModel> bookingWriteModels) {
        return bookingWriteModels.stream()
                .map(BookingFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Booking toEntity(BookingWriteModel bookingWriteModel) {
        Booking booking = new Booking();
        booking.setSize(bookingWriteModel.getSize());

        return booking;
    }

    //read

    public static List<BookingReadModel> toDto(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingFactory::toDto)
                .collect(Collectors.toList());
    }

    public static BookingReadModel toDto(Booking bookings) {
        return BookingReadModel.builder()
                .id(bookings.getId())
                .offerId(bookings.getOffer().getId())
                .arrivingCityName(bookings.getOffer().getArrivingCityName())
                .arrivingTime(bookings.getOffer().getArrivingTime())
                .departureCityName(bookings.getOffer().getDepartureCityName())
                .departureTime(bookings.getOffer().getDepartureTime())
                .size(bookings.getSize())
                .price(bookings.getPrice())
                .paid(bookings.isPaid())
                .appUser(AppUserFactory.toDto(bookings.getAppUser()))
                .build();
    }
    public static List<BookingUserPanel> toBookingUserPanel(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingFactory::toBookingUserPanel)
                .collect(Collectors.toList());
    }

    public static BookingUserPanel toBookingUserPanel(Booking bookings) {
        return BookingUserPanel.builder()
                .id(bookings.getId())
                .offerId(bookings.getOffer().getId())
                .arrivingCityName(bookings.getOffer().getArrivingCityName())
                .arrivingTime(bookings.getOffer().getArrivingTime())
                .departureCityName(bookings.getOffer().getDepartureCityName())
                .departureTime(bookings.getOffer().getDepartureTime())
                .size(bookings.getSize())
                .price(bookings.getPrice())
                .paid(bookings.isPaid())
                .photoUrl(bookings.getOffer().getArrivingCityPhotoUrl())
                .build();
    }
}
