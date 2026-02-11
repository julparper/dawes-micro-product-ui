package es.etg.dawes.micro.product.iu.view;
public enum FragmentoContenido {
    
    PRODUCT_LIST("fragments/content/listado"),
    PRODUCT_FORM("fragments/content/formulario"),;

    private final String path;

    FragmentoContenido(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    } 
}
