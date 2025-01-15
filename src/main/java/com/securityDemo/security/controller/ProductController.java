package com.securityDemo.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private  record Product(Integer productId, String productName, double productPrice){}

    List<Product> products = new ArrayList<>(
            List.of(new Product(1,"iphone",999.0),
                    new Product(2,"Mac",2999.0))
    );

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        products.add(product);
        return  product;
    }

    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request) {
        System.out.println("inside meeeeee");
       return (CsrfToken) request.getAttribute("_csrf");
    }

}
