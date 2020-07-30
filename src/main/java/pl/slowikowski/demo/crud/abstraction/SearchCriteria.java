package pl.slowikowski.demo.crud.abstraction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    private boolean orPredicate;

    public SearchCriteria(final String key, final String operation, final Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
