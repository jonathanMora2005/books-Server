package com.jonathan.services.controllers;

public interface Controller {
    String get(int k);
    String get();
    void post(String value);
    void put(int key, String value);
    void delete(int key);

}
