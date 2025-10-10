package view;

import java.time.LocalDate;
import java.util.Scanner;

import iuh.fit.oop.entity.*;
import iuh.fit.oop.repository.*;

public class LibraryMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static SachRepository sachRepo = new SachRepository(10);
    private static DocGiaRepository docGiaRepo = new DocGiaRepository(10);
    private static PhieuMuonSachRepository phieuMuonRepo = new PhieuMuonSachRepository(10);

    public static void main(String[] args) {
        sachRepo.readData();
        docGiaRepo.readData();
        phieuMuonRepo.readData();

        while (true) {
            System.out.println("\n==============================");
            System.out.println("        LIBRARY MENU          ");
            System.out.println("==============================");
            System.out.println("1. Quản lý Sách");
            System.out.println("2. Quản lý Độc giả");
            System.out.println("3. Quản lý Phiếu mượn");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> menuSach();
                case 2 -> menuDocGia();
                case 3 -> menuPhieuMuon();
                case 0 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // ===================== MENU SÁCH =====================
    private static void menuSach() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ SÁCH ---");
            System.out.println("1. Hiển thị tất cả");
            System.out.println("2. Tìm theo mã sách");
            System.out.println("3. Tìm theo tên sách");
            System.out.println("4. Thêm sách mới");
            System.out.println("5. Cập nhật sách");
            System.out.println("6. Xóa sách");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> displaySach(sachRepo.getListSach());
                case 2 -> {
                    System.out.print("Nhập mã sách: ");
                    Sach s = sachRepo.findByMaSach(scanner.nextLine());
                    displaySach((s != null) ? new Sach[]{s} : null);
                }
                case 3 -> {
                    System.out.print("Nhập tên sách: ");
                    Sach[] list = sachRepo.findByTenSach(scanner.nextLine());
                    displaySach(list);
                }
                case 4 -> createSach();
                case 5 -> updateSach();
                case 6 -> {
                    System.out.print("Nhập mã sách cần xóa: ");
                    sachRepo.deleteByMaSach(scanner.nextLine());
                }
                case 0 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displaySach(Sach[] list) {
        if (list == null || list.length == 0) {
            System.out.println("Không có dữ liệu.");
            return;
        }
        System.out.println("+------------+------------------------------------------+---------------------------+-----------------+-----------------------------------+------------+");
        System.out.printf("| %10s | %40s | %25s | %15s | %35s | %10s |\n",
                "Mã sách", "Tên sách", "Tác giả", "Ngày XB", "Nhà XB", "Trạng thái");
        System.out.println("+------------+------------------------------------------+---------------------------+-----------------+-----------------------------------+------------+");
        for (Sach s : list) {
            System.out.println(s);
        }
        System.out.println("+------------+------------------------------------------+---------------------------+-----------------+-----------------------------------+------------+");
    }

    private static void createSach() {
        System.out.print("Mã sách: ");
        String ma = scanner.nextLine();
        System.out.print("Tên sách: ");
        String ten = scanner.nextLine();
        System.out.print("Tác giả: ");
        String tg = scanner.nextLine();
        System.out.print("Ngày XB (yyyy-mm-dd): ");
        LocalDate ngay = LocalDate.parse(scanner.nextLine());
        System.out.print("Nhà XB: ");
        String nxb = scanner.nextLine();
        
        try {
        	sachRepo.createSach(ma, ten, tg, ngay, nxb, TrangThai.CHUA_MUON);
    	} catch (IllegalArgumentException e) {
    		System.err.println(e.getMessage());
    	}
    }

    private static void updateSach() {
        System.out.print("Nhập mã sách cần cập nhật: ");
        String ma = scanner.nextLine();
        Sach sach = sachRepo.findByMaSach(ma);
        if (sach == null) {
            System.out.println("Không tìm thấy sách!");
            return;
        }
        System.out.print("Tên sách mới: ");
        sach.setTenSach(scanner.nextLine());
        
        try {
        	sachRepo.updateSach(sach);
    	} catch (IllegalArgumentException e) {
    		System.err.println(e.getMessage());
    	}
    }

    // ===================== MENU ĐỘC GIẢ =====================
    private static void menuDocGia() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐỘC GIẢ ---");
            System.out.println("1. Hiển thị tất cả");
            System.out.println("2. Tìm theo mã độc giả");
            System.out.println("3. Thêm độc giả");
            System.out.println("4. Cập nhật độc giả");
            System.out.println("5. Xóa độc giả");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> displayDocGia(docGiaRepo.getlistDocGia());
                case 2 -> {
                    System.out.print("Nhập mã độc giả: ");
                    DocGia dg = docGiaRepo.findByMaDocGia(scanner.nextLine());
                    displayDocGia((dg != null) ? new DocGia[]{dg} : null);
                }
                case 3 -> createDocGia();
                case 4 -> updateDocGia();
                case 5 -> {
                    System.out.print("Nhập mã độc giả cần xóa: ");
                    docGiaRepo.deleteByMaDocGia(scanner.nextLine());
                }
                case 0 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displayDocGia(DocGia[] list) {
        if (list == null || list.length == 0) {
            System.out.println("Không có dữ liệu.");
            return;
        }
        System.out.println("+------------+----------------------+------------+----------------+------------+----------------------+");
        System.out.printf("| %10s | %20s | %10s | %14s | %10s | %20s |\n",
                "Mã DG", "Họ tên", "Ngày sinh", "SĐT", "Giới tính", "Địa chỉ");
        System.out.println("+------------+----------------------+------------+----------------+------------+----------------------+");
        for (DocGia dg : list) {
            System.out.println(dg);
        }
        System.out.println("+------------+----------------------+------------+----------------+------------+----------------------+");
    }

    private static void createDocGia() {
        System.out.print("Mã DG: ");
        String ma = scanner.nextLine();
        System.out.print("Họ tên: ");
        String ten = scanner.nextLine();
        System.out.print("Ngày sinh (yyyy-mm-dd): ");
        LocalDate ns = LocalDate.parse(scanner.nextLine());
        System.out.print("SĐT: ");
        String sdt = scanner.nextLine();
        System.out.print("Giới tính (Nam/Nu): ");
        GioiTinh gt = scanner.nextLine().equalsIgnoreCase("Nam") ? GioiTinh.NAM : GioiTinh.NU;
        System.out.print("Địa chỉ: ");
        String dc = scanner.nextLine();
        
        try {
        	docGiaRepo.createDocGia(ma, ten, ns, sdt, gt, dc);
    	} catch (IllegalArgumentException e) {
    		System.err.println(e.getMessage());
    	}
    }

    private static void updateDocGia() {
        System.out.print("Nhập mã độc giả cần cập nhật: ");
        String ma = scanner.nextLine();
        DocGia dg = docGiaRepo.findByMaDocGia(ma);
        if (dg == null) {
            System.out.println("Không tìm thấy độc giả!");
            return;
        }
        System.out.print("Tên mới: ");
        dg.setHoVaTen(scanner.nextLine());
        
        try {
        	docGiaRepo.updateDocGia(dg);
    	} catch (IllegalArgumentException e) {
    		System.err.println(e.getMessage());
    	}
    }

    // ===================== MENU PHIẾU MƯỢN =====================
    private static void menuPhieuMuon() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ PHIẾU MƯỢN ---");
            System.out.println("1. Hiển thị tất cả");
            System.out.println("2. Tìm theo mã phiếu");
            System.out.println("3. Tìm theo mã độc giả");
            System.out.println("4. Tìm theo mã sách");
            System.out.println("5. Tạo phiếu mượn");
            System.out.println("6. Trả sách");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> displayPhieuMuon(phieuMuonRepo.getListPhieuMuonSach());
                case 2 -> {
                    System.out.print("Nhập mã phiếu: ");
                    PhieuMuonSach pm = phieuMuonRepo.findByMaPhieuMuon(scanner.nextLine());
                    displayPhieuMuon((pm != null) ? new PhieuMuonSach[]{pm} : null);
                }
                case 3 -> {
                    System.out.print("Nhập mã DG: ");
                    displayPhieuMuon(phieuMuonRepo.findByMaDocGia(scanner.nextLine()));
                }
                case 4 -> {
                    System.out.print("Nhập mã sách: ");
                    displayPhieuMuon(phieuMuonRepo.findByMaSach(scanner.nextLine()));
                }
                case 5 -> createPhieuMuon();
                case 6 -> returnASach();
                case 0 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displayPhieuMuon(PhieuMuonSach[] list) {
        if (list == null || list.length == 0) {
            System.out.println("Không có dữ liệu.");
            return;
        }
        System.out.println("+-----------------+------------+------------+-----------------+-----------------+");
        System.out.printf("| %15s | %10s | %10s | %15s | %15s |\n",
                "Mã phiếu", "Mã DG", "Mã sách", "Ngày mượn", "Ngày trả");
        System.out.println("+-----------------+------------+------------+-----------------+-----------------+");
        for (PhieuMuonSach pm : list) {
            System.out.println(pm);
        }
        System.out.println("+-----------------+------------+------------+-----------------+-----------------+");
    }

    private static void createPhieuMuon() {
        System.out.print("Mã phiếu: ");
        String maPhieu = scanner.nextLine();
        System.out.print("Mã DG: ");
        String maDG = scanner.nextLine();
        System.out.print("Mã sách: ");
        String maSach = scanner.nextLine();
        System.out.print("Ngày mượn (yyyy-mm-dd): ");
        LocalDate nm = LocalDate.parse(scanner.nextLine());
        System.out.print("Ngày trả (yyyy-mm-dd): ");
        LocalDate nt = LocalDate.parse(scanner.nextLine());
        
        try {
        	phieuMuonRepo.createPhieuMuonSach(maPhieu, maDG, maSach, nm, nt);
        } catch (IllegalArgumentException e) {
        	System.err.print(e.getMessage());
        }
    }
    
    private static void returnASach() {
    	System.out.print("Mã phiếu: ");
        String maPhieu = scanner.nextLine();
        try {
        	phieuMuonRepo.returnASach(maPhieu);
        } catch (IllegalArgumentException e) {
        	System.err.print(e.getMessage());
        }
        
    }
}
