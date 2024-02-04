package dev.kerimfettahoglu.inventorymanagement;

import dev.kerimfettahoglu.inventorymanagement.api.input.ProductNewInput;
import dev.kerimfettahoglu.inventorymanagement.entity.Category;
import dev.kerimfettahoglu.inventorymanagement.entity.Product;
import dev.kerimfettahoglu.inventorymanagement.service.CategoryService;
import dev.kerimfettahoglu.inventorymanagement.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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

	private final CategoryService categoryService;
	private final ProductService productService;

	@PostConstruct
	private void initData() {
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

	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
						.permitAll())
				.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}

}
