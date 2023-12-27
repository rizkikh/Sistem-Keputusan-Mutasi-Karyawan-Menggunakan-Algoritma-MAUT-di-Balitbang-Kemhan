-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 02, 2023 at 08:30 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mutasi_balitbang`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(50) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `nama`, `password`) VALUES
('admin', 'Khairul Rizki', 'admin'),
('Johndoe', 'John Doe', 'john123');

-- --------------------------------------------------------

--
-- Table structure for table `db_bobot`
--

CREATE TABLE `db_bobot` (
  `id_bobot` varchar(10) NOT NULL,
  `usia` decimal(10,2) NOT NULL,
  `kinerja` decimal(10,2) NOT NULL,
  `pangkat` decimal(10,2) NOT NULL,
  `disiplin` decimal(10,2) NOT NULL,
  `pengalaman` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_bobot`
--

INSERT INTO `db_bobot` (`id_bobot`, `usia`, `kinerja`, `pangkat`, `disiplin`, `pengalaman`) VALUES
('B001', '0.74', '0.07', '0.07', '0.06', '0.06'),
('B002', '0.74', '0.07', '0.07', '0.06', '0.06');

-- --------------------------------------------------------

--
-- Table structure for table `db_hasil`
--

CREATE TABLE `db_hasil` (
  `nip` varchar(18) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `pangkat` varchar(50) NOT NULL,
  `jabatan` varchar(100) NOT NULL,
  `nilai` decimal(10,2) NOT NULL,
  `keterangan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_hasil`
--

INSERT INTO `db_hasil` (`nip`, `nama`, `pangkat`, `jabatan`, `nilai`, `keterangan`) VALUES
('01041976xxxx0040 ', 'Syahdan Syahriar Ambar ', 'III/c', 'Pengolah Data TU - Balitbang ', '0.66', '--'),
('03041967xxxx0029 ', 'Hevry Yanto ', 'Kolonel', 'Kabid SDM - Sumdahan  ', '0.84', '--'),
('03041991xxxx0048 ', 'Muhammad Sinna Abdillah', 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan ', '0.35', '--'),
('03051986xxxx0007 ', 'Vaghwa Hasib Nata Praja', 'Kapten', 'Pengolah Data TU - Balitbang ', '0.38', '--'),
('04071966xxxx0015 ', 'Juwino ', 'III/d', 'Penyusun Naskah Laut Non Alpalud - Alpahan ', '0.85', '--'),
('04091972xxxx0003 ', 'Fadlan Suryani ', 'III/d', 'Pengolah Data TU - Balitbang', '0.72', '--'),
('05031982xxxx0018 ', 'Imas Chodijah ', 'III/b', 'Pengolah Puslitbang - Iptekhan ', '0.50', '--'),
('05051976xxxx0033 ', 'Agus Mulyadi ', 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan ', '0.64', '--'),
('05121979xxxx0016 ', 'Evi Sulastri  ', 'III/d', 'Kasubbid Non Alat Peralatan Utama Bidang Matra Laut Puslitbang - Alpalhan ', '0.60', '--'),
('07021979xxxx0021 ', 'Febbi Pramudya Istanto ', 'III/d', 'Kasubbag Tu Puslitbang - Iptekhan ', '0.63', '--'),
('07081969xxxx0036 ', 'Misyanto, S.Kom., M.Si ', 'Kolonel', 'Peneliti Madya Puslitbang - Sumdahan ', '0.79', '--'),
('08011977xxxx0006 ', 'Ibnu Holdun ', 'IV/a', 'Kasubbag TU - Balitbang ', '0.52', '--'),
('09051981xxxx0008 ', 'Uman Sukmada  ', 'III/c', 'Penyusun Naskah Darat Non Aplalud - Alpahan ', '0.55', '--'),
('09121966xxxx0035 ', 'Sultan Akbar ', 'II/c', 'Pengadiminstrasi Keuangan - Balitbang ', '0.28', '--'),
('10101982xxxx0023 ', 'I Gusti Komang Rai Trada ', 'Sersan Mayor', 'Pranata Komputer Bag - Korlitbang ', '0.58', '--'),
('11051978xxxx0037 ', 'Meilia Diana Fauza ', 'Letnan Kolonel', 'Kasubbag Peg - Sumdahan ', '0.67', '--'),
('13041966xxxx0002 ', 'Yanti Mala', 'III/d', 'Kasubbag TU - Balitbang', '0.85', '--'),
('13061998xxxx0047 ', 'Yoga Hamdan ', 'II/c', 'Pengadministrasi Umum TU - Balitbang ', '0.19', '--'),
('13081982xxxx0011 ', 'Putu Suwitrayasa ', 'Sersan Mayor', 'Pengolah Data TU - Balitbang ', '0.59', '--'),
('14021979xxxx0025 ', 'Abdul Rosyid', 'III/b', 'Pengolah Subbag Sdm Peneliti Bagkorlitbang Set - Balitbang ', '0.62', '--'),
('14031966xxxx0009 ', 'Ema Martiawati  ', 'III/d', 'Penyusun Naskah Udara Alpalud - Alpahan ', '0.80', '--'),
('14061995xxxx0044 ', 'Intan Dewi Lestari ', 'II/c', 'Pengadministrasi Umum TU - Balitbang ', '0.25', '--'),
('15061989xxxx0001 ', 'Ria Yunika Lestari', 'Sersan Mayor', 'Pranata Teknologi Komputer - Balitbang', '0.43', '--'),
('15101972xxxx0030 ', 'Sakundira Parama Sakti  ', 'Mayor', 'Peneliti Muda - Balitbang ', '0.74', '--'),
('15121982xxxx0014 ', 'Rosihan Ramin ', 'III/c', 'Penyusun Naskah Udara Alpalud - Alpahan ', '0.52', '--'),
('16021999xxxx0041 ', 'Cefi Deliawan ', 'II/b', 'Pengolah Data TU - Balitbang ', '0.26', '--'),
('17011966xxxx0013 ', 'Suharlina ', 'IV/a', 'Kasubbid Bidbekkomlek - Iptekhan ', '0.86', '--'),
('17041965xxxx0027 ', 'Arya Agung Manan ', 'III/d', 'Anggota Set - Balitbang  ', '0.90', 'Layak Dimutasi'),
('17041968xxxx0026 ', 'Tarya Adiguna ', 'III/d', 'Anggota Set - Balitbang  ', '0.83', '--'),
('17071989xxxx0049 ', 'Angga Ferdiansyah ', 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan ', '0.40', '--'),
('18061984xxxx0010 ', 'Banu Widagdo ', 'III/a', 'Pengelola Data TU - Iptekhan ', '0.50', '--'),
('18081971xxxx0038 ', 'Alimisna  ', 'IV/a', 'Analisis Pertahanan Ahli Muda - Balitbang ', '0.74', '--'),
('18081976xxxx0045 ', 'Ari Ariotmojo ', 'III/c', 'Penyusun Naskah Darat Non Aplalud - Alpahan ', '0.66', '--'),
('19051980xxxx0043 ', 'Septiono Lala Priobodo ', 'III/b', 'Pengolah Puslitbang - Iptekhan ', '0.57', '--'),
('19061965xxxx0034 ', 'Etty Sulistyawati ', 'IV/b', 'Fungsional - Balitbang ', '0.87', '--'),
('19061970xxxx0024 ', 'Tarya Adiwijaya, S,Pd ', 'III/d', 'Anggota Set - Balitbang  ', '0.81', '--'),
('20011978xxxx0019 ', 'Daryono, S.Pd., M.M ', 'III/c', 'Kasubbid Dagri Bidang Lingstra Puslitbang - Strahan ', '0.63', '--'),
('20071973xxxx0022 ', 'Rubino ', 'III/b', 'Pengolah Data Subbid Alpalut Bid Matra Darat Puslitbang - Alpahan ', '0.72', '--'),
('21011989xxxx0042 ', 'Muhammad Zulifkar ', 'III/a', 'Pengelola Data TU - Iptekhan ', '0.37', '--'),
('21061984xxxx0039 ', 'Yurviany ', 'III/c', 'Penyusun Naskah Subbag Kepegawaian Bagum Set - Balitbang ', '0.41', '--'),
('23091980xxxx0028 ', 'Ade Adang Nugraha ', 'Lettu', 'Pengelola Data Subbag Sdm Lit Bag Korlitbang Set - Balitbang Kemhan ', '0.63', '--'),
('23101987xxxx0017 ', 'Maulana Randa ', 'III/d', 'Penyusun Naskah Bid Daya Gerak Puslitbang - Iptekhan ', '0.27', '--'),
('23121990xxxx0046 ', 'Siska Damaiyanti ', 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan ', '0.36', '--'),
('24101982xxxx0032 ', 'Dimaz Satrya Rezamudra ', 'III/d', 'Penyusun Naskah - Balitbang ', '0.48', '--'),
('25061980xxxx0020 ', 'Ni Ketut Sudiasih', 'III/b', 'Pengolah Data Subbag Fora Bagkorlitbang Set - Balitbang ', '0.62', '--'),
('27031993xxxx0050 ', 'Ilham Wijaya Kusuma ', 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan ', '0.28', '--'),
('27101966xxxx0031 ', 'Sukemi S.Sos ', 'III/d', 'Kasubbid Komput Bid Sdm Puslitbang - Sumdahan ', '0.78', '--'),
('28021979xxxx0004', 'Surini', 'III/b', 'Pengolah Data TU - Balitbang ', '0.57', '--'),
('29031998xxxx0005 ', 'Darma Yurian ', 'II/c', 'Pengadministrasi Umum TU - Balitbang ', '0.19', '--'),
('29061982xxxx0012 ', 'Atho Muhammad Sadzali ', 'Pelda', 'Pranata Teknologi Komputer - Iptekhan ', '0.51', '--');

-- --------------------------------------------------------

--
-- Table structure for table `db_karyawan`
--

CREATE TABLE `db_karyawan` (
  `nip` varchar(18) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `tl` varchar(20) NOT NULL,
  `ttl` date NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `usia` int(3) NOT NULL,
  `pangkat` varchar(100) NOT NULL,
  `jabatan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_karyawan`
--

INSERT INTO `db_karyawan` (`nip`, `nama_lengkap`, `tl`, `ttl`, `jenis_kelamin`, `usia`, `pangkat`, `jabatan`) VALUES
('01041976xxxx0040 ', 'Syahdan Syahriar Ambar ', 'Jakarta ', '1976-04-01', 'Laki - laki', 47, 'III/c', 'Pengolah Data TU - Balitbang '),
('03041967xxxx0029 ', 'Hevry Yanto ', 'Solo', '1967-04-03', 'Laki - laki', 56, 'Kolonel', 'Kabid SDM - Sumdahan  '),
('03041991xxxx0048 ', 'Muhammad Sinna Abdillah', 'Banyuwangi ', '1991-04-03', 'Laki - laki', 32, 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan '),
('03051986xxxx0007 ', 'Vaghwa Hasib Nata Praja', 'Jakarta', '1986-05-03', 'Laki - laki', 37, 'Kapten', 'Pengolah Data TU - Balitbang '),
('04071966xxxx0015 ', 'Juwino ', 'Klaten', '1966-07-04', 'Laki - laki', 56, 'III/d', 'Penyusun Naskah Laut Non Alpalud - Alpahan '),
('04091972xxxx0003 ', 'Fadlan Suryani ', 'Palembang ', '1972-09-04', 'Perempuan', 50, 'III/d', 'Pengolah Data TU - Balitbang'),
('05031982xxxx0018 ', 'Imas Chodijah ', 'Jakarta', '1982-03-05', 'Perempuan', 41, 'III/b', 'Pengolah Puslitbang - Iptekhan '),
('05051976xxxx0033 ', 'Agus Mulyadi ', 'Tasikmalaya  ', '1976-05-05', 'Laki - laki', 47, 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan '),
('05121979xxxx0016 ', 'Evi Sulastri  ', 'Aceh Utara ', '1979-12-05', 'Perempuan', 43, 'III/d', 'Kasubbid Non Alat Peralatan Utama Bidang Matra Laut Puslitbang - Alpalhan '),
('07021979xxxx0021 ', 'Febbi Pramudya Istanto ', 'Magelang ', '1979-02-07', 'Perempuan', 44, 'III/d', 'Kasubbag Tu Puslitbang - Iptekhan '),
('07081969xxxx0036 ', 'Misyanto, S.Kom., M.Si ', 'Jakarta ', '1969-08-07', 'Laki - laki', 54, 'Kolonel', 'Peneliti Madya Puslitbang - Sumdahan '),
('08011977xxxx0006 ', 'Ibnu Holdun ', 'Jakarta ', '1997-01-08', 'Laki - laki', 46, 'IV/a', 'Kasubbag TU - Balitbang '),
('09051981xxxx0008 ', 'Uman Sukmada  ', 'Jakarta  ', '1981-05-09', 'Laki - laki', 42, 'III/c', 'Penyusun Naskah Darat Non Aplalud - Alpahan '),
('09121966xxxx0035 ', 'Sultan Akbar ', 'Padang ', '1996-12-09', 'Laki - laki', 26, 'II/c', 'Pengadiminstrasi Keuangan - Balitbang '),
('10101982xxxx0023 ', 'I Gusti Komang Rai Trada ', 'Padangkerta ', '1982-10-10', 'Laki - laki', 40, 'Sersan Mayor', 'Pranata Komputer Bag - Korlitbang '),
('11051978xxxx0037 ', 'Meilia Diana Fauza ', 'Majalengka ', '1978-05-11', 'Perempuan', 45, 'Letnan Kolonel', 'Kasubbag Peg - Sumdahan '),
('13041966xxxx0002 ', 'Yanti Mala', 'Tangerang', '1966-04-03', 'Perempuan', 57, 'III/d', 'Kasubbag TU - Balitbang'),
('13061998xxxx0047 ', 'Yoga Hamdan ', 'Bekasi ', '1998-06-13', 'Laki - laki', 25, 'II/c', 'Pengadministrasi Umum TU - Balitbang '),
('13081982xxxx0011 ', 'Putu Suwitrayasa ', 'Buleleng ', '1982-08-13', 'Laki - laki', 41, 'Sersan Mayor', 'Pengolah Data TU - Balitbang '),
('14021979xxxx0025 ', 'Abdul Rosyid', 'Bekasi', '1979-02-14', 'Laki - laki', 44, 'III/b', 'Pengolah Subbag Sdm Peneliti Bagkorlitbang Set - Balitbang '),
('14031966xxxx0009 ', 'Ema Martiawati  ', 'Madiun ', '1966-03-14', 'Perempuan', 57, 'III/d', 'Penyusun Naskah Udara Alpalud - Alpahan '),
('14061995xxxx0044 ', 'Intan Dewi Lestari ', 'Sumedang ', '1995-06-14', 'Perempuan', 28, 'II/c', 'Pengadministrasi Umum TU - Balitbang '),
('15061989xxxx0001 ', 'Ria Yunika Lestari', 'Kotabumi', '1989-06-19', 'Perempuan', 34, 'Sersan Mayor', 'Pranata Teknologi Komputer - Balitbang'),
('15101972xxxx0030 ', 'Sakundira Parama Sakti  ', 'Manado ', '1972-10-15', 'Laki - laki', 51, 'Mayor', 'Peneliti Muda - Balitbang '),
('15121982xxxx0014 ', 'Rosihan Ramin ', 'Jakarta', '1982-12-15', 'Laki - laki', 41, 'III/c', 'Penyusun Naskah Udara Alpalud - Alpahan '),
('16021999xxxx0041 ', 'Cefi Deliawan ', 'Bandar Lampung ', '1999-02-16', 'Laki - laki', 24, 'II/b', 'Pengolah Data TU - Balitbang '),
('17011966xxxx0013 ', 'Suharlina ', 'Payakumbuh ', '1966-01-17', 'Perempuan', 57, 'IV/a', 'Kasubbid Bidbekkomlek - Iptekhan '),
('17041965xxxx0027 ', 'Arya Agung Manan ', 'Jakarta', '1965-04-17', 'Laki - laki', 56, 'III/d', 'Anggota Set - Balitbang  '),
('17041968xxxx0026 ', 'Tarya Adiguna ', 'Jakarta', '1968-04-17', 'Laki - laki', 53, 'III/d', 'Anggota Set - Balitbang  '),
('17071989xxxx0049 ', 'Angga Ferdiansyah ', 'Palembang ', '1989-07-17', 'Laki - laki', 34, 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan '),
('18061984xxxx0010 ', 'Banu Widagdo ', 'Jogjakarta ', '1984-06-18', 'Laki - laki', 39, 'III/a', 'Pengelola Data TU - Iptekhan '),
('18081971xxxx0038 ', 'Alimisna  ', 'Padang  ', '1971-08-18', 'Perempuan', 52, 'IV/a', 'Analisis Pertahanan Ahli Muda - Balitbang '),
('18081976xxxx0045 ', 'Ari Ariotmojo ', 'Jakarta ', '1976-08-18', 'Laki - laki', 47, 'III/c', 'Penyusun Naskah Darat Non Aplalud - Alpahan '),
('19051980xxxx0043 ', 'Septiono Lala Priobodo ', 'Probolinggo ', '1980-05-19', 'Laki - laki', 43, 'III/b', 'Pengolah Puslitbang - Iptekhan '),
('19061965xxxx0034 ', 'Etty Sulistyawati ', 'Kediri ', '1965-06-19', 'Perempuan', 58, 'IV/b', 'Fungsional - Balitbang '),
('19061970xxxx0024 ', 'Tarya Adiwijaya, S,Pd ', 'Jakarta', '1970-06-19', 'Laki - laki', 53, 'III/d', 'Anggota Set - Balitbang  '),
('20011978xxxx0019 ', 'Daryono, S.Pd., M.M ', 'Tegal ', '1978-01-20', 'Laki - laki', 45, 'III/c', 'Kasubbid Dagri Bidang Lingstra Puslitbang - Strahan '),
('20071973xxxx0022 ', 'Rubino ', 'Gunung Kidul ', '1973-07-20', 'Laki - laki', 50, 'III/b', 'Pengolah Data Subbid Alpalut Bid Matra Darat Puslitbang - Alpahan '),
('21011989xxxx0042 ', 'Muhammad Zulifkar ', 'Jakarta ', '1989-01-21', 'Laki - laki', 34, 'III/a', 'Pengelola Data TU - Iptekhan '),
('21061984xxxx0039 ', 'Yurviany ', 'Surabaya ', '1984-06-21', 'Perempuan', 39, 'III/c', 'Penyusun Naskah Subbag Kepegawaian Bagum Set - Balitbang '),
('23091980xxxx0028 ', 'Ade Adang Nugraha ', 'Jakarta', '1980-09-23', 'Laki - laki', 43, 'Lettu', 'Pengelola Data Subbag Sdm Lit Bag Korlitbang Set - Balitbang Kemhan '),
('23101987xxxx0017 ', 'Maulana Randa ', 'Jakarta', '1987-10-23', 'Laki - laki', 35, 'III/d', 'Penyusun Naskah Bid Daya Gerak Puslitbang - Iptekhan '),
('23121990xxxx0046 ', 'Siska Damaiyanti ', 'Bekasi ', '1990-12-23', 'Perempuan', 33, 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan '),
('24101982xxxx0032 ', 'Dimaz Satrya Rezamudra ', 'Bandung ', '1982-10-24', 'Laki - laki', 40, 'III/d', 'Penyusun Naskah - Balitbang '),
('25061980xxxx0020 ', 'Ni Ketut Sudiasih', 'Jakarta', '1980-06-25', 'Perempuan', 43, 'III/b', 'Pengolah Data Subbag Fora Bagkorlitbang Set - Balitbang '),
('27031993xxxx0050 ', 'Ilham Wijaya Kusuma ', 'Semarang ', '1993-03-27', 'Laki - laki', 30, 'III/a', 'Pengelola Kepegawaian Subbag Tu Puslitbang - Sumdahan '),
('27101966xxxx0031 ', 'Sukemi S.Sos ', 'Bumiayu ', '1966-10-27', 'Perempuan', 57, 'III/d', 'Kasubbid Komput Bid Sdm Puslitbang - Sumdahan '),
('28021979xxxx0004', 'Surini', 'Gunungkidul ', '1979-02-28', 'Perempuan', 43, 'III/b', 'Pengolah Data TU - Balitbang '),
('29031998xxxx0005 ', 'Darma Yurian ', 'Padang ', '1998-03-29', 'Laki - laki', 25, 'II/c', 'Pengadministrasi Umum TU - Balitbang '),
('29061982xxxx0012 ', 'Atho Muhammad Sadzali ', 'Tangerang', '1982-06-29', 'Laki - laki', 40, 'Pelda', 'Pranata Teknologi Komputer - Iptekhan ');

-- --------------------------------------------------------

--
-- Table structure for table `db_kriteria`
--

CREATE TABLE `db_kriteria` (
  `id_bobot` varchar(10) NOT NULL,
  `usia` int(4) NOT NULL,
  `kinerja` int(4) NOT NULL,
  `pangkat` int(4) NOT NULL,
  `disiplin` int(4) NOT NULL,
  `pengalaman` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_kriteria`
--

INSERT INTO `db_kriteria` (`id_bobot`, `usia`, `kinerja`, `pangkat`, `disiplin`, `pengalaman`) VALUES
('B001', 40, 4, 4, 3, 3),
('B002', 40, 4, 4, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `db_normalisasi`
--

CREATE TABLE `db_normalisasi` (
  `nip` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `usia` decimal(10,2) NOT NULL,
  `pangkat` decimal(10,2) NOT NULL,
  `kinerja` decimal(10,2) NOT NULL,
  `disiplin` decimal(10,2) NOT NULL,
  `pengalaman` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_normalisasi`
--

INSERT INTO `db_normalisasi` (`nip`, `nama`, `usia`, `pangkat`, `kinerja`, `disiplin`, `pengalaman`) VALUES
('01041976xxxx0040 ', 'Syahdan Syahriar Ambar ', '0.68', '0.33', '0.69', '0.87', '0.59'),
('03041967xxxx0029 ', 'Hevry Yanto ', '0.94', '0.00', '0.62', '0.94', '0.82'),
('03041991xxxx0048 ', 'Muhammad Sinna Abdillah', '0.24', '0.67', '0.77', '0.53', '0.68'),
('03051986xxxx0007 ', 'Vaghwa Hasib Nata Praja', '0.38', '0.33', '0.15', '0.87', '0.23'),
('04071966xxxx0015 ', 'Juwino ', '0.94', '0.33', '0.69', '0.80', '0.59'),
('04091972xxxx0003 ', 'Fadlan Suryani ', '0.76', '0.33', '0.46', '0.80', '0.86'),
('05031982xxxx0018 ', 'Imas Chodijah ', '0.50', '0.67', '0.07', '0.73', '0.64'),
('05051976xxxx0033 ', 'Agus Mulyadi ', '0.68', '0.67', '0.23', '0.61', '0.59'),
('05121979xxxx0016 ', 'Evi Sulastri  ', '0.56', '0.33', '0.69', '1.00', '0.86'),
('07021979xxxx0021 ', 'Febbi Pramudya Istanto ', '0.59', '0.33', '0.93', '0.87', '0.91'),
('07081969xxxx0036 ', 'Misyanto, S.Kom., M.Si ', '0.88', '0.00', '0.62', '0.80', '0.73'),
('08011977xxxx0006 ', 'Ibnu Holdun ', '0.65', '0.00', '0.15', '0.27', '0.13'),
('09051981xxxx0008 ', 'Uman Sukmada  ', '0.53', '0.33', '0.54', '0.94', '0.64'),
('09121966xxxx0035 ', 'Sultan Akbar ', '0.06', '1.00', '0.77', '1.00', '0.86'),
('10101982xxxx0023 ', 'I Gusti Komang Rai Trada ', '0.47', '1.00', '0.69', '1.00', '0.82'),
('11051978xxxx0037 ', 'Meilia Diana Fauza ', '0.62', '1.00', '0.62', '0.87', '0.68'),
('13041966xxxx0002 ', 'Yanti Mala', '0.97', '0.33', '0.38', '0.67', '0.73'),
('13061998xxxx0047 ', 'Yoga Hamdan ', '0.03', '1.00', '0.31', '0.61', '0.59'),
('13081982xxxx0011 ', 'Putu Suwitrayasa ', '0.50', '1.00', '0.69', '0.94', '0.73'),
('14021979xxxx0025 ', 'Abdul Rosyid', '0.59', '0.67', '0.46', '1.00', '0.68'),
('14031966xxxx0009 ', 'Ema Martiawati  ', '0.97', '0.33', '0.15', '0.47', '0.27'),
('14061995xxxx0044 ', 'Intan Dewi Lestari ', '0.12', '1.00', '0.38', '0.47', '0.68'),
('15061989xxxx0001 ', 'Ria Yunika Lestari', '0.29', '1.00', '0.38', '1.00', '0.96'),
('15101972xxxx0030 ', 'Sakundira Parama Sakti  ', '0.79', '0.33', '0.38', '0.94', '0.77'),
('15121982xxxx0014 ', 'Rosihan Ramin ', '0.50', '0.33', '0.69', '0.80', '0.59'),
('16021999xxxx0041 ', 'Cefi Deliawan ', '0.00', '1.00', '1.00', '1.00', '1.00'),
('17011966xxxx0013 ', 'Suharlina ', '0.97', '0.00', '1.00', '0.94', '0.31'),
('17041965xxxx0027 ', 'Arya Agung Manan ', '0.94', '0.33', '0.93', '1.00', '0.86'),
('17041968xxxx0026 ', 'Tarya Adiguna ', '0.85', '0.33', '0.93', '1.00', '0.86'),
('17071989xxxx0049 ', 'Angga Ferdiansyah ', '0.29', '0.67', '0.93', '0.53', '0.73'),
('18061984xxxx0010 ', 'Banu Widagdo ', '0.44', '0.67', '0.46', '0.80', '0.73'),
('18081971xxxx0038 ', 'Alimisna  ', '0.82', '0.00', '0.62', '0.94', '0.50'),
('18081976xxxx0045 ', 'Ari Ariotmojo ', '0.68', '0.33', '0.62', '0.73', '0.82'),
('19051980xxxx0043 ', 'Septiono Lala Priobodo ', '0.56', '0.67', '0.54', '0.53', '0.73'),
('19061965xxxx0034 ', 'Etty Sulistyawati ', '1.00', '0.00', '0.46', '0.87', '0.68'),
('19061970xxxx0024 ', 'Tarya Adiwijaya, S,Pd ', '0.85', '0.33', '0.69', '1.00', '0.82'),
('20011978xxxx0019 ', 'Daryono, S.Pd., M.M ', '0.62', '0.33', '0.62', '0.94', '0.73'),
('20071973xxxx0022 ', 'Rubino ', '0.76', '0.67', '0.38', '0.87', '0.59'),
('21011989xxxx0042 ', 'Muhammad Zulifkar ', '0.29', '0.67', '0.46', '0.61', '0.73'),
('21061984xxxx0039 ', 'Yurviany ', '0.44', '0.33', '0.15', '0.40', '0.50'),
('23091980xxxx0028 ', 'Ade Adang Nugraha ', '0.56', '0.67', '0.77', '1.00', '0.91'),
('23101987xxxx0017 ', 'Maulana Randa ', '0.32', '0.33', '0.15', '0.00', '0.00'),
('23121990xxxx0046 ', 'Siska Damaiyanti ', '0.26', '0.67', '0.46', '0.73', '0.68'),
('24101982xxxx0032 ', 'Dimaz Satrya Rezamudra ', '0.47', '0.33', '0.46', '0.94', '0.36'),
('25061980xxxx0020 ', 'Ni Ketut Sudiasih', '0.56', '0.67', '0.62', '0.94', '0.96'),
('27031993xxxx0050 ', 'Ilham Wijaya Kusuma ', '0.18', '0.67', '0.54', '0.34', '0.64'),
('27101966xxxx0031 ', 'Sukemi S.Sos ', '0.97', '0.33', '0.00', '0.27', '0.31'),
('28021979xxxx0004', 'Surini', '0.56', '0.67', '0.15', '0.94', '0.77'),
('29031998xxxx0005 ', 'Darma Yurian ', '0.03', '1.00', '0.31', '1.00', '0.27'),
('29061982xxxx0012 ', 'Atho Muhammad Sadzali ', '0.47', '1.00', '0.23', '0.80', '0.55');

-- --------------------------------------------------------

--
-- Table structure for table `db_penilaian`
--

CREATE TABLE `db_penilaian` (
  `nip` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `usia` int(3) NOT NULL,
  `pangkat` int(10) NOT NULL,
  `kinerja` decimal(10,2) NOT NULL,
  `disiplin` decimal(10,2) NOT NULL,
  `pengalaman` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_penilaian`
--

INSERT INTO `db_penilaian` (`nip`, `nama`, `usia`, `pangkat`, `kinerja`, `disiplin`, `pengalaman`) VALUES
('01041976xxxx0040 ', 'Syahdan Syahriar Ambar ', 47, 2, '2.25', '2.38', '1.94'),
('03041967xxxx0029 ', 'Hevry Yanto ', 56, 1, '2.19', '2.44', '2.25'),
('03041991xxxx0048 ', 'Muhammad Sinna Abdillah', 32, 3, '2.31', '2.06', '2.06'),
('03051986xxxx0007 ', 'Vaghwa Hasib Nata Praja', 37, 2, '1.81', '2.38', '1.44'),
('04071966xxxx0015 ', 'Juwino ', 56, 2, '2.25', '2.31', '1.94'),
('04091972xxxx0003 ', 'Fadlan Suryani ', 50, 2, '2.06', '2.31', '2.31'),
('05031982xxxx0018 ', 'Imas Chodijah ', 41, 3, '1.75', '2.25', '2.00'),
('05051976xxxx0033 ', 'Agus Mulyadi ', 47, 3, '1.88', '2.13', '1.94'),
('05121979xxxx0016 ', 'Evi Sulastri  ', 43, 2, '2.25', '2.50', '2.31'),
('07021979xxxx0021 ', 'Febbi Pramudya Istanto ', 44, 2, '2.44', '2.38', '2.38'),
('07081969xxxx0036 ', 'Misyanto, S.Kom., M.Si ', 54, 1, '2.19', '2.31', '2.13'),
('08011977xxxx0006 ', 'Ibnu Holdun ', 46, 1, '1.81', '1.81', '1.31'),
('09051981xxxx0008 ', 'Uman Sukmada  ', 42, 2, '2.13', '2.44', '2.00'),
('09121966xxxx0035 ', 'Sultan Akbar ', 26, 4, '2.31', '2.50', '2.31'),
('10101982xxxx0023 ', 'I Gusti Komang Rai Trada ', 40, 4, '2.25', '2.50', '2.25'),
('11051978xxxx0037 ', 'Meilia Diana Fauza ', 45, 4, '2.19', '2.38', '2.06'),
('13041966xxxx0002 ', 'Yanti Mala', 57, 2, '2.00', '2.19', '2.13'),
('13061998xxxx0047 ', 'Yoga Hamdan ', 25, 4, '1.94', '2.13', '1.94'),
('13081982xxxx0011 ', 'Putu Suwitrayasa ', 41, 4, '2.25', '2.44', '2.13'),
('14021979xxxx0025 ', 'Abdul Rosyid', 44, 3, '2.06', '2.50', '2.06'),
('14031966xxxx0009 ', 'Ema Martiawati  ', 57, 2, '1.81', '2.00', '1.50'),
('14061995xxxx0044 ', 'Intan Dewi Lestari ', 28, 4, '2.00', '2.00', '2.06'),
('15061989xxxx0001 ', 'Ria Yunika Lestari', 34, 4, '2.00', '2.50', '2.44'),
('15101972xxxx0030 ', 'Sakundira Parama Sakti  ', 51, 2, '2.00', '2.44', '2.19'),
('15121982xxxx0014 ', 'Rosihan Ramin ', 41, 2, '2.25', '2.31', '1.94'),
('16021999xxxx0041 ', 'Cefi Deliawan ', 24, 4, '2.50', '2.50', '2.50'),
('17011966xxxx0013 ', 'Suharlina ', 57, 1, '2.50', '2.44', '1.56'),
('17041965xxxx0027 ', 'Arya Agung Manan ', 56, 2, '2.44', '2.50', '2.31'),
('17041968xxxx0026 ', 'Tarya Adiguna ', 53, 2, '2.44', '2.50', '2.31'),
('17071989xxxx0049 ', 'Angga Ferdiansyah ', 34, 3, '2.44', '2.06', '2.13'),
('18061984xxxx0010 ', 'Banu Widagdo ', 39, 3, '2.06', '2.31', '2.13'),
('18081971xxxx0038 ', 'Alimisna  ', 52, 1, '2.19', '2.44', '1.81'),
('18081976xxxx0045 ', 'Ari Ariotmojo ', 47, 2, '2.19', '2.25', '2.25'),
('19051980xxxx0043 ', 'Septiono Lala Priobodo ', 43, 3, '2.13', '2.06', '2.13'),
('19061965xxxx0034 ', 'Etty Sulistyawati ', 58, 1, '2.06', '2.38', '2.06'),
('19061970xxxx0024 ', 'Tarya Adiwijaya, S,Pd ', 53, 2, '2.25', '2.50', '2.25'),
('20011978xxxx0019 ', 'Daryono, S.Pd., M.M ', 45, 2, '2.19', '2.44', '2.13'),
('20071973xxxx0022 ', 'Rubino ', 50, 3, '2.00', '2.38', '1.94'),
('21011989xxxx0042 ', 'Muhammad Zulifkar ', 34, 3, '2.06', '2.13', '2.13'),
('21061984xxxx0039 ', 'Yurviany ', 39, 2, '1.81', '1.94', '1.81'),
('23091980xxxx0028 ', 'Ade Adang Nugraha ', 43, 3, '2.31', '2.50', '2.38'),
('23101987xxxx0017 ', 'Maulana Randa ', 35, 2, '1.81', '1.56', '1.13'),
('23121990xxxx0046 ', 'Siska Damaiyanti ', 33, 3, '2.06', '2.25', '2.06'),
('24101982xxxx0032 ', 'Dimaz Satrya Rezamudra ', 40, 2, '2.06', '2.44', '1.63'),
('25061980xxxx0020 ', 'Ni Ketut Sudiasih', 43, 3, '2.19', '2.44', '2.44'),
('27031993xxxx0050 ', 'Ilham Wijaya Kusuma ', 30, 3, '2.13', '1.88', '2.00'),
('27101966xxxx0031 ', 'Sukemi S.Sos ', 57, 2, '1.69', '1.81', '1.56'),
('28021979xxxx0004', 'Surini', 43, 3, '1.81', '2.44', '2.19'),
('29031998xxxx0005 ', 'Darma Yurian ', 25, 4, '1.94', '2.50', '1.50'),
('29061982xxxx0012 ', 'Atho Muhammad Sadzali ', 40, 4, '1.88', '2.31', '1.88');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `db_bobot`
--
ALTER TABLE `db_bobot`
  ADD PRIMARY KEY (`id_bobot`);

--
-- Indexes for table `db_hasil`
--
ALTER TABLE `db_hasil`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `db_karyawan`
--
ALTER TABLE `db_karyawan`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `db_kriteria`
--
ALTER TABLE `db_kriteria`
  ADD PRIMARY KEY (`id_bobot`);

--
-- Indexes for table `db_normalisasi`
--
ALTER TABLE `db_normalisasi`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `db_penilaian`
--
ALTER TABLE `db_penilaian`
  ADD PRIMARY KEY (`nip`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
