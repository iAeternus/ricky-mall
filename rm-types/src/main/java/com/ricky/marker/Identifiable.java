package com.ricky.marker;

public interface Identifiable<ID extends Identifier> {
    ID getId();
}