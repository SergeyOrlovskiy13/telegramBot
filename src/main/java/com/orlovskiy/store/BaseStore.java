package com.orlovskiy.store;

import java.time.LocalDate;
import java.util.List;

public interface BaseStore {

    void save(LocalDate date, String deals);

    List<String> selectAll(LocalDate date);

}
