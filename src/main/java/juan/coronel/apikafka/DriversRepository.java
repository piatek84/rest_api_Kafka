package juan.coronel.apikafka;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriversRepository extends MongoRepository<Driver, String> {
}
