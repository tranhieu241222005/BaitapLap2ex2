
package function;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMuc {
    private String kind;
    private JPanel pnl;
    private JLabel lbl;

    public DanhMuc(String kind, JPanel pnl, JLabel lbl) {
        this.kind = kind;
        this.pnl = pnl;
        this.lbl = lbl;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getPnl() {
        return pnl;
    }

    public void setPnl(JPanel pnl) {
        this.pnl = pnl;
    }

    public JLabel getLbl() {
        return lbl;
    }

    public void setLbl(JLabel lbl) {
        this.lbl = lbl;
    }
    
    
}
