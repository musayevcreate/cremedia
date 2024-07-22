package com.cremedia.cremedia.service.Impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CalculatorServiceImplTest {

    @Mock
    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    @AfterEach
    void tearDown() {
        calculatorService = null;
    }

    @Test
    public void givenPositiveAAndPositiveBThenSum(){
        double result = calculatorService.sum(11,10);

        assertThat(result).isEqualTo(21);
        assertThat(result).isGreaterThan(0);
    }

    @Test
    public void givenNegativeAAndNegativeBThenSum(){
        double result = calculatorService.sum(-11,-10);

        assertThat(result).isEqualTo(0);
    }

    @Test public void givenAGreaterThanZeroAndBGreaterThanZeroThenDivide(){
        double result = calculatorService.divide(15,3);
        assertThat(result).isEqualTo(5);
    }

    @Test public void givenAGreaterThanZeroAndBEqualsThanZeroThenDivide(){
        assertThatThrownBy(()-> calculatorService.divide(15,0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("b can not be 0!");
    }
}