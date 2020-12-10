package pl.gregorymartin.touristapp.trip;

import pl.gregorymartin.touristapp.trip.dto.OfferSearch;
import pl.gregorymartin.touristapp.trip.dto.OfferReadModel;
import pl.gregorymartin.touristapp.trip.dto.OfferWriteModel;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .bookings(BookingFactory.toDto(new ArrayList<>(offer.getBookings())))

                .build();
    }

    // MVC
    public static List<OfferSearch> toOfferSearch(List<Offer> offer) {
        return offer.stream()
                .map(OfferFactory::toOfferSearch)
                .collect(Collectors.toList());
    }

    public static OfferSearch toOfferSearch(Offer offer) {
        int days = (int) ChronoUnit.DAYS.between(offer.getDepartureTime(), offer.getArrivingTime());

        return OfferSearch.builder()
                .id(offer.getId())
                .departureCityName(offer.getDepartureCityName())
                .departureTime(offer.getDepartureTime())
                .arrivingCityName(offer.getArrivingCityName())
                .arrivingTime(offer.getArrivingTime())
                .arrivingCityPhotoUrl(offer.getArrivingCityPhotoUrl())
                .price(offer.getPrice())
                .days(days)
                .build();
    }

}
