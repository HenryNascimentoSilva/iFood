package me.dio.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import me.dio.sacola.enumeration.PaymentMethod;

import java.util.List;

public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JsonIgnore
  private Client client;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Item> itens;
  private Double totalValue;

  @Enumerated
  private PaymentMethod payment;
  private boolean closed;
}
