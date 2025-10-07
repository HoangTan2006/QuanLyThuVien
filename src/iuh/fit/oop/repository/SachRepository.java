package iuh.fit.oop.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import iuh.fit.oop.entity.Sach;
import iuh.fit.oop.entity.TrangThai;

public class SachRepository {
	private final String pathFileData = "./data/sach.csv";
	
	private Sach[] listSach;
	private int count;
	
	public SachRepository(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size phai lon hon 0");
		}
		
		this.listSach = new Sach[size];
		this.count = 0;
	}
	
	public void readData() {
		
		try (BufferedReader dataReader = new BufferedReader(new FileReader(pathFileData))
		) {
			
			String line;
			
			while ((line = dataReader.readLine()) != null) {
				if (count == listSach.length) extend();
				
				String attributeValues[] = line.split(",");
				
				Sach sach = new Sach(
						attributeValues[0],
						attributeValues[1],
						attributeValues[2],
						LocalDate.parse(attributeValues[3]),
						attributeValues[4],
						(attributeValues[5].equals("Chưa mượn")) ? TrangThai.CHUA_MUON : TrangThai.DA_MUON);
				
				listSach[count++] = sach;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void writeData(Sach[] listSach, int quantity) {
		try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(pathFileData))
		) {
			
			for (int i = 0; i < quantity; i++) {
				StringBuilder line = new StringBuilder();
				
				line.append(listSach[i].getMaSach()).append(",");
				line.append(listSach[i].getTenSach()).append(",");
				line.append(listSach[i].getTenTacGia()).append(",");
				line.append(listSach[i].getNgayXB().toString()).append(",");
				line.append(listSach[i].getNhaXB()).append(",");
				line.append(listSach[i].getTrangThai());
				
				dataWriter.newLine();
				dataWriter.write(line.toString());
			}
			this.count = quantity;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void writeOneRowData(Sach sach) {
		try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(pathFileData, true))
		) {
			StringBuilder line = new StringBuilder();
			
			line.append(sach.getMaSach() + ";");
			line.append(sach.getTenSach() + ";");
			line.append(sach.getTenTacGia() + ";");
			line.append(sach.getNgayXB().toString() + ";");
			line.append(sach.getNhaXB() + ";");
			line.append(sach.getTrangThai());
				
			dataWriter.newLine();
			dataWriter.write(line.toString());
			count++;
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void extend() {
		this.listSach = Arrays.copyOf(listSach, count * 2);
	}
	
	public Sach findByMaSach(String maSach) {
		
		for(int i = 0; i < count; i++) {
			if (listSach[i].getMaSach().equals(maSach)) {
				return listSach[i];
			}
		}
		return null;
	}
	
	public Sach[] findByTenSach(String tenSach) {
		Sach[] foundSach = new Sach[count];
		int quantityFound = 0;
		
		for(int i = 0; i < count; i++) {
			if (listSach[i].getTenSach().toLowerCase().contains(tenSach.toLowerCase())) {
				foundSach[quantityFound++] = listSach[i];
			}
		}
		return (quantityFound != 0) ? Arrays.copyOf(foundSach, quantityFound) : null;
	}
	
	public Sach[] getListSach() {
		return Arrays.copyOf(listSach, count);
	}
	
	public void createSach(String maSach, String tenSach, String tenTacGia, LocalDate ngayXB, String nhaXB,
			TrangThai trangThai) {
		Sach sach = new Sach(maSach, tenSach, tenTacGia, ngayXB, nhaXB, trangThai);
		
		if (count == listSach.length) extend();
		
		listSach[count++] = sach;
		writeOneRowData(sach);
	}
	
	public void updateSach(Sach sach) {
		for (int i = 0; i < count; i++) {
			if (listSach[i].getMaSach().equals(sach.getMaSach())) {
				listSach[i] = sach;
			}
		}
		writeData(listSach, count);
	}
	
	public void deleteByMaSach(String maSach) {
		for(int i = 0; i < count; i++) {
			if (listSach[i].getMaSach().equals(maSach)) {
				for (int j = i; j < count - 1; j++) {
					listSach[j] = listSach[j + 1];
				}
				listSach[--count] = null;
				return;
			}
		}
		writeData(listSach, count);
	}
}
