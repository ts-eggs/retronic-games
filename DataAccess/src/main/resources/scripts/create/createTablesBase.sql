use [ts_test]
go

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
        fkAdminId int null,
        fkRoleId int not null,
        fkCountryId int not null,
		constraint pk_user primary key (id),
		constraint fk_user_user foreign key (fkAdminId) references [rgc].[user](id),
		constraint fk_user_role foreign key (fkRoleId) references [rgc].[role](id),
		constraint fk_user_country foreign key (fkCountryId) references [rgc].[country](id)
	)
end
go