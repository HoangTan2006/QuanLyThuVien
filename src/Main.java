import java.time.LocalDate;

import iuh.fit.oop.entity.DocGia;
import iuh.fit.oop.entity.Sach;
import iuh.fit.oop.entity.TrangThai;
import iuh.fit.oop.repository.DocGiaRepository;
import iuh.fit.oop.repository.SachRepository;

public class Main {
	public static void main(String[] args) {
		SachRepository sachRepository = new SachRepository(10);
		DocGiaRepository docGiaRepository = new DocGiaRepository(10);
		
		sachRepository.readData();
		docGiaRepository.readData();
		
		DocGia[] docGia = docGiaRepository.getlistDocGia();
		DocGia docGia1 = docGiaRepository.findByMaDocGia("DG0001");
		System.out.println(docGia1);
//		Sach[] sach = sachRepository.findByTenSach("Giải thuật");
		
//		for (DocGia dg : docGia) {
//			System.out.println(dg);
//		}
		
//		for (Sach s : sach) {
//			System.out.println(s);
//		}
	}
}
