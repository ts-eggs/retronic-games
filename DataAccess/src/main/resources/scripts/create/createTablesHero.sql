use [ts_test]
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
		fkSkillId int not null,
		constraint pk_race primary key (id),
		constraint fk_race_skill foreign key (fkSkillId) references [rgh].[skill](id)
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
		fkSkillId int not null,
		fkClassId int not null,
		constraint pk_classSkillRefs primary key (fkSkillId, fkClassId),
		constraint fk_classSkillRefs_skill foreign key (fkSkillId) references [rgh].[skill](id),
		constraint fk_classSkillRefs_class foreign key (fkClassId) references [rgh].[class](id)
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
		fkSkillId int null,
		constraint pk_armor primary key (id),
		constraint fk_armor_skill foreign key (fkSkillId) references [rgh].[skill](id)
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
		fkSkillId int null,
		constraint pk_weapon primary key (id),
		constraint fk_weapon_skill foreign key (fkSkillId) references [rgh].[skill](id)
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
		fkSkillId int null,
		constraint pk_item primary key (id),
		constraint fk_item_skill foreign key (fkSkillId) references [rgh].[skill](id)
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
		fkUserId int null,
		fkRaceId int not null,
		fkClassId int not null,
		constraint pk_character primary key (id),
		constraint fk_character_user foreign key (fkUserId) references [rgc].[user](id),
		constraint fk_character_race foreign key (fkRaceId) references [rgh].[race](id),
		constraint fk_character_class foreign key (fkClassId) references [rgh].[class](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'characterArmorRefs'))
begin
	create table [rgh].[characterArmorRefs] (
		fkCharacterId int not null,
		fkArmorId int not null,
		constraint pk_characterArmorRefs primary key (fkCharacterId, fkArmorId),
		constraint fk_characterArmorRefs_character foreign key (fkCharacterId) references [rgh].[character](id),
		constraint fk_characterArmorRefs_armor foreign key (fkArmorId) references [rgh].[armor](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'characterWeaponRefs'))
begin
	create table [rgh].[characterWeaponRefs] (
		fkCharacterId int not null,
		fkWeaponId int not null,
		constraint pk_characterWeaponRefs primary key (fkCharacterId, fkWeaponId),
		constraint fk_characterWeaponRefs_character foreign key (fkCharacterId) references [rgh].[character](id),
		constraint fk_characterWeaponRefs_weapon foreign key (fkWeaponId) references [rgh].[weapon](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'characterItemRefs'))
begin
	create table [rgh].[characterItemRefs] (
		fkCharacterId int not null,
		fkItemId int not null,
		constraint pk_characterItemRefs primary key (fkCharacterId, fkItemId),
		constraint fk_characterItemRefs_character foreign key (fkCharacterId) references [rgh].[character](id),
		constraint fk_characterItemRefs_item foreign key (fkItemId) references [rgh].[item](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'game'))
begin
	create table [rgh].[game] (
		id int not null,
		name varchar(64),
		difficult int not null default 1,
		secret varchar(64),
		fkUserId int null,
		constraint pk_game primary key (id),
		constraint fk_game_user foreign key (fkUserId) references [rgc].[user](id)
	)
end
go

if(not exists (select * from information_schema.tables where table_schema = 'rgh' and table_name = 'gameCharacterRef'))
begin
	create table [rgh].[gameCharacterRef] (
		fkGameId int not null,
		fkCharacterId int not null,
		constraint pk_gameCharacterRef primary key (fkGameId, fkCharacterId),
		constraint fk_gameCharacterRef_game foreign key (fkGameId) references [rgh].[game](id),
		constraint fk_gameCharacterRef_character foreign key (fkCharacterId) references [rgh].[character](id)
	)
end
go