package com.example.Currencyy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//This controller allows us test if our configuration in "CurrencyConfigurationService" works

@RestController
public class CurrencyConfigurationController {

    @Autowired
    private CurrencyServiceConfiguration configuration;



    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration retrieveEverything(){

        return configuration;
    }
}
