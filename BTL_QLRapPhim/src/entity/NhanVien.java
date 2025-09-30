package entity;

public class NhanVien {
	private String maNhanVien;
	private String hoTen;
	private String chucvu;
	private Boolean gioiTinh;
	private String soDienThoai;
	private String email;
	private String diaChi;
	private Boolean trangThai;
	
	public NhanVien(String maNhanVien, String hoTen, String chucvu, Boolean gioiTinh, String soDienThoai, String email,
			String diaChi, Boolean trangThai) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.chucvu = chucvu;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.diaChi = diaChi;
		this.trangThai = trangThai;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		 if (maNhanVien == null || !maNhanVien.matches("^NV[0-9]{3}$")) {
		        throw new IllegalArgumentException("Mã nhân viên phải bắt đầu bằng 'NV' và theo sau là 3 chữ số");
		    }
		    this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		if(hoTen == null || hoTen.matches("^[\\p{L}]+([ ][\\p{L}]+)*$")) {
			throw new IllegalArgumentException( "Tên nhân viên gồm các chữ cái (có thể có dấu) cách nhau bởi khoảng trắng.");
		}
		this.hoTen = hoTen;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		if (chucvu == null || chucvu.trim().isEmpty()) {
	        throw new IllegalArgumentException("Chức vụ không được để trống.");
	    }
	    this.chucvu = chucvu;
	}

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		 if (gioiTinh == null) {
		        throw new IllegalArgumentException("Giới tính không thể để trống.");
		    }
		    this.gioiTinh = gioiTinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		 if (soDienThoai == null || !soDienThoai.matches("^\\d{9}$")) {
		        throw new IllegalArgumentException("Số điện thoại phải gồm 9 chữ số.");
		    }
		    this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		 if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
		        throw new IllegalArgumentException("Địa chỉ email không hợp lệ.");
		    }
		    this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		if(hoTen == null || hoTen.matches("^[\\p{L}0-9\\s,./-]+$")) {
			throw new IllegalArgumentException( "Địa chỉ chỉ chứa chữ (có dấu), số, khoảng trắng và các ký tự như , . / -");
		}
		this.diaChi =diaChi;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		  if (trangThai == null) {
		        throw new IllegalArgumentException("Trạng thái không thể để trống.");
		    }
		    this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", chucvu=" + chucvu + ", gioiTinh="
				+ gioiTinh + ", soDienThoai=" + soDienThoai + ", email=" + email + ", diaChi=" + diaChi + ", trangThai="
				+ trangThai + "]";
	}
	
	
	
}
