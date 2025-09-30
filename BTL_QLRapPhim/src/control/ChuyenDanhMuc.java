package control;

import gui.FormInVe;
import gui.FormNhanVien;
import gui.FormPhim;
import gui.FormSuatChieu;
import gui.FormVe;
import function.DanhMuc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChuyenDanhMuc {

    private JPanel pWin;
    private String kindSelected = "";
    private List<DanhMuc> listItem = null;

    private final Color COLOR_SELECTED = new Color(102, 0, 0);
    private final Color COLOR_HOVER = new Color(102, 0, 0);
    private final Color COLOR_DEFAULT = new Color(153, 0, 0);

    public ChuyenDanhMuc(JPanel pWin) {
        this.pWin = pWin;
    }

    public void setFormView(JPanel pnl, JLabel lbl) {
        kindSelected = "Phim";
        pnl.setBackground(COLOR_SELECTED);
        lbl.setBackground(COLOR_SELECTED);
        pWin.removeAll();
        pWin.setLayout(new BorderLayout());
        pWin.add(new FormPhim());
        pWin.validate();
        pWin.repaint();
    }

    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc item : listItem) {
            item.getPnl().addMouseListener(new LabelEvent(item.getKind(), item.getPnl(), item.getLbl()));
        }
    }

    class LabelEvent implements MouseListener {
        private JPanel node;
        private String kind;
        private JPanel pnlItem;
        private JLabel lblItem;

        public LabelEvent(String kind, JPanel pnlItem, JLabel lblItem) {
            this.kind = kind;
            this.pnlItem = pnlItem;
            this.lblItem = lblItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Xử lý form
            switch (kind) {
                case "Phim":
                    node = new FormPhim();
                    break;
                case "SuatChieu":
                    node = new FormSuatChieu();
                    break;
                case "Ve":
                    node = new FormVe();
                    break;
                case "InVe":
                    node = new FormInVe();
                    break;
                case "NhanVien":
                    node = new FormNhanVien();
                    break;
                default:
                    break;
            }
            pWin.removeAll();
            pWin.setLayout(new BorderLayout());
            pWin.add(node);
            pWin.validate();
            pWin.repaint();

            // Cập nhật kindSelected và đổi màu
            kindSelected = kind;
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Không xử lý màu tại đây
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Không xử lý
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (!kind.equalsIgnoreCase(kindSelected)) {
                pnlItem.setBackground(COLOR_HOVER);
                lblItem.setBackground(COLOR_HOVER);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kind.equalsIgnoreCase(kindSelected)) {
                pnlItem.setBackground(COLOR_DEFAULT);
                lblItem.setBackground(COLOR_DEFAULT);
            
            }
        }
    }

    private void setChangeBackground(String selectedKind) {
        for (DanhMuc item : listItem) {
            if (item.getKind().equalsIgnoreCase(selectedKind)) {
                item.getPnl().setBackground(COLOR_SELECTED);
                item.getLbl().setBackground(COLOR_SELECTED);
            } else {
                item.getPnl().setBackground(COLOR_DEFAULT);
                item.getLbl().setBackground(COLOR_DEFAULT);
            }
        }
    }
//    private void setChangeBackground(String kind){
//        for ( DanhMuc item : listItem){
//            if ( item.getKind().equalsIgnoreCase(kind)){
//                item.getPnl().setBackground(new Color(153, 0, 0)); 
//                item.getLbl().setBackground(new Color(153, 0, 0));              
//            }else{
//                item.getPnl().setBackground(new Color(102, 0, 0)); 
//                item.getLbl().setBackground(new Color(102, 0, 0));   
//            }
//        }
//    }
}
