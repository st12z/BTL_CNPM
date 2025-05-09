/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ContractDAO;
import dao.PaymentDAO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.Contract;
import model.ContractStatistics;
import model.Payment;
import model.User;

/**
 *
 * @author T
 */
public class StatisticsDetailCustomerFrm extends javax.swing.JFrame implements ActionListener {

    private List<Contract> result;
    private JTable tblResult;
    private User user;
    private JLabel lblUsername;
    private JButton btnBack;
    private List<ContractStatistics> contractStatisticses;

    /**
     * Creates new form StatisticsDetailCustomerFrm
     */
    public StatisticsDetailCustomerFrm(List<ContractStatistics> contractStatisticses, List<Contract> result, User u, int customerId) {
        this.result = result;
        this.user = u;
        this.contractStatisticses = contractStatisticses;
        setTitle("Thống kê khách hàng với id: " + customerId);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DecimalFormat df = new DecimalFormat("#,###.##");

        // Bảng 1 - Thống kê tổng quát
        int totalContract = result.size();
        double totalDebt = result.stream().mapToDouble(Contract::getTotalLoan).sum();
        double totalDebtRemain = 0;
        double totalOverdue = 0;

        for (Contract c : result) {
            PaymentDAO dao = new PaymentDAO();
            List<Payment> listPayments = dao.getListPayment(c.getId());
            if (listPayments != null && !listPayments.isEmpty()) {
                float outstandingBalance = listPayments.get(listPayments.size() - 1).getRemainingDept();
                totalDebtRemain += outstandingBalance;

                for (int i = 1; i < listPayments.size(); i++) {
                    if (listPayments.get(i).getPaymentAmount() == 0) {
                        float overdue = listPayments.get(i).getRemainingDept() - listPayments.get(i - 1).getRemainingDept();
                        totalOverdue += overdue;
                    }
                }
            }
        }

        String[] headerRow = {"", "Tổng số hợp đồng", "Tổng số tiền đã vay", "Tổng dư nợ còn", "Tổng dư nợ quá hạn"};
        String[] dataRow = new String[5];
        dataRow[0] = "Thống kê";
        dataRow[1] = String.valueOf(totalContract);
        dataRow[2] = df.format(totalDebt) + " VNĐ";
        dataRow[3] = df.format(totalDebtRemain) + " VNĐ";
        dataRow[4] = df.format(totalOverdue) + " VNĐ";

        DefaultTableModel model1 = new DefaultTableModel(new String[][]{headerRow, dataRow}, new Object[]{"", "", "", "", ""}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable summaryTable = new JTable(model1);
        summaryTable.setEnabled(false);
        summaryTable.setRowHeight(30);
        JScrollPane summaryScroll = new JScrollPane(summaryTable);
        summaryScroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        // Label user và nút quay lại
        lblUsername = new JLabel("Welcome " + user.getUsername(), SwingConstants.RIGHT);
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(this);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(btnBack, BorderLayout.WEST);
        topPanel.add(lblUsername, BorderLayout.EAST);

        // Bảng 2 - Danh sách hợp đồng chi tiết
        String[] columnNames2 = {"Mã", "Ngày kí", "Tổng tiền vay", "Tổng số lần trả", "Tổng dư nợ còn", "Tổng dư nợ quá hạn", "Hành động"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // chỉ cột hành động
            }
        };

        for (Contract c : result) {
            PaymentDAO dao = new PaymentDAO();
            List<Payment> listPayments = dao.getListPayment(c.getId());
            float outstandingBalance = 0;
            float overdueBalance = 0;
            if (!listPayments.isEmpty()) {
                outstandingBalance = listPayments.get(listPayments.size() - 1).getRemainingDept();
                for (int i = 1; i < listPayments.size(); i++) {
                    if (listPayments.get(i).getPaymentAmount() == 0) {
                        overdueBalance += listPayments.get(i).getRemainingDept() - listPayments.get(i - 1).getRemainingDept();
                    }
                }
            }

            model.addRow(new Object[]{
                c.getId(),
                c.getSignDate(),
                df.format(c.getTotalLoan()) + " VNĐ",
                listPayments.size(),
                df.format(outstandingBalance) + " VNĐ",
                df.format(overdueBalance) + " VNĐ",
                "Xem chi tiết"
            });
        }

        tblResult = new JTable(model);
        tblResult.getColumn("Hành động").setCellRenderer(new StatisticsDetailCustomerFrm.ButtonRenderer());
        tblResult.getColumn("Hành động").setCellEditor(new StatisticsDetailCustomerFrm.ButtonEditor(new JCheckBox(), result, this));
        JScrollPane scrollPane = new JScrollPane(tblResult);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(summaryScroll);
        mainPanel.add(scrollPane);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.equals(btnBack)) {
            btnBack_actionperformed();
        }
    }

    public void btnBack_actionperformed() {
        this.dispose();
        new StatisticsCustomerFrm(contractStatisticses, user).setVisible(true);
    }

    // Renderer cho nút
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setText("Xem chi tiết");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor xử lý sự kiện click
    class ButtonEditor extends DefaultCellEditor implements ActionListener {

        private JButton btnViewDetail;
        private int selectedRow;
        private List<Contract> list;
        private StatisticsDetailCustomerFrm parent;

        public ButtonEditor(JCheckBox checkBox, List<Contract> list, StatisticsDetailCustomerFrm parent) {
            super(checkBox);
            this.list = list;
            this.parent = parent;
            btnViewDetail = new JButton("Xem chi tiết");
            btnViewDetail.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.equals(btnViewDetail)) {
                btnViewDetail_actionperformed();
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            selectedRow = row;
            return btnViewDetail;
        }

        public void btnViewDetail_actionperformed() {
            int contractId = list.get(selectedRow).getId();
            System.out.println(contractId);
        }

        @Override
        public Object getCellEditorValue() {
            return "Xem chi tiết";
        }
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
            java.util.logging.Logger.getLogger(StatisticsDetailCustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatisticsDetailCustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatisticsDetailCustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatisticsDetailCustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
