package juan.coronel.apikafka;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Driver {
    @Id
    String driverId;
    String url;
    String givenName;
    String familyName;
    String dateOfBirth;
    String nationality;
    String code;
    String permanentNumber;
}
