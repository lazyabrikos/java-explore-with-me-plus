package ru.practicum.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.practicum.ResponseStatDto;
import ru.practicum.StatDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class StatsClientImpl implements StatsClient {
    private final RestClient restClient;


    @Autowired
    public StatsClientImpl(@Value("${stats-server.url}") String statsUrl) {
        this.restClient = RestClient.builder().baseUrl(statsUrl).build();
    }

    @Override
    public List<ResponseStatDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            var response = restClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/stats")
                            .queryParam("start", start.format(formatter))
                            .queryParam("end", start.format(formatter))
                            .queryParam("uris", uris)
                            .queryParam("unique", unique)
                            .build())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<ResponseStatDto>>() {
                    });
            return response;
        } catch (Exception exp) {
            return Collections.emptyList();
        }
    }

    @Override
    public StatDto hit(StatDto statDto) {
        try {
            return restClient.post().uri("/git").body(statDto).retrieve().body(StatDto.class);
        } catch (Exception exp) {
            return null;
        }
    }


}
