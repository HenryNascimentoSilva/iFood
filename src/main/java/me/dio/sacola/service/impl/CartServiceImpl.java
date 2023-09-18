package me.dio.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.enumeration.PaymentMethod;
import me.dio.sacola.model.Cart;
import me.dio.sacola.model.Item;
import me.dio.sacola.repository.CartRepository;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.CartService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;

	@Override
	public Item insertItem(ItemDto itemDto) {
		return null;
	}

	@Override
	public Cart seeCart(Long id) {
		return cartRepository.findById(id).orElseThrow(
				() -> new RuntimeException("This cart does not exist")
		);
	}

	@Override
	public Cart closeCart(Long id, int paymentMethod) {
		Cart cart = seeCart(id);
		if(cart.getItens().isEmpty()) {
			throw new RuntimeException("Add items to cart!");
		}
		PaymentMethod paymentMethod1 =
				paymentMethod == 0 ? PaymentMethod.MONEY : PaymentMethod.TERMINAL;

		cart.setPaymentMethod(paymentMethod1);
		cart.setClosed(true);
		return cartRepository.save(cart);
	}
}
