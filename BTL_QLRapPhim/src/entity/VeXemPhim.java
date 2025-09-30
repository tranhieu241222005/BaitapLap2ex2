package entity;

import java.time.LocalDateTime;

public class VeXemPhim {
    private String maVe;
    public static final double GIA_VE = 60000;
    private String maPhim;
    private String maSuatChieu;
    private String maGhe;
    private LocalDateTime ngayDat;

    public VeXemPhim(String maPhim, String maSuatChieu, String maGhe) {
		this.maPhim = maPhim;
		this.maSuatChieu = maSuatChieu;
		this.maGhe = maGhe;
		this.ngayDat = LocalDateTime.now();
		setMaVe();
	}


    public void setMaVe() {
    	String last3MaPhim = this.maPhim.substring(this.maPhim.length() - 3);
        String last3SuatChieu = this.maSuatChieu.substring(this.maSuatChieu.length() - 3);
		this.maVe = last3MaPhim + last3SuatChieu + maGhe;
	}


	public String getMaVe() {
        return maVe;
    }

    public double getGiaVe() {
        return GIA_VE;
    }



    public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getMaSuatChieu() {
		return maSuatChieu;
	}

	public void setMaSuatChieu(String maSuatChieu) {
		this.maSuatChieu = maSuatChieu;
	}

	public String getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}

	public LocalDateTime getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDateTime ngayDat) {
        this.ngayDat = ngayDat;
    }

    @Override
    public String toString() {
        return "VeXemPhim [maVe=" + maVe + ", giaVe=" + GIA_VE + ", phim=" + maPhim 
                + ", suatChieu=" + maSuatChieu + ", ghe=" + maGhe 
                + ", ngayDat=" + ngayDat + "]";
    }
}