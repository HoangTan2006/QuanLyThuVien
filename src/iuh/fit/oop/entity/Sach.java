package iuh.fit.oop.entity;

import java.time.LocalDate;


public class Sach {
	private String maSach;
	private String tenSach;
	private String tenTacGia;
	private LocalDate ngayXB;
	private String nhaXB;
	private TrangThai trangThai;
	

	public Sach(String maSach, String tenSach, String tenTacGia, LocalDate ngayXB, String nhaXB,
			TrangThai trangThai) {
		setMaSach(maSach);
		setTenSach(tenSach);
		setTenTacGia(tenTacGia);
		setNgayXB(ngayXB);
		setNhaXB(nhaXB);
		setTrangThai(trangThai);
	}

	public String getMaSach() {
		return maSach;
	}

	public String getTenSach() {
		return tenSach;
	}
	
	public String getTenTacGia() {
		return tenTacGia;
	}

	public LocalDate getNgayXB() {
		return ngayXB;
	}

	public String getNhaXB() {
		return nhaXB;
	}

	public TrangThai getTrangThai() {
		return trangThai;
	}

	public void setMaSach(String maSach) {
		if(maSach == null || maSach.isBlank() || maSach.length() < 6) {
			throw new IllegalArgumentException("Ma sach khong duoc de trong va phai dai hon 6 ky tu");
		}
		this.maSach = maSach;
	}

	public void setTenSach(String tenSach) {
		if(tenSach == null || tenSach.isBlank()) {
			throw new IllegalArgumentException("Ten sach khong duoc de trong");
		}
		this.tenSach = tenSach;
	}


	public void setTenTacGia(String tenTacGia) {
		if(tenTacGia == null || tenTacGia.isBlank()) {
			throw new IllegalArgumentException("Ten tac gia khong duoc de trong");
		}
		
		this.tenTacGia = tenTacGia;
	}

	public void setNgayXB(LocalDate ngayXB) {
		if (ngayXB == null || ngayXB.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Nam xuat ban khong hop le");
		}
		
		this.ngayXB = ngayXB;
	}

	public void setNhaXB(String nhaXB) {
		if(nhaXB == null || nhaXB.isBlank()) {
			throw new IllegalArgumentException("Ten nha xuat ban khong duoc de trong");
		}
		this.nhaXB = nhaXB;
	}

	public void setTrangThai(TrangThai trangThai) {
		if(trangThai == null) {
			throw new IllegalArgumentException("Trang thai khong hop le");
		}
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		
		return String.format("| %10s | %40s | %25s | %15s | %35s | %10s |",
							maSach,
							tenSach,
							tenTacGia,
							ngayXB.toString(),
							nhaXB,
							trangThai);
	}
}
