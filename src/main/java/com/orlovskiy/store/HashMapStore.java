package com.orlovskiy.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class HashMapStore implements BaseStore{

    private Map<LocalDate, List<String>> localStore = new HashMap<>();

    @Override
    public void save(LocalDate key, String deals) {
        if (localStore.containsKey(key)){
            ArrayList<String> alreadyExistDeals = new ArrayList<>( localStore.get(key));
            alreadyExistDeals.add(deals);
            localStore.put(key,alreadyExistDeals);
        }
        else {
            localStore.put(key, asList(deals));
        }

    }

    @Override
    public List<String> selectAll(LocalDate key) {
        return localStore.get(key);
    }
}
