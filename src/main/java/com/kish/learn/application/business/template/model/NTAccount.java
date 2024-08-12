package com.kish.learn.application.business.template.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record NTAccount(Long accntSk ,String accntName , String accntDescription , int srcType , Long coaSk) {}
