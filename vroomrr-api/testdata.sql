#
# TABLE STRUCTURE FOR: car
#

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `license_plate` varchar(16) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `brand` varchar(64) NOT NULL,
  `color` varchar(64) NOT NULL,
  `type` varchar(64) NOT NULL,
  `horsepower` int(8) NOT NULL,
  `build_year` int(8) NOT NULL,
  `fuel_type` enum('benzine','diesel','lpg','elektriciteit') NOT NULL,
  `description` varchar(1024) NULL,
  `selected` BOOLEAN NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('00-njg-32', 'ebed39f6-5f7f-3913-b69b-a4c761c97945', ' Mitsubishi', 'LightSalmon', ' Caprice', 135, 1998, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('00-ubq-48', '143b1c12-557c-3681-83f9-fba2ee88f483', ' Chrysler', 'Lavender', ' Celica', 506, 1983, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('02-cvp-00', '2adbfccc-d07a-3fb1-8ba6-e93f6feb8f7c', ' Lancia', 'Ivory', ' Yukon', 85, 1994, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('02-gbl-14', 'cf959fe4-0344-3aeb-9f78-f85f0bb5547b', ' Mercedes-Benz', 'PeachPuff', ' Impreza WRX', 23, 1998, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('04-gxy-43', '788e5334-0b8c-3a15-b98d-1e84cc1d008f', ' Honda', 'DeepSkyBlue', ' LX', 595, 1970, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('05-kfk-15', '64f9a9f7-1350-3d62-8873-e30ef6d59ce6', ' Mazda', 'WhiteSmoke', ' Impreza WRX', 41, 2012, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('06-jgq-60', '50fa607d-fac9-32db-b3cb-d1cf094b7c7d', ' McLaren', 'MediumAquaMarine', ' VUE', 228, 1999, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('08-zaz-26', '83c9ba4e-edb6-39a8-862c-f306f909e41f', ' Audi', 'Chartreuse', ' Savana 3500', 326, 2001, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('09-wnu-74', '257646b8-041b-3d35-a001-e3f48479eac5', ' Seat', 'LemonChiffon', ' Wrangler', 411, 1975, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('10-lsn-91', 'd8a3d606-48f4-39b6-acf4-1443a923c391', ' Mitsubishi', 'Chocolate', ' D150 Club', 379, 2013, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('12-eez-73', '82a78833-69bc-387e-bd4d-0c03ac1db42b', ' Iveco', 'Turquoise', ' CLS-Class', 437, 1978, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('12-sgf-34', 'a8b19136-7b77-3725-8c35-2431d447ec54', ' Lancia', 'MediumAquaMarine', ' Windstar', 184, 2006, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('14-mve-08', '16bd11ca-90be-383c-a785-9e14c81248ac', ' Citroën', 'LightGray', ' S40', 300, 1997, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('17-uof-76', 'beb8d754-9b3c-3957-a68a-25aad7055822', ' KTM', 'Teal', ' Ciera', 79, 1981, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('23-iyd-99', '1b169f1b-9da0-3eec-88a7-800f1beac0d0', ' Citroën', 'Bisque', ' Caprice', 442, 1978, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('26-hki-95', 'dda18dfd-fc36-30b7-b330-e44006c7ee2c', ' Peugeot', 'SeaGreen', ' Elantra', 254, 1971, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('26-pje-21', '8cdb51f7-a1b4-38e8-b0f6-4dd4f3be3c3f', ' Aston Martin', 'Cyan', ' DBS', 56, 2007, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('28-fjo-80', '0bd8f849-b0f2-3cab-be71-96c435945062', ' Mitsubishi', 'MediumBlue', ' F250', 277, 1991, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('29-qhe-73', '04e99e1e-5a61-3d4e-bbd2-c5b7657f7b27', ' Nissan', 'LemonChiffon', ' Endeavor', 409, 1987, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('31-ugh-36', '55991d81-26d4-3a5d-b104-04c092cb5e24', ' Iveco', 'LemonChiffon', ' Viper', 381, 1982, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('31-wek-99', '50f2985a-8c9e-31e1-b0b6-02efc1eaceba', ' Skoda', 'DarkSeaGreen', ' Crossfire Roadster', 98, 1998, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('32-rlx-22', 'd712fd33-9271-33f9-9131-9b1a025b4d48', ' Mercedes-Benz', 'CornflowerBlue', ' Century', 255, 1980, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('32-xer-67', '889218ae-8f9e-32f4-a8a2-5a3ea852fdd4', ' Seat', 'Cyan', ' Legend', 23, 1989, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('33-vzz-00', '2b71b566-5c26-3210-b7e3-3bc1a2270e32', ' Toyota', 'OliveDrab', ' Civic', 21, 1998, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('34-ghz-52', 'b9838f7f-06f7-3dab-a29b-60f42b9fc8c4', ' Lotus', 'BlanchedAlmond', ' F250', 81, 1997, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('34-imw-37', '312e662e-a1e1-31d4-a2fc-d3931e979a96', ' Toyota', 'Crimson', ' 911', 120, 1985, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('35-ffu-66', 'd687c796-e1c4-3b49-b73f-6723d017709e', ' Honda', 'Purple', ' GTO', 443, 2004, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('35-fqf-05', 'e79eb8cc-bb7d-3c58-a1d6-86f0e179caba', ' KTM', 'HoneyDew', ' Century', 263, 1983, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('35-oyy-54', 'b0c770bf-e654-37b6-bc42-fa20fd9dfdd4', ' Audi', 'DarkTurquoise', ' A3', 529, 1979, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('36-aic-18', 'c0d74261-4dcf-3b59-82f4-f3298b191e16', ' Dacia', 'DarkKhaki', ' LS', 75, 1992, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('36-cfd-02', 'ea2f364d-d90e-3373-9866-0122ad067e03', ' DS', 'OldLace', ' Frontier', 212, 2016, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('36-fwn-97', 'd2fe1882-8f9c-3e55-86d8-def28ed0041c', ' DS', 'BurlyWood', ' C8 Double 12 S', 562, 2001, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('36-gku-75', 'bd4d6ce0-8910-3329-9a9c-215a7f42cf57', ' Fiat', 'LightYellow', ' Legend', 361, 2001, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('36-sbb-98', '1b4526cd-0d22-3c8d-80c2-340a62768aeb', ' Peugeot', 'PapayaWhip', ' Th!nk', 457, 2004, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('37-kfc-97', '04e8e2f8-5520-376f-8873-d3997af6352b', ' Lotus', 'PaleVioletRed', ' 300', 581, 1983, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('40-pmz-37', 'e12c91c1-bbed-3d52-bff6-5c0e495aa7a3', 'Abarth', 'MediumTurquoise', ' Town Car', 122, 2006, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('44-arp-07', 'bde4c4ca-ca87-3b0c-a42c-ad0461fc85d7', ' Dodge', 'PaleTurquoise', ' C30', 398, 1992, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('45-ssr-30', '472dd700-9367-32c6-bdcd-9c5de5b0290e', ' Fisker', 'Black', ' Windstar', 354, 1996, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('45-xuh-69', '58601b71-af24-319e-84ec-b0b1678898e3', ' Daewoo', 'DarkBlue', ' GTO', 259, 1972, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('46-khz-33', 'bc107d7f-82eb-3c46-8a93-a8b4af40442e', ' Saab', 'SteelBlue', ' DBS', 130, 1993, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('48-ftp-97', '9b7114d8-ad98-3419-a2d6-0b045b16b2dd', ' KTM', 'MediumSlateBlue', ' GL-Class', 518, 1979, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('49-qld-40', 'a7fb2f65-3f5f-35da-a7a9-34d93d31b30f', ' Ford', 'DarkOliveGreen', ' Century', 113, 1989, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('49-sic-37', 'ef5216ca-db14-34b8-b903-a20d4fc183d7', ' Daewoo', 'WhiteSmoke', ' Mustang', 581, 2006, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('51-lye-50', 'e3f1967b-ed28-3144-bfd4-1bb84fa000aa', ' Maybach', 'LawnGreen', ' Spyder', 410, 2008, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('51-mjk-15', '685df856-c5ae-3e26-9da0-c90fe34cea0c', ' Morgan', 'LavenderBlush', ' DTS', 196, 2018, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('51-yij-81', '5bd3a232-b3bc-364a-b336-fba616f9e547', ' Dacia', 'AntiqueWhite', ' 430', 107, 1970, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('53-yzi-82', '64931350-76a9-3395-82a8-35d819fe6968', ' Chrysler', 'OldLace', ' Skylark', 570, 1995, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('54-oyz-16', 'ea1c3b9b-2a3e-3ea7-bc51-974cd7d6847e', ' Tesla', 'PeachPuff', ' C70', 363, 1974, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('56-xdc-36', 'd4dfa069-9a62-381a-985a-db376ef77c20', ' Ferrari', 'Indigo ', ' C70', 98, 1982, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('57-syb-44', 'e6603f97-21e4-361f-8c0b-1e14ed10b775', ' Nissan', 'PaleTurquoise', ' Tahoe', 183, 2003, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('58-jev-26', 'f3020d12-0d87-306a-9c55-0af2d05af801', ' Ferrari', 'Sienna', ' Crossfire Roadster', 214, 1971, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('59-duq-12', 'd885e7ac-5bd2-34a0-bf43-a61546682278', ' Donkervoort', 'FloralWhite', ' RX-7', 488, 1994, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('59-pjx-34', '93eebc3e-ffc6-339d-8bb2-52092b885910', ' Bugatti', 'Chocolate', ' Cutlass Cruiser', 149, 2010, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('60-jyc-17', '7addb11b-9e3e-3b85-9a14-669ebb4f1c40', ' Hummer', 'MidnightBlue', ' Spyder', 72, 2018, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('60-mhb-09', '310ac307-de97-3eee-b837-40d1f7907e3d', ' MG', 'DodgerBlue', ' Century', 320, 1993, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('60-wpa-41', '26d23860-8007-38ef-952f-f3b5a9cd5c41', ' Volvo', 'Silver', ' Yukon', 535, 1980, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('61-czb-75', 'dda277fe-1fe8-39a4-848c-ef583c610f5e', ' Lexus', 'DimGray', ' Element', 450, 2008, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('61-olp-18', 'd0c117d3-0fef-3e1a-b48a-51ae30486c5f', ' Mini', 'LightGray', ' 911', 363, 1970, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('62-odt-88', '9727dd68-7b68-301f-aab3-390312ee3289', ' Toyota', 'LightGray', ' Pajero', 481, 2014, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('63-lio-49', '8d5350f7-7f12-3bd3-a294-be19ea850ad7', ' Fiat', 'MediumSlateBlue', ' Miata MX-5', 130, 2016, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('63-lvk-81', '1765518f-75f0-3d39-bb87-10c0522d8194', ' Volvo', 'SlateBlue', ' VUE', 472, 2017, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('63-spq-41', '2fc554e5-5874-3abf-9749-a72696804d7b', ' Ferrari', 'MediumVioletRed', ' Breeze', 65, 1972, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('65-zei-64', 'f2355fd1-9be9-3a16-8b3a-ab82c191eb81', ' Morgan', 'Blue', ' 430', 126, 1988, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('66-jba-30', 'd53d094a-6575-380e-8f41-78e1dfdf710f', ' Mercedes-Benz', 'DodgerBlue', ' Tahoe', 110, 2011, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('66-yho-15', '225914bc-93f0-3e52-9ea9-fc2450a7c113', ' Mazda', 'Orchid', ' Breeze', 137, 2017, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('68-eer-23', '7b3c152b-16ac-3d0a-a9cb-87475b4b3c29', ' Opel', 'Beige', ' Allante', 424, 1972, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('69-qdf-93', '68e0c03d-21ef-3cbf-a2a8-0cfed36710ac', ' McLaren', 'Cornsilk', ' Miata MX-5', 356, 2019, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('70-bsh-82', '0eb8f3ba-b649-315f-b60d-bae67eed79ca', ' Landwind', 'PapayaWhip', ' Challenger', 123, 1974, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('70-nuj-82', 'f5fe68a5-a096-3525-a6fd-53d23689d6b4', ' Peugeot', 'LemonChiffon', ' Ciera', 108, 1991, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('72-sao-14', '707a72d4-e61f-3ed2-ad46-809b166e4d6c', ' Opel', 'DarkSlateGray', ' Impreza WRX', 98, 1986, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('74-skl-09', 'f1fcae48-f4ce-3674-a294-e4e75a3f8de4', ' Dodge', 'Gold', ' Continental', 452, 2012, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('75-wcj-53', 'dea9b3af-1845-39f0-8764-e5823793b6a7', ' Smart', 'PowderBlue', ' Aurora', 311, 1990, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('75-zbn-84', '0bc66861-7a1e-3501-b0e0-5dd8c2a3a27d', ' Bugatti', 'Indigo ', ' Civic', 142, 1988, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('76-cfa-47', '0d7a3884-f1bd-3312-b6b6-e1c73a004fdc', ' Lexus', 'DarkGreen', ' Passport', 413, 2015, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('76-jnn-16', '95cf388f-6759-3280-b772-9b623359ef0e', ' Aston Martin', 'Red', ' Alcyone SVX', 323, 1992, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('79-grx-41', 'fc3ea413-1e18-3abf-8ad4-f89379297503', ' Dodge', 'DarkMagenta', ' Element', 254, 1987, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('79-jpq-28', 'e9e572e8-5f0b-33f5-90df-5815c395be78', ' Lexus', 'Brown', ' Miata MX-5', 23, 2011, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('80-ijm-10', 'be527d33-aca7-37cc-be6c-1acbebb3d97a', ' Ferrari', 'DimGray', ' Miata MX-5', 283, 2002, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('81-etx-19', '3bf8594d-d381-324a-abd3-8215b3f56b82', 'Abarth', 'Lime', ' Century', 255, 1991, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('81-vog-81', '5e5c8d2d-e124-386a-b0aa-f6697e4aee79', ' Land Rover', 'Wheat', ' Liberty', 149, 2009, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('82-aaw-77', 'dc1b17c0-4839-3c5b-b937-e3831079684e', ' Lada', 'SaddleBrown', ' Caprice', 530, 1980, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('83-urs-76', '4837d9fb-742b-35b8-bcb5-6fb29d7f2efa', ' MG', 'Indigo ', ' Ram 2500 Club', 30, 1997, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('85-sau-26', '19b4b0ee-148e-3058-b144-b2165a7de3e3', ' Mazda', 'Aqua', ' LeBaron', 279, 2007, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('87-hif-65', '1df5454e-1e54-311e-8ab2-453144da2e56', ' Opel', 'PeachPuff', ' Skylark', 259, 2019, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('88-ioh-02', '442546db-0b87-3780-866f-38d13fda211d', ' Bugatti', 'DarkGray', ' Passport', 590, 1993, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('88-wiy-96', '5521bcd2-4c6c-3df4-826c-8fa5134f901c', ' Infiniti', 'LightGray', ' B-Series Plus', 440, 1994, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('90-gcx-85', '6d58d494-89f3-3923-9905-1cfc24d2ad9e', ' Subaru', 'RosyBrown', ' A3', 48, 1970, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('91-dvs-49', '2fe48d44-8642-383a-9f72-144af68d3f8f', ' Nissan', 'DarkKhaki', ' 62', 231, 1992, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('93-adu-20', '8487e96e-c2b9-3c39-bc0f-113037228e27', ' Landwind', 'Sienna', ' A3', 505, 2012, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('93-iao-67', 'ff5a5be3-12a5-3a2a-bb3c-6b967b886207', ' Morgan', 'Cornsilk', ' F250', 352, 1970, 'benzine', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('93-kdp-53', 'b309fb85-590d-3b2c-a7d5-28439d0f8ceb', ' Lotus', 'YellowGreen', ' Alcyone SVX', 77, 2004, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('93-ntp-51', 'fda886fd-9683-3cf5-9604-7ef2fb2068e2', ' Donkervoort', 'SlateGray', ' Challenger', 101, 2013, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('93-ptd-14', '843fd395-d7f6-3857-ba69-dc471dc12af6', ' Daewoo', 'Indigo ', ' Wrangler', 560, 2001, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('94-ahb-74', '4038f8f3-99f0-327b-b2bd-87cc2ea6065a', ' Landwind', 'LightCoral', ' Miata MX-5', 203, 1982, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('94-ljg-81', '22bc8d29-4e19-3217-ac40-30342d1f317f', ' Honda', 'CornflowerBlue', ' MKZ', 401, 1981, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('95-jha-72', 'd90e9a6b-29b7-3efe-9271-c1ab5aa0b20a', ' Chevrolet', 'MediumOrchid', ' Viper', 174, 1975, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('95-kvz-09', '9a3b12e7-ee67-3707-afb8-9db53e5cb4ee', ' Lexus', 'LightSteelBlue', ' Viper', 367, 2013, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('97-uas-41', 'b78adaff-f2c3-30a9-8d62-01a04a29721e', ' Citroën', 'LightCyan', ' Malibu', 514, 1990, 'elektriciteit', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('98-mtp-80', '083c4714-07bc-31ec-bf31-62fcbc10c4ec', ' Aston Martin', 'DimGray', ' Windstar', 201, 1975, 'diesel', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('99-fho-31', '2c06f9e8-e621-38a4-913c-011ac4b66075', ' Nissan', 'Chartreuse', ' Escalade EXT', 285, 1988, 'lpg', true);
INSERT INTO `car` (`license_plate`, `user_id`, `brand`, `color`, `type`, `horsepower`, `build_year`, `fuel_type`, `selected`) VALUES ('49jfgv', '47b6b871-26ce-43f3-9dc0-17a6ccb0505a', ' Volvo', 'Black', ' S60', 140, 2002, 'benzine', true);
#
# TABLE STRUCTURE FOR: car_images
#

DROP TABLE IF EXISTS `car_images`;

CREATE TABLE `car_images` (
  `car_images_id` varchar(64) NOT NULL,
  `license_plate` varchar(16) NOT NULL,
  `image` varchar(2048) NOT NULL,
  PRIMARY KEY (`car_images_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

#
# TABLE STRUCTURE FOR: chat
#

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` varchar(64) NOT NULL,
  `start` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

#
# TABLE STRUCTURE FOR: message
#

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` varchar(64) NOT NULL,
  `chat_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp(),
  `content` varchar(1028) NOT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

#
# TABLE STRUCTURE FOR: opinion
#

DROP TABLE IF EXISTS `opinion`;

CREATE TABLE `opinion` (
  `user_id` varchar(64) NOT NULL,
  `user_id_match` varchar(64) NOT NULL,
  `opinion` enum('green','yellow','red','') NOT NULL,
  PRIMARY KEY (`user_id`,`user_id_match`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

#
# TABLE STRUCTURE FOR: session
#

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
  `session_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

#
# TABLE STRUCTURE FOR: user
#

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `public_key` varchar(1028) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('04e8e2f8-5520-376f-8873-d3997af6352b', 'aspinka', 'Casimir', '9194b46814b28eae3dc1e3c130fc72d141462af0', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('04e99e1e-5a61-3d4e-bbd2-c5b7657f7b27', 'kellen66', 'Oscar', 'bc2e2b7b78e78bec39d497904e15659d04873bf4', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('083c4714-07bc-31ec-bf31-62fcbc10c4ec', 'rowe.axel', 'Renee', 'ee61277d22d1d541dc298c090d3c889d3df7a442', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('0bc66861-7a1e-3501-b0e0-5dd8c2a3a27d', 'iparisian', 'Twila', '559810de0c876a95a7967229a6a86781d87b45a1', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('0bd8f849-b0f2-3cab-be71-96c435945062', 'viva35', 'Wilfred', 'a155aac4d958d8ebeaba70b333c57d59fbd685b4', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('0d7a3884-f1bd-3312-b6b6-e1c73a004fdc', 'friesen.audreanne', 'Cleveland', '080fd9f602fe3b5d011f436912c5d53322e49275', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('0eb8f3ba-b649-315f-b60d-bae67eed79ca', 'macy70', 'Mina', 'c24c64e713e1da391383bc7b0bd62b94bd6cc827', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('143b1c12-557c-3681-83f9-fba2ee88f483', 'ahand', 'Tillman', '5a4b3803d526e011e0a2130fc70d9dc4f137de97', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('16bd11ca-90be-383c-a785-9e14c81248ac', 'iva64', 'Zula', 'b03fff6b1c440ac84a84414e38afe9848aca7932', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('1765518f-75f0-3d39-bb87-10c0522d8194', 'cassin.tyree', 'Raphaelle', '4cd1933932c0723aba25e9440d294969c4036b12', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('19b4b0ee-148e-3058-b144-b2165a7de3e3', 'ruecker.arch', 'Kirk', '3178d60c8bbeaf7dfac29b9b65e9546d879ed383', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('1b169f1b-9da0-3eec-88a7-800f1beac0d0', 'janessa.hayes', 'Christine', '87927797d6580548a193aa0ddce201b3087ee707', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('1b4526cd-0d22-3c8d-80c2-340a62768aeb', 'zschaden', 'Earl', '55d7d7f8b6a43560f8f840cf1ac499a2f07da7d9', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('1df5454e-1e54-311e-8ab2-453144da2e56', 'jared47', 'Evelyn', '5f2908724e4188d6a515348365ca88b98b6a95c4', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('225914bc-93f0-3e52-9ea9-fc2450a7c113', 'lkassulke', 'Stone', '1e95d8e8bf0a27331cab1e980be23ad28fe6f602', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('22bc8d29-4e19-3217-ac40-30342d1f317f', 'jakubowski.camila', 'Simeon', '1d291d6c58a65583263a837b3393409d2c427e28', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('257646b8-041b-3d35-a001-e3f48479eac5', 'sandra.bednar', 'Donna', '5ac03ed062ab97523c55edb52b6a3096a8b789f4', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('26d23860-8007-38ef-952f-f3b5a9cd5c41', 'ari.considine', 'Cheyanne', '141e14fb7739706d859e554405e94d358c699620', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('2adbfccc-d07a-3fb1-8ba6-e93f6feb8f7c', 'fkunde', 'Jamel', 'dc59e3a37d31cd8e6b9c649da5fa46d9f0b8814c', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('2b71b566-5c26-3210-b7e3-3bc1a2270e32', 'eleonore03', 'Kale', '837df2b2e80ab72094c448e414e563571ff952e9', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('2c06f9e8-e621-38a4-913c-011ac4b66075', 'kpfannerstill', 'Elza', 'd4141f735f1e419eff9eeece14671c5ffcb588e5', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('2fc554e5-5874-3abf-9749-a72696804d7b', 'nader.kyler', 'Emmy', '30d62e35e79f7226ba0583f19f08da15e18ab60e', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('2fe48d44-8642-383a-9f72-144af68d3f8f', 'bechtelar.aylin', 'Alec', 'b412479c0e73b20312bc99b18de7d8b27448b29a', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('310ac307-de97-3eee-b837-40d1f7907e3d', 'viva.kunze', 'Deven', 'beabf5c43e75eb10773971ef7cc8495ab1faa8bc', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('312e662e-a1e1-31d4-a2fc-d3931e979a96', 'gleichner.aditya', 'Nelson', '5f75483ce056e6bba693090157d0321a00eb5e78', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('3bf8594d-d381-324a-abd3-8215b3f56b82', 'ottis86', 'Bart', '1fcd97c21893f00919df0f0621eff225504d60e8', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('4038f8f3-99f0-327b-b2bd-87cc2ea6065a', 'mariana77', 'Camilla', '5cdf98e612a0b93fc5038a8c39716a1d4fe77607', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('442546db-0b87-3780-866f-38d13fda211d', 'edna92', 'Katarina', '734174f4e29e28fec0d5bfa1d84297d806dcebd5', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('472dd700-9367-32c6-bdcd-9c5de5b0290e', 'jeffertz', 'Delmer', '5a783f40183883cad864c119b45c9adca54432b8', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('4837d9fb-742b-35b8-bcb5-6fb29d7f2efa', 'daniella45', 'Brett', '4969a7fb4de96d0db9d7bfbc73d35adff3ecc7f7', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('50f2985a-8c9e-31e1-b0b6-02efc1eaceba', 'ocarroll', 'Rosalia', 'd40342c16daa8b418db7a75c9435bf6fcafa35ce', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('50fa607d-fac9-32db-b3cb-d1cf094b7c7d', 'fcarroll', 'Gunner', 'da4ac87278bce16a71c9c64ef072951b3cd4021e', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('5521bcd2-4c6c-3df4-826c-8fa5134f901c', 'jalyn12', 'Demarcus', 'a3a4d3f8f16b58c2299768a0b893a0d3ec1f1f4b', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('55991d81-26d4-3a5d-b104-04c092cb5e24', 'carmine32', 'Ashleigh', 'd9d66f3ac46b7ae48512a5a362644dbcbbca610c', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('58601b71-af24-319e-84ec-b0b1678898e3', 'lynn96', 'Lorna', '434d249e5279ee600131dca1add6ebf84ee0016a', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('5bd3a232-b3bc-364a-b336-fba616f9e547', 'lfunk', 'Marquis', '7631312f2388eb4a89a029e9400ba5159052a10f', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('5e5c8d2d-e124-386a-b0aa-f6697e4aee79', 'oframi', 'Greta', 'ae59129101b8d27cffe62a190bae1d90175109b7', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('64931350-76a9-3395-82a8-35d819fe6968', 'ykrajcik', 'Kamren', 'd722ff3493c041f43784e60c202c6847b5287fec', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('64f9a9f7-1350-3d62-8873-e30ef6d59ce6', 'roosevelt73', 'Jovanny', '82eebd32d384643c5be3b1f80fcd3e6d8edea387', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('685df856-c5ae-3e26-9da0-c90fe34cea0c', 'ymurphy', 'Jimmy', '4172fe76586c2e0d24b6ef050b28e642f31746f7', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('68e0c03d-21ef-3cbf-a2a8-0cfed36710ac', 'hickle.dante', 'Leopold', 'e6ebcb29db1117458f6b5c5f7f3e01a2b74a21b9', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('6d58d494-89f3-3923-9905-1cfc24d2ad9e', 'ivory.wehner', 'Alivia', '5615a032290157517b0eb185fcbad3813a6de66c', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('707a72d4-e61f-3ed2-ad46-809b166e4d6c', 'bednar.lavada', 'Vilma', '0cc4e756035dae744ade6b593c8dd34604387f76', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('788e5334-0b8c-3a15-b98d-1e84cc1d008f', 'rleffler', 'Vincenzo', 'ebde8a1168c4af26f4bf0fbca98a7e7fed293e68', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('7addb11b-9e3e-3b85-9a14-669ebb4f1c40', 'eli41', 'Tyson', '84b2548a3f850d620540367307eb1f48d7832f1b', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('7b3c152b-16ac-3d0a-a9cb-87475b4b3c29', 'elmore68', 'Orlando', 'ed6d7d06ca63924033da7552737e9afd8a444364', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('82a78833-69bc-387e-bd4d-0c03ac1db42b', 'erdman.joshua', 'Shanna', '5322c072a7df7ff6fd67d294e78d902d0d655936', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('83c9ba4e-edb6-39a8-862c-f306f909e41f', 'rafaela.parker', 'Lila', '57c47df6867237159a0e6f6c80017c4a256bff24', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('843fd395-d7f6-3857-ba69-dc471dc12af6', 'ernser.rowland', 'Baron', '718d8adaaee35b49c77ca8f0ec621ef37b1ed6a6', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('8487e96e-c2b9-3c39-bc0f-113037228e27', 'block.cassie', 'Bailey', '1636c352874d510e1644b7edc5867f076ba57372', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('889218ae-8f9e-32f4-a8a2-5a3ea852fdd4', 'zschoen', 'Wilhelm', 'b894af6a0235c351a1f01b66b29dbdd994135e21', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('8cdb51f7-a1b4-38e8-b0f6-4dd4f3be3c3f', 'clementine82', 'Viola', 'cf7dcaf3c29316fda6d08e4e9a9a2d1d179d7f86', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('8d5350f7-7f12-3bd3-a294-be19ea850ad7', 'mathew.lockman', 'Wellington', '7ea4bb832663e37193b2870fe17a719f7427ba25', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('93eebc3e-ffc6-339d-8bb2-52092b885910', 'christop36', 'Layne', '6ea33a5db4e4fd7c09ea9774b93682a7b7648c4d', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('95cf388f-6759-3280-b772-9b623359ef0e', 'gilda72', 'Shawn', '524fddb49b30ed9f225b5d89767d0da8d5ee0334', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('9727dd68-7b68-301f-aab3-390312ee3289', 'kaylie94', 'Noelia', '2a0adc51b054e57d2129f4d382139b9bc787a6a2', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('9a3b12e7-ee67-3707-afb8-9db53e5cb4ee', 'jlangosh', 'Ona', '627e42f9b5d2b7f8ee901d40bf126ac3e6728016', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('9b7114d8-ad98-3419-a2d6-0b045b16b2dd', 'littel.laila', 'Kelley', '88ee23172dc3b39f5e497d89b677ec890183f0d9', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('a7fb2f65-3f5f-35da-a7a9-34d93d31b30f', 'jast.kimberly', 'Kellie', 'caef0b9a002c669d33f5ba1af3c6b9359628a3d0', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('a8b19136-7b77-3725-8c35-2431d447ec54', 'ddibbert', 'Alvera', '21388a31d56802c29f546085dd759b2a57c4a10f', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('b0c770bf-e654-37b6-bc42-fa20fd9dfdd4', 'elwyn42', 'Maci', 'd2bf754b4a1ba34b23217c9bb4729f91806d144d', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('b309fb85-590d-3b2c-a7d5-28439d0f8ceb', 'else.bins', 'Lon', '76050942b7da12575303eb59c2d7f66981e3eea9', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('b78adaff-f2c3-30a9-8d62-01a04a29721e', 'nbecker', 'Danyka', '30ff223c13e44cb0055b069d42af5cf1d214e2ee', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('b9838f7f-06f7-3dab-a29b-60f42b9fc8c4', 'junior70', 'Garett', 'dadbf0135918974d2f955634e6b93bfedde89f0b', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('bc107d7f-82eb-3c46-8a93-a8b4af40442e', 'rohan.simeon', 'Lenny', '715e8923e862091d06fdc8ebe2b340893bd48d1e', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('bd4d6ce0-8910-3329-9a9c-215a7f42cf57', 'destinee.hickle', 'Lavada', 'c014d28ecf33b286b468989563ddcaa15d80670c', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('bde4c4ca-ca87-3b0c-a42c-ad0461fc85d7', 'little.annetta', 'Jairo', '9f4f8cd9888ecb1bb3404815915df4853593b484', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('be527d33-aca7-37cc-be6c-1acbebb3d97a', 'athompson', 'Saige', '3522794ca0a99db312915c51549cdd22a674b1d9', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('beb8d754-9b3c-3957-a68a-25aad7055822', 'block.kameron', 'Ervin', '3847de5ca9c173ce179ec8c2b17b00bf717ec359', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('c0d74261-4dcf-3b59-82f4-f3298b191e16', 'camilla.little', 'Lauretta', '84871039f529bb537fa2171709ad94b886d1e790', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('cf959fe4-0344-3aeb-9f78-f85f0bb5547b', 'cmurazik', 'Oswaldo', '611d7f5ae1b4de84f8fe70b3b8399788cd906cd0', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d0c117d3-0fef-3e1a-b48a-51ae30486c5f', 'vtreutel', 'Tad', '627c49c8f8e0a01cd96039e4eb679badb48704fd', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d2fe1882-8f9c-3e55-86d8-def28ed0041c', 'runolfsdottir.alexandrine', 'Rachelle', 'd80d7ab587bf49f7674b5d34d25e43d696b43600', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d4dfa069-9a62-381a-985a-db376ef77c20', 'dudley.mclaughlin', 'Sunny', 'f64e20ed3b25f0998c3e61494881869321010b17', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d53d094a-6575-380e-8f41-78e1dfdf710f', 'dominic.ernser', 'Rubie', '4488a3e0b86dd09b3cabebeee3ca602d29e8058f', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d687c796-e1c4-3b49-b73f-6723d017709e', 'frederic.little', 'Beth', 'db95e7ae6d931ab947361bd66b78a6e798c76c29', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d712fd33-9271-33f9-9131-9b1a025b4d48', 'immanuel92', 'Evelyn', '94827a3c4eb07297dee8612938fff38b207dd68d', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d885e7ac-5bd2-34a0-bf43-a61546682278', 'hudson.jayde', 'Margie', 'd3b01a83f444dee1f5e8b6cd3b306681d8826960', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d8a3d606-48f4-39b6-acf4-1443a923c391', 'blarson', 'Chelsey', 'b6f556c1b807ed36bd8d44db80cd0a920a1b4a8c', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('d90e9a6b-29b7-3efe-9271-c1ab5aa0b20a', 'demario99', 'Christine', '42d9855d3d46bb0d6f359ad3cb944dfd99e92095', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('dc1b17c0-4839-3c5b-b937-e3831079684e', 'lemke.cleo', 'Shawna', '43e35168a88be860b70008a5c533a06e8674b22b', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('dda18dfd-fc36-30b7-b330-e44006c7ee2c', 'collin.wilderman', 'Milo', '573a38ab92bc466052e80db1b990ce936702c699', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('dda277fe-1fe8-39a4-848c-ef583c610f5e', 'padberg.ava', 'Gladys', '74105f76b03563e461fce9149793e57420fe6a2d', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('dea9b3af-1845-39f0-8764-e5823793b6a7', 'althea.ondricka', 'Candice', 'af2d7c7e1ad56be4f9cae92d47c52750f8d0ec2a', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('e12c91c1-bbed-3d52-bff6-5c0e495aa7a3', 'lia41', 'Shanelle', '7c168c34ff294452818e9c6f245a02e09ce5ceff', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('e3f1967b-ed28-3144-bfd4-1bb84fa000aa', 'ghagenes', 'Leonie', 'd513eaf9dffae099183c798b73d5fed999eb6bc8', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('e6603f97-21e4-361f-8c0b-1e14ed10b775', 'xschneider', 'Vena', '0a3bfd80d3c982e931c0ce9d3e088cdeb0d8a0c3', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('e79eb8cc-bb7d-3c58-a1d6-86f0e179caba', 'georgiana.bashirian', 'Nicholas', 'ea9a007a6a464f9da1456e920e6d5ff9befb1469', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('e9e572e8-5f0b-33f5-90df-5815c395be78', 'bosco.yolanda', 'Jayme', '36fc867522cfb2ab40411964f4751bd8dd14af2b', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('ea1c3b9b-2a3e-3ea7-bc51-974cd7d6847e', 'ocarter', 'Lenore', '689d9dfcf8ce22286a3dddb518651b73b0afa89c', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('ea2f364d-d90e-3373-9866-0122ad067e03', 'bettye.abernathy', 'Brando', '82a0e9e6fb1ce065f54b96c243a43be599985235', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('ebed39f6-5f7f-3913-b69b-a4c761c97945', 'wreynolds', 'Rosina', '33763775700e409b5610d46172398207b5c23532', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('ef5216ca-db14-34b8-b903-a20d4fc183d7', 'bmueller', 'Kory', '08e7f249247d6b092c55a3349b7a36216b13b7c9', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('f1fcae48-f4ce-3674-a294-e4e75a3f8de4', 'rahul97', 'Addie', 'b737158ab58aab70da76fb02ba1baed1ce5aed5a', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('f2355fd1-9be9-3a16-8b3a-ab82c191eb81', 'adooley', 'Cheyanne', '1ffced343ef568e06e414b861e3c25c76334cb9a', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('f3020d12-0d87-306a-9c55-0af2d05af801', 'amara46', 'Forrest', '61fd8cdb7d87d2dfcb963abdbfe2f7b706ca1046', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('f5fe68a5-a096-3525-a6fd-53d23689d6b4', 'rosemarie.stanton', 'Doyle', 'bf2e49870150d697a589cfc63277ab835ebcea36', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('fc3ea413-1e18-3abf-8ad4-f89379297503', 'damian59', 'Kurt', '853c810cd9e7a7c7a35fc2b66a106e6bdaf26ae3', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('fda886fd-9683-3cf5-9604-7ef2fb2068e2', 'roxanne67', 'Terrance', '43dbf3b2d1bfc532ba4a07463a01953bf3e9da77', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('ff5a5be3-12a5-3a2a-bb3c-6b967b886207', 'trisha13', 'Ruth', 'd655c4d04bfb00363599a751a052f61bc0e56203', '');
INSERT INTO `user` (`user_id`, `username`, `name`, `password`, `public_key`) VALUES ('47b6b871-26ce-43f3-9dc0-17a6ccb0505a', 'luuk', 'Luuk Esselbrugge', '0f52c4659c484a859996e4e3dce7c32b1d9b04085ae517b6da19a3bca3b262b1', 'KEY');
#
# TABLE STRUCTURE FOR: user_matches
#

DROP TABLE IF EXISTS `user_matches`;

CREATE TABLE `user_matches` (
  `chat_id` varchar(64) NOT NULL,
  `user_id_first` varchar(64) NOT NULL,
  `user_id_second` varchar(64) NOT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

