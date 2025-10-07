package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import iuh.fit.oop.entity.Sach;
import iuh.fit.oop.entity.TrangThai;

public class TestSach {


    @ParameterizedTest
    @CsvSource({
        // maSach, tenSach, tenTacGia, ngayXB, nhaXB, trangThai, expected
    	"S00001,Lập Trình Java Cơ Bản,Nguyễn Văn An,2020-03-15,NXB Giáo Dục Việt Nam,CHUA_MUON,true",
    	"S00002,Cấu Trúc Dữ Liệu và Giải Thuật,Trần Thị Bình,2019-07-10,NXB Khoa Học Kỹ Thuật,DA_MUON,true",
    	"S00003,Học Máy Nhập Môn,Phạm Minh Đức,2021-01-22,NXB Thống Kê,CHUA_MUON,true",
    	"S004,Trí Tuệ Nhân Tạo,Lê Hồng Phong,2018-11-05,NXB Công Nghệ Thông Tin,DA_MUON,false",
    	"S5,Cơ Sở Dữ Liệu,Vũ Thị Lan,2022-05-18,NXB Đại Học Quốc Gia,CHUA_MUON,false",
    })
    void testConstructor(String maSach, String tenSach, String tenTacGia, String ngayXB,
                         String nhaXB, TrangThai trangThai, boolean expected) {

        if (expected) {
            Sach s = new Sach(maSach, tenSach, tenTacGia, LocalDate.parse(ngayXB),
                    nhaXB, trangThai);
            assertNotNull(s);
            assertEquals(maSach, s.getMaSach());
            assertEquals(tenSach, s.getTenSach());
            assertEquals(tenTacGia, s.getTenTacGia());
            assertEquals(LocalDate.parse(ngayXB), s.getNgayXB());
            assertEquals(nhaXB, s.getNhaXB());
            assertEquals(trangThai, s.getTrangThai());
        } else {
        	assertThrows(IllegalArgumentException.class, () -> {
                new Sach(maSach, tenSach, tenTacGia, LocalDate.parse(ngayXB), nhaXB, trangThai);
            });
        }
    }


    @ParameterizedTest
    @CsvSource({
        "S00001, true",
        "A1, false",
        "'', false",
        "'   ', false",
        "null, false"
    })
    void testSetMaSach(String maSach, boolean expected) {
        Sach s = new Sach("S99999", "Java", "Nguyễn A", LocalDate.of(2020,1,1), "NXB Trẻ", TrangThai.CHUA_MUON);
        if (expected) {
            s.setMaSach(maSach);
            assertEquals(maSach, s.getMaSach());
        } else {
            assertThrows(IllegalArgumentException.class, () -> s.setMaSach(maSach));
        }
    }


    @ParameterizedTest
    @CsvSource({
        "Lập trình Java, true",
        "'', false",
        "'   ', false",
    })
    void testSetTenSach(String tenSach, boolean expected) {
        Sach s = new Sach("S99999", "Java", "Nguyễn A", LocalDate.of(2020,1,1), "NXB Trẻ", TrangThai.CHUA_MUON);
        if (expected) {
            s.setTenSach(tenSach);
            assertEquals(tenSach, s.getTenSach());
        } else {
            assertThrows(IllegalArgumentException.class, () -> s.setTenSach(tenSach));
        }
    }


    @ParameterizedTest
    @CsvSource({
        "Nguyễn Văn An, true",
        "'', false",
        "'   ', false",
    })
    void testSetTenTacGia(String tenTacGia, boolean expected) {
        Sach s = new Sach("S99999", "Java", "Nguyễn A", LocalDate.of(2020,1,1), "NXB Trẻ", TrangThai.CHUA_MUON);
        if (expected) {
            s.setTenTacGia(tenTacGia);
            assertEquals(tenTacGia, s.getTenTacGia());
        } else {
            assertThrows(IllegalArgumentException.class, () -> s.setTenTacGia(tenTacGia));
        }
    }


    @ParameterizedTest
    @CsvSource({
        "2020-01-01, true",
        "2030-01-01, false",
    })
    void testSetNgayXB(String ngayXB, boolean expected) {
        Sach s = new Sach("S99999", "Java", "Nguyễn A", LocalDate.of(2020,1,1), "NXB Trẻ", TrangThai.CHUA_MUON);
        if (expected) {
            s.setNgayXB(LocalDate.parse(ngayXB));
            assertEquals(LocalDate.parse(ngayXB), s.getNgayXB());
        } else {
            assertThrows(IllegalArgumentException.class, () -> s.setNgayXB(ngayXB == null ? null : LocalDate.parse(ngayXB)));
        }
    }


    @ParameterizedTest
    @CsvSource({
        "NXB Trẻ, true",
        "'', false",
        "'   ', false",
    })
    void testSetNhaXB(String nhaXB, boolean expected) {
        Sach s = new Sach("S99999", "Java", "Nguyễn A", LocalDate.of(2020,1,1), "NXB Trẻ", TrangThai.CHUA_MUON);
        if (expected) {
            s.setNhaXB(nhaXB);
            assertEquals(nhaXB, s.getNhaXB());
        } else {
            assertThrows(IllegalArgumentException.class, () -> s.setNhaXB(nhaXB));
        }
    }


    @ParameterizedTest
    @CsvSource({
        "CHUA_MUON, true",
        "DA_MUON, true",
        "null, false"
    })
    void testSetTrangThai(String trangThai, boolean expected) {
        Sach s = new Sach("S99999", "Java", "Nguyễn A", LocalDate.of(2020,1,1), "NXB Trẻ", TrangThai.CHUA_MUON);
        if (expected) {
            s.setTrangThai(TrangThai.valueOf(trangThai));
            assertEquals(TrangThai.valueOf(trangThai), s.getTrangThai());
        } else {
            assertThrows(IllegalArgumentException.class, () -> s.setTrangThai(null));
        }
    }
}
