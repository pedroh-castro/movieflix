package com.phc.movieflix.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long id, String email, String name) {
}
