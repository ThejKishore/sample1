package com.kish.learn.application.business.template.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NTAccountExtended {
    @EqualsAndHashCode.Include
    Long accntSk ;
    String accntName , accntDescription ;
    int srcType ;
    Long coaSk;
    BigDecimal percentage;

}
