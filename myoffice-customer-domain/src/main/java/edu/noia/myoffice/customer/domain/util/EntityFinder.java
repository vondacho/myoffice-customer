package edu.noia.myoffice.customer.domain.util;

import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityFinder {

    public static <T,U> T find(Class<T> clazz, U id, Function<U, Optional<T>> finder) {
        return finder.apply(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No %s identified by %s has been found", clazz, id)));    }
}
