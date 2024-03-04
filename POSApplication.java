import javax.swing.*;
import java.awt.*;

public class POSApplication {
    private final JFrame window = new JFrame();

    private final ImageIcon windowIcon = new ImageIcon("elements\\window_icon.png");

    private final JButton[] numberPadButtons = new JButton[12];

    private final JPanel saleMetrics = new JPanel();
    private final JPanel products = new JPanel();
    private final JPanel numberPad = new JPanel();

    private final Color veryLightBlue = new Color(191, 218, 255);
    private final Color lightBlue = new Color(162, 196, 242);
    private final Color normalBlue = new Color(116, 170, 242);

    public static void main(String[] args) {
        new POSApplication();
    }

    public POSApplication(){
        window.setSize(new Dimension(1280, 720));
        window.setMinimumSize(window.getSize());

        initializeNumberPadButtons();

        configureWindowSettings();
        configurePanelSettings();

        addElementsToPanels();
        addPanelsToWindow();

        window.setVisible(true);
    }

    private void addElementsToPanels(){
        for(JButton button : numberPadButtons){
            numberPad.add(button);
        }
    }

    private void addPanelsToWindow(){
        window.add(saleMetrics);
        window.add(products);
        window.add(numberPad);
    }

    private void configurePanelSettings(){
        // Setting Positions & Dimensions.
        saleMetrics.setBounds(0, 0, window.getWidth(), (int) (window.getHeight() * 0.250));
        products.setBounds(0, saleMetrics.getHeight(), (int) (window.getWidth() * 0.666), window.getHeight() - saleMetrics.getHeight());
        numberPad.setBounds(products.getWidth(), saleMetrics.getHeight(), (int) (window.getWidth() * 0.333), window.getHeight() - saleMetrics.getHeight());

        // Setting Backgrounds.
        saleMetrics.setBackground(veryLightBlue);
        products.setBackground(normalBlue);
        numberPad.setBackground(lightBlue);

        numberPad.setLayout(new GridLayout(4, 3));
    }

    private void configureWindowSettings(){
        window.setTitle("POS System");
        window.setIconImage(windowIcon.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setResizable(true);
    }

    public void initializeNumberPadButtons(){
        numberPadButtons[0] = new JButton("1");
        numberPadButtons[1] = new JButton("2");
        numberPadButtons[2] = new JButton("3");
        numberPadButtons[3] = new JButton("4");
        numberPadButtons[4] = new JButton("5");
        numberPadButtons[5] = new JButton("6");
        numberPadButtons[6] = new JButton("7");
        numberPadButtons[7] = new JButton("8");
        numberPadButtons[8] = new JButton("9");
        numberPadButtons[9] = new JButton("Delete");
        numberPadButtons[10] = new JButton("0");
        numberPadButtons[11] = new JButton("Enter");

        for (JButton button : numberPadButtons){
            button.setFocusable(false);
            button.setVisible(true);
            button.setFont(new Font("Calibri", Font.PLAIN, (Utility.isInteger(button.getText()))? 50 : 25));
        }
    }
}
