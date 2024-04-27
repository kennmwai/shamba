package com.kenm.spring.farmleaseservice.dto.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentMethodRequest {

    @NotNull
    private Card card;
}