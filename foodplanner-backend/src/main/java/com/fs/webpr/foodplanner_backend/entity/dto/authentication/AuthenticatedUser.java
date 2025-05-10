package com.fs.webpr.foodplanner_backend.entity.dto.authentication;

import java.util.UUID;

public record AuthenticatedUser(UUID userId, String username, String email) {
}
