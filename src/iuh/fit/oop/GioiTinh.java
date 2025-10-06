package iuh.fit.oop;

public enum GioiTinh {
	NAM("Nam"),
	NU("Nu");
	
	private String gioiTinh;

	private GioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
	@Override
	public String toString() {
		return gioiTinh;
	}
}
