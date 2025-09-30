package entity;

import javax.management.RuntimeErrorException;

public class Phim{
	private String maPhim;
	private String tenPhim;
	private String theloai;
	private String quocGia;
	private int thoiLuong;
	
	
	
	public Phim(String maPhim, String tenPhim, String theloai, String quocGia, int thoiLuong) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.theloai = theloai;
		this.quocGia = quocGia;
		this.thoiLuong = thoiLuong;
	}

	

	public String getMaPhim() {
		return maPhim;
	}



	public void setMaPhim(String maPhim) {
		if (maPhim.isEmpty() || maPhim == null)
			throw new RuntimeException("Mã phim không được rỗng");
		this.maPhim = maPhim;
	}



	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}



	public String getTheloai() {
		return theloai;
	}



	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}



	public String getQuocGia() {
		return quocGia;
	}



	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}



	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", maPhim, tenPhim, theloai,quocGia, thoiLuong);
	}

}
	
