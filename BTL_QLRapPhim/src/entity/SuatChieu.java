package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SuatChieu{
	private String maSuat;
	private LocalDateTime thoiGian;
	private LocalDate ngay;
	private String maPhim;
	private String maPhong;
	public SuatChieu(String maSuat, LocalDateTime thoiGian, LocalDate ngay, String maPhim, String maPhong) {
		super();
		this.maSuat = maSuat;
		this.thoiGian = thoiGian;
		this.ngay = ngay;
		this.maPhim = maPhim;
		this.maPhong = maPhong;
	}
	public String getMaSuat() {
		return maSuat;
	}
	public void setMaSuat(String maSuat) {
		this.maSuat = maSuat;
	}
	public LocalDateTime getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(LocalDateTime thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public LocalDate getNgay() {
		return ngay;
	}

	public void setNgay(LocalDate ngay) {
		this.ngay = ngay;
	}

	public void xemThongTin() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Suất chiếu [Mã: " + maSuat + ", Thời gian: " + thoiGian.format(formatter) + "]");
    }
}
	
