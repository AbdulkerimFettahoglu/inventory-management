package dev.kerimfettahoglu.inventorymanagement.api;

import dev.kerimfettahoglu.inventorymanagement.api.input.PurchaseNewInput;
import dev.kerimfettahoglu.inventorymanagement.api.input.PurchaseUpdateInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Purchase;
import dev.kerimfettahoglu.inventorymanagement.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Purchase> create(@Valid @RequestBody PurchaseNewInput input) {
        return ResponseEntity.ok(purchaseService.create(input));
    }

    @PutMapping
    public ResponseEntity<Purchase> update(@Valid @RequestBody PurchaseUpdateInput input) {
        return ResponseEntity.ok(purchaseService.update(input));
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> get(@RequestParam(required = false) Long id) {
        return ResponseEntity.ok(purchaseService.getAll(id));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {
        return ResponseEntity.ok(purchaseService.delete(id));
    }

}
