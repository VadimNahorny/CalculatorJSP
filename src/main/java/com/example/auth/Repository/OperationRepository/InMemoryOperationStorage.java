package com.example.auth.Repository.OperationRepository;

import com.example.auth.Repository.UserRepository.RegistrationRepository;

import java.util.*;

public class InMemoryOperationStorage {
    private Map<String, List<String>> storage = new HashMap<>();
    private static InMemoryOperationStorage inMemoryOperationStorage;

    public static InMemoryOperationStorage getInMemoryOperationStorage() {
        if (inMemoryOperationStorage == null) inMemoryOperationStorage = new InMemoryOperationStorage();
        return inMemoryOperationStorage;
    }

    public void addOperation(String key, String mathExpression) {
        if (getInMemoryOperationStorage().storage.containsKey(key)) {
            getInMemoryOperationStorage().storage.get(key).add(mathExpression);
        } else {
            getInMemoryOperationStorage().storage.put(key, new ArrayList<>(Collections.singletonList(mathExpression)));
        }
    }

    public List<String> getOperationsList(String key) {
        if ( getInMemoryOperationStorage().storage.containsKey(key))
            return  getInMemoryOperationStorage().storage.get(key);
        return null;
    }

    public void clear(String key) {

        if (getInMemoryOperationStorage().storage.containsKey(key)) {
            List<String> temporaryList =  getInMemoryOperationStorage().storage.get(key);
            temporaryList.clear();
            getInMemoryOperationStorage().storage.put(key, temporaryList);
        }
    }
}

