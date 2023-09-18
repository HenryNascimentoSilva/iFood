package me.dio.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import me.dio.sacola.enumeration.PaymentMethod;

import java.util.List;

// @ Entity representa um banco de dados
@Entity
public class Cart {

  // primeira Key gerada automaticamente
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // um cliente pode ter varios carts
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JsonIgnore
  private Client client;

  // um item para vários clientes
  @OneToMany(cascade = CascadeType.ALL)
  private List<Item> itens;

  // valor total dos produtos no cart
  private Double totalValue;

  // método de pagamento(dinheiro ou cartão)
  @Enumerated
  private PaymentMethod payment;

  private boolean closed;
}
