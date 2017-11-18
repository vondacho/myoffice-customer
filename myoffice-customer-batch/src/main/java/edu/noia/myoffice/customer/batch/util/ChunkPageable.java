package edu.noia.myoffice.customer.batch.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChunkPageable implements Pageable {

    int pageNumber = 0;
    int pageSize = 10;
    int offset = 0;

    @Accessors(fluent = true)
    Pageable previousOrFirst;

    public ChunkPageable() {
        previousOrFirst = this;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    public Pageable first() {
        return new ChunkPageable();
    }

    public Pageable next() {
        return new ChunkPageable(getPageNumber() + 1, getPageSize(), 0, this);
    }

    @Override
    public boolean hasPrevious() {
        return pageNumber > 0;
    }
}
