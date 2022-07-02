package juan.coronel.apikafka;
import lombok.Data;

@Data
public class Driver {
    String driverId;
    String url;
    String givenName;
    String familyName;
    String dateOfBirth;
    String nationality;
    String code;
    String permanentNumber;
}
