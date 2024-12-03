package com.Qzin.service.impl;


import com.Qzin.entity.Kitchen;
import com.Qzin.repository.KitchenRepository;
import com.Qzin.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen createKitchen(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public List<Kitchen> getAllKitchens() {
        return kitchenRepository.findAll();
    }
}

