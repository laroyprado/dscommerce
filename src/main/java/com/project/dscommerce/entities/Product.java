package com.project.dscommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;

    private String imgUrl;
    



    @ManyToMany
    @JoinTable(name = "tb_product_category",
        joinColumns = @JoinColumn(name="product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    

    public Set<Category> gCategories(){
        return categories;
    }

    public Set<OrderItem> getItems(){
        return items;
    }


    public List<Order> getOrders(){
        return items.stream().map(x -> x.getOrder()).toList();
    }

}
