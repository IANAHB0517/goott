use gottclass6;
CREATE TABLE `Member` (
	`membereId`	VARCHAR(20)	NOT NULL,
	`memberPwd`	VARCHAR(100)	NOT NULL,
	`memberName`	VARCHAR(100)	NOT NULL,
	`memberMobile`	VARCHAR(100)	NOT NULL,
	`memberEmail`	VARCHAR(100)	NOT NULL,
	`memberSesId`	VARCHAR(300)	NULL,
	`memberSesLimit`	VARCHAR(300)	NULL,
	`memberPoint`	INT	NOT NULL,
	`memberAge`	VARCHAR(300)	NULL,
	`memberGender`	VARCHAR(300)	NOT NULL,
	`memberProviderType`	VARCHAR(300)	NULL
);

CREATE TABLE gottclass6.`Product` (
	`productId`	VARCHAR(10)	NOT NULL,
	`productName`	VARCHAR(100)	NOT NULL,
	`productPrice`	INT	NOT NULL,
	`players`	VARCHAR(30)	NULL,
	`age`	VARCHAR(30)	NULL,
	`productModel`	VARCHAR(100)	NOT NULL,
	`productCompany`	VARCHAR(100)	NOT NULL,
	`productCountry`	VARCHAR(100)	NOT NULL,
	`productQuality`	VARCHAR(200)	NOT NULL,
	`productWarning`	VARCHAR(500)	NOT NULL,
	`productStock`	INT	NOT NULL,
	`brandId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `Admin` (
	`adminId`	VARCHAR(20)	NOT NULL,
	`adminPwd`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `Genre` (
	`genreId`	VARCHAR(10)	NOT NULL,
	`genreName`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `ProductGenre` (
	`genreRecode`	VARCHAR(10)	NOT NULL,
	`productId`	VARCHAR(10)	NOT NULL,
	`genreId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `ThumbImg` (
	`thumbImgId`	INT	NOT NULL,
	`thumbImgURL`	VARCHAR(500)	NOT NULL,
	`productId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `Brand` (
	`brandId`	VARCHAR(10)	NOT NULL,
	`brandName`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `ProductQnA` (
	`productQnAId`	INT	NOT NULL,
	`productQnATitle`	VARCHAR(100)	NOT NULL,
	`productQnAContent`	VARCHAR(1000)	NOT NULL,
	`productQnADate`	DATETIME	NOT NULL	DEFAULT now(),
	`productQnAStatus`	VARCHAR(50)	NOT NULL,
	`membereId`	VARCHAR(20)	NOT NULL,
	`productId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `Review` (
	`reviewNo`	INT	NOT NULL,
	`reviewContent`	VARCHAR(500)	NOT NULL,
	`reviewRate`	Float	NOT NULL	DEFAULT 5.0,
	`reviewWriteTime`	DATETIME	NOT NULL	DEFAULT now(),
	`reviewImg`	VARCHAR(300)	NULL,
	`productId`	VARCHAR(10)	NOT NULL,
	`membereId`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `Cart` (
	`cartNo`	INT	NOT NULL,
	`cartSesId`	VARCHAR(300)	NOT NULL,
	`quantity`	INT	NOT NULL,
	`price`	INT	NOT NULL,
	`cartSesLimt`	DATETIME	NULL,
	`status`	VARCHAR(10)	NOT NULL,
	`membereId`	VARCHAR(20)	NULL,
	`productId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `Purchase` (
	`purchaseId`	VARCHAR(12)	NOT NULL,
	`purchaseDate`	VARCHAR(100)	NOT NULL,
	`usedPoint`	VARCHAR(300)	NULL,
	`email`	VARCHAR(100)	NOT NULL,
	`memo`	VARCHAR(300)	NULL,
	`cartNo`	VARCHAR(10)	NOT NULL,
	`ShippingAddrId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `ShippingAddr` (
	`shippingAddrId`	VARCHAR(20)	NOT NULL,
	`shippingAddrNickName`	VARCHAR(100)	NOT NULL,
	`shippingAddrPostNo`	VARCHAR(100)	NOT NULL,
	`shippingAddrMain`	VARCHAR(100)	NOT NULL,
	`shippingAddrDetail`	VARCHAR(100)	NOT NULL,
	`shippingAddrMobile`	VARCHAR(100)	NOT NULL,
	`shippingReceiver`	VARCHAR(100)	NOT NULL,
	`membereId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `Exchange` (
	`exchangeNo`	INT	NOT NULL,
	`exchangeDate`	VARCHAR(300)	NOT NULL,
	`exchangeStatus`	VARCHAR(300)	NOT NULL,
	`exchangeReason`	VARCHAR(300)	NOT NULL,
	`purchaseId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `Pointpolicy` (
	`pointpolicyId`	VARCHAR(5)	NOT NULL,
	`pointpolicyWhy`	VARCHAR(30)	NULL,
	`pointpolicyHowmuch`	INT	NULL
);

CREATE TABLE `DescImgs` (
	`descImgsId`	VARCHAR(12)	NOT NULL,
	`descImgs`	VARCHAR(100)	NOT NULL,
	`productId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `Notice` (
	`noticeNo`	VARCHAR(20)	NOT NULL,
	`noticeTitle`	VARCHAR(100)	NOT NULL,
	`noticeWriteDate`	DATETIME	NOT NULL,
	`noticeWriter`	VARCHAR(100)	NOT NULL,
	`noticeViewCnt`	INT	NOT NULL	DEFAULT 0,
	`noticeContent`	VARCHAR(1000)	NOT NULL,
	`adminId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `NoticeUpfiles` (
	`noticeupfileId`	VARCHAR(1000)	NOT NULL,
	`noticeupfileName`	VARCHAR(1000)	NOT NULL,
	`noticeupfileBase64`	MEDIUMTEXT	NULL,
	`noticeupfileEXT`	VARCHAR(10)	NOT NULL,
	`noticeNo`	INT	NOT NULL
);

CREATE TABLE `QnA` (
	`qId`	VARCHAR(20)	NOT NULL,
	`qDate`	VARCHAR(100)	NOT NULL,
	`qTitle`	VARCHAR(100)	NOT NULL,
	`qCategory`	VARCHAR(100)	NOT NULL,
	`qContent`	VARCHAR(100)	NOT NULL,
	`qStatus`	VARCHAR(100)	NOT NULL,
	`membereId`	VARCHAR(10)	NOT NULL
);

CREATE TABLE `QnAUpfiles` (
	`qnAupfileId`	VARCHAR(100)	NOT NULL,
	`qnAupfileName`	VARCHAR(100)	NOT NULL,
	`qnAupfileBase64`	MEDIUMTEXT	NULL,
	`qnAupfileEXT`	VARCHAR(10)	NOT NULL,
	`qId`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `PointLog` (
	`pointLogId`	VARCHAR(15)	NOT NULL,
	`pointLogDate`	DATETIME	NOT NULL,
	`pointpolicyId`	VARCHAR(5)	NOT NULL,
	`membereId`	VARCHAR(20)	NOT NULL,
	`purchaseId`	VARCHAR(12)	NOT NULL
);

ALTER TABLE `Member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
	`membereId`
);

ALTER TABLE `Product` ADD CONSTRAINT `PK_PRODUCT` PRIMARY KEY (
	`productId`
);

ALTER TABLE `Admin` ADD CONSTRAINT `PK_ADMIN` PRIMARY KEY (
	`adminId`
);

ALTER TABLE `Genre` ADD CONSTRAINT `PK_GENRE` PRIMARY KEY (
	`genreId`
);

ALTER TABLE `ProductGenre` ADD CONSTRAINT `PK_PRODUCTGENRE` PRIMARY KEY (
	`genreRecode`
);

ALTER TABLE `ThumbImg` ADD CONSTRAINT `PK_THUMBIMG` PRIMARY KEY (
	`thumbImgId`
);

ALTER TABLE `Brand` ADD CONSTRAINT `PK_BRAND` PRIMARY KEY (
	`brandId`
);

ALTER TABLE `ProductQnA` ADD CONSTRAINT `PK_PRODUCTQNA` PRIMARY KEY (
	`productQnAId`
);

ALTER TABLE `Review` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
	`reviewNo`
);

ALTER TABLE `Cart` ADD CONSTRAINT `PK_CART` PRIMARY KEY (
	`cartNo`
);

ALTER TABLE `Purchase` ADD CONSTRAINT `PK_PURCHASE` PRIMARY KEY (
	`purchaseId`
);

ALTER TABLE `ShippingAddr` ADD CONSTRAINT `PK_SHIPPINGADDR` PRIMARY KEY (
	`shippingAddrId`
);

ALTER TABLE `Exchange` ADD CONSTRAINT `PK_EXCHANGE` PRIMARY KEY (
	`exchangeNo`
);

ALTER TABLE `Pointpolicy` ADD CONSTRAINT `PK_POINTPOLICY` PRIMARY KEY (
	`pointpolicyId`
);

ALTER TABLE `DescImgs` ADD CONSTRAINT `PK_DESCIMGS` PRIMARY KEY (
	`descImgsId`
);

ALTER TABLE `Notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (
	`noticeNo`
);

ALTER TABLE `NoticeUpfiles` ADD CONSTRAINT `PK_NOTICEUPFILES` PRIMARY KEY (
	`noticeupfileId`
);

ALTER TABLE `QnA` ADD CONSTRAINT `PK_QNA` PRIMARY KEY (
	`qId`
);

ALTER TABLE `QnAUpfiles` ADD CONSTRAINT `PK_QNAUPFILES` PRIMARY KEY (
	`qnAupfileId`
);

ALTER TABLE `PointLog` ADD CONSTRAINT `PK_POINTLOG` PRIMARY KEY (
	`pointLogId`
);

