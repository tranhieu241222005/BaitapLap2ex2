-- Tạo database và sử dụng
DROP DATABASE IF EXISTS QuanLyRapPhim
DROP TABLE IF EXISTS SuatChieu;
CREATE DATABASE QuanLyRapPhim
GO
USE QuanLyRapPhim;
GO
--Bảng tài khoản
CREATE TABLE TaiKhoan (
    id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(50) NOT NULL UNIQUE CHECK (username <> ''),
    password VARCHAR(100) NOT NULL CHECK (password <> ''),
    name NVARCHAR(100) NOT NULL CHECK (name <> '')
);
INSERT INTO TaiKhoan (username, password, name)
VALUES ('admin', 'admin123', N'Nguyễn Phương Nhật Hào');
select * from TaiKhoan
-- Bảng NhanVien
CREATE TABLE NhanVien (
    maNhanVien VARCHAR(10) PRIMARY KEY 
        CHECK (maNhanVien LIKE 'NV[0-9][0-9][0-9]'),
    
    hoTen NVARCHAR(100) NOT NULL 
        CHECK (hoTen <> ''),
    
    chucVu NVARCHAR(50) NOT NULL 
        CHECK (chucVu IN (N'Quản lý', N'Nhân viên')),
    
    gioiTinh BIT NOT NULL,
    
    soDienThoai VARCHAR(15) 
        CHECK (LEN(soDienThoai) = 10 AND soDienThoai NOT LIKE '%[^0-9]%'),
    
    email VARCHAR(100) 
        CHECK (email LIKE '_%@_%._%'),
    
    diaChi NVARCHAR(225) 
        CHECK (LTRIM(RTRIM(diaChi)) <> ''),
    
    trangThai BIT NOT NULL
);

INSERT INTO NhanVien (maNhanVien, hoTen, chucVu, gioiTinh, soDienThoai, email, diaChi, trangThai) VALUES
('NV001', N'Nguyễn Văn A', N'quản lý', 1, '0912345678', 'a.nguyen@example.com', N'Hà Nội', 1),
('NV002', N'Lê Thị B', N'nhân viên', 0, '0923456789', 'b.le@example.com', N'Hồ Chí Minh', 1),
('NV003', N'Trần Văn C', N'nhân viên', 1, '0934567890', 'c.tran@example.com', N'Đà Nẵng', 1),
('NV004', N'Phạm Thị D', N'quản lý', 0, '0945678901', 'd.pham@example.com', N'Cần Thơ', 1),
('NV005', N'Hoàng Văn E', N'nhân viên', 1, '0956789012', 'e.hoang@example.com', N'Huế', 1),
('NV006', N'Vũ Thị F', N'nhân viên', 0, '0967890123', 'f.vu@example.com', N'Quảng Ninh', 1),
('NV007', N'Đỗ Văn G', N'nhân viên', 1, '0978901234', 'g.do@example.com', N'Nghệ An', 1),
('NV008', N'Ngô Thị H', N'quản lý', 0, '0989012345', 'h.ngo@example.com', N'Bình Dương', 1),
('NV009', N'Bùi Văn I', N'nhân viên', 1, '0990123456', 'i.bui@example.com', N'Thái Bình', 1),
('NV010', N'Tạ Thị K', N'nhân viên', 0, '0901234567', 'k.ta@example.com', N'Lâm Đồng', 1);


-- Phim
CREATE TABLE Phim (
    maPhim VARCHAR(10) PRIMARY KEY 
        CHECK (maPhim LIKE 'P[0-9][0-9][0-9]'),
    
    tenPhim NVARCHAR(100) NOT NULL 
        CHECK (tenPhim <> ''),
    
    theLoai NVARCHAR(50) NOT NULL,
    
    quocGia VARCHAR(30) NOT NULL 
        CHECK (quocGia LIKE '[A-Z]%'),
    
    thoiLuong INT NOT NULL 
        CHECK (thoiLuong > 0)
);


INSERT INTO Phim VALUES ('P001', N'Phim 1', N'Hành động','American', 91);
INSERT INTO Phim VALUES ('P002', N'Phim 2', N'Hài hước','Thailand', 104);
INSERT INTO Phim VALUES ('P003', N'Phim 3', N'Tình cảm','Korena', 141);
INSERT INTO Phim VALUES ('P004', N'Phim 4', N'Tình cảm','Korena', 105);
INSERT INTO Phim VALUES ('P005', N'Phim 5', N'Hành động','American', 146);
INSERT INTO Phim VALUES ('P006', N'Phim 6', N'Tình cảm','VietNam', 103);
INSERT INTO Phim VALUES ('P007', N'Phim 7', N'Tình cảm','Korena', 110);
INSERT INTO Phim VALUES ('P008', N'Phim 8', N'Hành động','Italia', 94);
INSERT INTO Phim VALUES ('P009', N'Phim 9', N'Tình cảm','Korena', 137);
INSERT INTO Phim VALUES ('P010', N'Phim 10', N'Tình cảm','VietNam', 139);

-- Bảng Phong
CREATE TABLE Phong (
    maPhong VARCHAR(10) PRIMARY KEY 
        CHECK (maPhong LIKE 'P[0-9][0-9][0-9]'),
    
    tenPhong NVARCHAR(50) NOT NULL 
        CHECK (tenPhong <> '')
);


INSERT INTO Phong (maPhong, tenPhong) VALUES
('P001', N'Phòng 1'),
('P002', N'Phòng 2'),
('P003', N'Phòng 3');
-- Bảng SuatChieu
CREATE TABLE SuatChieu (
    maSuatChieu VARCHAR(10) PRIMARY KEY 
        CHECK (maSuatChieu LIKE 'SC[0-9][0-9][0-9]'),
    
    thoiGian DATETIME NOT NULL,
    ngay DATE NOT NULL,
    maPhim VARCHAR(10),
    maPhong VARCHAR(10),

    CONSTRAINT FK_SuatChieu_Phim FOREIGN KEY (maPhim) REFERENCES Phim(maPhim),
    CONSTRAINT FK_Phong_SuatChieu FOREIGN KEY (maPhong) REFERENCES Phong(maPhong)
);


INSERT INTO SuatChieu(maSuatCHieu, thoiGian, ngay, maPhong, maPhim)  VALUES 
('SC001', '2025-05-02 19:00:00', '2025-05-02','P001','P001'),
('SC002', '2025-05-03 19:00:00', '2025-05-03','P001','P001'),
('SC003', '2025-05-04 19:00:00', '2025-05-04','P002','P002'),
('SC004', '2025-05-05 19:00:00', '2025-05-05','P002','P002'),
('SC005', '2025-05-06 19:00:00', '2025-05-06','P001','P003'),
('SC006', '2025-05-07 19:00:00', '2025-05-07','P003','P001'),
('SC007', '2025-05-08 19:00:00', '2025-05-08','P001','P002'),
('SC008', '2025-05-09 19:00:00', '2025-05-09','P001','P003'),
('SC009', '2025-05-10 19:00:00', '2025-05-10','P001','P002'),
('SC010', '2025-05-11 19:00:00', '2025-05-11','P001','P003'),
('SC011', '2025-05-12 10:00:00', '2025-05-12', 'P001', 'P001'),
('SC012', '2025-05-12 12:30:00', '2025-05-12', 'P002', 'P001'),
('SC013', '2025-05-12 15:00:00', '2025-05-12', 'P003', 'P001'),
('SC014', '2025-05-12 17:30:00', '2025-05-12', 'P001', 'P001'),
('SC015', '2025-05-12 20:00:00', '2025-05-12', 'P002', 'P001'),
('SC016', '2025-05-13 18:00:00', '2025-05-13', 'P001', 'P001'),
('SC017', '2025-05-13 18:00:00', '2025-05-13', 'P002', 'P002'),
('SC018', '2025-05-13 18:00:00', '2025-05-13', 'P003', 'P003'),
('SC019', '2025-05-13 18:00:00', '2025-05-13', 'P001', 'P002'),
('SC020', '2025-05-13 18:00:00', '2025-05-13', 'P002', 'P003');
select * from SuatChieu
-- Bảng Ghe

CREATE TABLE Ghe (
    maGhe VARCHAR(10) CHECK (
        LEN(maGhe) = 3 AND 
        maGhe LIKE '[A-Z][0-9][0-9]'
    ),
    maSuatChieu VARCHAR(10),
    PRIMARY KEY (maGhe, maSuatChieu),
    CONSTRAINT FK_Ghe_SuatChieu FOREIGN KEY (maSuatChieu) REFERENCES SuatChieu(maSuatChieu)
);
select *from Ghe
--Dùng để test dữ liệu
--INSERT INTO Ghe (maGhe, maSuatChieu) VALUES 
--('A01', 'SC001'),
--('B03', 'SC002'),
--('C07', 'SC003'),
--('D05', 'SC004'),
--('E02', 'SC005'),
--('A10', 'SC006'),
--('B08', 'SC007'),
--('C04', 'SC008'),
--('D01', 'SC009'),
--('E09', 'SC010'),
--('A03', 'SC002'),
--('B02', 'SC004'),
--('C01', 'SC006'),
--('D08', 'SC008'),
--('E06', 'SC001'),
--('A07', 'SC003'),
--('B09', 'SC005'),
--('C10', 'SC007'),
--('D03', 'SC009'),
--('E04', 'SC010');



-- vé xem phim

CREATE TABLE VeXemPhim (
    maVe VARCHAR(20) PRIMARY KEY,
    giaVe Float NOT NULL DEFAULT 60000,
    maPhim VARCHAR(10) NOT NULL,
    maSuatChieu VARCHAR(10) NOT NULL,
    maGhe VARCHAR(10) NOT NULL,
    ngayDat DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP

	CONSTRAINT FK_VeXemPhim_Phim FOREIGN KEY (maPhim) REFERENCES Phim(maPhim),
    CONSTRAINT FK_VeXemPhim_SuatChieu FOREIGN KEY (maSuatChieu) REFERENCES SuatChieu(maSuatChieu),
    CONSTRAINT FK_VeXemPhim_Ghe FOREIGN KEY (maGhe, maSuatChieu) REFERENCES Ghe(maGhe, maSuatChieu)
);
select *from VeXemPhim