import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;

public class POSApplication {

    // Window
    private static JFrame window;
    private static Dimension windowSize;

    // Panels
    private static JPanel saleMetricsPanel;
    private static JPanel productsPanel;
    private static JPanel numberPadPanel;

    // Constructor
    public  POSApplication(String productDataPath){
        // Initializes the frame.
        window = new JFrame();

        // Sets the window size.
        Dimension displayResolution = Toolkit.getDefaultToolkit().getScreenSize();
        windowSize = new Dimension((int) (displayResolution.height * 0.75 * 16.0 / 9.0),
                                   (int) (displayResolution.height * 0.75)); // 75% of the display's resolution at the 16:9 ratio.


        saleMetricsPanel = new SaleMetrics().panel;
        productsPanel = new Products(productDataPath).panel;

        numberPadPanel = new JPanel(new GridLayout(5, 3,15, 15)){
            @Override public Insets getInsets(){
                return new Insets(15, 15, 15, 15);
            }
        };

        // Populates the elements within panels.
        addElementsToNumberPad();

        // Settings for the window and main panels.
        configureWindowSettings();
        configurePanelSettings();
        updateWindow();
    }

    public static void handleClick(Product product){
        // saleMetricsPanel = new SaleMetrics(product).panel;
        // updateWindow();
    }

    private static void updateWindow(){
        // Adds sale metrics panel to window.
        window.getContentPane().add(saleMetricsPanel, BorderLayout.NORTH);

        // Adds number pad panel to window.
        window.getContentPane().add(numberPadPanel, BorderLayout.EAST);

        // Adds product panel to window.
        JScrollPane productsPane = new JScrollPane(productsPanel);
        productsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        window.getContentPane().add(productsPane, BorderLayout.CENTER);

        // Launches the window.
        window.setVisible(true);
    }

    private static void configurePanelSettings(){
        saleMetricsPanel.setBounds(0, 0, windowSize.width, (int) (windowSize.height * 0.250));
        productsPanel.setBounds(0, saleMetricsPanel.getHeight(), (int) (windowSize.width * 0.6666), windowSize.height - saleMetricsPanel.getHeight());
        numberPadPanel.setBounds(productsPanel.getWidth(), saleMetricsPanel.getHeight(), (int) (windowSize.width * 0.3333), windowSize.height - saleMetricsPanel.getHeight());

        saleMetricsPanel.setBackground(AppColors.ORANGE);
        productsPanel.setBackground(AppColors.BLUE);
        numberPadPanel.setBackground(AppColors.BLUE);

        saleMetricsPanel.setBorder(BorderFactory.createBevelBorder(0));
        productsPanel.setBorder(BorderFactory.createBevelBorder(0));
        numberPadPanel.setBorder(BorderFactory.createBevelBorder(0));
    }

    private static void configureWindowSettings(){
        window.setTitle("POS System");
        window.setIconImage(new ImageIcon("elements\\window_icon.png").getImage());
        window.setMinimumSize(windowSize);
        window.setResizable(false);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

            numberPadPanel.add(button);
        }
    }
}
