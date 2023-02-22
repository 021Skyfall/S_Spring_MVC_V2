package SampleWeek2.coffee.repository;

import SampleWeek2.coffee.entity.Coffee;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

//    @Query("SELECT * FROM COFFEE WHERE COFFFEE_ID = :coffeeId")
//    Optional<Coffee> findByCoffee(Long coffeeId);
    Optional<Coffee> findById(long id);
    // 근데 이거 지금 보니까 어차피 CrudRepository 에 똑같은 메서드가 있긴하네...
    // 그래도 내가 직접 Coffee 엔티티 객체의 Id 와 연결을 해줬다고 받아들여야할듯
}
