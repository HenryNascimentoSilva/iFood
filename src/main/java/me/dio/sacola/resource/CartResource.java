package me.dio.sacola.resource;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.model.Cart;
import me.dio.sacola.model.Item;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartResource {
	private final CartService cartService;


	@PostMapping
	public Item insertItem(@RequestBody ItemDto itemDto) {
		return cartService.insertItem(itemDto);
	}

	@GetMapping("/{id}")
	public Cart seeCart(@PathVariable Long id){
		return cartService.seeCart(id);
	}

	@PatchMapping("/closeCart/{idCart}")
	public Cart closeCart(@PathVariable Long idCart,
	                      @RequestParam("paymentMethod")  int paymentMethod){
		return cartService.closeCart(idCart, paymentMethod);
	}
}
