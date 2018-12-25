package com.fys.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderStateService orderStateService;

  @RequestMapping(method = {RequestMethod.GET})
  public ResponseEntity orders() {
    String orders = orderStateService.listDBEntries();
    return new ResponseEntity(orders, HttpStatus.OK);
  }

  @RequestMapping(value = "/{orderId}", method = {RequestMethod.POST})
  public ResponseEntity processOrderState(@PathVariable("orderId") Integer orderId,
                                          @RequestParam("event") OrderStatusChangeEvent event) {
    Boolean result = orderStateService.change(orderId, event);
    return new ResponseEntity(result,HttpStatus.OK);
  }

}
