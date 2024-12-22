package com.scaler.repositories;

import com.scaler.models.DietaryRequirement;
import com.scaler.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuRepositoryImpl implements MenuRepository {

    private final List<MenuItem> menuItems;

    public MenuRepositoryImpl() {
        this.menuItems = new ArrayList<>();
    }

    public MenuItem add(MenuItem menuItem) {
        menuItems.add(menuItem);
        return menuItem;
    }

    public List<MenuItem> getAll() {
        return menuItems;
    }

    public List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement) {
        return menuItems.stream().filter(menuItem ->
                menuItem.getDietaryRequirement()
                        .equals(dietaryRequirement)).toList();
    }
}
