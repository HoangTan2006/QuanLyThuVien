package iuh.fit.oop.entity;

public enum TrangThai {
	DA_MUON("Đã mượn"),
	CHUA_MUON("Chưa mượn");
	
	private String trangThai;

	private TrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return trangThai;
	}
}
