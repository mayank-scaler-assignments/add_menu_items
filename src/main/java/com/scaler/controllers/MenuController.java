package com.scaler.controllers;

import com.scaler.dtos.*;
import com.scaler.exceptions.UnAuthorizedAccess;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.MenuItem;
import com.scaler.services.MenuService;

public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    public AddMenuItemResponseDto addMenuItem(AddMenuItemRequestDto requestDto) {
        AddMenuItemResponseDto responseDto = new AddMenuItemResponseDto();
        try {
            MenuItem menuItem = menuService.addMenuItem(
                    requestDto.getUserId(),
                    requestDto.getName(),
                    requestDto.getPrice(),
                    requestDto.getDietaryRequirement(),
                    requestDto.getItemType(),
                    requestDto.getDescription());
            responseDto.setMenuItem(menuItem);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | UnAuthorizedAccess e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
