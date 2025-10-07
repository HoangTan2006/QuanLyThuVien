package iuh.fit.oop.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import iuh.fit.oop.entity.DocGia;
import iuh.fit.oop.entity.GioiTinh;

public class DocGiaRepository {
private final String pathFileData = "./data/docgia.csv";
	
	private DocGia[] listDocGia;
	private int count;
	
	public DocGiaRepository(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size phai lon hon 0");
		}
		
		this.listDocGia = new DocGia[size];
		this.count = 0;
	}
	
	public void readData() {
		
		try (BufferedReader dataReader = new BufferedReader(new FileReader(pathFileData))
		) {
			
			String line;
			
			while ((line = dataReader.readLine()) != null) {
				if (count == listDocGia.length) extend();
				
				String attributeValues[] = line.split(",");
				
				DocGia docGia = new DocGia(
						attributeValues[0],
						attributeValues[1],
						LocalDate.parse(attributeValues[2]),
						attributeValues[3],
						(attributeValues[4].equals("Nam")) ? GioiTinh.NAM : GioiTinh.NU,
						attributeValues[5]);
				
				listDocGia[count++] = docGia;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void writeData(DocGia[] listDocGia, int quantity) {
		try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(pathFileData))
		) {
			
			for (int i = 0; i < quantity; i++) {
				StringBuilder line = new StringBuilder();
				
				line.append(listDocGia[i].getMaDocGia()).append(",");
				line.append(listDocGia[i].getHoVaTen()).append(",");
				line.append(listDocGia[i].getNgaySinh()).append(",");
				line.append(listDocGia[i].getSoDienThoai().toString()).append(",");
				line.append(listDocGia[i].getGioiTinh()).append(",");
				line.append(listDocGia[i].getDiaChi());
				
				dataWriter.newLine();
				dataWriter.write(line.toString());
			}
			this.count = quantity;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void writeOneRowData(DocGia docGia) {
		try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(pathFileData, true))
		) {
			StringBuilder line = new StringBuilder();
			
			line.append(docGia.getMaDocGia() + ";");
			line.append(docGia.getHoVaTen() + ";");
			line.append(docGia.getNgaySinh().toString() + ";");
			line.append(docGia.getSoDienThoai().toString() + ";");
			line.append(docGia.getGioiTinh() + ";");
			line.append(docGia.getDiaChi());
				
			dataWriter.newLine();
			dataWriter.write(line.toString());
			count++;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void extend() {
		this.listDocGia = Arrays.copyOf(listDocGia, count * 2);
	}
	
	public DocGia findByMaDocGia(String maDocGia) {
		
		for(int i = 0; i < count; i++) {
			if (listDocGia[i].getMaDocGia().equals(maDocGia)) {
				return listDocGia[i];
			}
		}
		return null;
	}
	
	public DocGia[] getlistDocGia() {
		return Arrays.copyOf(listDocGia, count);
	}
	
	public void createDocGia(String maDocGia, String hoVaTen, LocalDate ngaySinh, String soDienThoai, GioiTinh gioiTinh,
			String diaChi) {
		DocGia docGia = new DocGia(maDocGia, hoVaTen, ngaySinh, soDienThoai, gioiTinh, diaChi);
		
		if (count == listDocGia.length) extend();
		
		listDocGia[count++] = docGia;
		writeOneRowData(docGia);
	}
	
	public void updateDocGia(DocGia docGia) {
		for (int i = 0; i < count; i++) {
			if (listDocGia[i].getMaDocGia().equals(docGia.getMaDocGia())) {
				listDocGia[i] = docGia;
			}
		}
		writeData(listDocGia, count);
	}
	
	public void deleteByMaDocGia(String maDocGia) {
		for(int i = 0; i < count; i++) {
			if (listDocGia[i].getMaDocGia().equals(maDocGia)) {
				for (int j = i; j < count - 1; j++) {
					listDocGia[j] = listDocGia[j + 1];
				}
				listDocGia[--count] = null;
				return;
			}
		}
		writeData(listDocGia, count);
	}
}