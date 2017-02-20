package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gschool on 2/19/17.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping({"/pizza", "/pizza/"})
    public String postPizzaOrders(PizzaRequest pizzaRequest){

        return String.format("Thanks for your order of a %s %s %s crusted %s.",
                pizzaRequest.getSize(),
                pizzaRequest.getShape(),
                pizzaRequest.getCrust(),
                pizzaRequest.getType());
    }


    // Getting a 415 error :(
    // Not sure @RequestBody can be used like this...
//    @PostMapping({"/sandwich", "/sandwich/"})
//    public String postSandwichOrders(@RequestBody Map<String, String> body) {
//
//        return String.format("Thanks for your order of a %s and %s %s",
//                body.get("bread"),
//                body.get("cheese"),
//                body.get("type"));
//    }


    @PostMapping({"/candy", "/candy/"})
    public String postCandyOrders(@RequestBody String body) {
        return String.format("Thanks for your order of %s", body);
    }



    private static class PizzaRequest {
        private String type;
        private String size;
        private String shape;
        private String crust;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public String getCrust() {
            return crust;
        }

        public void setCrust(String crust) {
            this.crust = crust;
        }
    }
}
