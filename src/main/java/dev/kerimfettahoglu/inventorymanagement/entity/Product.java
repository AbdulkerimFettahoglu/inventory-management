package dev.kerimfettahoglu.inventorymanagement.entity;

import dev.kerimfettahoglu.inventorymanagement.constant.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Product {

    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;

    @NotNull(message = Constants.CATEGORY_NULL)
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private String name;

    private String brand;

    private Integer itemCount;

    private Double totalCost;

    private Double medianCost;

}
