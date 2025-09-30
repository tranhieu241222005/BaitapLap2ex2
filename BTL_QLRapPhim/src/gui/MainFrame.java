/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import control.ChuyenDanhMuc;
import function.DanhMuc;
import java.util.ArrayList;
import java.util.List;
public class MainFrame extends javax.swing.JFrame {
    public MainFrame() {
        initComponents();   
        ChuyenDanhMuc ctrl = new ChuyenDanhMuc(pView);
        ctrl.setFormView(pMenuPhim, lblPhim);
        
        List <DanhMuc> listItem = new ArrayList<>();
        listItem.add(new DanhMuc("Phim", pMenuPhim, lblPhim));
        listItem.add(new DanhMuc("SuatChieu", pMenuSuatChieu, lblSuatChieu));
        listItem.add(new DanhMuc("Ve", pMenuVe, lblVe));
        listItem.add(new DanhMuc("InVe",pMenuInVe , lblInVe));
        listItem.add(new DanhMuc("NhanVien", pMenuNhanVien, lblNhanVien));
        ctrl.setEvent(listItem);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pWin = new javax.swing.JPanel();
        pMenu = new javax.swing.JPanel();
        pTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pMenuPhim = new javax.swing.JPanel();
        lblPhim = new javax.swing.JLabel();
        pMenuSuatChieu = new javax.swing.JPanel();
        lblSuatChieu = new javax.swing.JLabel();
        pMenuVe = new javax.swing.JPanel();
        lblVe = new javax.swing.JLabel();
        pMenuInVe = new javax.swing.JPanel();
        lblInVe = new javax.swing.JLabel();
        pMenuNhanVien = new javax.swing.JPanel();
        lblNhanVien = new javax.swing.JLabel();
        pView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Lý Rạp Phim");
        setPreferredSize(new java.awt.Dimension(1160, 730));
        getContentPane().setLayout(null);

        pWin.setPreferredSize(new java.awt.Dimension(1150, 720));

        pMenu.setBackground(new java.awt.Color(0, 0, 0));
        pMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pMenu.setPreferredSize(new java.awt.Dimension(200, 700));

        pTitle.setBackground(new java.awt.Color(153, 0, 0));

        lblTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO/movie.png"))); // NOI18N
        lblTitle.setText("BeanCinema");

        javax.swing.GroupLayout pTitleLayout = new javax.swing.GroupLayout(pTitle);
        pTitle.setLayout(pTitleLayout);
        pTitleLayout.setHorizontalGroup(
            pTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTitleLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        pTitleLayout.setVerticalGroup(
            pTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTitleLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pMenuPhim.setBackground(new java.awt.Color(153, 0, 0));
        pMenuPhim.setBorder(new javax.swing.border.MatteBorder(null));

        lblPhim.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblPhim.setForeground(new java.awt.Color(255, 255, 255));
        lblPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO/btnMovie.png"))); // NOI18N
        lblPhim.setText("Phim");

        javax.swing.GroupLayout pMenuPhimLayout = new javax.swing.GroupLayout(pMenuPhim);
        pMenuPhim.setLayout(pMenuPhimLayout);
        pMenuPhimLayout.setHorizontalGroup(
            pMenuPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuPhimLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pMenuPhimLayout.setVerticalGroup(
            pMenuPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuPhimLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblPhim)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pMenuSuatChieu.setBackground(new java.awt.Color(153, 0, 0));

        lblSuatChieu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblSuatChieu.setForeground(new java.awt.Color(255, 255, 255));
        lblSuatChieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuatChieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO/btnSuatChieu.png"))); // NOI18N
        lblSuatChieu.setText("Suất Chiếu");

        javax.swing.GroupLayout pMenuSuatChieuLayout = new javax.swing.GroupLayout(pMenuSuatChieu);
        pMenuSuatChieu.setLayout(pMenuSuatChieuLayout);
        pMenuSuatChieuLayout.setHorizontalGroup(
            pMenuSuatChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuSuatChieuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        pMenuSuatChieuLayout.setVerticalGroup(
            pMenuSuatChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuSuatChieuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblSuatChieu)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pMenuVe.setBackground(new java.awt.Color(153, 0, 0));

        lblVe.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblVe.setForeground(new java.awt.Color(255, 255, 255));
        lblVe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO/btnVe.png"))); // NOI18N
        lblVe.setText("Vé ");

        javax.swing.GroupLayout pMenuVeLayout = new javax.swing.GroupLayout(pMenuVe);
        pMenuVe.setLayout(pMenuVeLayout);
        pMenuVeLayout.setHorizontalGroup(
            pMenuVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuVeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVe, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        pMenuVeLayout.setVerticalGroup(
            pMenuVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuVeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblVe)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pMenuInVe.setBackground(new java.awt.Color(153, 0, 0));

        lblInVe.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblInVe.setForeground(new java.awt.Color(255, 255, 255));
        lblInVe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO/btnVe.png"))); // NOI18N
        lblInVe.setText("Danh Sách Vé");

        javax.swing.GroupLayout pMenuInVeLayout = new javax.swing.GroupLayout(pMenuInVe);
        pMenuInVe.setLayout(pMenuInVeLayout);
        pMenuInVeLayout.setHorizontalGroup(
            pMenuInVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuInVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMenuInVeLayout.setVerticalGroup(
            pMenuInVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuInVeLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lblInVe)
                .addGap(20, 20, 20))
        );

        pMenuNhanVien.setBackground(new java.awt.Color(153, 0, 0));

        lblNhanVien.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO/btnNhanVien.png"))); // NOI18N
        lblNhanVien.setText("Nhân viên");

        javax.swing.GroupLayout pMenuNhanVienLayout = new javax.swing.GroupLayout(pMenuNhanVien);
        pMenuNhanVien.setLayout(pMenuNhanVienLayout);
        pMenuNhanVienLayout.setHorizontalGroup(
            pMenuNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuNhanVienLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        pMenuNhanVienLayout.setVerticalGroup(
            pMenuNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuNhanVienLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lblNhanVien)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pMenuLayout = new javax.swing.GroupLayout(pMenu);
        pMenu.setLayout(pMenuLayout);
        pMenuLayout.setHorizontalGroup(
            pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pMenuPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pMenuSuatChieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pMenuVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pMenuInVe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pMenuNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pMenuLayout.setVerticalGroup(
            pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuLayout.createSequentialGroup()
                .addComponent(pTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(pMenuPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pMenuSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pMenuVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pMenuInVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(pMenuNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );

        pView.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pView.setPreferredSize(new java.awt.Dimension(900, 700));

        javax.swing.GroupLayout pViewLayout = new javax.swing.GroupLayout(pView);
        pView.setLayout(pViewLayout);
        pViewLayout.setHorizontalGroup(
            pViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        pViewLayout.setVerticalGroup(
            pViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pWinLayout = new javax.swing.GroupLayout(pWin);
        pWin.setLayout(pWinLayout);
        pWinLayout.setHorizontalGroup(
            pWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pWinLayout.createSequentialGroup()
                .addComponent(pMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pView, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE))
        );
        pWinLayout.setVerticalGroup(
            pWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pWinLayout.createSequentialGroup()
                .addGroup(pWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                    .addComponent(pMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        getContentPane().add(pWin);
        pWin.setBounds(0, 0, 1150, 700);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblInVe;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblPhim;
    private javax.swing.JLabel lblSuatChieu;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblVe;
    private javax.swing.JPanel pMenu;
    private javax.swing.JPanel pMenuInVe;
    private javax.swing.JPanel pMenuNhanVien;
    private javax.swing.JPanel pMenuPhim;
    private javax.swing.JPanel pMenuSuatChieu;
    private javax.swing.JPanel pMenuVe;
    private javax.swing.JPanel pTitle;
    private javax.swing.JPanel pView;
    private javax.swing.JPanel pWin;
    // End of variables declaration//GEN-END:variables
}
