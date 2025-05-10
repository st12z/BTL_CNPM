/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.BusinessPartnerDAO;
import dao.CustomerDAO;
import dao.ItemContractDAO;
import dao.ItemDAO;
import dao.PaymentDAO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.BusinessPartner;
import model.Contract;
import model.Customer;
import model.ItemContract;
import model.Payment;
import model.Item;
import model.User;

/**
 *
 * @author T
 */
public class StatisticsDetailContractFrm extends javax.swing.JFrame implements ActionListener {

    private JTable tblCustomer;
    private JTable tblBusinessPartner;
    private JTable tblItemContract;
    private JTable tblPayment;
    private JButton btnBack;
    private JLabel lblUsername;
    private User u;
    private Customer customer;
    public StatisticsDetailContractFrm(Contract contract, User u) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        this.u = u;
        int contractId = contract.getId();
        int customerId = contract.getCustomerId();
        CustomerDAO customerDAO = new CustomerDAO();
        BusinessPartnerDAO businessPartnerDAO = new BusinessPartnerDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        ItemContractDAO itemContractDAO = new ItemContractDAO();
        this.customer = customerDAO.getCustomer(customerId);
        BusinessPartner businessPartner = businessPartnerDAO.getBusinessPartner(contractId);
        List<ItemContract> itemContracts = itemContractDAO.getListItemContracts(contractId);
        List<Payment> payments = paymentDAO.getListPayment(contractId);
        setTitle("Chi tiết hợp đồng id: " + contract.getId());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblUsername = new JLabel("Welcome " + u.getUsername(), SwingConstants.RIGHT);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(lblUsername);
        mainPanel.add(topPanel);

        String[] columnCustomer = {"Mã", "Tên khách hàng", "Số điện thoại"};
        Object[][] dataCustomer = {
            {customer.getId(), customer.getName(), customer.getPhoneNumber()}
        };
        tblCustomer = new JTable(new DefaultTableModel(dataCustomer, columnCustomer));
        addSection(mainPanel, "Thông tin khách hàng", tblCustomer);

        String[] columnBusinessPartner = {"Mã", "Tên đối tác", "Địa chỉ"};
        Object[][] dataBusinessPartner = {
            {businessPartner.getId(), businessPartner.getName(), businessPartner.getAddress()}
        };
        tblBusinessPartner = new JTable(new DefaultTableModel(dataBusinessPartner, columnBusinessPartner));
        addSection(mainPanel, "Thông tin đối tác", tblBusinessPartner);

        String[] columnItems = {"Mã", "Số lượng", "Đơn giá", "Tổng tiền"};
        Object[][] dataItems = new Object[itemContracts.size()][4];
        for (int i = 0; i < itemContracts.size(); i++) {
            ItemContract ic = itemContracts.get(i);
            Item item = new ItemDAO().getItem(ic.getItemId());
            double total = ic.getQuantity() * item.getUnitPrice();
            dataItems[i] = new Object[]{item.getId(), ic.getQuantity(), 
                df.format(item.getUnitPrice())+" VNĐ", df.format(total)+" VNĐ"};
        }
        tblItemContract = new JTable(new DefaultTableModel(dataItems, columnItems));
        addSection(mainPanel, "Danh sách mặt hàng", tblItemContract);

        String[] columnPayments = {"Mã", "Ngày thanh toán", "Số tiền thanh toán", "Trạng thái"};
        Object[][] dataPayments = new Object[payments.size()][4];
        for (int i = 0; i < payments.size(); i++) {
            Payment p = payments.get(i);
            String status = (p.getPaymentAmount() == 0) ? "Chưa thanh toán" : "Đã thanh toán";
            dataPayments[i] = new Object[]{p.getId(), p.getPaymentDate(),
                df.format(p.getPaymentAmount())+ " VNĐ", status};
        }
        tblPayment = new JTable(new DefaultTableModel(dataPayments, columnPayments));
        addSection(mainPanel, "Danh sách đợt thanh toán", tblPayment);

        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(this);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnBack);

        setContentPane(new JScrollPane(mainPanel));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            btnBack_actionperformed();
        }
    }

    public void btnBack_actionperformed() {
        this.dispose();
        new StatisticsDetailCustomerFrm(u, customer).setVisible(true);
    }

    private void addSection(JPanel panel, String title, JTable table) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(750, 80));

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(15));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StatisticsDetailContractFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatisticsDetailContractFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatisticsDetailContractFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatisticsDetailContractFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
