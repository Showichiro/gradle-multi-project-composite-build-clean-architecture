package com.example.domain.core;

import java.util.List;
import java.util.Optional;

public interface IGateway<S, ID> {
    S create(S entity);

    S update(S product);

    void delete(ID id);

    Optional<S> findById(ID id);

    List<S> findAll();

    List<S> findAll(int size, int page);
}
