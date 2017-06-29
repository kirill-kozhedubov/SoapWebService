package com.netcracker.stcenter.project.models;


import java.math.BigDecimal;
import java.util.Map;

public interface Calculable {
    Map<Correlation, Graphic> getCorrelation();
    BigDecimal getAverage();
    BigDecimal getOlympicAverage();
    BigDecimal getDispersion();
    BigDecimal getMathExpectation();
}
