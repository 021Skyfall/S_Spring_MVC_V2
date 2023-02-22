package SampleWeek2.coffee.controller;

import SampleWeek2.coffee.dto.CoffeePatchDTO;
import SampleWeek2.coffee.dto.CoffeePostDTO;
import SampleWeek2.coffee.dto.CoffeeResponseDTO;
import SampleWeek2.coffee.entity.Coffee;
import SampleWeek2.coffee.mapper.CoffeeMapper;
import SampleWeek2.coffee.service.CoffeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/coffees")
@Validated
@AllArgsConstructor
@Slf4j
public class CoffeeController {
    private final static String COFFEE_DEFAULT_URL = "/v1/coffees";
    private CoffeeService service;
    private CoffeeMapper mapper;

    @PostMapping
    public ResponseEntity postCoffee(@Validated @RequestBody CoffeePostDTO coffeePostDTO) {
        Coffee coffee = service.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDTO));
        URI location = UriComponentsBuilder
                .newInstance()
                .path(COFFEE_DEFAULT_URL + "/{coffee-id}")
                .buildAndExpand(coffee.getCoffeeId())
                .toUri();
        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Validated @RequestBody CoffeePatchDTO coffeePatchDTO) {
        coffeePatchDTO.setCoffeeId(coffeeId);
        Coffee coffee = service.updateCoffee(mapper.coffeePatchDtoToCoffee(coffeePatchDTO));
        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee),HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        Coffee coffee = service.findCoffee(coffeeId);
        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        List<Coffee> coffees = service.findCoffees();
        List<CoffeeResponseDTO> response = mapper.coffeesToCoffeeResponseDto(coffees);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        service.deleteCoffee(coffeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
