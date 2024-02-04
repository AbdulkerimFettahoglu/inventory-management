package dev.kerimfettahoglu.inventorymanagement.service;

import dev.kerimfettahoglu.inventorymanagement.api.input.ExitNewInput;
import dev.kerimfettahoglu.inventorymanagement.api.input.ExitUpdateInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Exit;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.exception.DataNotFoundException;
import dev.kerimfettahoglu.inventorymanagement.repository.ExitRepository;
import dev.kerimfettahoglu.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExitService {

    private final ProductRepository productRepository;
    private final ExitRepository exitRepository;
    private final InventoryService inventoryService;

    public Exit create(ExitNewInput input) {
        Optional<Product> optionalProduct = productRepository.findById(input.getProductId());
        if (optionalProduct.isPresent()) {
            Exit exit = new Exit();
            exit.setItemCount(input.getItemCount());
            exit.setProduct(optionalProduct.get());
            inventoryService.makeExitFromInventory(exit);
            exitRepository.save(exit);
            return exit;
        } else {
            throw new DataNotFoundException(input.getProductId());
        }
    }

    public Exit update(ExitUpdateInput input) {
        Optional<Product> optionalProduct = productRepository.findById(input.getProductId());
        if (optionalProduct.isPresent()) {
            Optional<Exit> optionalExit = exitRepository.findById(input.getId());
            if (optionalExit.isPresent()) {
                //TODO: önceki puchase adımında yapılanlar product entitysinden geri alınmalı
                Exit exit = optionalExit.get();
                exit.setItemCount(input.getItemCount());
                exit.setProduct(optionalProduct.get());
                exitRepository.save(exit);
                //TODO: Product kaydındaki item count ve total cost güncellenmeli.
                return exit;
            } else {
                throw new DataNotFoundException(input.getId());
            }
        } else {
            throw new DataNotFoundException(input.getProductId());
        }
    }

    public List<Exit> getAll(Long id) {
        if (Optional.ofNullable(id).isPresent()) {
            return List.of(this.get(id));
        } else {
            return this.getAll();
        }
    }

    public Exit get(Long id) {
        Optional<Exit> optionalExit = exitRepository.findById(id);
        if (optionalExit.isPresent()) {
            return optionalExit.get();
        } else {
            throw new DataNotFoundException(id);
        }
    }

    public List<Exit> getAll() {
        return exitRepository.findAll();
    }

    public Boolean delete(Long id) {
        Optional<Exit> optionalExit = exitRepository.findById(id);
        if (optionalExit.isPresent()) {
            //TODO: önceki puchase adımında yapılanlar product entitysinden geri alınmalı
            exitRepository.delete(optionalExit.get());
            return true;
        } else {
            throw new DataNotFoundException(id);
        }
    }

}
