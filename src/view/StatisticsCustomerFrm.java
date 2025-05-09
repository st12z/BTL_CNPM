/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ContractDAO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.ContractStatistics;
import model.User;
import model.Contract;

/**
 *
 * @author T
 */
public class StatisticsCustomerFrm extends javax.swing.JFrame implements ActionListener {

    private List<ContractStatistics> result;
    private JTable tblResult;
    private JButton btnBack;
    private User user;
    private JLabel lblUsername;

    public StatisticsCustomerFrm(List<ContractStatistics> result, User u) {
        this.result = result;
        this.user = u;
        setTitle("Thống kê dư nợ khách hàng");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tính toán thống kê
        int totalCustomer = result.size();
        long totalCustomerRemainingDebt = result.stream().filter(c -> c.getOutstandingBalance() > 0).count();
        double totalDebt = result.stream().mapToDouble(ContractStatistics::getOutstandingBalance).sum();
        DecimalFormat df = new DecimalFormat("#,###.##");

        // Bảng 1 - thống kê tổng quát
        String[] headerRow = {"", "Tổng số khách hàng", "Số khách hàng còn nợ", "Tổng dư nợ"};
        String[] dataRow = {
            "Thống kê",
            String.valueOf(totalCustomer),
            String.valueOf(totalCustomerRemainingDebt),
            df.format(totalDebt) + " VNĐ"
        };

        DefaultTableModel model1 = new DefaultTableModel(new String[][]{headerRow, dataRow}, new Object[]{"", "", "", ""}) {
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

        // Tạo label tên người dùng
        lblUsername = new JLabel("Welcome " + user.getUsername());
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);

        // Tạo nút quay lại
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(this);

        // Panel top chứa nút và label user
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(btnBack, BorderLayout.WEST);
        topPanel.add(lblUsername, BorderLayout.EAST);

        // Bảng 2 - danh sách khách hàng
        String[] columnNames2 = {"Mã", "Tên", "Số điện thoại", "Tổng dư nợ quá hạn", "Tổng dư nợ còn", "Hành động"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // chỉ cột "Hành động" được click
            }
        };

        for (ContractStatistics c : result) {
            model.addRow(new Object[]{
                c.getId(),
                c.getName(),
                c.getPhoneNumber(),
                df.format(c.getOverdueBalance()) + " VNĐ",
                df.format(c.getOutstandingBalance()) + " VNĐ",
                "Xem chi tiết"
            });
        }

        tblResult = new JTable(model);
        tblResult.getColumn("Hành động").setCellRenderer(new ButtonRenderer());
        tblResult.getColumn("Hành động").setCellEditor(new ButtonEditor(new JCheckBox(), result, this));
        JScrollPane scrollPane = new JScrollPane(tblResult);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel giữa chứa cả bảng thống kê và bảng danh sách
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(summaryScroll); // bảng 1
        centerPanel.add(Box.createVerticalStrut(10)); // khoảng cách
        centerPanel.add(scrollPane); // bảng 2

        mainPanel.add(centerPanel, BorderLayout.CENTER);
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
        new HomeFrm(user).setVisible(true);
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
        private List<ContractStatistics> list;
        private StatisticsCustomerFrm parent;

        public ButtonEditor(JCheckBox checkBox, List<ContractStatistics> list, StatisticsCustomerFrm parent) {
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
            int customerId = list.get(selectedRow).getId();
            ContractDAO dao = new ContractDAO();
            List<Contract> contracts = dao.getListContract(customerId);
            parent.dispose();
            StatisticsDetailCustomerFrm detailCustomerFrm = new StatisticsDetailCustomerFrm(result, contracts, user, customerId);
            detailCustomerFrm.setVisible(true);

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
            java.util.logging.Logger.getLogger(StatisticsCustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatisticsCustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatisticsCustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatisticsCustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
