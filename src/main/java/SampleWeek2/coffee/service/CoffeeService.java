package SampleWeek2.coffee.service;

import SampleWeek2.coffee.entity.Coffee;
import SampleWeek2.exception.BusinessLogicException;
import SampleWeek2.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    public Coffee createCoffee(Coffee coffee) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public Coffee updateCoffee(Coffee coffee) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public Coffee findCoffee(long coffeeId) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public List<Coffee> findCoffees() {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public void deleteCoffee(long coffeeId) {
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

}
