--CREATE DATABASE IF NOT EXISTS`security_db`;

use security_db;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
  id                    VARCHAR(64)  NOT NULL PRIMARY KEY COMMENT '主键',
  username              VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  password              VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  status                CHAR(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  email                 VARCHAR(64) DEFAULT NULL COMMENT '邮箱',
  phone                 VARCHAR(32) DEFAULT NULL COMMENT '手机号',
  gender                CHAR(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  avatar                VARCHAR(128) DEFAULT NULL COMMENT '头像',
  user_type             CHAR(1) NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  create_time           TIMESTAMP  DEFAULT NULL COMMENT '创建时间',
  update_time           TIMESTAMP  DEFAULT NULL COMMENT '更新时间',
  del_flag              INT(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）'
);
INSERT INTO sys_user(id, username, password)
values ('1','John', '$2a$10$nASuuDJtguszkZoFQwK5yO9rsgxItceiPIIUMfGond32sx.pNIX.q'); -- password: 123

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role
(
  user_id       varchar(128) NOT NULL COMMENT '用户id',
  role_id       varchar(128) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (user_id,role_id)
) COMMENT='用户与角色关联表';
INSERT INTO sys_user_role(user_id, role_id)
values ('1','1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission (
  id                varchar(128) NOT NULL PRIMARY KEY,
  name              varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '权限名',
  status            char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  code              varchar(100) DEFAULT NULL COMMENT '权限代码',
  create_time       TIMESTAMP DEFAULT NULL,
  update_time       TIMESTAMP DEFAULT NULL,
  remark            varchar(500) DEFAULT NULL COMMENT '备注'
) COMMENT='权限表';
INSERT INTO sys_permission(id, name, code)
values ('1','部门管理','system:dept:list'), ('2','薪资管理','system:salary:list');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id                varchar(128) NOT NULL PRIMARY KEY,
  name              varchar(128) DEFAULT NULL COMMENT '角色名字',
  status            char(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  create_time       TIMESTAMP DEFAULT NULL,
  update_time       TIMESTAMP DEFAULT NULL,
  remark            varchar(500) DEFAULT NULL COMMENT '备注'
) COMMENT='角色表';
INSERT INTO sys_role(id, name)
values ('1','CEO'), ('2','HR');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission (
  role_id               varchar(128) NOT NULL COMMENT '角色ID',
  permission_id         varchar(128) NOT NULL DEFAULT '0' COMMENT '菜单id',
  PRIMARY KEY (role_id,permission_id)
) COMMENT='角色与权限关联表';
INSERT INTO sys_role_permission(role_id, permission_id)
values ('1','1'), ('1','2'), ('2','2');

SET FOREIGN_KEY_CHECKS = 1;