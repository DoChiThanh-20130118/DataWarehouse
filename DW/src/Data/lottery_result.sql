/*
 Navicat MySQL Data Transfer

 Source Server         : Warehouse
 Source Server Type    : MySQL
 Source Server Version : 100428 (10.4.28-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : staging

 Target Server Type    : MySQL
 Target Server Version : 100428 (10.4.28-MariaDB)
 File Encoding         : 65001

 Date: 12/12/2023 01:08:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lottery_result
-- ----------------------------
DROP TABLE IF EXISTS `lottery_result`;
CREATE TABLE `lottery_result`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `regions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name_province` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiTam` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_Tam` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiBay` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_Bay` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiSau` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_Sau` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiNam` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_Nam` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiTu` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_Tu` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiBa` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_Ba` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiNhi` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_Nhi` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiNhat` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_Nhat` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `GiaiDacBiet` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TienThuong_DB` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lottery_result
-- ----------------------------
INSERT INTO `lottery_result` VALUES (1, '08/11/2023', 'Miền Nam', 'Đồng Nai', '54', '100N', '460', '200N', '0681; 1666; 2510', '400N', '8656', '1TR', '72871; 93683; 84449; 69165;\r\n27082; 53716; 38297', '3TR', '99537; 90711', '10TR', '14410', '15TR', '14001', '30TR', '005801', '2Tỷ');
INSERT INTO `lottery_result` VALUES (2, '12/08/2023', 'KẾT QUẢ XỔ SỐ Miền Nam - KQXS MN', 'TP. HCM', '03', '100N', '011', '200N', '1263;4269;9406;', '400N', '2235', '1TR', '14995;57577;75317;85109;16278;52693;16383;', '3TR', '20975;68210;', '10TR', '41899', '15TR', '17382', '30TR', '508327', '2Tỷ');
INSERT INTO `lottery_result` VALUES (3, '12/08/2023', 'KẾT QUẢ XỔ SỐ Miền Nam - KQXS MN', 'Long An', '31', '100N', '886', '200N', '6085;9532;7258;', '400N', '5443', '1TR', '60218;35068;30567;36293;5387;84254;14857;', '3TR', '37081;79336;', '10TR', '27563', '15TR', '63859', '30TR', '155521', '2Tỷ');
INSERT INTO `lottery_result` VALUES (4, '10/12/2023', 'KẾT QUẢ XỔ SỐ Miền Nam - KQXS MN', 'Tiền Giang', '98', '100N', '862', '200N', '3157;4833;7066;', '400N', '5230', '1TR', '09979;95599;60095;63127;47445;40767;58451;', '3TR', '00418;42254;', '10TR', '59391', '15TR', '55110', '30TR', '300589', '2Tỷ');
INSERT INTO `lottery_result` VALUES (5, '10/12/2023', 'KẾT QUẢ XỔ SỐ Miền Nam - KQXS MN', 'Kiên Giang', '95', '100N', '606', '200N', '8027;7419;9306;', '400N', '6125', '1TR', '27856;11839;53318;88116;76436;39500;29375;', '3TR', '19298;71270;', '10TR', '14380', '15TR', '41148', '30TR', '793321', '2Tỷ');
INSERT INTO `lottery_result` VALUES (6, '10/12/2023', 'KẾT QUẢ XỔ SỐ Miền Nam - KQXS MN', 'Đà Lạt', '53', '100N', '581', '200N', '5432;8630;4054;', '400N', '8663', '1TR', '89202;44644;41827;01961;71842;61005;23208;', '3TR', '29470;65181;', '10TR', '38632', '15TR', '48394', '30TR', '323859', '2Tỷ');
INSERT INTO `lottery_result` VALUES (7, '10/12/2023', 'KẾT QUẢ XỔ SỐ Miền Trung - KQXS MT', 'Thừa T. Huế', '45', '100N', '339', '200N', '8594;9348;8576;', '400N', '8225', '1TR', '83313;36334;85371;00682;63171;16106;76885;', '3TR', '73466;75246;', '10TR', '88670', '15TR', '10228', '30TR', '663408', '2Tỷ');
INSERT INTO `lottery_result` VALUES (8, '10/12/2023', 'KẾT QUẢ XỔ SỐ Miền Trung - KQXS MT', 'Khánh Hòa', '21', '100N', '352', '200N', '1533;3615;4710;', '400N', '8118', '1TR', '28000;29538;46996;97798;26658;05209;44815;', '3TR', '55723;27991;', '10TR', '05727', '15TR', '58011', '30TR', '661321', '2Tỷ');
INSERT INTO `lottery_result` VALUES (9, '10/12/2023', 'KẾT QUẢ XỔ SỐ Miền Trung - KQXS MT', 'Kon Tum', '95', '100N', '647', '200N', '0671;4408;8986;', '400N', '4498', '1TR', '69850;78022;38280;20487;29165;94934;01563;', '3TR', '78835;28473;', '10TR', '76491', '15TR', '69899', '30TR', '382358', '2Tỷ');
INSERT INTO `lottery_result` VALUES (10, '10/12/2023', 'KẾT QUẢ XỔ SỐ Miền Bắc - KQXS MB', 'Thái Bình', 'null', 'null', '95; 45; 08; 63;', '40N', '163; 177; 086;', '100N', '1518; 0392; 8758; 2218; 4504; 5669;', '200N', '4173; 1112; 3763; 1888;', '400N', '59452; 73572; 10791; 73037; 30913; 85255;', '1TR', '59670; 85173;', '5TR', '29463', '10TR', '87360', '1 Tỷ');

SET FOREIGN_KEY_CHECKS = 1;
