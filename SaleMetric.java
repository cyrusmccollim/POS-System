import javax.swing.*;
import java.awt.*;

public class SaleMetric {
    public JPanel panel;

    private final String name;
    private final String value;

    public SaleMetric(String name, String value){
        this.name = name;
        this.value = value;
        panel = createSaleMetricPanel();
    }

    private JPanel createSaleMetricPanel(){
        JPanel saleMetricPanel = new JPanel(new GridBagLayout()){
            @Override public Insets getInsets(){
                return new Insets(20, 0, 30, 0);
            }
        };
        saleMetricPanel.setBorder(BorderFactory.createBevelBorder(0));
        saleMetricPanel.setVisible(true);

        GridBagConstraints constraints = new GridBagConstraints();

        // TITLE
        JLabel title = new JLabel();
            title.setPreferredSize(new Dimension(300, 100));
            title.setText("<html><center>" + value);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(new Font("Calibri", Font.PLAIN, 80));
            title.setVisible(true);
            // Constraints
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weighty = 0.66;
            constraints.fill = GridBagConstraints.BOTH;
            saleMetricPanel.add(title, constraints);

        // SUBTITLE
        JLabel subtitle = new JLabel();
            subtitle.setPreferredSize(new Dimension(300, 30));
            subtitle.setText("<html><center>" + name);
            subtitle.setHorizontalAlignment(JLabel.CENTER);
            subtitle.setFont(new Font("Calibri", Font.PLAIN, 25));
            subtitle.setVisible(true);
            // Constraints
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weighty = 0.33;
            constraints.fill = GridBagConstraints.BOTH;
            saleMetricPanel.add(subtitle, constraints);

        return saleMetricPanel;
    }
}
