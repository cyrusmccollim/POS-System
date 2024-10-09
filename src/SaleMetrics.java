import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SaleMetrics {
    public JPanel panel;

    Product product;
    ArrayList<SaleMetric> saleMetrics;

    public SaleMetrics(){
        this.product = null;
        saleMetrics = DatabaseReader.retrieveSaleMetrics();
        panel = createSaleMetricsPanel();
    }

    public SaleMetrics(Product product){
        this.product = product;
        saleMetrics = new ArrayList<>();
            saleMetrics.add(new SaleMetric("Total", "0.0"));
            saleMetrics.add(new SaleMetric("Cost/lb", String.valueOf(product.price)));
            saleMetrics.add(new SaleMetric("Weight", "0.0"));
            saleMetrics.add(new SaleMetric("Name", String.valueOf(product.name)));
        panel = createSaleMetricsPanel();
    }

    private JPanel createSaleMetricsPanel(){
        JPanel saleMetricsPanel = new JPanel(new GridLayout(1, 4, 20, 20)){
            @Override public Insets getInsets(){
                return new Insets(20, 20, 20, 20);
            }
        };

        for (SaleMetric s : saleMetrics) {
            saleMetricsPanel.add(s.panel);
        }

        return saleMetricsPanel;
    }
}
