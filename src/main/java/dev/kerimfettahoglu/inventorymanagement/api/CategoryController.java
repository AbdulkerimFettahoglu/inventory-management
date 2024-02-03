package dev.kerimfettahoglu.inventorymanagement.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<String>> all() {
        return ResponseEntity.ok(List.of("kerim", "mehmet"));
    }

}
