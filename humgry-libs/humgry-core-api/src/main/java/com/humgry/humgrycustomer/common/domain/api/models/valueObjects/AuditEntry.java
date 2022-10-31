package com.humgry.humgrycustomer.common.domain.api.models.valueObjects;

import java.time.LocalDate;

public record AuditEntry(String who, LocalDate when) {}

