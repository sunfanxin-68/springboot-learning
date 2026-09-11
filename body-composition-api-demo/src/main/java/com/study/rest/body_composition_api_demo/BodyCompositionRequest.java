package com.study.rest.body_composition_api_demo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// POSTのJSONボディ。@Validで入力チェック
public record BodyCompositionRequest(
    @NotNull(message = "weight is required")
    @Positive(message = "weight must be positive")
    Double weight,   

    @NotNull(message = "bodyFat is required")
    @Positive(message = "bodyFat must be positive")
    Double bodyFat  
) {}