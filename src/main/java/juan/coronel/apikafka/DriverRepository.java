package juan.coronel.apikafka;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface  DriverRepository extends MongoRepository<Driver, String> {

//    @Query(sort = "{ team : 1 }")
//    List<Driver> findByTeamBetween(int from, int to);
}
