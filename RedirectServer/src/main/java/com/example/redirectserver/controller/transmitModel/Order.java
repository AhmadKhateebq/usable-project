package com.example.redirectserver.controller.transmitModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    String product_names ;
    String total_products ;
    String transaction_id ;
    String total_amount ;

}
