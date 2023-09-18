package me.dio.sacola.service;

import me.dio.sacola.model.Cart;
import me.dio.sacola.model.Item;
import me.dio.sacola.resource.dto.ItemDto;

public interface CartService {

	Item insertItem(ItemDto itemDto);

	Cart seeCart(Long id);

	Cart closeCart(Long id, int paymentMethod);

}
