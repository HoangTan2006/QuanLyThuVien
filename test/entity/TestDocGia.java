package entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import iuh.fit.oop.entity.DocGia;
import iuh.fit.oop.entity.GioiTinh;

public class TestDocGia {

    @ParameterizedTest
    @CsvSource({
        // maDocGia, hoVaTen, ngaySinh, soDienThoai, gioiTinh, diaChi, expected
        "DG0001,Nguyen Van A,2000-01-01,0123456789,NAM,TP HCM,true",
        "DG0002,Tran Thi B,1998-05-20,0987654321,NU,Ha Noi,true",
        "D01,Nguyen Van C,2001-03-15,0123456789,NAM,Da Nang,false",
        "DG0003,'',2000-01-01,0123456789,NAM,TP HCM,false",
        "DG0004,Nguyen Van D,2030-01-01,0123456789,NAM,TP HCM,false",
        "DG0005,Nguyen Van E,2000-01-01,abc123,NAM,TP HCM,false",
        "DG0006,Nguyen Van F,2000-01-01,0123456789,null,TP HCM,false",
        "DG0007,Nguyen Van G,2000-01-01,0123456789,NAM,'',false"
    })
    void testConstructor(String maDocGia, String hoVaTen, String ngaySinh,
                         String soDienThoai, String gioiTinh, String diaChi,
                         boolean expected) {

        if (expected) {
            DocGia dg = new DocGia(maDocGia, hoVaTen, LocalDate.parse(ngaySinh),
                    soDienThoai, GioiTinh.valueOf(gioiTinh), diaChi);
            assertNotNull(dg);
            assertEquals(maDocGia, dg.getMaDocGia());
            assertEquals(hoVaTen, dg.getHoVaTen());
            assertEquals(LocalDate.parse(ngaySinh), dg.getNgaySinh());
            assertEquals(soDienThoai, dg.getSoDienThoai());
            assertEquals(GioiTinh.valueOf(gioiTinh), dg.getGioiTinh());
            assertEquals(diaChi, dg.getDiaChi());
        } else {
            assertThrows(IllegalArgumentException.class, () -> {
                new DocGia(maDocGia, hoVaTen,
                        ngaySinh == null ? null : LocalDate.parse(ngaySinh),
                        soDienThoai,
                        gioiTinh == null ? null : GioiTinh.valueOf(gioiTinh),
                        diaChi);
            });
        }
    }

    @ParameterizedTest
    @CsvSource({
        "DG0001,true",
        "'',false",
        "'   ',false",
        "123,false",
        "null,false"
    })
    void testSetMaDocGia(String maDocGia, boolean expected) {
        DocGia dg = new DocGia("DG9999", "Nguyen Van A",
                LocalDate.of(2000, 1, 1), "0123456789",
                GioiTinh.NAM, "TP HCM");

        if (expected) {
            dg.setMaDocGia(maDocGia);
            assertEquals(maDocGia, dg.getMaDocGia());
        } else {
            assertThrows(IllegalArgumentException.class, () -> dg.setMaDocGia(maDocGia));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "Nguyen Van A,true",
        "'',false",
        "'   ',false"
    })
    void testSetHoVaTen(String hoVaTen, boolean expected) {
        DocGia dg = new DocGia("DG9999", "A",LocalDate.of(2000, 1, 1), "0123456789",
                GioiTinh.NAM, "TP HCM");
        if (expected) {
            dg.setHoVaTen(hoVaTen);
            assertEquals(hoVaTen, dg.getHoVaTen());
        } else {
            assertThrows(IllegalArgumentException.class, () -> dg.setHoVaTen(hoVaTen));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "2000-01-01,true",
        "2030-01-01,false"
    })
    void testSetNgaySinh(String ngaySinh, boolean expected) {
        DocGia dg = new DocGia("DG9999", "Nguyen Van A",
                LocalDate.of(2000, 1, 1), "0123456789",
                GioiTinh.NAM, "TP HCM");
        if (expected) {
            dg.setNgaySinh(LocalDate.parse(ngaySinh));
            assertEquals(LocalDate.parse(ngaySinh), dg.getNgaySinh());
        } else {
            assertThrows(IllegalArgumentException.class,
                    () -> dg.setNgaySinh(LocalDate.parse(ngaySinh)));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "0123456789,true",
        "'',false",
        "'   ',false",
        "abc123,false"
    })
    void testSetSoDienThoai(String sdt, boolean expected) {
        DocGia dg = new DocGia("DG9999", "Nguyen Van A",
                LocalDate.of(2000, 1, 1), "0123456789",
                GioiTinh.NAM, "TP HCM");
        if (expected) {
            dg.setSoDienThoai(sdt);
            assertEquals(sdt, dg.getSoDienThoai());
        } else {
            assertThrows(IllegalArgumentException.class, () -> dg.setSoDienThoai(sdt));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "NAM,true",
        "NU,true",
        "null,false"
    })
    void testSetGioiTinh(String gioiTinh, boolean expected) {
        DocGia dg = new DocGia("DG9999", "Nguyen Van A",
                LocalDate.of(2000, 1, 1), "0123456789",
                GioiTinh.NAM, "TP HCM");
        if (expected) {
            dg.setGioiTinh(GioiTinh.valueOf(gioiTinh));
            assertEquals(GioiTinh.valueOf(gioiTinh), dg.getGioiTinh());
        } else {
            assertThrows(IllegalArgumentException.class, () -> dg.setGioiTinh(null));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "TP HCM,true",
        "'',false",
        "'   ',false"
    })
    void testSetDiaChi(String diaChi, boolean expected) {
        DocGia dg = new DocGia("DG9999", "Nguyen Van A",
                LocalDate.of(2000, 1, 1), "0123456789",
                GioiTinh.NAM, "TP HCM");
        if (expected) {
            dg.setDiaChi(diaChi);
            assertEquals(diaChi, dg.getDiaChi());
        } else {
            assertThrows(IllegalArgumentException.class, () -> dg.setDiaChi(diaChi));
        }
    }
}