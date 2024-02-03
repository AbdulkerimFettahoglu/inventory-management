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
public class Purchase {

    @SequenceGenerator(name = "purchase_sequence", sequenceName = "purchase_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_sequence")
    private Long id;

    @NotNull(message = Constants.PRODUCT_NULL)
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private Integer itemCount;

    private Double itemCost;

    private Double totalCost;

}
