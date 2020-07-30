package pl.slowikowski.demo.crud.abstraction;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public abstract class AbstractDto {
    @PositiveOrZero
    private Long id;
}
