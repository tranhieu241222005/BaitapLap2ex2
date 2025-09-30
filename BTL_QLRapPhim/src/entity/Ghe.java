package entity;

import java.util.Objects;

public class Ghe {
	private String maGhe;
	private String maSuatChieu;
	public Ghe(String maGhe, String maSuatChieu) {
		super();
		this.maGhe = maGhe;
		this.maSuatChieu = maSuatChieu;
	}
	public String getMaGhe() {
		return maGhe;
	}
	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}
	public String getMaSuatChieu() {
		return maSuatChieu;
	}
	public void setMaSuatChieu(String maSuatChieu) {
		this.maSuatChieu = maSuatChieu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maGhe, maSuatChieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ghe other = (Ghe) obj;
		return Objects.equals(maGhe, other.maGhe) && Objects.equals(maSuatChieu, other.maSuatChieu);
	}

	
}
