package com.calculator.calculator.model;

import java.util.List;

public class ThirdPartyResponse {
    private List<Integer> numbers;

    public ThirdPartyResponse() {
    }

    public ThirdPartyResponse(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
