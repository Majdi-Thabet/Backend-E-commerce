package com.project.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Ecommerce.model.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {
}
