package br.com.fiap.baitersburger.products.core.domain.model;

public enum Category {
    BURGER("BURGER"),
    SIDE_DISH("SIDE_DISH"),
    DRINK("DRINK"),
    DESSERT("DESSERT");

    private final String productCategory;

    Category(String productCategory) {
        this.productCategory = productCategory;
    }

    public static Category fromString(String categoryName) {
        for (Category category : Category.values()) {
            if (category.productCategory
                    .equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("This category does not exist!");
    }
}
