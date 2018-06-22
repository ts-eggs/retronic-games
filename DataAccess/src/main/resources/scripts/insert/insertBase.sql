use [ts_test]
go

delete from rgc.[country]
delete from rgc.[role]
go

insert into rgc.country (name, domain) values ('Deutschland', 'de')
insert into rgc.country (name, domain) values ('Großbritannien', 'uk')
go

insert into rgc.[role] (name, accessLevel) values ('System', 100)
insert into rgc.[role] (name, accessLevel) values ('Admin', 80)
insert into rgc.[role] (name, accessLevel) values ('Operator', 60)
insert into rgc.[role] (name, accessLevel) values ('Advanced', 40)
insert into rgc.[role] (name, accessLevel) values ('Base', 20)
go

insert into rgc.[user] (login, password, created, access, fkRoleId, fkCountryId)
values ('system', 'bfae66cd2c9309b33e954ed8605644e165c5642abf2938aab4590a37b3ce9b9a', GETDATE(), 1, (select id from rgc.[role] where name = 'System'), 1)
insert into rgc.[user] (login, password, created, access, fkAdminId, fkRoleId, fkCountryId)
values ('admin_ts', '652b25c27d8fe26e4380f7007470fe9072067b811d3e0f7d0fe0863b1afdcbb9', GETDATE(), 1, (select id from rgc.[user] where login = 'system'), 2, 1)
insert into rgc.[user] (login, password, created, access, fkAdminId, fkRoleId, fkCountryId)
values ('user_ts', 'e9ffa976db6c29306637d2962c5d0cf2b116f303c29955a0526b5d50a0e87185', GETDATE(), 1, (select id from rgc.[user] where login = 'admin_ts'), 4, 1)
go