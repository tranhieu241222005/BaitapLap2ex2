package entity;

public class KhachHang {
	private String hoTen;
	private String soDienThoai;
	public KhachHang(String hoTen, String soDienThoai) {
		super();
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	@Override
	public String toString() {
		return "KhachHang [hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + "]";
	}
	
	public void nhapThongTin(String hoten, String sodt) {
	    this.hoTen = hoten;
	    this.soDienThoai = sodt;
	}

}
