package com.tw.jingxi.controller;

import com.tw.jingxi.entity.Product;
import com.tw.jingxi.repository.InventoryRepository;
import com.tw.jingxi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/products")
public class ProductController {
    private String ipAddress="49.140.86.22";

    @Autowired
    ProductRepository productRepository;
    @Autowired
    InventoryRepository inventoryRepository;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product) throws Exception {
        if (product.getName() == null || product.getPrice() == null) {
            throw new Exception("Invalid product!");
        }
        Long id = productRepository.save(product).getId();
        HttpHeaders responseHeaders = setLocationInResponseHeader(id);
        inventoryRepository.saveByProductId(id);
        return new ResponseEntity<>(productRepository.findProductById(id), responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) throws Exception {
        productRepository.findById(id).orElseThrow(() -> new Exception("Product"+id+" not found!"));
        productRepository.updateById(id, product.getName(), product.getDescription(), product.getPrice());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new Exception("Product"+id+" not found!"));
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        if (!name.isEmpty() && !description.isEmpty()) {
            return productRepository.findByNameAndDescriptionContaining(name, description);
        } else if (!name.isEmpty()) {
            return productRepository.findByName(name);
        } else {
            return productRepository.findAll();
        }
    }

    private HttpHeaders setLocationInResponseHeader(Long id) {
        URI location = URI.create("http://"+ipAddress+":8083/products/" + id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return responseHeaders;
    }
}