package br.com.fiap.baitersburger.products.core.domain.model;

public enum Category {
    BURGER("Burger"),
    SIDE_DISH("Side Dish"),
    DRINK("Drink"),
    DESSERT("Dessert");

    private final String category;

    Category(String category) {
        this.category = category;
    }
}
