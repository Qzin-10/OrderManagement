package com.Qzin.service;

import com.Qzin.entity.Kitchen;

import java.util.List;

public interface KitchenService  {

    Kitchen createKitchen(Kitchen kitchen);

    List<Kitchen> getAllKitchens();
}
