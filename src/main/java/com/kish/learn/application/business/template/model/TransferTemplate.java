package com.kish.learn.application.business.template.model;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record TransferTemplate(
        String vendorName,
        String vendorType,
        List<NTAccount> from,
        List<NTAccount> to
)
        implements Template{ }