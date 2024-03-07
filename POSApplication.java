import javax.swing.*;
import java.awt.*;

public class POSApplication {
    // Main window.
    private final JFrame window;
    private final Dimension windowSize;
    private final Dimension screenSize;

    // Main panels.
    private final JPanel saleMetrics;
    private final JPanel products;
    private final JPanel numberPad;

    public static void main(String[] args) {
        new POSApplication();
    }

    public POSApplication(){
        // Window
        window = new JFrame();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowSize = new Dimension((int) (screenSize.width * 0.75), (int) (screenSize.height * 0.75));

        // Main Panels
        saleMetrics = new JPanel(new GridLayout(1, 4, 10, 10)){
            @Override public Insets getInsets(){
                return new Insets(10, 10, 10, 10);
            }
        };
        products = new JPanel(new GridLayout(5, 4, 10, 10)){
            @Override public Insets getInsets(){
                return new Insets(10, 10, 10, 10);
            }
        };
        numberPad = new JPanel(new GridLayout(4, 3,10, 10)){
            @Override public Insets getInsets(){
                return new Insets(10, 10, 10, 10);
            }
        };

        // Configurations
        configureWindowSettings();
        configurePanelSettings();

        // Creating Elements
        addNumberPadButtons();
        addSaleMetrics();

        // Adding Panels
        window.add(saleMetrics, BorderLayout.NORTH);
        window.add(products, BorderLayout.CENTER);
        window.add(numberPad, BorderLayout.EAST);

        // Launching Window
        window.pack();
        window.setVisible(true);
    }

    private void configurePanelSettings(){
        // Bounds
        saleMetrics.setBounds(0, 0, windowSize.width, (int) (windowSize.height * 0.250));
        products.setBounds(0, saleMetrics.getHeight(), (int) (windowSize.width * 0.6666), windowSize.height - saleMetrics.getHeight());
        numberPad.setBounds(products.getWidth(), saleMetrics.getHeight(), (int) (windowSize.width * 0.3333), windowSize.height - saleMetrics.getHeight());
        // Backgrounds
        saleMetrics.setBackground(AppColors.VERY_LIGHT_BLUE);
        products.setBackground(AppColors.NORMAL_BLUE);
        numberPad.setBackground(AppColors.LIGHT_BLUE);
        // Border Styles
        saleMetrics.setBorder(BorderFactory.createBevelBorder(0));
        products.setBorder(BorderFactory.createBevelBorder(0));
        numberPad.setBorder(BorderFactory.createBevelBorder(0));
    }

    private void configureWindowSettings(){
        // Identity
        window.setTitle("POS System");
        window.setIconImage(new ImageIcon("elements\\window_icon.png").getImage());
        // Sizing
        window.setMinimumSize(windowSize);
        window.setResizable(false);
        // Layout
        window.setLayout(new BorderLayout());
        // Behavior
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addSaleMetrics(){
        String[][] elements = {
                // Title, Subtitle, Image
                {"$0.00", "Total", null},
                {"$0.00", "Cost Per Pound", null},
                {"0.00lb", "Weight", null},
                {null, null, "elements\\search_icon.png"},
        };

        for (String[] element : elements) {
            JPanel saleMetric = new JPanel(new GridBagLayout()){
                @Override public Insets getInsets(){ return new Insets(20, 0, 30, 0); }
            };
            saleMetric.setBorder(BorderFactory.createBevelBorder(0));
            saleMetric.setVisible(true);

            // TITLE
            if (element[0] != null) {
                JLabel title = new JLabel();
                title.setText("<html><center>" + element[0]);
                title.setFont(new Font("Calibri", Font.PLAIN, 80));
                title.setVisible(true);
                // Constraints
                GridBagConstraints titleConstraints = new GridBagConstraints();
                titleConstraints.gridx = 0;
                titleConstraints.gridy = 0;
                titleConstraints.weighty = 0.66;
                titleConstraints.fill = GridBagConstraints.BOTH;
                // Add
                saleMetric.add(title, titleConstraints);
            }

            // SUBTITLE
            if (element[1] != null) {
                JLabel subtitle = new JLabel();
                subtitle.setText("<html><center>" + element[1]);
                subtitle.setFont(new Font("Calibri", Font.PLAIN, 30));
                subtitle.setVisible(true);
                // Constraints
                GridBagConstraints subtitleConstraints = new GridBagConstraints();
                subtitleConstraints.gridx = 0;
                subtitleConstraints.gridy = 1;
                subtitleConstraints.weighty = 0.33;
                subtitleConstraints.fill = GridBagConstraints.BOTH;
                // Add
                saleMetric.add(subtitle, subtitleConstraints);
            }

            // IMAGE
            if (element[2] != null){
                JLabel imageLabel = new JLabel();
                Image imageScaled = new ImageIcon(element[2]).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(imageScaled));
                saleMetric.add(imageLabel);
            }

            saleMetrics.add(saleMetric);
        }
    }

    private void populateProducts(){
        // TBI
    }

    private void addNumberPadButtons(){
        String[] buttons = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "Del", "0", "Enter"};
        for (String buttonName : buttons) {
            JButton button = new JButton(buttonName);
            button.setFont(new Font("Calibri", Font.PLAIN, (Utility.isInteger(button.getText())) ? 50 : 35));
            button.setFocusable(false);
            button.setVisible(true);
            numberPad.add(button);
        }
    }

}
