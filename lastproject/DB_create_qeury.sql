use gottclass6;
-- ========================================================================================================
-- create table
CREATE TABLE `Admin` (
  `adminId` varchar(20) NOT NULL,
  `adminPwd` varchar(100) NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Answer` (
  `aId` varchar(30) NOT NULL,
  `aDate` datetime NOT NULL,
  `aContent` varchar(1000) NOT NULL,
  `qId` varchar(30) NOT NULL,
  PRIMARY KEY (`aId`),
  KEY `Answer_qId_fk_idx` (`qId`),
  CONSTRAINT `Answer_qId_fk` FOREIGN KEY (`qId`) REFERENCES `QnA` (`qId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `AnswerUpfiles` (
  `qnAupfileId` varchar(100) NOT NULL,
  `qnAupfileName` varchar(100) NOT NULL,
  `qnAupfileBase64` mediumtext,
  `qnAupfileEXT` varchar(10) NOT NULL,
  `aId` varchar(30) NOT NULL,
  PRIMARY KEY (`qnAupfileId`),
  KEY `AnswerUpfiles_aId_fk_idx` (`aId`),
  CONSTRAINT `AnswerUpfiles_aId_fk` FOREIGN KEY (`aId`) REFERENCES `Answer` (`aId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Answer` (
  `aId` varchar(30) NOT NULL,
  `aDate` datetime NOT NULL,
  `aContent` varchar(1000) NOT NULL,
  `qId` varchar(30) NOT NULL,
  PRIMARY KEY (`aId`),
  KEY `Answer_qId_fk_idx` (`qId`),
  CONSTRAINT `Answer_qId_fk` FOREIGN KEY (`qId`) REFERENCES `QnA` (`qId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Brand` (
  `brandId` varchar(10) NOT NULL,
  `brandName` varchar(100) NOT NULL,
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Cart` (
  `cartNo` int(11) NOT NULL AUTO_INCREMENT,
  `cartSesId` varchar(300) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `cartSesLimt` datetime DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `memberId` varchar(40) DEFAULT NULL,
  `productId` varchar(10) NOT NULL,
  PRIMARY KEY (`cartNo`),
  KEY `Cart_productId_fk` (`productId`),
  KEY `Cart_memberId_fk` (`memberId`),
  CONSTRAINT `Cart_memberId_fk` FOREIGN KEY (`memberId`) REFERENCES `Member` (`memberId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `Cart_productId_fk` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `DescImgs` (
  `descImgsId` varchar(12) NOT NULL,
  `descImgs` varchar(100) NOT NULL,
  `productId` varchar(10) NOT NULL,
  PRIMARY KEY (`descImgsId`),
  KEY `DescImgs_productId_fk_idx` (`productId`),
  CONSTRAINT `DescImgs_productId_fk` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `DescImgs` (
  `descImgsId` varchar(12) NOT NULL,
  `descImgs` varchar(100) NOT NULL,
  `productId` varchar(10) NOT NULL,
  PRIMARY KEY (`descImgsId`),
  KEY `DescImgs_productId_fk_idx` (`productId`),
  CONSTRAINT `DescImgs_productId_fk` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 교환 테이블
CREATE TABLE `Exchange` (
  `exchangeNo` int(11) NOT NULL,
  `exchangeDate` datetime NOT NULL default now(),
  `exchangeStatus` varchar(300) NOT NULL,
  `exchangeReason` varchar(300) NOT NULL,
  `purchaseId` varchar(10) NOT NULL,
  PRIMARY KEY (`exchangeNo`),
  KEY `Exchange_purchaseId_fk_idx` (`purchaseId`),
  CONSTRAINT `Exchange_purchaseId_fk` FOREIGN KEY (`purchaseId`) REFERENCES `Purchase` (`purchaseId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 주문 취소
CREATE TABLE `OrderCancel` (
  `orderCancelNo` int(11) NOT NULL,
  `orderCancelDate` datetime NOT NULL default now(),
  `orderCancelStatus` varchar(300) NOT NULL,
  `orderCancelReason` varchar(300) NOT NULL,
  `purchaseId` varchar(10) NOT NULL,
  PRIMARY KEY (`orderCancelNo`),
  KEY `Exchange_purchaseId_fk_idx` (`purchaseId`),
  CONSTRAINT `OrderCancel_purchaseId_fk` FOREIGN KEY (`purchaseId`) REFERENCES `Purchase` (`purchaseId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Genre` (
  `genreId` varchar(10) NOT NULL,
  `genreName` varchar(100) NOT NULL,
  PRIMARY KEY (`genreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Member` (
  `memberId` varchar(40) NOT NULL,
  `memberPwd` varchar(100) NOT NULL,
  `memberName` varchar(100) NOT NULL,
  `memberMobile` varchar(100) DEFAULT NULL,
  `memberEmail` varchar(100) NOT NULL,
  `memberSesId` varchar(300) DEFAULT NULL,
  `memberSesLimit` varchar(300) DEFAULT NULL,
  `memberPoint` int(11) NOT NULL,
  `memberAge` varchar(300) DEFAULT NULL,
  `memberGender` varchar(300) NOT NULL,
  `memberProviderType` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`memberId`),
  UNIQUE KEY `memberMobile_UNIQUE` (`memberMobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Member` (
  `memberId` varchar(40) NOT NULL,
  `memberPwd` varchar(100) NOT NULL,
  `memberName` varchar(100) NOT NULL,
  `memberMobile` varchar(100) DEFAULT NULL,
  `memberEmail` varchar(100) NOT NULL,
  `memberSesId` varchar(300) DEFAULT NULL,
  `memberSesLimit` varchar(300) DEFAULT NULL,
  `memberPoint` int(11) NOT NULL,
  `memberAge` varchar(300) DEFAULT NULL,
  `memberGender` varchar(300) NOT NULL,
  `memberProviderType` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`memberId`),
  UNIQUE KEY `memberMobile_UNIQUE` (`memberMobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Notice` (
  `noticeNo` varchar(20) NOT NULL,
  `noticeTitle` varchar(100) NOT NULL,
  `noticeWriteDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `noticeWriter` varchar(100) NOT NULL DEFAULT 'admin',
  `noticeViewCnt` int(11) NOT NULL DEFAULT '0',
  `noticeContent` varchar(1000) NOT NULL,
  `adminId` varchar(10) NOT NULL DEFAULT 'admin',
  PRIMARY KEY (`noticeNo`),
  KEY `Notice_adminId_fk` (`adminId`),
  CONSTRAINT `Notice_adminId_fk` FOREIGN KEY (`adminId`) REFERENCES `Admin` (`adminId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `NoticeUpfiles` (
  `noticeupfileId` varchar(1000) NOT NULL,
  `noticeupfileName` varchar(1000) NOT NULL,
  `noticeupfileBase64` mediumtext,
  `noticeupfileEXT` varchar(10) NOT NULL,
  `noticeNo` varchar(20) NOT NULL,
  KEY `NoticeUpfiles_noticeNo_fk_idx` (`noticeNo`),
  CONSTRAINT `NoticeUpfiles_noticeNo_fk` FOREIGN KEY (`noticeNo`) REFERENCES `Notice` (`noticeNo`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PointLog` (
  `pointLogId` varchar(15) NOT NULL,
  `pointLogDate` datetime NOT NULL,
  `pointpolicyId` varchar(5) NOT NULL,
  `memberId` varchar(20) NOT NULL,
  `purchaseId` varchar(12) NOT NULL,
  PRIMARY KEY (`pointLogId`),
  KEY `PointLog_memberId_fk_idx` (`memberId`),
  KEY `PointLog_purchaseId_fk_idx` (`purchaseId`),
  KEY `PointLog_pointPolicyId_fk_idx` (`pointpolicyId`),
  CONSTRAINT `PointLog_memberId_fk` FOREIGN KEY (`memberId`) REFERENCES `Member` (`memberId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PointLog_pointPolicyId_fk` FOREIGN KEY (`pointpolicyId`) REFERENCES `Pointpolicy` (`pointpolicyId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PointLog_purchaseId_fk` FOREIGN KEY (`purchaseId`) REFERENCES `Purchase` (`purchaseId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Pointpolicy` (
  `pointpolicyId` varchar(5) NOT NULL,
  `pointpolicyWhy` varchar(30) DEFAULT NULL,
  `pointpolicyHowmuch` int(11) DEFAULT NULL,
  PRIMARY KEY (`pointpolicyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Product` (
  `productId` varchar(10) NOT NULL,
  `productName` varchar(100) NOT NULL,
  `productPrice` int(11) NOT NULL,
  `players` varchar(30) DEFAULT NULL,
  `age` varchar(30) DEFAULT NULL,
  `productModel` varchar(100) NOT NULL,
  `productCompany` varchar(100) NOT NULL,
  `productCountry` varchar(100) NOT NULL,
  `productQuality` varchar(200) NOT NULL,
  `productWarning` varchar(500) NOT NULL,
  `productStock` int(11) NOT NULL,
  `brandId` varchar(10) NOT NULL,
  PRIMARY KEY (`productId`),
  KEY `Product_brandId_fk_idx` (`brandId`),
  CONSTRAINT `Product_brandId_fk` FOREIGN KEY (`brandId`) REFERENCES `Brand` (`brandId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ProductGenre` (
  `genreRecode` varchar(10) NOT NULL,
  `productId` varchar(10) NOT NULL,
  `genreId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`genreRecode`),
  KEY `ProductGenre_productId_fk_idx` (`productId`),
  KEY `ProductGenre_genreId_fk_idx` (`genreId`),
  CONSTRAINT `ProductGenre_genreId_fk` FOREIGN KEY (`genreId`) REFERENCES `Genre` (`genreId`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `ProductGenre_productId_fk` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ProductGenre` (
  `genreRecode` varchar(10) NOT NULL,
  `productId` varchar(10) NOT NULL,
  `genreId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`genreRecode`),
  KEY `ProductGenre_productId_fk_idx` (`productId`),
  KEY `ProductGenre_genreId_fk_idx` (`genreId`),
  CONSTRAINT `ProductGenre_genreId_fk` FOREIGN KEY (`genreId`) REFERENCES `Genre` (`genreId`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `ProductGenre_productId_fk` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ProductQnA` (
  `productQnAId` int(11) NOT NULL,
  `productQnATitle` varchar(100) NOT NULL,
  `productQnAContent` varchar(1000) NOT NULL,
  `productQnADate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `productQnAStatus` varchar(50) NOT NULL,
  `memberId` varchar(20) NOT NULL,
  `productId` varchar(10) NOT NULL,
  PRIMARY KEY (`productQnAId`),
  KEY `ProductQnA_memberId_fk` (`memberId`),
  KEY `ProductQnA_productId_fk` (`productId`),
  CONSTRAINT `ProductQnA_memberId_fk` FOREIGN KEY (`memberId`) REFERENCES `Member` (`memberId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `ProductQnA_productId_fk` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Purchase` (
  `purchaseId` varchar(12) NOT NULL,
  `purchaseDate` varchar(100) NOT NULL DEFAULT 'now()',
  `usedPoint` varchar(300) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `memo` varchar(300) DEFAULT NULL,
  `cartList` varchar(1000) NOT NULL,
  `ShippingAddrId` varchar(20) NOT NULL,
  `paidPrice` int(11) NOT NULL,
  PRIMARY KEY (`purchaseId`),
  KEY `Purchase_shippingAddrId_fk_idx` (`ShippingAddrId`),
  CONSTRAINT `Purchase_shippingAddrId_fk` FOREIGN KEY (`ShippingAddrId`) REFERENCES `ShippingAddr` (`shippingAddrId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Purchase` (
  `purchaseId` varchar(12) NOT NULL,
  `purchaseDate` varchar(100) NOT NULL DEFAULT 'now()',
  `usedPoint` varchar(300) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `memo` varchar(300) DEFAULT NULL,
  `cartList` varchar(1000) NOT NULL,
  `ShippingAddrId` varchar(20) NOT NULL,
  `paidPrice` int(11) NOT NULL,
  PRIMARY KEY (`purchaseId`),
  KEY `Purchase_shippingAddrId_fk_idx` (`ShippingAddrId`),
  CONSTRAINT `Purchase_shippingAddrId_fk` FOREIGN KEY (`ShippingAddrId`) REFERENCES `ShippingAddr` (`shippingAddrId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `QnA` (
  `qId` varchar(30) NOT NULL,
  `qDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `qTitle` varchar(100) NOT NULL,
  `qCategory` varchar(100) NOT NULL,
  `qContent` varchar(100) NOT NULL,
  `qStatus` varchar(100) NOT NULL DEFAULT '응답전',
  `memberId` varchar(40) NOT NULL,
  PRIMARY KEY (`qId`),
  KEY `QnA_memberId_fk_idx` (`memberId`),
  CONSTRAINT `QnA_memberId_fk` FOREIGN KEY (`memberId`) REFERENCES `Member` (`memberId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `QnAUpfiles` (
  `qnAupfileId` varchar(100) NOT NULL,
  `qnAupfileName` varchar(100) NOT NULL,
  `qnAupfileBase64` mediumtext,
  `qnAupfileEXT` varchar(10) NOT NULL,
  `qId` varchar(30) NOT NULL,
  PRIMARY KEY (`qnAupfileId`),
  KEY `QnAUpfiles_qid_fk_idx` (`qId`),
  CONSTRAINT `QnAUpfiles_qid_fk` FOREIGN KEY (`qId`) REFERENCES `QnA` (`qId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Review` (
  `reviewNo` int(11) NOT NULL,
  `reviewContent` varchar(500) NOT NULL,
  `reviewRate` float NOT NULL DEFAULT '5',
  `reviewWriteTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reviewImg` varchar(300) DEFAULT NULL,
  `productId` varchar(10) NOT NULL,
  `memberId` varchar(20) NOT NULL,
  PRIMARY KEY (`reviewNo`),
  KEY `Review_memberId_fk` (`memberId`),
  KEY `Review_productId_fk` (`productId`),
  CONSTRAINT `Review_memberId_fk` FOREIGN KEY (`memberId`) REFERENCES `Member` (`memberId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `Review_productId_fk` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ShippingAddr` (
  `shippingAddrId` varchar(20) NOT NULL,
  `shippingAddrNickName` varchar(100) NOT NULL,
  `shippingAddrPostNo` varchar(100) NOT NULL,
  `shippingAddrMain` varchar(100) NOT NULL,
  `shippingAddrDetail` varchar(100) NOT NULL,
  `shippingAddrMobile` varchar(100) NOT NULL,
  `shippingReceiver` varchar(100) NOT NULL,
  `memberId` varchar(40) NOT NULL,
  PRIMARY KEY (`shippingAddrId`),
  KEY `ShippingAddr_memberId_fk_idx` (`memberId`),
  CONSTRAINT `ShippingAddr_memberId_fk` FOREIGN KEY (`memberId`) REFERENCES `Member` (`memberId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ThumbImg` (
  `thumbImgId` int(11) NOT NULL AUTO_INCREMENT,
  `thumbImgURL` varchar(500) NOT NULL,
  `productId` varchar(10) NOT NULL,
  PRIMARY KEY (`thumbImgId`),
  KEY `ThumbImg_productId_fk_idx` (`productId`),
  CONSTRAINT `ThumbImg_productId_fk` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;



-- ========================================================================================================

-- 가데이터
insert into Pointpolicy values("PPI01", "회원가입", 3000);

insert into Brand values("B001", "코리아보드게임즈");

insert into Admin values("admin","1234");

-- member
insert into Member values("nalyoo",sha1(md5('@Qwer1234')), "유재석", "010-1234-6789", "nalyoo@muhan.com", null, null, 3000, 42, "M", null);
insert into Member values("doni",sha1(md5('@Qwer1234')), "정형돈", "010-9877-6789", "doni@muhan.com", null, null, 3000, 36, "M", null);
insert into Member values("nojji",sha1(md5('@Qwer1234')), "노홍철", "010-6781-6789", "nojji@muhan.com", null, null, 3000, 32, "M", null);
insert into Member values("gpark",sha1(md5('@Qwer1234')), "박명수", "010-2354-6789", "gpark@muhan.com", null, null, 3000, 44, "M", null);
insert into Member values("haha",sha1(md5('@Qwer1234')), "하하", "010-7968-6789", "haha@muhan.com", null, null, 3000, 32, "M", null);
insert into Member values("thewaygill",sha1(md5('@Qwer1234')), "하하", "010-3254-6789", "thewaygill@muhan.com", null, null, 3000, 36, "M", null);

insert into Member values("jangyoonjoo",sha1(md5('@Qwer1234')), "장윤주", "010-2398-6789", "jangyoonjoo@muhan.com", null, null, 3000, 31, "F", null);
insert into Member values("norajung",sha1(md5('@Qwer1234')), "정준하", "010-6598-6789", "junjunha@muhan.com", null, null, 3000, 43, "N", null);


-- product
insert into Product values("prod001", "하나야마 캐스트퍼즐 키2(KEY 2) LV2/한글판", 15000, "1명", "8세 이상", "하나야마 캐스트퍼즐 키2(KEY 2) LV2" , "생각투자", "중국", "손상이 있거나 제품의 성능에 이상이 있는 상태로 유통된 제품은 교환/환불 가능. 관련법 및 소비자분쟁해결기준에 따름.", "파손되거나 변형된 제품은 안전을 위해 사용하지 마세요. 화재의 위험이 있으니 절대로 불 가까운 곳에서 사용하지 마세요. 삼키면 질식할 위험이 있으니 절대로 내용물을 입에 넣지 마세요.", "500", "B002");


-- Notice
select * from Notice;

insert into Notice (noticeNo, noticeTitle, noticeContent ) values("N001", "교환정책", "교환 정책을 준수 하겠습니다");
insert into Notice (noticeNo, noticeTitle, noticeContent ) values("N002", "배송정책", "배송 정책을 준수 하겠습니다");
insert into Notice (noticeNo, noticeTitle, noticeContent ) values("N003", "환불정책", "환불 정책을 준수 하겠습니다");

-- QnA
select * from QnA;
-- qId 는 일자 + 아이디 + QA + 숫자값 로 처리하기
insert into QnA(qId, qTitle, qCategory, qContent,  memberId) values ('230424ericHan12QA002', "상품문의", "상품", "뱅 추가 상품은 없나요",  "ericHan12" );
insert into QnA(qId, qTitle, qCategory, qContent,  memberId) values ('230424doniQA001', "배송이 왜케 느려요", "배송", "어제 구매했는데 왜 안와요",  "doni" );
insert into QnA(qId, qTitle, qCategory, qContent,  memberId) values ('230424sky123QA003', "환불해줘요", "환불", "아그냥 해줘요",  "sky123" );

select count(*) cnt from QnA;

-- Answer
select * from Answer;
insert into Answer(aId, aContent, qId) values ('230425QAN001', '뱅에 는 추가상품이 없습니다', '230424ericHan12QA002');
insert into Answer(aId, aContent, qId) values ('230424doniQAN002', '모든 배송은 주문후 3일 이내에 완료됩니다', '230424doniQA001');
insert into Answer(aId, aContent, qId) values ('230424sky123QAN003', '모든 배송은 주문후 3일 이내에 완료됩니다', '230424sky123QA003');

update QnA set qStatus = '응답됨' where qId = '230424ericHan12QA002';

select * from QnA ;
select * from QnA order by qDate limit 0,1;

select * from QnA where memberId  like '%doni%' order by qDate desc limit 0,10 ;


update QnA set qnaTitle = '제목이렇게수정하나' and qnaCategory = 'shipping' and qnaContent = '<p>어질 어질 하다!</p><p><br></p><p>와! 태그 안가져 온다!</p> ' where qnaId = '230509gpark0018';

update QnA set qnaTitle = '어지뤄어!' , qnaCategory = 'product' , qnaContent = '<p>어질 어질 하다!</p><p><br></p><p>과연 
and 연산 이었던건가</p>' where qnaId = '230509gpark0021' ;

commit;


-- ========================================================================================================

-- 테이블 조회
select * from Admin ;
select * from Answer ;
select * from AnswerUpfiles ;
select * from Brand ;
select * from Cart ;
select * from DescImgs ;
select * from Exchange ;
select * from Genre ;
select * from Member ;
select * from Notice ;
select * from NoticeUpfiles ;
select * from PointLog ;
select * from Product ;
select * from ProductGenre ;
select * from ProductQnA ;
select * from Purchase ;
select * from Pointpolicy ;
select * from QnA ;
select * from QnAUpfiles ;
select * from Review ;
select * from ShippingAddr ;
select * from ThumbImg ;

