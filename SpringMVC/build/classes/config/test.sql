/*
MySQL Data Transfer
Source Host: localhost
Source Database: test
Target Host: localhost
Target Database: test
Date: 2017-10-11 23:51:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for d_user
-- ----------------------------
DROP TABLE IF EXISTS `d_user`;
CREATE TABLE `d_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `age` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `d_user` VALUES ('1', 'hello', '25');
INSERT INTO `d_user` VALUES ('2', 'fds', '11');
INSERT INTO `d_user` VALUES ('3', '1', '11');
INSERT INTO `d_user` VALUES ('4', '2', '11');
INSERT INTO `d_user` VALUES ('5', '2', '11');
INSERT INTO `d_user` VALUES ('6', '2', '11');
INSERT INTO `d_user` VALUES ('7', '2', '11');
INSERT INTO `d_user` VALUES ('8', '2', '11');
INSERT INTO `d_user` VALUES ('9', '2', '11');
INSERT INTO `d_user` VALUES ('10', '2', '11');
INSERT INTO `d_user` VALUES ('11', '2', '11');
INSERT INTO `d_user` VALUES ('12', '2', '11');
INSERT INTO `d_user` VALUES ('13', '2', '11');
INSERT INTO `d_user` VALUES ('14', '2', '11');
INSERT INTO `d_user` VALUES ('15', '2', '11');
INSERT INTO `d_user` VALUES ('16', '2', '11');
INSERT INTO `d_user` VALUES ('17', '2', '11');
INSERT INTO `d_user` VALUES ('18', '2', '11');
INSERT INTO `d_user` VALUES ('19', '2', '11');
INSERT INTO `d_user` VALUES ('20', '2', '11');
INSERT INTO `d_user` VALUES ('21', '2', '11');
INSERT INTO `d_user` VALUES ('22', '2', '11');
