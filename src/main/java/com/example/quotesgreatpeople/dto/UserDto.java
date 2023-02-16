package com.example.quotesgreatpeople.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private LocalDateTime creationDate;
}
