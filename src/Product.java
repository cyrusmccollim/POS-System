import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Product {
    public final JPanel panel;

    public final String CPU;
    public final String name;
    public final double price;

    public Product(String name, double price, String CPU){
        this.CPU = CPU;
        this.name = name;
        this.price = price;
        this.panel = createProductPanel();
        this.panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                POSApplication.handleClick(Product.this);
            }
        });
    }

    private JPanel createProductPanel() {
        JPanel productPanel = new JPanel(new GridBagLayout()){
            @Override public Insets getInsets(){
                return new Insets(10, 0, 10, 0); }
        };
        productPanel.setPreferredSize(new Dimension(100, 100));
        productPanel.setBackground(AppColors.DARK_BLUE);
        productPanel.setBorder(BorderFactory.createBevelBorder(0));
        productPanel.setVisible(true);

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel CPULabel = new JLabel(CPU);
            CPULabel.setForeground(Color.white);
            CPULabel.setHorizontalAlignment(JLabel.CENTER);
            CPULabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weighty = 0.25;
        productPanel.add(CPULabel, constraints);

        JLabel nameLabel = new JLabel(name);
            nameLabel.setForeground(Color.white);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setPreferredSize(new Dimension(200, 25));
            nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weighty = 0.50;
        productPanel.add(nameLabel, constraints);

        JLabel priceLabel = new JLabel(String.valueOf(price));
            priceLabel.setForeground(Color.white);
            priceLabel.setHorizontalAlignment(JLabel.CENTER);
            priceLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.weighty = 0.25;
        productPanel.add(priceLabel, constraints);

        return productPanel;
    }
}
