import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Products {
    public JPanel panel;

    private final ArrayList<Product> products;

    public Products(String productDataPath){
        products = DatabaseReader.readProducts(productDataPath);
        panel = createProductsPanel();
    }

    private JPanel createProductsPanel(){
        JPanel productsPanel = new JPanel(new GridLayout(0, 4, 20, 20)){
            @Override public Insets getInsets(){
                return new Insets(30, 30, 30, 30);
            }
        };

        for (Product p : products){
            productsPanel.add(p.panel);
        }

        return productsPanel;
    }
}
