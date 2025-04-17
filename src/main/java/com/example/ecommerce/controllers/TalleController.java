package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Talle;
import com.example.ecommerce.services.TalleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talles")
public class TalleController extends BaseController<Talle, Long>{
    public TalleController(TalleService talleService){
        super(talleService);
    }
}
