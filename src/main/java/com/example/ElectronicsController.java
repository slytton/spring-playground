package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by gschool on 2/19/17.
 */
@RestController
@RequestMapping("/electronics")
public class ElectronicsController {

    @GetMapping({"/", ""})
    public String getElectronics(@RequestParam Map<String, String> params) {
        String result = String.format("You searched for the following %s:", params.get("type"));
        params.remove("type");
        for(Map.Entry<String, String> entry : params.entrySet()) {
            result += String.format("\n%s: %s", entry.getKey(), entry.getValue());
        }
        return result;
    }

    @PostMapping({"/", ""})
    public String postElectronics(@RequestParam String type, @RequestParam String brand, @RequestParam("model-number") String modelNumber) {
        return String.format("Successfully added a gizmo with the following details:\n" +
                "type: %s\n" +
                "brand: %s\n" +
                "model-number: %s", type, brand, modelNumber);
    }

    @PatchMapping({"/", ""})
    public String patchElectronics(ElectronicsInfo info){
        return String.format("Successfully updated a gizmo with the following details:\n" +
                "type: %s\n" +
                "brand: %s\n" +
                "model number: %s", info.getType(), info.getBrand(), info.getModelNumber());
    }

    public static class ElectronicsInfo {
        private String type;
        private String brand;
        private String modelNumber;

        public String getModelNumber() {
            return modelNumber;
        }

        public void setModelNumber(String modelNumber) {
            this.modelNumber = modelNumber;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
