import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class POSApplication {
    // Main window.
    private final JFrame window;
    private final Dimension windowSize;
    private final Dimension displayResolution;

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
        displayResolution = Toolkit.getDefaultToolkit().getScreenSize();
        windowSize = new Dimension((int) (displayResolution.height * 0.75 * 16.0 / 9.0), (int) (displayResolution.height * 0.75));

        // Main Panels
        saleMetrics = new JPanel(new GridLayout(1, 4, 20, 20)){
            @Override public Insets getInsets(){
                return new Insets(20, 20, 20, 20);
            }
        };
        products = new JPanel(new GridLayout(0, 4, 20, 20)){
            @Override public Insets getInsets(){
                return new Insets(30, 30, 30, 30);
            }
        };
        numberPad = new JPanel(new GridLayout(5, 3,15, 15)){
            @Override public Insets getInsets(){
                return new Insets(15, 15, 15, 15);
            }
        };

        // Configurations
        configureWindowSettings();
        configurePanelSettings();

        // Creating Elements
        addNumberPadButtons();
        addSaleMetrics();
        populateProducts();

        JScrollPane productsPane = new JScrollPane(products);
        productsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Adding Panels
        window.getContentPane().add(saleMetrics, BorderLayout.NORTH);
        window.getContentPane().add(productsPane, BorderLayout.CENTER);
        window.getContentPane().add(numberPad, BorderLayout.EAST);

        // Launching Window
        window.setVisible(true);
    }

    private void configurePanelSettings(){
        // Bounds
        saleMetrics.setBounds(0, 0, windowSize.width, (int) (windowSize.height * 0.250));
        products.setBounds(0, saleMetrics.getHeight(), (int) (windowSize.width * 0.6666), windowSize.height - saleMetrics.getHeight());
        numberPad.setBounds(products.getWidth(), saleMetrics.getHeight(), (int) (windowSize.width * 0.3333), windowSize.height - saleMetrics.getHeight());
        // Backgrounds
        saleMetrics.setBackground(AppColors.ORANGE);
        products.setBackground(AppColors.BLUE);
        numberPad.setBackground(AppColors.BLUE);
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
                // Title, Subtitle
                {"$0.00", "Total"},
                {"$0.00", "Cost/lb"},
                {"0.00lb", "Weight"},
                {"--", "Product"},
        };

        for (String[] element : elements) {
            JPanel saleMetric = new JPanel(new GridBagLayout()){
                @Override public Insets getInsets(){ return new Insets(20, 0, 30, 0); }
            };
            saleMetric.setBorder(BorderFactory.createBevelBorder(0));
            saleMetric.setVisible(true);

            GridBagConstraints constraints = new GridBagConstraints();

            // TITLE
            if (element[0] != null) {
                JLabel title = new JLabel();
                    title.setPreferredSize(new Dimension(300, 100));
                    title.setHorizontalAlignment(JLabel.CENTER);
                    title.setText("<html><center>" + element[0]);
                    title.setFont(new Font("Calibri", Font.PLAIN, 80));
                    title.setVisible(true);
                    // Constraints
                    constraints.gridx = 0;
                    constraints.gridy = 0;
                    constraints.weighty = 0.66;
                    constraints.fill = GridBagConstraints.BOTH;
                saleMetric.add(title, constraints);
            }

            // SUBTITLE
            if (element[1] != null) {
                JLabel subtitle = new JLabel();
                    subtitle.setPreferredSize(new Dimension(300, 30));
                    subtitle.setText("<html><center>" + element[1]);
                    subtitle.setHorizontalAlignment(JLabel.CENTER);
                    subtitle.setFont(new Font("Calibri", Font.PLAIN, 25));
                    subtitle.setVisible(true);
                    // Constraints
                    constraints.gridx = 0;
                    constraints.gridy = 1;
                    constraints.weighty = 0.33;
                    constraints.fill = GridBagConstraints.BOTH;
                saleMetric.add(subtitle, constraints);
            }

            saleMetrics.add(saleMetric);
        }
    }

    private void populateProducts(){
        ArrayList<String[]> productData = new ArrayList<>(DatabaseReader.readFile("data\\mock_database.csv"));

        for (String[] productInfo : productData){
            JPanel product = new JPanel(new GridBagLayout()){
                @Override public Insets getInsets(){ return new Insets(10, 0, 10, 0); }
            };
            product.setPreferredSize(new Dimension(100, 100));
            product.setBackground(AppColors.DARK_BLUE);
            product.setBorder(BorderFactory.createBevelBorder(0));
            product.setVisible(true);

            GridBagConstraints constraints = new GridBagConstraints();

            try {
                // CODE
                JLabel code = new JLabel(productInfo[2], JLabel.CENTER);
                    code.setForeground(Color.white);
                    code.setFont(new Font("Calibri", Font.PLAIN, 15));
                    constraints.gridx = 0;
                    constraints.gridy = 0;
                    constraints.weighty = 0.25;
                product.add(code, constraints);
                // TITLE
                JLabel title = new JLabel(productInfo[0]);
                    title.setForeground(Color.white);
                    title.setHorizontalAlignment(JLabel.CENTER);
                    title.setPreferredSize(new Dimension(200, 25));
                    title.setFont(new Font("Calibri", Font.PLAIN, 20));
                    constraints.gridx = 0;
                    constraints.gridy = 1;
                    constraints.weighty = 0.50;
                product.add(title, constraints);
                // COST
                JLabel cost = new JLabel(productInfo[1]);
                    cost.setForeground(Color.white);
                    cost.setFont(new Font("Calibri", Font.PLAIN, 15));
                    constraints.gridx = 0;
                    constraints.gridy = 2;
                    constraints.weighty = 0.25;
                product.add(cost, constraints);
            } catch (Exception e){
                continue;
            }

            products.add(product);
        }
    }

    private void addNumberPadButtons(){
        String[] buttons = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "elements\\delete_icon.png", "0", "elements\\enter_icon.png", "elements\\settings_icon.png", "elements\\search_icon.png", "elements\\printer_icon.png"};

        for (String element : buttons) {
            JButton button = new JButton();
                button.setFocusable(false);
                button.setPreferredSize(new Dimension(100, 150));
                //button.setBackground(Color.white);
                button.setBorder(BorderFactory.createBevelBorder(0));


            if (Utility.isInteger(element)){
                button.setText(element);
                button.setVerticalTextPosition(JButton.CENTER);
                button.setVerticalAlignment(JButton.CENTER);
                button.setFont(new Font("Calibri", Font.PLAIN, (Utility.isInteger(button.getText())) ? 50 : 35));
            } else {
                Image imageScaled = new ImageIcon(element).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(imageScaled));
            }

            numberPad.add(button);
        }
    }
}
