package com.scaler.services;

import com.scaler.exceptions.UnAuthorizedAccess;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.MenuRepository;
import com.scaler.repositories.UserRepository;

public class MenuServiceImpl implements MenuService {


    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public MenuServiceImpl(MenuRepository menuRepository, UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MenuItem addMenuItem(long userId, String name, double price, String dietaryRequirement, String itemType, String description) throws UserNotFoundException, UnAuthorizedAccess {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!user.getUserType().equals(UserType.ADMIN)) {
            throw new UnAuthorizedAccess("User not authorized to add menu item");
        }

        MenuItem menuItem = getMenuItem(name, price, dietaryRequirement, itemType, description);
        return menuRepository.add(menuItem);
    }

    private static MenuItem getMenuItem(String name, double price, String dietaryRequirement, String itemType, String description) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setDietaryRequirement(DietaryRequirement.valueOf(dietaryRequirement.toUpperCase()));
        menuItem.setItemType(ItemType.valueOf(itemType.toUpperCase()));
        menuItem.setDescription(description);
        return menuItem;
    }
}
