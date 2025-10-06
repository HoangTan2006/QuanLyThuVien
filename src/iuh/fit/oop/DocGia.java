package iuh.fit.oop;

import java.time.LocalDate;

public class DocGia {
	private String maDocGia;
	private String hoVaTen;
	private LocalDate ngaySinh;
	private String soDienThoai;
	private GioiTinh gioiTinh;
	private String diaChi;
	
	
	
	public DocGia(String maDocGia, String hoVaTen, LocalDate ngaySinh, String soDienThoai, GioiTinh gioiTinh,
			String diaChi) {
		setMaDocGia(maDocGia);
		setHoVaTen(hoVaTen);
		setNgaySinh(ngaySinh);
		setSoDienThoai(soDienThoai);
		setGioiTinh(gioiTinh);
		setDiaChi(diaChi);
	}

	public String getMaDocGia() {
		return maDocGia;
	}
	
	public String getHoVaTen() {
		return hoVaTen;
	}
	
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	
	public String getSoDienThoai() {
		return soDienThoai;
	}
	
	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}
	
	public String getDiaChi() {
		return diaChi;
	}
	
	public void setMaDocGia(String maDocGia) {
		if (maDocGia == null || maDocGia.isBlank() || maDocGia.length() < 6) {
			throw new IllegalArgumentException("Ma doc gia khong duoc de trong va phai dai hon 6 ky tu");
		}
		this.maDocGia = maDocGia;
	}
	
	public void setHoVaTen(String hoVaTen) {
		if (hoVaTen == null || hoVaTen.isBlank()) {
			throw new IllegalArgumentException("Ma doc gia khong duoc de trong va phai dai hon 6 ky tu");
		}
		this.hoVaTen = hoVaTen;
	}
	
	public void setNgaySinh(LocalDate ngaySinh) {
		if (ngaySinh == null || ngaySinh.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Ngay sinh khong hop le");
		}
		this.ngaySinh = ngaySinh;
	}
	
	public void setSoDienThoai(String soDienThoai) {
		if (soDienThoai == null || soDienThoai.isBlank() || !soDienThoai.matches("\\d+")) {
			throw new IllegalArgumentException("So dien thoai khong hop le");
		}
		this.soDienThoai = soDienThoai;
	}
	
	public void setGioiTinh(GioiTinh gioiTinh) {
		if (gioiTinh == null) {
			throw new IllegalArgumentException("Gioi tinh khong hop le");
		}
		this.gioiTinh = gioiTinh;
	}
	
	public void setDiaChi(String diaChi) {
		if (diaChi == null || diaChi.isBlank()) {
			throw new IllegalArgumentException("Ma doc gia khong duoc de trong va phai dai hon 6 ky tu");
		}
		this.diaChi = diaChi;
	}
	
	@Override
	public String toString() {
		return String.format("| %10 | %20 | %S10 | %S12 | %S5 | %s20 |",
				maDocGia,
				hoVaTen,
				ngaySinh.toString(),
				soDienThoai,
				gioiTinh,
				diaChi);
	}
}
