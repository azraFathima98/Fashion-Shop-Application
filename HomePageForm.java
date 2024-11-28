/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HomePageForm extends JFrame {
    private JLabel lblTitle;
    private JLabel copyrightLabel;
    private JLabel imageLabel;
    private JButton btnSearch;
    private JButton btnViewReports;
    private JButton btnSetOrderStatus;
    private JButton btnDeleteOrder;
    private JButton btnPlaceOrder;

    HomePageForm(List ordersCollection) {
        setSize(500, 550);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ----------------- Title --------------------
        lblTitle = new JLabel("Fashion Shop", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBackground(new Color(51, 102, 255));
        lblTitle.setOpaque(true);
        lblTitle.setBounds(0, 0, 500, 50);
        add(lblTitle);
        
        // ----------------- Buttons --------------------
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
        btnSearch.setBounds(50, 80, 200, 50);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Object[] options = { "Search Customer", "Search Order", "Cancel" };
                int response = JOptionPane.showOptionDialog(
                        null,
                        "Please select the option",
                        "Search Options",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                switch (response) {
                    case 0:
                        new SearchCustomerForm(ordersCollection).setVisible(true);
                        break;
                    case 1:
                        new SearchOrderForm(ordersCollection).setVisible(true);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Unexpected response.");
                        break;
                }
            }
        });

        btnViewReports = new JButton("Reports");
        btnViewReports.setFont(new Font("Arial", Font.BOLD, 16));
        btnViewReports.setBounds(50, 150, 200, 50);
        add(btnViewReports);
        btnViewReports.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new ReportsForm(ordersCollection).setVisible(true);
                dispose();
            }
        });

        btnSetOrderStatus = new JButton("Status");
        btnSetOrderStatus.setFont(new Font("Arial", Font.BOLD, 16));
        btnSetOrderStatus.setBounds(50, 220, 200, 50);
        add(btnSetOrderStatus);
        btnSetOrderStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new StatusForm(ordersCollection).setVisible(true);
                dispose();
            }
        });

        btnDeleteOrder = new JButton("Delete");
        btnDeleteOrder.setFont(new Font("Arial", Font.BOLD, 16));
        btnDeleteOrder.setBounds(50, 290, 200, 50);
        add(btnDeleteOrder);
        btnDeleteOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new DeleteOrderForm(ordersCollection).setVisible(true);
                dispose();
            }
        });
        
        btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.setFont(new Font("Arial", Font.BOLD, 20));
        btnPlaceOrder.setBackground(new Color(4, 203, 201));
        btnPlaceOrder.setBounds(50, 360, 200, 80);
        add(btnPlaceOrder);
        btnPlaceOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new PlaceOrderForm(ordersCollection).setVisible(true);
                dispose();
            }
        });

        // ----------------- Image --------------------
        imageLabel = new JLabel(new ImageIcon("Images\\profile1.png"));
        imageLabel.setBounds(250, 80, 230, 360);
        add(imageLabel);

        // ----------------- Footer Label --------------------
        copyrightLabel = new JLabel("Copyrights @ Azra 2024", SwingConstants.CENTER);
        copyrightLabel.setBounds(0, 470, 500, 20);
        add(copyrightLabel);
    }
}*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HomePageForm extends JFrame {
    private JLabel lblTitle;
    private JLabel copyrightLabel;
    private JLabel imageLabel;
    private JButton btnSearch;
    private JButton btnViewReports;
    private JButton btnSetOrderStatus;
    private JButton btnDeleteOrder;
    private JButton btnPlaceOrder;

    HomePageForm(List ordersCollection) {
        setSize(500, 550);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------------- Title --------------------
        lblTitle = new JLabel("Fashion Shop", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBackground(new Color(54, 64, 79)); // Dark Gold // Changed to a darker blue
        lblTitle.setOpaque(true);
        add(lblTitle, BorderLayout.NORTH);
        
        // ----------------- Button Panel --------------------
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column
        add(buttonPanel, BorderLayout.WEST);

        btnSearch = createButton("Search", new Color(255, 204, 204), e -> showSearchOptions(ordersCollection));
        btnViewReports = createButton("Reports", new Color(204, 255, 204), e -> openForm(new ReportsForm(ordersCollection)));
        btnSetOrderStatus = createButton("Status", new Color(204, 204, 255), e -> openForm(new StatusForm(ordersCollection)));
        btnDeleteOrder = createButton("Delete", new Color(255, 255, 204), e -> openForm(new DeleteOrderForm(ordersCollection)));
        btnPlaceOrder = createButton("Place Order", new Color(4, 203, 201), e -> openForm(new PlaceOrderForm(ordersCollection)));

        buttonPanel.add(btnSearch);
        buttonPanel.add(btnViewReports);
        buttonPanel.add(btnSetOrderStatus);
        buttonPanel.add(btnDeleteOrder);
        buttonPanel.add(btnPlaceOrder);

        // ----------------- Image --------------------
        imageLabel = new JLabel(new ImageIcon("Image/download-removebg-preview.png")); // Use forward slashes for cross-platform compatibility
        add(imageLabel, BorderLayout.CENTER);

        // ----------------- Footer Label --------------------
        copyrightLabel = new JLabel("Copyrights @ Azra 2024", SwingConstants.CENTER);
        copyrightLabel.setForeground(Color.GRAY); // Changed footer text color
        copyrightLabel.setFont(new Font("Arial", Font.ITALIC, 12)); // Italicized font
        add(copyrightLabel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text, Color bgColor, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setOpaque(true);
        button.setBorderPainted(false); // Optional: remove border for a cleaner look
        button.addActionListener(action);
        return button;
    }

    private void showSearchOptions(List ordersCollection) {
        Object[] options = { "Search Customer", "Search Order", "Cancel" };
        int response = JOptionPane.showOptionDialog(
                this,
                "Please select the option",
                "Search Options",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        switch (response) {
            case 0:
                openForm(new SearchCustomerForm(ordersCollection));
                break;
            case 1:
                openForm(new SearchOrderForm(ordersCollection));
                break;
            case 2:
                break;
            default:
                System.out.println("Unexpected response.");
                break;
        }
    }

    private void openForm(JFrame form) {
        form.setVisible(true);
        dispose();
    }
}
