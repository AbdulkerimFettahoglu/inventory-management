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
public class Exit {

    @SequenceGenerator(name = "exit_sequence", sequenceName = "exit_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exit_sequence")
    private Long id;

    @NotNull(message = Constants.PRODUCT_NULL)
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private Integer itemCount;


}
