package es.etg.dawes.micro.product.iu.view;
public enum ThymView {
    
    PRODUCT_LIST("productos-lista"),
    PRODUCT_FORM("productos-formulario"),
    PRODUCT_CREATED("productos-creado"),
    PRODUCT_DETAIL("productos-detalle"),
    ERROR_GENERIC("error/error-general");

    private final String path;

    ThymView(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    } 
}
