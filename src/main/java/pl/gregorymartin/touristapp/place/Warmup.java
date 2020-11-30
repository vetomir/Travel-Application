package pl.gregorymartin.touristapp.place;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.gregorymartin.touristapp.place.dto.CityWriteModel;

import java.util.ArrayList;
import java.util.List;

@Component("placeWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final PlaceService placeService;

    Warmup(final PlaceService placeService) {

        this.placeService = placeService;
    }


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        putPlaces();

    }

    void putPlaces(){
        List<CityWriteModel> cities = new ArrayList<>();

        cities.add(new CityWriteModel("Warsaw","","Poland"));
        cities.add(new CityWriteModel("Cracow","","Poland"));
        cities.add(new CityWriteModel("Wroclaw","","Poland"));
        cities.add(new CityWriteModel("Poznan","","Poland"));
        cities.add(new CityWriteModel("Madrid","","Spain"));
        cities.add(new CityWriteModel("Barcelona","","Spain"));
        cities.add(new CityWriteModel("London","","Great Britain"));
        cities.add(new CityWriteModel("Paris","","France"));
        cities.add(new CityWriteModel("Cannes","","France"));
        cities.add(new CityWriteModel("Moscow","","Russia"));
        cities.add(new CityWriteModel("Stockholm","","Sweden"));
        cities.add(new CityWriteModel("Norway","","Oslo"));

        cities.stream().forEach(placeService::addCity);
    }
}
