package com.cremedia.cremedia.service.Impl;

import com.cremedia.cremedia.service.CalculatorService;

public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public double sum(int a, int b) {
        double result = 0;
        if (a>=0||b>=0){
            result=a+b;
        }
        return result;
    }

    @Override
    public double divide(int a, int b) {
        int result = 0;
        if (b!=0){
            result=a/b;
        }else {
            throw new ArithmeticException("b can not be 0!");
        }
        return result;
    }
}
