import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseReader {
    public static ArrayList<Product> readProducts(String path) {
        ArrayList<Product> products = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            Logger.addLog("Unable to read file: " + path);
            return null;
        }

        List<String> lines = reader.lines().toList();
        String[] productInfo = {};
        for (String productAttributes : lines){
            try {
                productInfo = productAttributes.strip().split(",");
                products.add(new Product(productInfo[0], Double.parseDouble(productInfo[1]), productInfo[2]));
            } catch (Exception e){
                Logger.addLog("Unable to parse product. Data: " + Arrays.toString(productInfo));
            }
        }

        return products;
    }

    public static ArrayList<SaleMetric> retrieveSaleMetrics(){
        ArrayList<SaleMetric> saleMetrics = new ArrayList<>();

        String[] metrics = {"Total", "Cost/lb", "Weight", "Product"};

        for (String metric : metrics) {
            SaleMetric saleMetric = new SaleMetric(metric, "--");
            saleMetrics.add(saleMetric);
        }

        return saleMetrics;
    }
}
