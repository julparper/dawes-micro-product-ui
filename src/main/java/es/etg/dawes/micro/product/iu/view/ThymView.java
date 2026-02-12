package es.etg.dawes.micro.product.iu.view;
public enum ThymView {
    
    PRODUCT_MAIN("productos-main");
  
    private final String path;

    ThymView(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    } 
}
