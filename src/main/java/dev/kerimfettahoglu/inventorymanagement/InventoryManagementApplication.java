package dev.kerimfettahoglu.inventorymanagement;

import dev.kerimfettahoglu.inventorymanagement.api.input.ProductNewInput;
import dev.kerimfettahoglu.inventorymanagement.api.input.PurchaseNewInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.service.CategoryService;
import dev.kerimfettahoglu.inventorymanagement.service.ProductService;
import dev.kerimfettahoglu.inventorymanagement.service.PurchaseService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Value("${init-data}")
	private Boolean initData;
	private final CategoryService categoryService;
	private final ProductService productService;
	private final PurchaseService purchaseService;

	@PostConstruct
	private void initData() {
		if (initData) {
			Category category1 = new Category();
			category1.setName("cat1");
			Category category2 = new Category();
			category2.setName("cat2");
			category1 = categoryService.create(category1);
			category2 = categoryService.create(category2);
			ProductNewInput pro1 = new ProductNewInput();
			pro1.setCategoryId(category1.getId());
			pro1.setName("pro1");
			pro1.setBrand("pro1");
			Product p1 = productService.create(pro1);
			ProductNewInput pro2 = new ProductNewInput();
			pro2.setCategoryId(category2.getId());
			pro2.setName("pro2");
			pro2.setBrand("pro2");
			Product p2 = productService.create(pro2);
			PurchaseNewInput input1 = new PurchaseNewInput();
			input1.setProductId(p1.getId());
			input1.setItemCount(40);
			input1.setItemCost(2D);
			purchaseService.create(input1);
			PurchaseNewInput input2 = new PurchaseNewInput();
			input2.setProductId(p2.getId());
			input2.setItemCount(2);
			input2.setItemCost(2.5D);
			purchaseService.create(input2);
		}
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
						.permitAll())
				.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}

}
