package pl.gregorymartin.touristapp.trip;

import pl.gregorymartin.touristapp.trip.dto.BookingReadModel;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.trip.dto.OfferReadModel;
import pl.gregorymartin.touristapp.trip.dto.OfferWriteModel;
import pl.gregorymartin.touristapp.user.AppUser;

import java.util.List;
import java.util.stream.Collectors;

class BookingFactory {

    public static List<Booking> toEntity(List<BookingWriteModel> bookingWriteModels) {
        return bookingWriteModels.stream()
                .map(BookingFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Booking toEntity(BookingWriteModel bookingWriteModel) {
        Booking booking = new Booking();
        booking.setAppUser(new AppUser(bookingWriteModel.getUserId()));
        booking.setOffer(new Offer(bookingWriteModel.getOfferId()));
        booking.setAmount(bookingWriteModel.getAmount());

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
                .arrivingCityName(bookings.getOffer().getArrivingCityName())
                .arrivingTime(bookings.getOffer().getArrivingTime())
                .departureCityName(bookings.getOffer().getDepartureCityName())
                .departureTime(bookings.getOffer().getDepartureTime())
                .amount(bookings.getAmount())
                .price(bookings.getPrice())
                .username(bookings.getAppUser().getUsername())
                .build();
    }
}

class OfferFactory {

    public static List<Offer> toEntity(List<OfferWriteModel> offerWriteModel) {
        return offerWriteModel.stream()
                .map(OfferFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Offer toEntity(OfferWriteModel offerWriteModel) {
        Offer offer = new Offer();
        offer.setArrivingCityName(offerWriteModel.getArrivingCityName());
        offer.setArrivingTime(offerWriteModel.getArrivingTime());
        offer.setDepartureCityName(offerWriteModel.getDepartureCityName());
        offer.setDepartureTime(offerWriteModel.getDepartureTime());
        offer.setCapacity(offerWriteModel.getCapacity());
        offer.setPrice(offerWriteModel.getPrice());

        return offer;
    }

    //read

    public static List<OfferReadModel> toDto(List<Offer> offer) {
        return offer.stream()
                .map(OfferFactory::toDto)
                .collect(Collectors.toList());
    }

    public static OfferReadModel toDto(Offer offer) {
        return OfferReadModel.builder()
                .id(offer.getId())
                .arrivingCityName(offer.getArrivingCityName())
                .arrivingTime(offer.getArrivingTime())
                .departureCityName(offer.getDepartureCityName())
                .departureTime(offer.getDepartureTime())
                .capacity(offer.getCapacity())
                .price(offer.getPrice())
                //.bookings(BookingFactory.toDto((List<Booking>) offer.getBookings()))
                .build();
    }
}
