import javax.swing.*;
import java.awt.*;
import java.util.*;

public class POSApplication {
    // Window
    private final JFrame window;
    private final Dimension windowSize;

    // Panels
    private final JPanel saleMetrics;
    private final JPanel products;
    private final JPanel numberPad;

    // Main
    public static void main(String[] args) {
        new POSApplication();
    }

    // Constructor
    public POSApplication(){
        // Initializes the frame.
        window = new JFrame();

        // Sets the window size.
        Dimension displayResolution = Toolkit.getDefaultToolkit().getScreenSize();
        windowSize = new Dimension((int) (displayResolution.height * 0.75 * 16.0 / 9.0),
                                   (int) (displayResolution.height * 0.75)); // 75% of the display's resolution at the 16:9 ratio.

        // Initializes the main panels.
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

        // Settings for the window and main panels.
        configureWindowSettings();
        configurePanelSettings();

        // Populates the elements within panels.
        addElementsToSaleMetrics();
        addElementsToProducts();
        addElementsToNumberPad();

        // Adds sale metrics panel to window.
        window.getContentPane().add(saleMetrics, BorderLayout.NORTH);

        // Adds product panel to window.
        JScrollPane productsPane = new JScrollPane(products);
            productsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            productsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        window.getContentPane().add(productsPane, BorderLayout.CENTER);

        // Adds number pad panel to window.
        window.getContentPane().add(numberPad, BorderLayout.EAST);

        // Launches the window.
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

    private void addElementsToSaleMetrics(){
        String[] titles = {"$0.00", "$0.00", "0.00lb", "--"};
        String[] subtitles = {"Total", "Cost/lb", "Weight", "Product"};

        for (int i = 0; i < titles.length; i++) {
            JPanel saleMetric = new JPanel(new GridBagLayout()){
                @Override public Insets getInsets(){
                    return new Insets(20, 0, 30, 0);
                }
            };
            saleMetric.setBorder(BorderFactory.createBevelBorder(0));
            saleMetric.setVisible(true);

            GridBagConstraints constraints = new GridBagConstraints();

            // TITLE
            JLabel title = new JLabel();
                title.setPreferredSize(new Dimension(300, 100));
                title.setText("<html><center>" + titles[i]);
                title.setHorizontalAlignment(JLabel.CENTER);
                title.setFont(new Font("Calibri", Font.PLAIN, 80));
                title.setVisible(true);
                // Constraints
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.weighty = 0.66;
                constraints.fill = GridBagConstraints.BOTH;
            saleMetric.add(title, constraints);

            // SUBTITLE
            JLabel subtitle = new JLabel();
                subtitle.setPreferredSize(new Dimension(300, 30));
                subtitle.setText("<html><center>" + subtitles[i]);
                subtitle.setHorizontalAlignment(JLabel.CENTER);
                subtitle.setFont(new Font("Calibri", Font.PLAIN, 25));
                subtitle.setVisible(true);
                // Constraints
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.weighty = 0.33;
                constraints.fill = GridBagConstraints.BOTH;
            saleMetric.add(subtitle, constraints);

            saleMetrics.add(saleMetric);
        }
    }

    private void addElementsToProducts(){
        // Loads product data from database.
        ArrayList<String[]> productData = DatabaseReader.readFile("data\\mock_database.csv");

        // Adds data to the products panel.
        for (String[] productInfo : productData){
            // This inner-panel holds the title, cost, and code for one product.
            JPanel product = new JPanel(new GridBagLayout()){
                @Override public Insets getInsets(){
                    return new Insets(10, 0, 10, 0); }
            };
            product.setPreferredSize(new Dimension(100, 100));
            product.setBackground(AppColors.DARK_BLUE);
            product.setBorder(BorderFactory.createBevelBorder(0));
            product.setVisible(true);

            // Used to adjust spacing between data elements with inner-panel.
            GridBagConstraints constraints = new GridBagConstraints();

            try {
                // Adds code to product inner-panel.
                JLabel code = new JLabel(productInfo[2], JLabel.CENTER);
                    code.setForeground(Color.white);
                    code.setHorizontalAlignment(JLabel.CENTER);
                    code.setFont(new Font("Calibri", Font.PLAIN, 15));
                    constraints.gridx = 0;
                    constraints.gridy = 0;
                    constraints.weighty = 0.25;
                product.add(code, constraints);
                // Adds title to product inner-panel.
                JLabel title = new JLabel(productInfo[0]);
                    title.setForeground(Color.white);
                    title.setHorizontalAlignment(JLabel.CENTER);
                    title.setPreferredSize(new Dimension(200, 25));
                    title.setFont(new Font("Calibri", Font.PLAIN, 20));
                    constraints.gridx = 0;
                    constraints.gridy = 1;
                    constraints.weighty = 0.50;
                product.add(title, constraints);
                // Adds cost to product inner-panel.
                JLabel cost = new JLabel(productInfo[1]);
                    cost.setForeground(Color.white);
                    cost.setHorizontalAlignment(JLabel.CENTER);
                    cost.setFont(new Font("Calibri", Font.PLAIN, 15));
                    constraints.gridx = 0;
                    constraints.gridy = 2;
                    constraints.weighty = 0.25;
                product.add(cost, constraints);
            // Skips adding the inner-panel if a product's data is corrupt.
            } catch (Exception e){
                continue;
            }
            // Adds inner-panel to products panel.
            products.add(product);
        }
    }

    private void addElementsToNumberPad(){
        // Holds text/image for number pad buttons.
        String[] buttonData = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "elements\\delete_icon.png", "0", "elements\\enter_icon.png", "elements\\settings_icon.png", "elements\\search_icon.png", "elements\\printer_icon.png"};

        // Adds buttons to number pad panel.
        for (String element : buttonData) {
            JButton button = new JButton();
                button.setFocusable(false);
                button.setPreferredSize(new Dimension(100, 150));
                button.setBorder(BorderFactory.createBevelBorder(0));

            if (Utility.isInteger(element)){
                button.setText(element);
                button.setFont(new Font("Calibri", Font.PLAIN, 50));
            } else {
                Image imageScaled = new ImageIcon(element).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(imageScaled));
            }

            numberPad.add(button);
        }
    }
}
