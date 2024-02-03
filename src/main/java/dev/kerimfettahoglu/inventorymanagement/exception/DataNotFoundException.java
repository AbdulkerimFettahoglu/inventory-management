package dev.kerimfettahoglu.inventorymanagement.exception;

public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(Long id) {
        super("Data not found :" + id);
    }

}
