package me.dio.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.enumeration.PaymentMethod;
import me.dio.sacola.model.Cart;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Restaurant;
import me.dio.sacola.repository.CartRepository;
import me.dio.sacola.repository.ItemRepository;
import me.dio.sacola.repository.ProductRepository;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final ItemRepository itemRepository;

	@Override
	public Item insertItem(ItemDto itemDto) {
		Cart cart = seeCart(itemDto.getIdCart());

		if(cart.isClosed()){
			throw new RuntimeException("This cart is closed!");
		}

		Item item = Item.builder()
				.quantity(itemDto.getQuantity())
				.cart(cart)
				.product(productRepository.findById(itemDto.getProductId()).
						orElseThrow(() -> new RuntimeException("This product does not exist")
						))
				.build();

		List<Item> itens = cart.getItens();
		if(itens.isEmpty()){
			itens.add(item);
		}
		else {
			Restaurant restaurant = itens.get(0).getProduct().getRestaurant();
			Restaurant addItem = item.getProduct().getRestaurant();
			if(restaurant.equals(addItem)){
				itens.add(item);}
				else{
					throw new RuntimeException("This product does not belong to this restaurant!");
				}
			}
		cartRepository.save(cart);
		return itemRepository.save(item);
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
