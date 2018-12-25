package com.fys.statemachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_test")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @NotNull
  @Column(name = "order_id")
  private Integer orderId;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  @Column(name = "status")
  private OrderStatus status;

}
