package com.store.stationary.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id private String id;
    @NotBlank private String barCode;
    private String name;
    private String description;
    private String category;
    private Integer quantity;
}
