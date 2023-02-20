package SampleWeek2.coffee.mapper;

import SampleWeek2.coffee.dto.CoffeePatchDTO;
import SampleWeek2.coffee.dto.CoffeePostDTO;
import SampleWeek2.coffee.dto.CoffeeResponseDTO;
import SampleWeek2.coffee.entity.Coffee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDTO coffeePostDTO);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDTO coffeePatchDTO);
    CoffeeResponseDTO coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDTO> coffeesToCoffeeResponseDto(List<Coffee> coffees);
}
