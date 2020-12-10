package pl.gregorymartin.touristapp.view.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public
class SearchMVC {
    private String from;
    private String to;

    private String date;
}
