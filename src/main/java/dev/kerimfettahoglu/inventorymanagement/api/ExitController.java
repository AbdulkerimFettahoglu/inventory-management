package dev.kerimfettahoglu.inventorymanagement.api;

import dev.kerimfettahoglu.inventorymanagement.api.input.ExitNewInput;
import dev.kerimfettahoglu.inventorymanagement.api.input.ExitUpdateInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Exit;
import dev.kerimfettahoglu.inventorymanagement.service.ExitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("exit")
public class ExitController {

    private final ExitService exitService;

    @PostMapping
    public ResponseEntity<Exit> create(@Valid @RequestBody ExitNewInput input) {
        return ResponseEntity.ok(exitService.create(input));
    }

    @PutMapping
    public ResponseEntity<Exit> update(@Valid @RequestBody ExitUpdateInput input) {
        return ResponseEntity.ok(exitService.update(input));
    }

    @GetMapping
    public ResponseEntity<List<Exit>> get(@RequestParam(required = false) Long id) {
        return ResponseEntity.ok(exitService.getAll(id));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {
        return ResponseEntity.ok(exitService.delete(id));
    }

}
