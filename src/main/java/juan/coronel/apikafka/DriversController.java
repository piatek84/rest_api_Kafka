package juan.coronel.apikafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@RestController
public class DriversController {

    @Value( "${f1_api_url}" )
    private String f1ApiUrl;

    private final WebClient f1WebClient;

    public DriversController(WebClient f1WebClient) {
        this.f1WebClient = f1WebClient;
    }

    @RequestMapping("/api/getrandomdriver")
    @GetMapping
    public Mono<Object> getDrivers() throws JsonProcessingException {
        Random random = new Random();
        int randomDriver = random.nextInt(1, 854);
        Mono<Object> result = this.f1WebClient
                .get()
                .uri("/drivers.json?limit=" + randomDriver +"&offset="+ randomDriver)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response-> response.path("MRData").path("DriverTable").path("Drivers").get(0))
                .map(s-> {
                    try {
                        Driver driver = deserializeJsonToDriver(s.toString());
                        return driver;
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                ;
        return result;
    }

    private Driver deserializeJsonToDriver(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return objectMapper.readValue(jsonString, new TypeReference<Driver>(){});
    }
}
