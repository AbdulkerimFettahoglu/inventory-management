package dev.kerimfettahoglu.inventorymanagement.api;

import dev.kerimfettahoglu.inventorymanagement.api.output.InventorySummaryOutput;
import dev.kerimfettahoglu.inventorymanagement.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/summary")
    public ResponseEntity<InventorySummaryOutput> summary() {
        return ResponseEntity.ok(inventoryService.summary());
    }

}
