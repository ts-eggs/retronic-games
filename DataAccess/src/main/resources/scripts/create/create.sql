DECLARE @dbname nvarchar(128)
SET @dbname = N'ts_test'

if(not exists (select name from master.dbo.sysdatabases where ('[' + name + ']' = @dbname OR name = @dbname)))
begin
	create database ts_test
end
go

USE [ts_test]
GO

CREATE USER [eggs\ts] FOR LOGIN [EGGS\ts] WITH DEFAULT_SCHEMA=[rgc]
GO

if not exists (select * from sys.schemas where name = 'rgc')
begin
	exec('create schema rgc')
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgc' and table_name = 'country'))
begin
	create table [rgc].[country] (
	    id int identity(1,1) not null,
		[name] varchar(128) not null,
		[domain] varchar(128) not null,
		constraint pk_country primary key (id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgc' and table_name = 'role'))
begin
	create table [rgc].[role] (
		id int identity(1,1) not null,
		name varchar(255) not null,
		accessLevel int default 10,
		constraint pk_role primary key (id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgc' and table_name = 'user'))
begin
	create table [rgc].[user] (
		id int identity(1,1) not null,
		login varchar(64) not null,
        [password] varchar(64) not null,
        access bit not null default 0,
        loginTrials int not null default 0,
        sex int null,
        firstname varchar(64) null,
        lastname varchar(64) null,
        street varchar(128) null,
        citycode varchar(16) null,
        city varchar(128) null,
        lastlogin datetime null,
        created datetime null,
        fkRoleId int not null,
        fkCountryId int not null,
		constraint pk_user primary key (id),
		constraint fk_user_role foreign key (fkRoleId) references [rgc].[role](id),
		constraint fk_user_country foreign key (fkCountryId) references [rgc].[country](id)
	)
end
go

if not exists (select * from sys.schemas where name = 'rgh')
begin
	exec('create schema rgh')
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'skill'))
begin
	create table [rgh].[skill] (
		id int identity(1,1) not null,
		name varchar(64),
		[description] varchar(1024),
		[level] int default 1,
		[action] int default 0,
		value float default 0,
		multi bit default 0,
		permanent bit default 0,
		constraint pk_skill primary key (id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'race'))
begin
	create table [rgh].[race] (
		id int identity(1,1) not null,
		name varchar(64),
		skillId int not null,
		constraint pk_race primary key (id),
		constraint fk_race_skill foreign key (skillId) references [rgh].[skill](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'class'))
begin
	create table [rgh].[class] (
		id int identity(1,1) not null,
		name varchar(64),
		constraint pk_class primary key (id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'classSkillRefs'))
begin
	create table [rgh].[classSkillRefs] (
		skillId int not null,
		classId int not null,
		constraint pk_classSkillRefs primary key (skillId, classId),
		constraint fk_classSkillRefs_skill foreign key (skillId) references [rgh].[skill](id),
		constraint fk_classSkillRefs_class foreign key (classId) references [rgh].[class](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'armor'))
begin
	create table [rgh].[armor] (
		id int identity(1,1) not null,
		name varchar(64),
		[description] varchar(1024),
		[level] int default 1,
		hands int default 1,
		costs int default 1,
		value float default 1,
		skillId int null,
		constraint pk_armor primary key (id),
		constraint fk_armor_skill foreign key (skillId) references [rgh].[skill](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'weapon'))
begin
	create table [rgh].[weapon] (
		id int identity(1,1) not null,
		name varchar(64),
		[description] varchar(1024),
		[level] int default 1,
		hands int default 1,
		costs int default 1,
		value float default 1,
		skillId int null,
		constraint pk_weapon primary key (id),
		constraint fk_weapon_skill foreign key (skillId) references [rgh].[skill](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'item'))
begin
	create table [rgh].[item] (
		id int identity(1,1) not null,
		name varchar(64),
		[description] varchar(1024),
		[level] int default 1,
		costs int default 1,
		value float default 1,
		skillId int null,
		constraint pk_item primary key (id),
		constraint fk_item_skill foreign key (skillId) references [rgh].[skill](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'character'))
begin
	create table [rgh].[character] (
		id int not null,
		name varchar(64),
		fixed bit default 0,
		[level] int not null default 1,
		strength int not null default 1,
		vitality int not null default 1,
		dexterity int not null default 1,
		raceId int not null,
		classId int not null,
		armorHeadId int null,
		armorBodyId int null,
		weaponRightId int null,
		weaponLeftId int null,
		constraint pk_player primary key (id),
		constraint fk_character_race foreign key (raceId) references [rgh].[race](id),
		constraint fk_character_class foreign key (classId) references [rgh].[class](id),
		constraint fk_character_armor_head foreign key (armorHeadId) references [rgh].[armor](id),
		constraint fk_character_armor_body foreign key (armorBodyId) references [rgh].[armor](id),
		constraint fk_character_weapon_right foreign key (weaponRightId) references [rgh].[weapon](id),
		constraint fk_character_weapon_left foreign key (weaponLeftId) references [rgh].[weapon](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'characterItemRefs'))
begin
	create table [rgh].[characterItemRefs] (
		characterId int not null,
		itemId int not null,
		constraint pk_characterItemRefs primary key (characterId, itemId),
		constraint fk_characterItemRefs_character foreign key (characterId) references [rgh].[character](id),
		constraint fk_characterItemRefs_item foreign key (itemId) references [rgh].[item](id)
	)
end
go