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
        loadPlaces();

    }

    void loadPlaces(){
        List<CityWriteModel> cities = new ArrayList<>();

        cities.add(new CityWriteModel("Warsaw","https://images.unsplash.com/photo-1601469762504-91a427fc6472?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1398&q=80","Poland"));
        cities.add(new CityWriteModel("Cracow","https://cdn.pixabay.com/photo/2019/09/12/18/34/street-4472321_960_720.jpg","Poland"));
        cities.add(new CityWriteModel("Wroclaw","https://cdn.pixabay.com/photo/2020/04/19/08/05/old-town-5062328_960_720.jpg","Poland"));
        cities.add(new CityWriteModel("Poznan","https://cdn.pixabay.com/photo/2017/11/06/10/10/poznan-2923222_960_720.jpg","Poland"));
        cities.add(new CityWriteModel("Madrid","https://images.unsplash.com/photo-1543783207-ec64e4d95325?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80","Spain"));
        cities.add(new CityWriteModel("Barcelona","https://images.unsplash.com/photo-1562883676-8c7feb83f09b?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=661&q=80","Spain"));
        cities.add(new CityWriteModel("London","https://images.unsplash.com/photo-1505761671935-60b3a7427bad?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80","Great Britain"));
        cities.add(new CityWriteModel("Paris","https://images.unsplash.com/photo-1533660823039-3793670cbe15?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80","France"));
        cities.add(new CityWriteModel("Moscow","https://images.unsplash.com/photo-1495542779398-9fec7dc7986c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1268&q=80","Russia"));
        cities.add(new CityWriteModel("Stockholm","https://images.unsplash.com/photo-1563814034797-3ed2e25e1752?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=632&q=80","Sweden"));
        cities.add(new CityWriteModel("Oslo","https://images.unsplash.com/photo-1433757741270-94a3bcadc2f3?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1269&q=80","Norway"));

        cities.forEach(placeService::addCity);
    }
}
