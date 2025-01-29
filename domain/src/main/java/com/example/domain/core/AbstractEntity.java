package com.example.domain.core;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    private ID id;
}