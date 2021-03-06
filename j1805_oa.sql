-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: j1805_oa
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `call_borad_notice`
--

DROP TABLE IF EXISTS `call_borad_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `call_borad_notice` (
  `id` varchar(36) NOT NULL,
  `content` longtext,
  `release_time` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `write_time` datetime(6) DEFAULT NULL,
  `author_user_id` varchar(36) DEFAULT NULL,
  `type_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4vq051hm46tn7yeu8j2j23smk` (`author_user_id`),
  KEY `FKtmp30j3ppr2hj3g5ix2pt9a8m` (`type_id`),
  CONSTRAINT `FK4vq051hm46tn7yeu8j2j23smk` FOREIGN KEY (`author_user_id`) REFERENCES `id_user` (`id`),
  CONSTRAINT `FKtmp30j3ppr2hj3g5ix2pt9a8m` FOREIGN KEY (`type_id`) REFERENCES `call_borad_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `call_borad_notice`
--

LOCK TABLES `call_borad_notice` WRITE;
/*!40000 ALTER TABLE `call_borad_notice` DISABLE KEYS */;
INSERT INTO `call_borad_notice` VALUES ('46d78d24-95b5-4c2d-b677-69bfcfeadb6e','<p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: \"Microsoft Yahei\"; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">11月17日，习近平亚太之行进入APEC时间。当天上午，亚太经合组织（APEC）工商领导人峰会先期举行。邀请中、美、俄等大国领导人发表演讲是峰会的传统。习近平连续第六年在这一场合发表了主旨演讲。他的演讲，是从一艘船开始的。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: \"Microsoft Yahei\"; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">1习近平以船开篇 发表主旨演讲</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: \"Microsoft Yahei\"; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920765677.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/f/f7/f7256609eab9e9795788a459ad2fa011.jpg?imageView&thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p>','2018-11-18 00:55:32.771000','RECALL','为何之前已经处理的违章，现在又变成未处理了？2','2018-11-18 00:54:52.382000','27f1cea1-ca3e-4f92-907e-f468b9d871fd','80d70885-1615-428d-8170-f5b92cae7bd7'),('6e5b779f-7b07-433d-a3fb-98b75b1c9178','<p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\"><br>国家副主席王岐山14日至16日在湖北调研。他强调，要深入贯彻习近平新时代中国特色社会主义思想和党的十九大精神，自觉践行绿色发展，牢固树立文化自信，推进经济社会全面可持续发展。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"王岐山考察丹江口水库 饮用刚打上来的水\" src=\"http://cms-bucket.nosdn.127.net/catchpic/8/81/8132afeed49ff1eed0e9c6ce2e8495aa.jpg?imageView&amp;thumbnail=550x0\" width=\"550\" height=\"358\" style=\"vertical-align: top;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"王岐山考察丹江口水库 饮用刚打上来的水\" src=\"http://cms-bucket.nosdn.127.net/catchpic/a_/ad/ad09c439b474c204b30aa0dec44c5f97.jpg?imageView&amp;thumbnail=550x0\" width=\"550\" height=\"396\" style=\"vertical-align: top;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">王岐山先后来到武汉、十堰，考察湖北省博物馆等文博单位，了解文物保护和文化传承情况，前往丹江口水库，考察南水北调工程建设、库区生态环境保护、移民安置和脱贫攻坚等情况，并听取当地践行新发展理念情况介绍。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\"><b>王岐山考察南水北调工程</b></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\"><b>试喝丹江口水库清水</b></p>','2018-11-18 01:06:26.409000','RELEASED',' 交友 王岐山考察丹江口水库 饮用刚打上来的水','2018-11-18 01:06:01.871000','27f1cea1-ca3e-4f92-907e-f468b9d871fd','50d307c0-a266-46e8-80bf-4925829d7c74'),('87117e38-4a23-4fdc-b1c8-f9ff38182102','<p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><br><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920723511.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/8/8a/8ab29a7a87eee78d75e1a3b7604ce4d8.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">11月17日，习近平亚太之行进入APEC时间。当天上午，亚太经合组织（APEC）工商领导人峰会先期举行。邀请中、美、俄等大国领导人发表演讲是峰会的传统。习近平连续第六年在这一场合发表了主旨演讲。他的演讲，是从一艘船开始的。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">1习近平以船开篇 发表主旨演讲</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920765677.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/f/f7/f7256609eab9e9795788a459ad2fa011.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△17日上午的峰会就是在这条太平洋探索者号（Pacific Explorer）上举行的。（央视记者王萌萌拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">习近平的演讲从开会的这艘船讲起。他说，“在当今世界经济风起云涌、风险挑战凸显的形势下，我们同在一条船上，一起谋划发展合作，具有特别的意义”。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">演讲结束时，他又提到了船。“刚才登船时，望着广袤无边的大海，我想到了‘同舟共济’这个词。让我们握稳舵盘、把准航向、齐力划桨、乘风破浪，共同驾驶着世界经济的大船驶向更加美好的彼岸！”</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920789187.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/8/86/8671567f3fa46c547eb96bf2f86e40f5.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△习近平步入会场。他当天发表的主旨演讲，题目是《同舟共济创造美好未来》。（新华社记者丁林拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">在演讲中，习近平提到了十多天前的首届中国国际进口博览会。在进博会开幕式讲话中，习近平提到了“大海论”。这一次，他又讲起了“大船论”。他以大船开篇作喻，强调世界经济发展要坚持开放、发展、包容、创新、规则导向。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920809801.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/f/f8/f836aded88b1971c278c0a8d361c6132.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△APEC会议新闻中心，各国记者凝神倾听习近平的演讲。（央视记者李晓周拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">习近平的“大船论”，发出了掷地有声的中国声音。在这篇4500多字的讲话中，他20次使用了“不能”“不会”“不是”“不应该”“不可能”这些词。《时政新闻眼》摘录了几句，一起来听听。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">——如果人为设置壁垒，切断各国经济上的密切联系，不仅违背经济规律和历史潮流，也不符合各国人民普遍愿望，既是短视的，也是不会成功的。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">——科技创新成果不应该被封锁起来，不应该成为只为少数人牟利的工具。设立知识产权制度的目的是保护和激励创新，而不是制造甚至扩大科技鸿沟。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△【独家V观】“一带一路”是中国同世界共享机遇 共谋发展的阳光大道</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920841680.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/3/38/3811359eaaa8ad29247a3a59ce74fba2.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△参加峰会的各代表团随行人员正在通过直播信号收看习主席演讲。（央视记者骆魏拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">继续来听：</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">——规则应该由国际社会共同制定，而不是谁的胳膊粗、气力大谁就说了算。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">——遇到分歧应该通过协商解决，不能搞小圈子，不能强加于人。历史告诉我们，如果走上对抗的道路，无论是冷战、热战还是贸易战，都不会有真正的赢家。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">——共建“一带一路”，没有地缘政治目的，不针对谁也不排除谁，不会关起门来搞小圈子，不是有人说的这样那样的所谓“陷阱”。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">习近平的这一番话，“有人”是得好好听一听。6年前的APEC会议上，习近平就首次提出了“亚太命运共同体”。既然命运与共，就得坚持开放、发展、包容、创新、规则导向。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920877607.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/6/64/64fb7ea9f27eb09c0bfb99ed50281c64.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△国外记者出镜报道习主席演讲（央视记者李晓周拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">《时政新闻眼》还想补充一个细节。这并非习近平第一次在APEC工商领导人峰会主旨演讲中以船作比喻。三年前，他在马尼拉就说过，“面对世界经济中的激流险滩，亚太这艘巨轮必须校准航向、把好舵盘，亚太各经济体必须勇于担当、同舟共济，努力推动全球增长”。两年前，在秘鲁利马，他又提到“同舟共济”。他说，“面对风险和挑战，各方要发扬同舟共济、合作共赢的伙伴精神”。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">2大船开到了莫尔斯比港</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">再来说说APEC期间三艘大船的故事。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">举办APEC会议的巴新首都莫尔斯比港，得名于英国殖民者费尔法克斯·莫尔斯比海军上将，这里是巴新的政治、经济和交通中心。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920904429.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/7/75/756f8e59ee3666a61769fba156b829de.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△莫尔斯比港游艇俱乐部（央视记者包佳节拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">会议前夕，三艘大型邮轮如约而至。最先到达的一艘叫太平洋珍宝号（Pacific Jewel），一些参会代表团和媒体记者下榻在这里，其中就包括《时政新闻眼》的部分记者。现在，就跟随我们的脚步，一起登船看看吧。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920927702.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/e/ea/ea135e823699982cbb3f51192be7afcb.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△太平洋珍宝号邮轮（央视记者包佳节拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920946926.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/e/ef/ef45062e1fbcf9253e13e1797398cbf0.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△这里是珍宝号的登船入口。（央视记者王聪拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920958292.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/5/51/51c6df5adbde47dc7c278c9757804aff.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△珍宝号的大堂（央视记者包佳节拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366920984102.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/b/b9/b96e510f571165f021472c1394185e44.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△珍宝号上的电影院（央视记者王聪拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921004707.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/0/02/0289d97c9ff07471430c18eb2f9c24e8.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△珍宝号的船顶（央视记者王聪拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921020298.gif\" src=\"http://cms-bucket.nosdn.127.net/catchpic/7/79/79572344653d0b11055fa3caecfe6b61.gif?imageView&amp;thumbnail=550x0&amp;tostatic=0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△《时政新闻眼》的同事在船上偶然间拍到了一位神秘“访客”，一条海豚。（央视外籍工程师Daffi Akhtar拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">没过几天，珍宝号来了一艘姊妹船，叫太平洋探索者号（Pacific Explorer）。17日的APEC工商领导人峰会就是在这艘船上举行。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921088760.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/a/ab/abaf3f283e667d2015706a57171a66bc.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△好不容易拍到了一个同框照，左边是探索者号，右边是珍宝号。（央视记者包佳节拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921102234.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/3/3a/3ae2538c532dab00e0c840d458eabcb1.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△从珍宝号看探索者号（央广记者潘毅拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921111454.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/2/25/256c4adac4aca69a1fc4f5b85f532fb7.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△从探索者号看珍宝号（央视记者包佳节拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921135297.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/e/e8/e8956315c38768b15b18b731ca975aca.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△探索者号于1997年投入使用。共有客舱999间，可容纳近2000名乘客。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">这次还有另一艘叫海洋公主号（Sea Princess）的邮轮也来到了莫尔斯比港，据说停泊在海湾的另一面。很遗憾，《时政新闻眼》没能拍到。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921165020.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/6/68/683ece4bf6429c43ae389a7021cc43fa.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△站在这里，可以远远望到两艘邮轮。靠左边的是珍宝号，右边是探索者号。（央视记者包佳节拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">3下午转场APEC大厦 处处可见“船”的身影</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">17日下午，习近平出席了APEC领导人同工商咨询理事会代表对话会。这也是这些年他参加APEC会议的惯例。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921180960.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/d/d9/d93bcda440c07e745d6b3a45edad2d8b.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△媒体记者集体进入APEC会场（央视记者张淳拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921216920.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/8/8d/8d4fe7c85f89575c15ec0f459795b22b.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△据说，APEC大厦的造型创意来自于当地一种古代商船——蟹爪船。（央视记者张晓鹏拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921236433.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/b/b0/b07e800f61be3668cc5490e5ed31af39.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△APEC大厦前方的雕塑（央视记者王梦拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921251884.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/d/de/de4f6afb27cc66301541ad1a7ed314a1.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△APEC大厦的造型是不是和这种古代的蟹爪船很像呢？这是在机场展台拍到的蟹爪船模型。（央视记者包佳节拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">当天下午，习近平在APEC大厦一楼参加了APEC领导人同工商咨询理事会代表对话会。他同代表们就构建开放型世界经济、共建“一带一路”等交换看法。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921276756.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/b/b5/b5c27d694021d2284bd65b8c6c6f74aa.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△这是对话会开始前的会场。会标上的“ABAC”是APEC工商咨询理事会的英文简称。（央视记者章猛拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921293679.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/8/83/835ae36bd40acbd2c8d99f109c50d43b.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△习近平还参加了ABAC对话会的分组讨论。这是讨论开始前的会场。（央视记者杨立峰拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921302369.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/b/bd/bd7547a09323ff608556376191f0804a.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△注意到没有，会场的墙上挂着这幅当地的画作，主题是船。（央视记者杨立峰拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921324667.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/6/6c/6cf872a31ef8847367df7de2442b8c21.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△18日上午，习近平将出席在大厦二楼举行的APEC第二十六次领导人非正式会议。会场的后方，也陈列着一艘当地的古船。（央视记者拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">当天，习近平还会见了智利总统皮涅拉、韩国总统文在寅、印度尼西亚总统佐科等三位领导人，并出席了APEC第二十六次领导人非正式会议的欢迎晚宴。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921344170.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/f/f2/f2829f9e36d88cd7a15249ade13b2447.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△晚霞如约而至，迎接各方贵宾。央视记者李晓周从中国代表团下榻酒店拍摄。</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921362852.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/5/5c/5ce4d4dd7f238e576bb80f1767961c9f.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△晚宴前乐队演练（央视记者张淳拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; color: rgb(64, 64, 64); text-align: center;\"><img alt=\"习近平:共同驾驶世界经济大船驶向美好彼岸\" oldsrc=\"W020181118366921382819.jpg\" src=\"http://cms-bucket.nosdn.127.net/catchpic/9/96/962788261100788a6c8e7316192ce80f.jpg?imageView&amp;thumbnail=550x0\" style=\"vertical-align: top; margin-right: auto; margin-left: auto; max-width: 640px; display: block;\"></p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">△这是晚宴前集体合影。（新华社记者谢环驰拍摄）</p><p style=\"margin-top: 28px; margin-bottom: 0px; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 28px; font-family: &quot;Microsoft Yahei&quot;; text-indent: 2em; color: rgb(64, 64, 64); text-align: justify;\">18日上午，APEC第二十六次领导人非正式会议将在莫尔斯比港APEC大厦举行，习近平将参加合影、对话会、全会、工作午宴等多场活动，中央广播电视总台《时政新闻眼》将持续为您带来更多幕后消息。</p>',NULL,'RECALL','习近平:共同驾驶世界经济大船驶向美好彼岸','2018-11-17 20:57:24.527000','27f1cea1-ca3e-4f92-907e-f468b9d871fd','50d307c0-a266-46e8-80bf-4925829d7c74');
/*!40000 ALTER TABLE `call_borad_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `call_borad_notice_read`
--

DROP TABLE IF EXISTS `call_borad_notice_read`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `call_borad_notice_read` (
  `id` varchar(36) NOT NULL,
  `read_time` datetime(6) DEFAULT NULL,
  `notice_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs3eb6qxqxpxbqhn711uu4acup` (`notice_id`),
  KEY `FK2hiebul30yuipu60cepyrag64` (`user_id`),
  CONSTRAINT `FK2hiebul30yuipu60cepyrag64` FOREIGN KEY (`user_id`) REFERENCES `id_user` (`id`),
  CONSTRAINT `FKs3eb6qxqxpxbqhn711uu4acup` FOREIGN KEY (`notice_id`) REFERENCES `call_borad_notice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `call_borad_notice_read`
--

LOCK TABLES `call_borad_notice_read` WRITE;
/*!40000 ALTER TABLE `call_borad_notice_read` DISABLE KEYS */;
INSERT INTO `call_borad_notice_read` VALUES ('a2d6ebdd-ce7f-42c5-95d3-bbb3a7404593','2018-11-18 01:18:26.346000','6e5b779f-7b07-433d-a3fb-98b75b1c9178','28b04736-3d07-48c8-b188-ba44d73bdc5b'),('efca02a0-af4b-454d-92b9-494ba6013dd1','2018-11-18 01:16:59.375000','6e5b779f-7b07-433d-a3fb-98b75b1c9178','27f1cea1-ca3e-4f92-907e-f468b9d871fd');
/*!40000 ALTER TABLE `call_borad_notice_read` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `call_borad_type`
--

DROP TABLE IF EXISTS `call_borad_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `call_borad_type` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `call_borad_type`
--

LOCK TABLES `call_borad_type` WRITE;
/*!40000 ALTER TABLE `call_borad_type` DISABLE KEYS */;
INSERT INTO `call_borad_type` VALUES ('4cb7eed0-0bae-4a2c-8ea9-c8513bca0f11','招聘公告'),('50d307c0-a266-46e8-80bf-4925829d7c74','学习交流'),('80d70885-1615-428d-8170-f5b92cae7bd7','人事任命'),('9d2ac6e8-e2b5-4d7a-956e-8477acd1bf3b','团建活动'),('ec5cd38a-5f34-4343-a9a4-a0d0c5ef1ea5','内部培训');
/*!40000 ALTER TABLE `call_borad_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disk_file_item`
--

DROP TABLE IF EXISTS `disk_file_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disk_file_item` (
  `id` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `file_info_id` varchar(36) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `upload_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd3sre3t14cw2sbmm874wnocha` (`file_info_id`),
  KEY `FKavmxfm2i04bsrqgfle6r4o0sq` (`parent_id`),
  KEY `FKn92ldiowi1obcq3v4bdan2nya` (`user_id`),
  CONSTRAINT `FKavmxfm2i04bsrqgfle6r4o0sq` FOREIGN KEY (`parent_id`) REFERENCES `disk_file_item` (`id`),
  CONSTRAINT `FKd3sre3t14cw2sbmm874wnocha` FOREIGN KEY (`file_info_id`) REFERENCES `storage_file` (`id`),
  CONSTRAINT `FKn92ldiowi1obcq3v4bdan2nya` FOREIGN KEY (`user_id`) REFERENCES `id_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disk_file_item`
--

LOCK TABLES `disk_file_item` WRITE;
/*!40000 ALTER TABLE `disk_file_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `disk_file_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disk_quota`
--

DROP TABLE IF EXISTS `disk_quota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disk_quota` (
  `id` varchar(255) NOT NULL,
  `total_spaces` bigint(20) NOT NULL,
  `used_spaces` bigint(20) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3a3ejvxh89osdinaymv7wrbm0` (`user_id`),
  CONSTRAINT `FK3a3ejvxh89osdinaymv7wrbm0` FOREIGN KEY (`user_id`) REFERENCES `id_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disk_quota`
--

LOCK TABLES `disk_quota` WRITE;
/*!40000 ALTER TABLE `disk_quota` DISABLE KEYS */;
/*!40000 ALTER TABLE `disk_quota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_department`
--

DROP TABLE IF EXISTS `hr_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_department` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` double DEFAULT NULL,
  `manager_id` varchar(36) DEFAULT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5i3ts1fhnb2aws3mdj68v7ou` (`manager_id`),
  KEY `FKdt4sdtabohxwdah2u38y370ie` (`parent_id`),
  CONSTRAINT `FK5i3ts1fhnb2aws3mdj68v7ou` FOREIGN KEY (`manager_id`) REFERENCES `hr_employee` (`id`),
  CONSTRAINT `FKdt4sdtabohxwdah2u38y370ie` FOREIGN KEY (`parent_id`) REFERENCES `hr_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_department`
--

LOCK TABLES `hr_department` WRITE;
/*!40000 ALTER TABLE `hr_department` DISABLE KEYS */;
INSERT INTO `hr_department` VALUES ('394409f1-5b74-4199-a6d9-e0475e750d76','手机',10000000,'d036698d-3c84-41ca-8b97-5d9c257fd933','78c2a834-8c12-4523-8e0c-1e7e28abdd81'),('62ef42e1-752d-4134-8c07-b24ff349777a','测试部',10000000,'c010cc4d-e881-4baa-b765-79ff37104beb',NULL),('78c2a834-8c12-4523-8e0c-1e7e28abdd81','小米',50000000,'90529b0d-db06-4b97-880d-08d89046302b',NULL),('7a9c19a6-f53f-479c-a29f-eac6c3ca57be','人事部',30000000,'316f936b-291d-433a-a9c0-d7efa02600ea',NULL),('b130b692-5a13-4dec-8c0c-b4fdced69e86','软件开发部',40000000,'90529b0d-db06-4b97-880d-08d89046302b',NULL),('ed57b8a0-5130-48a7-bb4d-11d2f4ef20bf','财务部',20000000,'e09a9bae-4971-4ff0-a766-f9a7607db64b',NULL);
/*!40000 ALTER TABLE `hr_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_employee`
--

DROP TABLE IF EXISTS `hr_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_employee` (
  `id` varchar(36) NOT NULL,
  `join_time` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKej9l1p52uidr5u882u1dgsdvo` (`user_id`),
  CONSTRAINT `FKej9l1p52uidr5u882u1dgsdvo` FOREIGN KEY (`user_id`) REFERENCES `id_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_employee`
--

LOCK TABLES `hr_employee` WRITE;
/*!40000 ALTER TABLE `hr_employee` DISABLE KEYS */;
INSERT INTO `hr_employee` VALUES ('316f936b-291d-433a-a9c0-d7efa02600ea',NULL,NULL,'2c733000-16b5-4d73-8e6e-da379f2f102b'),('90529b0d-db06-4b97-880d-08d89046302b',NULL,NULL,'3acd0082-3bf3-4ef3-941f-685afa1b2c77'),('c010cc4d-e881-4baa-b765-79ff37104beb',NULL,NULL,'27f1cea1-ca3e-4f92-907e-f468b9d871fd'),('d036698d-3c84-41ca-8b97-5d9c257fd933',NULL,NULL,'7c4075ee-0f8b-4f89-92b6-35b54a5fd9fb'),('e09a9bae-4971-4ff0-a766-f9a7607db64b',NULL,NULL,'28b04736-3d07-48c8-b188-ba44d73bdc5b');
/*!40000 ALTER TABLE `hr_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_employee_department`
--

DROP TABLE IF EXISTS `hr_employee_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_employee_department` (
  `department_id` varchar(36) DEFAULT NULL,
  `employee_id` varchar(36) NOT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FK77ge74gy6gyv95bm69jxqkmsr` (`department_id`),
  CONSTRAINT `FK77ge74gy6gyv95bm69jxqkmsr` FOREIGN KEY (`department_id`) REFERENCES `hr_department` (`id`),
  CONSTRAINT `FKim5lehbnxg3eqm91hcfyxr61d` FOREIGN KEY (`employee_id`) REFERENCES `hr_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_employee_department`
--

LOCK TABLES `hr_employee_department` WRITE;
/*!40000 ALTER TABLE `hr_employee_department` DISABLE KEYS */;
INSERT INTO `hr_employee_department` VALUES ('394409f1-5b74-4199-a6d9-e0475e750d76','d036698d-3c84-41ca-8b97-5d9c257fd933'),('62ef42e1-752d-4134-8c07-b24ff349777a','c010cc4d-e881-4baa-b765-79ff37104beb'),('78c2a834-8c12-4523-8e0c-1e7e28abdd81','90529b0d-db06-4b97-880d-08d89046302b'),('7a9c19a6-f53f-479c-a29f-eac6c3ca57be','316f936b-291d-433a-a9c0-d7efa02600ea'),('ed57b8a0-5130-48a7-bb4d-11d2f4ef20bf','e09a9bae-4971-4ff0-a766-f9a7607db64b');
/*!40000 ALTER TABLE `hr_employee_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_role`
--

DROP TABLE IF EXISTS `id_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_role` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `role_key` varchar(255) DEFAULT NULL,
  `fixed` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_role`
--

LOCK TABLES `id_role` WRITE;
/*!40000 ALTER TABLE `id_role` DISABLE KEYS */;
INSERT INTO `id_role` VALUES ('252401e8-dca3-4015-976c-0cf99b2349b3','项目经理','PM',_binary '\0'),('2558d20d-aabe-4f16-8845-69fb50a9e6dd','人事经理','HR',_binary '\0'),('45e1f633-fede-4635-9b54-b42259dfa082','超级管理员','ADMIN',_binary '\0'),('cead9252-1f7d-4ac2-adfa-b447b8c5e0be','普通用户','USER',_binary '');
/*!40000 ALTER TABLE `id_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_user`
--

DROP TABLE IF EXISTS `id_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_user` (
  `id` varchar(36) NOT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `expired_time` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nxxv0v5ks1ndp8w5uxod2d13` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_user`
--

LOCK TABLES `id_user` WRITE;
/*!40000 ALTER TABLE `id_user` DISABLE KEYS */;
INSERT INTO `id_user` VALUES ('10a76781-e650-48a9-a298-1158b142acbb','111','11111111111','{S}$e0801$pxntMOcfNJjl+UdE2F/Lgu1zTUsLOJpqrnl/k/0/kq/sy3TxTvWvfyBiP1krhoD8mXbg1R/03LR5FLXhP9obDw==$m1LXwd1lh5lAjjwb1s1HqmQZSY/BYRJDAw/bw2Dq5Jo=','2019-01-30 03:07:19.277000','NORMAL'),('21024043-bd11-42e7-b595-d2ffcd4cd117','user5','user5','{S}$e0801$RbkEIXgxqFEiqiLfbvf6LJNXJ+Qg4JsvRu4s4yR875Hv2/Q2LEm2KbTOBJhMniXL718Acggala8h44QrPabvXA==$6MFfCpn1aPLSnogZqMxAyjAw2efyoFp0Ec46vTrUQIc=','2019-01-26 02:34:45.963000','NORMAL'),('27f1cea1-ca3e-4f92-907e-f468b9d871fd','guanyu','肖大大','$2a$10$nUMi2gKnXYn4wS7Mo0yuUusLUkyzY7H/hH5nQ6I23lNBlcPsMnZ02','2019-01-12 19:59:53.002000','NORMAL'),('28b04736-3d07-48c8-b188-ba44d73bdc5b','luochunlong','罗春龙','{S}$e0801$WrVY+15rUpAvx7y1RoKwdex9YbwLoJaPg5+FfIPMdt7Z5iY8JTLyrydYxSjyElg+TxlMQeA1qN8qz7yjk1PvCA==$p1J4p/N4VzGzRERzhNzuf+sxlS5+4yu+FBoIzMeNcy8=','2019-01-18 00:37:34.420000','NORMAL'),('2c1a4db7-cb66-4bb4-b05a-5a0834d7cd23','abcdef',NULL,'{S}$e0801$RgfCZq3AMJDAYkapNU5fW7O74nu0SGYSiHo0I8FXcA1FS8akHO8zbVeZC2+lkSOBKUVNOr8ZXSSf3SV1vCr6FQ==$4uoX24+0sIqsxOhYqnOrYLzxinCKibbj2DkeqL5rGrY=','2019-02-04 05:51:24.012000','NORMAL'),('2c733000-16b5-4d73-8e6e-da379f2f102b','ligang','李刚','{S}$e0801$kiRuKp/k1MYYMFmzBpaCrJZcbXN2AIYIEMkl+a6TGcJqwqIjwjwamkL+XtjZ71mC4IEXanzOr90NH4EgPmtxjg==$zCL9yLhVewSkJ/Fjcu0uqlkUx09zEjXoOWtDrF2+YCI=','2019-01-06 00:52:06.709000','NORMAL'),('3acd0082-3bf3-4ef3-941f-685afa1b2c77','leijun','雷军','{S}$e0801$NHoo7wLr8iGQ6GntlyOpWVEFVkUltOZMQww+taev/uQQY+pyC9TWm3AmOrqQzZCGU0LNFtMn7K4tpl9NgCyNhw==$ApbvvSTXaWyzBEVgBfZQX1kmhhmtZfhbCL2iyk2H3Vs=','2019-01-06 00:55:48.342000','NORMAL'),('4defbf09-1cc5-4ed7-9fd9-e37b867460d5','user8','user8','{S}$e0801$XRsR5Z/0rJaaHjijB+4+Zrj9PbzX+JDx6vnpzrfnxCE+46N7N6wLnwgg9OAihUYI1XoFzYV/GHOhGB4KCthkoQ==$apxAGvduvZboJ2MISXBpQv990PnVbXnmGQqJN64xSJA=','2019-01-29 19:43:05.749000','NORMAL'),('676a14eb-4858-4daa-a9db-54ac32957be9','user3','user3','{S}$e0801$h4mU4ctGDZ70qxJ+TqjYDNLzLZ+jr0yKjsunLphNLRjjNGdaZpf+oUYsVBbRzUzeoXAOc2YjleUCsyKdv/tgnw==$7EDAuWpQQBtBHwnCClvoi+q73oC/++80RvpHVX07Kxk=','2019-01-26 02:33:08.729000','NORMAL'),('6cc3d7d4-0ae2-472f-a044-9a69655f3d8d','user4','user4','{S}$e0801$2wZtKbB/gmIZkBwex4j3HWOssUzSoyPi4R5V6SeTDoC7kCwplu0Rd+JataIFI12AnccjDF/QUqHJ0NkJhoBvIg==$PJHWpHmZIpHn8iPJxUyp9XQKA0HCS2jw1FyIqjaqvQQ=','2019-01-26 02:34:29.511000','NORMAL'),('70090976-0803-4acb-8a56-7fa8072a2529','huangyong','黄勇','asdf','2019-01-06 00:01:19.105000','DISABLED'),('771fd3ad-10a0-40bc-88ec-071e3180adfc','lwq','罗文强','1234','2019-01-06 00:06:05.028000','NORMAL'),('7c4075ee-0f8b-4f89-92b6-35b54a5fd9fb','liuqiangdong','刘强东','1234','2019-01-06 00:01:28.954000','NORMAL'),('8c34643b-f39f-4caf-a5ee-1541a3fec156','linus','林纳斯','1234','2019-01-06 00:02:24.527000','NORMAL'),('8ed69512-21e8-4b74-a403-b6dc50dca8be','user1','user1','{S}$e0801$bDLeYVOiR5/6bVto3o4BFF4pl0KqX5Gf2KGlV+NOLCykaVyEY82slSMHG8FiQte6z/r43NSWT8+N6ifPWKetJg==$XOlTO8eWOgld8ITCebRdcjSFbWBSOXWlirqYuuAxI/c=','2019-01-26 02:31:43.444000','NORMAL'),('958d0ede-cac7-4352-95a2-92c3986577d7','1112','11111111113','{S}$e0801$llfvSSJ5bMcCscLt0lZZkXbtbYR8M3nBqm74tVLo9gETfnC5EZQRa6L4sm+XRkdnNPFUJ2g6cESR1QyLju/AHA==$o+r+2oCSPhcoIDEZWFYuwAu60R7KZCzQ7X5uTG1KPe0=','2019-01-30 03:12:57.606000','NORMAL'),('9dfd2c95-577f-41e0-944d-c80772055b36','bill','比尔','1234','2019-01-06 00:04:48.812000','NORMAL'),('9e97fe45-0311-447b-871d-a75376b8e026','wanglaoji','王老吉','1234','2019-01-06 00:02:14.041000','NORMAL'),('aae853cd-27ae-49f8-80d3-12dfea30d514','luowenqiang','13924120301','{S}$e0801$IniylFKm9X+ekb88qlMOZvOTmo06v3wVGLJKAlOdF1mQq4FEhWQE2jKAzZkqyaHjYFzoJf8eh/TgzuqoJu+QeA==$qVppR2adPTT2qsRTGRS1gxSI1eGS9cNpSmYaxNd+n0k=','2019-01-30 03:13:23.001000','NORMAL'),('b696e71c-0424-4cb4-917b-fe087303e58f','zengyunyang','曾运扬','1234','2019-01-06 00:06:23.057000','NORMAL'),('c356301b-e4ad-4305-950e-b4acb2bd404c','lwq3','罗文强3','{S}$e0801$q8FgZcwBkhwo5OPvhW1YZS6lFhk9adXpY5MadHfJPZ6W+vvsndPiJMnDZHC9b7P5EueD9oOhNslf+Q5LE+rnYA==$C42dleoV3c6L/PMZYJCDeMXu1Aarg/5SEnOvsrBsmAw=','2019-01-26 02:25:15.673000','NORMAL'),('ca252289-43fb-4366-be8e-b38d24ae4bcc','13924120301','luowenqiang','{S}$e0801$j1xinBKIJjLYXMoFjAzInVj0o4opGtF4g6ArdJpXHkCmOuw7Hd3AXgKbT0M09F2Y5MeAqi5o2xHANChpmhYEzQ==$tEqDb+DP0+p/EecgCVYYcSGsp5u6P710iP+r47fR1Go=','2019-01-30 06:42:14.616000','NORMAL'),('cfbc234f-4cdf-4c6b-ace5-5a5e3d7a2879','lwq4','罗文强4','{S}$e0801$uUyZpQuacJ8V8LUjXGc1rJyNwgFldtyxAPmmnQbbvbqVbOWmP3djKEmfmS7q9854CYEhhBfG3MKA8M8brDnBAA==$Oxih0xTLHg8DFwEinto6khjrcLUSHa7dvaO5/Oobh/o=','2019-01-26 02:27:22.331000','NORMAL'),('f8dbdb40-0f44-4723-aea4-a878f0f1859d','mayun','马云','1234','2019-01-06 00:02:11.953000','NORMAL'),('f9fcaa4c-0242-440a-a5a3-e17e672f587a','jobs','乔布斯','1234','2019-01-06 00:01:47.336000','NORMAL');
/*!40000 ALTER TABLE `id_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_user_roles`
--

DROP TABLE IF EXISTS `id_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_user_roles` (
  `user_id` varchar(36) NOT NULL,
  `role_id` varchar(36) NOT NULL,
  KEY `FKh80ml6njlk5wd5390atq2e5xa` (`role_id`),
  KEY `FKh9mgpnsps7f37caf5konmr07g` (`user_id`),
  CONSTRAINT `FKh80ml6njlk5wd5390atq2e5xa` FOREIGN KEY (`role_id`) REFERENCES `id_role` (`id`),
  CONSTRAINT `FKh9mgpnsps7f37caf5konmr07g` FOREIGN KEY (`user_id`) REFERENCES `id_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_user_roles`
--

LOCK TABLES `id_user_roles` WRITE;
/*!40000 ALTER TABLE `id_user_roles` DISABLE KEYS */;
INSERT INTO `id_user_roles` VALUES ('b696e71c-0424-4cb4-917b-fe087303e58f','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('2c733000-16b5-4d73-8e6e-da379f2f102b','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('3acd0082-3bf3-4ef3-941f-685afa1b2c77','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('27f1cea1-ca3e-4f92-907e-f468b9d871fd','45e1f633-fede-4635-9b54-b42259dfa082'),('27f1cea1-ca3e-4f92-907e-f468b9d871fd','252401e8-dca3-4015-976c-0cf99b2349b3'),('27f1cea1-ca3e-4f92-907e-f468b9d871fd','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('28b04736-3d07-48c8-b188-ba44d73bdc5b','2558d20d-aabe-4f16-8845-69fb50a9e6dd'),('28b04736-3d07-48c8-b188-ba44d73bdc5b','45e1f633-fede-4635-9b54-b42259dfa082'),('28b04736-3d07-48c8-b188-ba44d73bdc5b','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('c356301b-e4ad-4305-950e-b4acb2bd404c','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('cfbc234f-4cdf-4c6b-ace5-5a5e3d7a2879','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('8ed69512-21e8-4b74-a403-b6dc50dca8be','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('676a14eb-4858-4daa-a9db-54ac32957be9','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('6cc3d7d4-0ae2-472f-a044-9a69655f3d8d','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('21024043-bd11-42e7-b595-d2ffcd4cd117','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('4defbf09-1cc5-4ed7-9fd9-e37b867460d5','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('10a76781-e650-48a9-a298-1158b142acbb','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('958d0ede-cac7-4352-95a2-92c3986577d7','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('aae853cd-27ae-49f8-80d3-12dfea30d514','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('ca252289-43fb-4366-be8e-b38d24ae4bcc','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('2c1a4db7-cb66-4bb4-b05a-5a0834d7cd23','cead9252-1f7d-4ac2-adfa-b447b8c5e0be');
/*!40000 ALTER TABLE `id_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgeupubdqncc1lpgf2cn4fqwbc` (`parent_id`),
  CONSTRAINT `FKgeupubdqncc1lpgf2cn4fqwbc` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('02e37861-3ad8-466c-96e9-83c8dd578657','通用服务',80000000,'LINK','#',NULL),('06169502-a4ae-4914-a67d-99da1366dd73','角色管理',30000000,'LINK','/identity/role','efa6367f-f8e6-4501-aef8-274a60c00281'),('06dee100-498e-49ff-ad12-dedf3c8b1fc7','假期类型',10000000,'LINK','/human-resource/leave/type','d103320c-1df6-4d34-91a7-846e91f7f89f'),('1655ba9a-8062-439a-916c-2f451e27c2d2','详情/修改',20000000,'BUTTON','/identity/user/[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}','d4a0807c-d0bd-4f7a-bcc6-922c6e5c0b2c'),('28bdd4b7-ea0f-4f1c-9377-e025a1e68721','添加',10000000,'BUTTON','/identity/user/add','d4a0807c-d0bd-4f7a-bcc6-922c6e5c0b2c'),('2fa226ff-64a2-4bbc-8497-235cf74f02fc','请假',20000000,'LINK','/workflow/instance/LeaveRequest','d103320c-1df6-4d34-91a7-846e91f7f89f'),('30d4e043-590e-41d9-970f-5352cc2b0d03','HelloWorld',10000000,'LINK','/workflow/instance/HelloWorld','f6fa4019-c3a0-4ff8-84ea-88f1fe870570'),('4cc22f15-c12f-478d-bb96-7b32a54a50bc','删除菜单',20000000,'BUTTON','/menu/[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}','536b02d4-16b3-4775-8a11-d4dc57fba12b'),('536b02d4-16b3-4775-8a11-d4dc57fba12b','菜单',20000000,'LINK','/menu','efa6367f-f8e6-4501-aef8-274a60c00281'),('5429d830-90f1-4395-bd5b-ae1129825d07','查看流程图',40000000,'LINK','/workflow/definition/image/*','5d4020cf-32d4-45b4-8dd0-daaef90e5247'),('5d4020cf-32d4-45b4-8dd0-daaef90e5247','流程定义',40000000,'BUTTON','/workflow/definition','efa6367f-f8e6-4501-aef8-274a60c00281'),('644383d7-6543-4330-a2b3-650cdd949728','文件管理',10000000,'LINK','/storage/file','02e37861-3ad8-466c-96e9-83c8dd578657'),('68b2310b-fe54-4032-b9f6-16a87efef4e3','启动流程实例',50000000,'BUTTON','/workflow/instance/*','5d4020cf-32d4-45b4-8dd0-daaef90e5247'),('6f1d7302-17e1-431c-8d62-b0cea75e3ec2','查询当前用户的菜单',30000000,'BUTTON','/menu/menus','536b02d4-16b3-4775-8a11-d4dc57fba12b'),('801775ac-462c-4139-8545-5bbaba9ad89c','查看流程',30000000,'LINK','/workflow/definition/*','5d4020cf-32d4-45b4-8dd0-daaef90e5247'),('82db2c52-dc7d-4a1f-81cc-f54eecc5df12','部门管理',5000000,'LINK','/human-resource/department','d103320c-1df6-4d34-91a7-846e91f7f89f'),('bf49456a-7f06-4a45-bff4-8396b0e38136','公告管理',20000000,'LINK','/notice','def76eef-251d-4206-a651-ed5f5a917daa'),('bf724302-a73b-4f37-9690-fdc9b778c58a','绩效',30000000,'LINK','#','d103320c-1df6-4d34-91a7-846e91f7f89f'),('cae689bd-0aa8-4a92-8085-2734a2f9d16d','资产',70000000,'LINK','#',NULL),('cdbc6e63-fdb1-48d4-a4ee-64130d3e8d4d','财务',30000000,'LINK','#',NULL),('ce669646-44b4-411d-8118-2928a1c76431','移动菜单',10000000,'BUTTON','/menu/move','536b02d4-16b3-4775-8a11-d4dc57fba12b'),('d103320c-1df6-4d34-91a7-846e91f7f89f','人事',20000000,'LINK','#',NULL),('d4a0807c-d0bd-4f7a-bcc6-922c6e5c0b2c','用户管理',10000000,'LINK','/identity/user','efa6367f-f8e6-4501-aef8-274a60c00281'),('dc13fbec-9e53-4b3d-964a-1553c909ce6f','激活流程',20000000,'LINK','/workflow/definition/active/*','5d4020cf-32d4-45b4-8dd0-daaef90e5247'),('def76eef-251d-4206-a651-ed5f5a917daa','公告',50000000,'LINK','#',NULL),('e6b40df9-487f-4b14-b364-65dce823d65d','禁用流程',10000000,'LINK','/workflow/definition/disable/*','5d4020cf-32d4-45b4-8dd0-daaef90e5247'),('e86e412e-5584-4e78-ac39-65cdca2b5dab','公告类型',10000000,'LINK','/notice/type','def76eef-251d-4206-a651-ed5f5a917daa'),('efa6367f-f8e6-4501-aef8-274a60c00281','系统管理',10000000,'LINK','#',NULL),('f6fa4019-c3a0-4ff8-84ea-88f1fe870570','测试流程的菜单',90000000,'LINK','#',NULL),('fa1a9ab8-3e03-49ce-92d7-094eed9e8f21','流程',40000000,'LINK','#',NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_roles`
--

DROP TABLE IF EXISTS `menu_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_roles` (
  `menu_id` varchar(36) NOT NULL,
  `roles_id` varchar(36) NOT NULL,
  KEY `FK1f1f89jvq66v62joip926nsag` (`roles_id`),
  KEY `FKq7k54hb6f3ngdbfpblwj68bhg` (`menu_id`),
  CONSTRAINT `FK1f1f89jvq66v62joip926nsag` FOREIGN KEY (`roles_id`) REFERENCES `id_role` (`id`),
  CONSTRAINT `FKq7k54hb6f3ngdbfpblwj68bhg` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_roles`
--

LOCK TABLES `menu_roles` WRITE;
/*!40000 ALTER TABLE `menu_roles` DISABLE KEYS */;
INSERT INTO `menu_roles` VALUES ('d4a0807c-d0bd-4f7a-bcc6-922c6e5c0b2c','45e1f633-fede-4635-9b54-b42259dfa082'),('28bdd4b7-ea0f-4f1c-9377-e025a1e68721','45e1f633-fede-4635-9b54-b42259dfa082'),('efa6367f-f8e6-4501-aef8-274a60c00281','45e1f633-fede-4635-9b54-b42259dfa082'),('d103320c-1df6-4d34-91a7-846e91f7f89f','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('cdbc6e63-fdb1-48d4-a4ee-64130d3e8d4d','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('fa1a9ab8-3e03-49ce-92d7-094eed9e8f21','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('def76eef-251d-4206-a651-ed5f5a917daa','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('536b02d4-16b3-4775-8a11-d4dc57fba12b','45e1f633-fede-4635-9b54-b42259dfa082'),('ce669646-44b4-411d-8118-2928a1c76431','45e1f633-fede-4635-9b54-b42259dfa082'),('4cc22f15-c12f-478d-bb96-7b32a54a50bc','45e1f633-fede-4635-9b54-b42259dfa082'),('1655ba9a-8062-439a-916c-2f451e27c2d2','45e1f633-fede-4635-9b54-b42259dfa082'),('cae689bd-0aa8-4a92-8085-2734a2f9d16d','2558d20d-aabe-4f16-8845-69fb50a9e6dd'),('cae689bd-0aa8-4a92-8085-2734a2f9d16d','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('06169502-a4ae-4914-a67d-99da1366dd73','45e1f633-fede-4635-9b54-b42259dfa082'),('644383d7-6543-4330-a2b3-650cdd949728','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('e86e412e-5584-4e78-ac39-65cdca2b5dab','45e1f633-fede-4635-9b54-b42259dfa082'),('e86e412e-5584-4e78-ac39-65cdca2b5dab','252401e8-dca3-4015-976c-0cf99b2349b3'),('bf49456a-7f06-4a45-bff4-8396b0e38136','45e1f633-fede-4635-9b54-b42259dfa082'),('bf49456a-7f06-4a45-bff4-8396b0e38136','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('6f1d7302-17e1-431c-8d62-b0cea75e3ec2','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('5d4020cf-32d4-45b4-8dd0-daaef90e5247','45e1f633-fede-4635-9b54-b42259dfa082'),('e6b40df9-487f-4b14-b364-65dce823d65d','45e1f633-fede-4635-9b54-b42259dfa082'),('dc13fbec-9e53-4b3d-964a-1553c909ce6f','45e1f633-fede-4635-9b54-b42259dfa082'),('801775ac-462c-4139-8545-5bbaba9ad89c','45e1f633-fede-4635-9b54-b42259dfa082'),('5429d830-90f1-4395-bd5b-ae1129825d07','2558d20d-aabe-4f16-8845-69fb50a9e6dd'),('5429d830-90f1-4395-bd5b-ae1129825d07','45e1f633-fede-4635-9b54-b42259dfa082'),('5429d830-90f1-4395-bd5b-ae1129825d07','252401e8-dca3-4015-976c-0cf99b2349b3'),('5429d830-90f1-4395-bd5b-ae1129825d07','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('2fa226ff-64a2-4bbc-8497-235cf74f02fc','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('f6fa4019-c3a0-4ff8-84ea-88f1fe870570','2558d20d-aabe-4f16-8845-69fb50a9e6dd'),('f6fa4019-c3a0-4ff8-84ea-88f1fe870570','45e1f633-fede-4635-9b54-b42259dfa082'),('f6fa4019-c3a0-4ff8-84ea-88f1fe870570','252401e8-dca3-4015-976c-0cf99b2349b3'),('f6fa4019-c3a0-4ff8-84ea-88f1fe870570','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('30d4e043-590e-41d9-970f-5352cc2b0d03','2558d20d-aabe-4f16-8845-69fb50a9e6dd'),('30d4e043-590e-41d9-970f-5352cc2b0d03','45e1f633-fede-4635-9b54-b42259dfa082'),('30d4e043-590e-41d9-970f-5352cc2b0d03','252401e8-dca3-4015-976c-0cf99b2349b3'),('30d4e043-590e-41d9-970f-5352cc2b0d03','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('68b2310b-fe54-4032-b9f6-16a87efef4e3','2558d20d-aabe-4f16-8845-69fb50a9e6dd'),('68b2310b-fe54-4032-b9f6-16a87efef4e3','45e1f633-fede-4635-9b54-b42259dfa082'),('68b2310b-fe54-4032-b9f6-16a87efef4e3','252401e8-dca3-4015-976c-0cf99b2349b3'),('68b2310b-fe54-4032-b9f6-16a87efef4e3','cead9252-1f7d-4ac2-adfa-b447b8c5e0be'),('82db2c52-dc7d-4a1f-81cc-f54eecc5df12','2558d20d-aabe-4f16-8845-69fb50a9e6dd'),('82db2c52-dc7d-4a1f-81cc-f54eecc5df12','45e1f633-fede-4635-9b54-b42259dfa082'),('bf724302-a73b-4f37-9690-fdc9b778c58a','2558d20d-aabe-4f16-8845-69fb50a9e6dd'),('bf724302-a73b-4f37-9690-fdc9b778c58a','45e1f633-fede-4635-9b54-b42259dfa082'),('06dee100-498e-49ff-ad12-dedf3c8b1fc7','45e1f633-fede-4635-9b54-b42259dfa082');
/*!40000 ALTER TABLE `menu_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage_file`
--

DROP TABLE IF EXISTS `storage_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storage_file` (
  `id` varchar(36) NOT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `upload_time` datetime(6) DEFAULT NULL,
  `owner_user_id` varchar(36) DEFAULT NULL,
  `fingerprint` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9108d41y6mdt1lsnsax34vplc` (`owner_user_id`),
  CONSTRAINT `FK9108d41y6mdt1lsnsax34vplc` FOREIGN KEY (`owner_user_id`) REFERENCES `id_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage_file`
--

LOCK TABLES `storage_file` WRITE;
/*!40000 ALTER TABLE `storage_file` DISABLE KEYS */;
INSERT INTO `storage_file` VALUES ('df01ab64-20de-46e7-b752-06268126a7aa','image/jpeg',180820,'00a16d1d96.jpg','16cc4245-9487-46f6-bba0-2815c9a39999','2018-11-15 02:06:10.490000','27f1cea1-ca3e-4f92-907e-f468b9d871fd',NULL);
/*!40000 ALTER TABLE `storage_file` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-09 17:38:19
