package iuh.fit.oop.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import iuh.fit.oop.entity.PhieuMuonSach;
import iuh.fit.oop.entity.Sach;
import iuh.fit.oop.entity.TrangThai;

public class PhieuMuonSachRepository {
	private final String pathFileData = "./data/phieumuonsach.csv";
	
	private PhieuMuonSach[] listPhieuMuonSach;
	private int count;
	
	public PhieuMuonSachRepository(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size phai lon hon 0");
		}
		
		this.listPhieuMuonSach = new PhieuMuonSach[size];
		this.count = 0;
	}
	
	public void readData() {
		
		try (BufferedReader dataReader = new BufferedReader(new FileReader(pathFileData))
		) {
			
			String line;
			
			while ((line = dataReader.readLine()) != null) {
				if (count == listPhieuMuonSach.length) extend();
				
				String attributeValues[] = line.split(",");
				
				PhieuMuonSach phieuMuonSach = new PhieuMuonSach(
						attributeValues[0],
						attributeValues[1],
						attributeValues[2],
						LocalDate.parse(attributeValues[3]),
						LocalDate.parse(attributeValues[4]));
				
				listPhieuMuonSach[count++] = phieuMuonSach;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void writeData(PhieuMuonSach[] listPhieuMuonSach, int quantity) {
		try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(pathFileData))
		) {
			
			for (int i = 0; i < quantity; i++) {
				StringBuilder line = new StringBuilder();
				
				line.append(listPhieuMuonSach[i].getMaPhieuMuon()).append(",");
				line.append(listPhieuMuonSach[i].getMaDocGia()).append(",");
				line.append(listPhieuMuonSach[i].getMaSach()).append(",");
				line.append(listPhieuMuonSach[i].getNgayMuon()).append(",");
				line.append(listPhieuMuonSach[i].getNgayTra());
				
				dataWriter.write(line.toString());
				dataWriter.newLine();
			}
			this.count = quantity;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void writeOneRowData(PhieuMuonSach phieuMuonSach) {
		try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(pathFileData, true))
		) {
			StringBuilder line = new StringBuilder();
			
			line.append(phieuMuonSach.getMaPhieuMuon()).append(",");
			line.append(phieuMuonSach.getMaDocGia()).append(",");
			line.append(phieuMuonSach.getMaSach()).append(",");
			line.append(phieuMuonSach.getNgayMuon()).append(",");
			line.append(phieuMuonSach.getNgayTra());
				
			dataWriter.newLine();
			dataWriter.write(line.toString());
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void extend() {
		this.listPhieuMuonSach = Arrays.copyOf(listPhieuMuonSach, count * 2);
	}
	
	private boolean isExists(String maPhieuMuon) {
		for (int i = 0; i < count; i++) {
			if (listPhieuMuonSach[i].getMaPhieuMuon().equals(maPhieuMuon)) {
				return true;
			}
		}
		return false;
	}
	
	public PhieuMuonSach findByMaPhieuMuon(String maPhieuMuon) {
		for (int i = 0; i < count; i++) {
			if (listPhieuMuonSach[i].getMaPhieuMuon().equals(maPhieuMuon)) {
				return listPhieuMuonSach[i];
			}
		}
		return null;
	}
	
	public PhieuMuonSach[] getListPhieuMuonSach() {
		return Arrays.copyOf(listPhieuMuonSach, count);
	}
	
	public PhieuMuonSach[] findByMaDocGia(String maDocGia) {
		PhieuMuonSach[] foundPhieuMuonSach = new PhieuMuonSach[count];
		int quantityFound = 0;
		
		for (int i = 0; i < count; i++) {
			if (listPhieuMuonSach[i].getMaDocGia().equals(maDocGia)) {
				foundPhieuMuonSach[quantityFound++] = listPhieuMuonSach[i];
			}
		}
		return (quantityFound > 0) ? Arrays.copyOf(foundPhieuMuonSach, quantityFound) : null;
	}
	
	public PhieuMuonSach[] findByMaSach(String maSach) {
		PhieuMuonSach[] foundPhieuMuonSach = new PhieuMuonSach[count];
		int quantityFound = 0;
		
		for (int i = 0; i < count; i++) {
			if (listPhieuMuonSach[i].getMaSach().equals(maSach)) {
				foundPhieuMuonSach[quantityFound++] = listPhieuMuonSach[i];
			}
		}
		return (quantityFound > 0) ? Arrays.copyOf(foundPhieuMuonSach, quantityFound) : null;
	}
	
	public void createPhieuMuonSach(String maPhieuMuon, String maDocGia, String maSach, LocalDate ngayMuon, LocalDate ngayTra) {
		if (isExists(maPhieuMuon)) {
			throw new IllegalArgumentException("Ma phieu muon da ton tai");
		}
		SachRepository sachRepository = new SachRepository(100);
		sachRepository.readData();
		
		Sach sach = sachRepository.findByMaSach(maSach);
		
		if (sach == null || sach.getTrangThai().equals(TrangThai.DA_MUON)) {
			throw new IllegalArgumentException("Sach da co nguoi muon");
		}

		sach.setTrangThai(TrangThai.DA_MUON);
		sachRepository.updateSach(sach);
		
		if (count == listPhieuMuonSach.length) extend();
		
		PhieuMuonSach phieuMuonSach = new PhieuMuonSach(maPhieuMuon, maDocGia, maSach, ngayMuon, ngayTra);
		
		listPhieuMuonSach[count++] = phieuMuonSach;
		writeOneRowData(phieuMuonSach);
	}
	
	public void returnASach(String maPhieuMuon) {
		if (maPhieuMuon == null || maPhieuMuon.isBlank()) {
			throw new IllegalArgumentException("Ma phieu muon khong hop le");
		}
		
		PhieuMuonSach phieuMuonSach = findByMaPhieuMuon(maPhieuMuon);
		if (phieuMuonSach != null) {
			SachRepository sachRepository = new SachRepository(100);
			sachRepository.readData();
			Sach sach = sachRepository.findByMaSach(phieuMuonSach.getMaSach());
			
			if (sach != null) {
				sach.setTrangThai(TrangThai.CHUA_MUON);
				sachRepository.updateSach(sach);
			}
		}
	}
}
