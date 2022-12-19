package com.project.Ecommerce.service;

import com.project.Ecommerce.dto.cart.AddToCartDto;
import com.project.Ecommerce.dto.cart.CartDto;
import com.project.Ecommerce.dto.cart.CartItemDto;
import com.project.Ecommerce.exceptions.CartItemNotExistException;
import com.project.Ecommerce.model.*;
import com.project.Ecommerce.repository.CartRepository;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.IsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.project.Ecommerce.model.User;

@Service
@Transactional
public class ProductService {

	@Autowired
	private CartRepository cartRepository;

	public ProductService() {
	}

	public ProductService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
		Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
		cartRepository.save(cart);
	}

	public CartDto listCartItems(User user) {
		List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDto> cartItems = new ArrayList<>();
		for (Cart cart : cartList) {
			CartItemDto cartItemDto = getDtoFromCart(cart);
			cartItems.add(cartItemDto);
		}
		double totalCost = 0;
		for (CartItemDto cartItemDto : cartItems) {
			totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
		}
		return new CartDto(cartItems, totalCost);
	}

	public static CartItemDto getDtoFromCart(Cart cart) {
		return new CartItemDto(cart);
	}

	public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
		Optional<Cart> cart = cartRepository.findById(Integer.valueOf(cartDto.getId()));

		if (cart.isPresent()) {
			Cart newCart = cart.get();
			newCart.setQuantity(cartDto.getQuantity());
			newCart.setCreatedDate(new Date());
			cartRepository.save(newCart);
		}

	}

	public void deleteCartItem(int id, int userId) throws CartItemNotExistException {
		if (!cartRepository.existsById(id))
			throw new CartItemNotExistException("Cart id is invalid : " + id);
		cartRepository.deleteById(id);

	}

	public void deleteCartItems(int userId) {
		cartRepository.deleteAll();
	}

	public void deleteUserCartItems(User user) {
		cartRepository.deleteByUser(user);
	}
}