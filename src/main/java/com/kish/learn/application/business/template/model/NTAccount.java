package com.kish.learn.application.business.template.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class NTAccount{
    Long accntSk ;String accntName ; String accntDescription ; int srcType ; Long coaSk;
}
