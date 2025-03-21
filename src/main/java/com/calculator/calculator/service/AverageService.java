package com.calculator.calculator.service;

import com.calculator.calculator.model.ThirdPartyResponse;
import com.calculator.calculator.storage.InMemoryStore;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AverageService {

    private final RestTemplate restTemplate;
    private final InMemoryStore inMemoryStore;


    private static final String PRIME_URL    = "http://20.244.56.144/test/primes";
    private static final String FIBO_URL     = "http://20.244.56.144/test/fibo";
    private static final String EVEN_URL     = "http://20.244.56.144/test/even";
    private static final String RANDOM_URL   = "http://20.244.56.144/test/rand";

    public AverageService(InMemoryStore inMemoryStore) {
        this.restTemplate = new RestTemplate();
        this.inMemoryStore = inMemoryStore;
    }

    public Map<String, Object> processRequest(String numberId) {

        String endpoint = switch (numberId) {
            case "p" -> PRIME_URL;
            case "f" -> FIBO_URL;
            case "e" -> EVEN_URL;
            case "r" -> RANDOM_URL;
            default -> throw new IllegalArgumentException("Invalid number ID: " + numberId);
        };

        // fetching request
        ThirdPartyResponse response = restTemplate.getForObject(endpoint, ThirdPartyResponse.class);
        List<Integer> newNumbers = (response != null) ? response.getNumbers() : Collections.emptyList();


        List<Integer> prevSet = inMemoryStore.getPreviousState(numberId);
        List<Integer> currSet = new ArrayList<>(newNumbers);

        double avg = computeAverage(currSet);


        inMemoryStore.updateState(numberId, currSet);


        Map<String, Object> result = new HashMap<>();
        result.put("windowPrevState", prevSet);
        result.put("windowCurrState", currSet);
        result.put("numbers", currSet);
        result.put("avg", avg);

        return result;
    }

    private double computeAverage(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Integer num : numbers) {
            sum += num;
        }
        return sum / numbers.size();
    }
}
