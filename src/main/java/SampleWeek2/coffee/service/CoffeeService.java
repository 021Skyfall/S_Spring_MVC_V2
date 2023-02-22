package SampleWeek2.coffee.service;

import SampleWeek2.coffee.entity.Coffee;
import SampleWeek2.coffee.repository.CoffeeRepository;
import SampleWeek2.exception.BusinessLogicException;
import SampleWeek2.exception.ExceptionCode;
import SampleWeek2.order.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoffeeService {
    private CoffeeRepository coffeeRepository;

    // 커피 등록
    public Coffee createCoffee(Coffee coffee) {
        // CoffeeCode 를 대문자로 변경
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();

        // 이미 등록된 커피인지 확인
        verifyExistsCoffee(coffeeCode);
        coffee.setCoffeeCode(coffeeCode);

        return coffeeRepository.save(coffee);
    }

    // 커피 정보 수정
    public Coffee updateCoffee(Coffee coffee) {
        // 조회하려는 커피가 검증된 커피인지 확인
        Coffee findCoffee = findVerifiedCoffee(coffee.getCoffeeId());

        Optional.ofNullable(coffee.getKorName()).ifPresent(findCoffee::setKorName);
        Optional.ofNullable(coffee.getEngName()).ifPresent(findCoffee::setEngName);
        Optional.ofNullable(coffee.getPrice()).ifPresent(findCoffee::setPrice);

        return coffeeRepository.save(findCoffee);
    }

    // 커피 조회
    public Coffee findCoffee(long coffeeId) {
        return findVerifiedCoffee(coffeeId);
    }

    // 주문에 해당하는 커피 조회
    public List<Coffee> findOrderedCoffees(Order order) {
        return order.getOrderCoffees()
                .stream()
                .map(coffeeRef -> findCoffee(coffeeRef.getCoffeeId()))
                .collect(Collectors.toList());
    }

    // 전체 커피 조회
    public List<Coffee> findCoffees() {
        return (List<Coffee>) coffeeRepository.findAll();
    }

    // 커피 삭제
    public void deleteCoffee(long coffeeId) {
        Coffee coffee = findVerifiedCoffee(coffeeId);
        coffeeRepository.delete(coffee);
    }

    // 전체 커피 삭제
    public void deleteAllCoffee() {
        coffeeRepository.deleteAll();
    }

    private void verifyExistsCoffee(String coffeeCode) {
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if (coffee.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COFFEE_CODE_EXISTS);
        }
    }

    public Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        Coffee findCoffee =
                optionalCoffee.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));
        return findCoffee;
    }

//    private Coffee findVerifiedCoffeeByQuery(long coffeeId) {
//        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
//        Coffee findCoffee =
//                optionalCoffee.orElseThrow(() ->
//                        new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));
//        return findCoffee;
//    }
}
