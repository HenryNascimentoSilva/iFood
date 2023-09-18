package me.dio.sacola.resource;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.model.Item;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartResource {
	private final CartService cartService;


	@PostMapping
	public Item insertItem(ItemDto itemDto) {
		return cartService.insertItem(itemDto);
	}
}
