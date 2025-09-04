package com.dtheof.reservation_system.dto.CustomersDtos;

import java.util.List;

public record AllCustomersDto(List<CustomerInfoDto> customers,
                              boolean isLast,
                              long totalPages,
                              long totalElement) {
}
