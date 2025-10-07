package iuh.fit.oop.entity;

import java.time.LocalDate;

public class PhieuMuonSach {
	private String maPhieuMuon;
	private String maDocGia;
	private String maSach;
	private LocalDate ngayMuon;
	private LocalDate ngayTra;
	
	public PhieuMuonSach(String maPhieuMuon, String maDocGia, String maSach, LocalDate ngayMuon, LocalDate ngayTra) {
		this.maPhieuMuon = maPhieuMuon;
		this.maDocGia = maDocGia;
		this.maSach = maSach;
		this.ngayMuon = ngayMuon;
		this.ngayTra = ngayTra;
	}

	public String getMaPhieuMuon() {
		return maPhieuMuon;
	}

	public String getMaDocGia() {
		return maDocGia;
	}

	public String getMaSach() {
		return maSach;
	}

	public LocalDate getNgayMuon() {
		return ngayMuon;
	}

	public LocalDate getNgayTra() {
		return ngayTra;
	}

	public void setMaPhieuMuon(String maPhieuMuon) {
		if (maPhieuMuon == null || maPhieuMuon.isBlank() || maPhieuMuon.length() < 6) {
			throw new IllegalArgumentException("Ma phieu muon khong duoc trong va phai dai hon 6 ky tu");
		}
		this.maPhieuMuon = maPhieuMuon;
	}

	public void setMaDocGia(String maDocGia) {
		if (maDocGia == null || maDocGia.isBlank() || maDocGia.length() < 6) {
			throw new IllegalArgumentException("Ma doc gia khong duoc de trong va phai dai hon 6 ky tu");
		}
		this.maDocGia = maDocGia;
	}

	public void setMaSach(String maSach) {
		if(maSach == null || maSach.isBlank() || maSach.length() < 6) {
			throw new IllegalArgumentException("Ma sach khong duoc de trong va phai dai hon 6 ky tu");
		}
		this.maSach = maSach;
	}

	public void setNgayMuon(LocalDate ngayMuon) {
		if (ngayMuon == null || ngayMuon.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Ngay muon khong hop le");
		}
		this.ngayMuon = ngayMuon;
	}

	public void setNgayTra(LocalDate ngayTra) {
		if (ngayTra == null || ngayTra.isBefore(this.ngayMuon)) {
			throw new IllegalArgumentException("Ngay tra khong hop le");
		}
		this.ngayTra = ngayTra;
	}
	
	@Override
	public String toString() {
		return String.format("|| %15s || %10s || %10s || %15s || %15s ||",
				maPhieuMuon,
				maDocGia,
				maSach,
				ngayMuon,
				ngayTra);
	}
}
