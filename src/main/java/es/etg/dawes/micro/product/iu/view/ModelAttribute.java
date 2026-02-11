package es.etg.dawes.micro.product.iu.view;

public enum ModelAttribute {
    PRODUCT_LIST("productos"),
    FRAGMENTO_CONTENIDO("content"),
    SINGLE_PRODUCT("producto"),
    ERROR_MESSAGE("errorMsg"),
    SUCCESS_MESSAGE("successMsg");

    private final String name;

    ModelAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}