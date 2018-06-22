use [ts_test]
go

-- SKILLS --
delete from rgh.skill
go

-- insert race skills
insert into rgh.skill ([level], name, [description], [action]) values (1, 'Betäubung', 'Der Gegner kann in dieser Kampfrunde keine Fähigkeiten einsetzen.', 13)
insert into rgh.skill ([level], name, [description], [action], value) values (1, 'Beritten', 'Der Spieler kann bei jeder Bewegeaktion bis zu 2 Felder vorrücken.', 7, 2)
insert into rgh.skill ([level], name, [description], [action], value) values (1, 'Ausweichen', 'Der Schaden des Gegners wird für diese Kampfrunde halbiert.', 2, 0.5)
insert into rgh.skill ([level], name, [description], [action]) values (1, 'Bastler', 'Der Spieler kann spezielle Rüstung anlegen.', 11)
go

-- insert class skills
insert into rgh.skill ([level], name, [description], [action], value) values (1, 'Schildwall', 'Erhöht Verteidigung um 1 für diese Kampfrunde.', 1, 1)
insert into rgh.skill ([level], name, [description], [action]) values (3, 'Waffengeschick', 'Der Spieler kann verbesserte Waffen anlegen.', 12)
insert into rgh.skill ([level], name, [description], [action], value, multi) values (5, 'Klingenwirbel', 'Erhöht Angriff um 2 und attackiert alle Gegner in dieser Kampfrunde.', 0, 2, 1)
insert into rgh.skill ([level], name, [description], [action], value) values (1, 'Heiliger Blitz', 'Heilt den Spieler um 1 Vitalität.', 4, 1)
insert into rgh.skill ([level], name, [description], [action], value) values (3, 'Feuerball', 'Erhöht Angriff um  für diese Kampfrunde.', 0, 2)
insert into rgh.skill ([level], name, [description], [action], value, multi) values (5, 'Bizzard', 'Erhöht Angriff um 2 und attackiert alle Gegner in dieser Kampfrunde.', 0, 2, 1)
insert into rgh.skill ([level], name, [description], [action], value, permanent) values (1, 'Wolf', 'Der Spieler wird in jedem Kampf von einem Wolf unterstützt.', 8, 1, 1)
insert into rgh.skill ([level], name, [description], [action], value) values (3, 'Falle', 'Setzt einen beliebigen Begleiter außer gefecht.', 10, 1)
insert into rgh.skill ([level], name, [description], [action], value) values (5, 'Gezielter Schuss', 'Erhöht Angriff um 3 und der Gegner kann in dieser Runde keine Fähigkeiten einsetzen.', 21, 3)
insert into rgh.skill ([level], name, [description], [action], value, permanent) values (1, 'Gift', 'Verringert nach jeder Kampfrunde die Vitalität des Gegners um 1 (nur einmal einsetzbar).', 9, 1, 1)
insert into rgh.skill ([level], name, [description], [action]) values (3, 'Diebstahl', 'Der Spieler kann erreichbare Spieler oder Händler bestehlen (auch ausserhalb des Kampfes).', 17)
insert into rgh.skill ([level], name, [description], [action], value) values (5, 'Hinterhalt', 'Erhöht Angriff um 5 (nur möglich mit Begleitern oder zu Kampfbeginn).', 0, 5)
go

-- insert item skills
insert into rgh.skill ([level], name, [description], [action], value) values (1, 'Heilung', 'Heilt den Spieler um 1 Vitalität.', 4, 1)
insert into rgh.skill ([level], name, [description], [action], value, permanent) values (1, 'Stärke', 'Erhöht die Stärke für den kompletten Kampf um 1.', 6, 1, 1)
insert into rgh.skill ([level], name, [description], [action], value, permanent) values (1, 'Geschick', 'Erhöht das Geschick für den kompletten Kampf um 1.', 5, 1, 1)
insert into rgh.skill ([level], name, [description], [action], value) values (1, 'Angriff', 'Erhöht Angriff um 1 für diese Kampfrunde.', 0, 1)
insert into rgh.skill ([level], name, [description], [action], value) values (1, 'Verteidigung', 'Erhöht Verteidigung um 1 für diese Kampfrunde.', 1, 1)
insert into rgh.skill ([level], name, [description], [action], value, multi) values (2, 'Explosion', 'Erhöht Angriff um 1 und attackiert alle Gegner für diese Kampfrunde.', 0, 1, 1)
insert into rgh.skill ([level], name, [description], [action]) values (1, 'Entkommen (klein)', 'Erleichtert das Entkommen (verbesserter Würfel).', 15)
insert into rgh.skill ([level], name, [description], [action]) values (3, 'Entkommen (groß)', 'Entkommt automatisch aus dem Kampf.', 16)
go

-- insert armor/weapon skills
insert into rgh.skill ([level], name, [description], [action], value, permanent) values (1, 'Waffe', 'Erhöht Angriff um 1.', 0, 1, 1)
insert into rgh.skill ([level], name, [description], [action], value, permanent) values (1, 'Rüstung', 'Erhöht Verteidigung um 1.', 1, 1, 1)
insert into rgh.skill ([level], name, [description], [action], value) values (1, 'Todesstoß', 'Erhöht Angriff um 3 und der Gegner ist Tod wenn seine Vitalität unter 1 fällt (ohne Ausnahme).', 20, 2)
go

-- RACES --
delete from rgh.race
go

insert into rgh.race (name, fkSkillId) values ('Zwerg', (select id from rgh.skill where name = 'Betäubung'))
insert into rgh.race (name, fkSkillId) values ('Mensch', (select id from rgh.skill where name = 'Beritten'))
insert into rgh.race (name, fkSkillId) values ('Elf', (select id from rgh.skill where name = 'Ausweichen'))
insert into rgh.race (name, fkSkillId) values ('Gnom', (select id from rgh.skill where name = 'Bastler'))
go

-- CLASSES --
delete from rgh.class
go

insert into rgh.class (name) values ('Krieger')
insert into rgh.class (name) values ('Zauberer')
insert into rgh.class (name) values ('Jäger')
insert into rgh.class (name) values ('Dieb')
go

-- CLASS-SKILLS --
delete from rgh.classSkillRefs
go

insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Krieger'), (select id from rgh.skill where name = 'Schildwall'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Krieger'), (select id from rgh.skill where name = 'Waffengeschick'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Krieger'), (select id from rgh.skill where name = 'Klingenwirbel'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Zauberer'), (select id from rgh.skill where name = 'Heiliger Blitz'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Zauberer'), (select id from rgh.skill where name = 'Feuerball'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Zauberer'), (select id from rgh.skill where name = 'Bizzard'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Jäger'), (select id from rgh.skill where name = 'Wolf'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Jäger'), (select id from rgh.skill where name = 'Falle'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Jäger'), (select id from rgh.skill where name = 'Gezielter Schuss'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Dieb'), (select id from rgh.skill where name = 'Gift'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Dieb'), (select id from rgh.skill where name = 'Diebstahl'))
insert into rgh.classSkillRefs (fkClassId, fkSkillId) values ((select id from rgh.class where name = 'Dieb'), (select id from rgh.skill where name = 'Hinterhalt'))
go

-- ARMOR --
delete from rgh.armor
go

-- insert basic armor
insert into rgh.armor ([level], name, [description], value, costs) values (1, 'Lederhelm', 'Erhöht Verteidigung um 1.', 1, 1)
insert into rgh.armor ([level], name, [description], value, costs) values (2, 'Eisenhelm', 'Erhöht Verteidigung um 2.', 2, 2)
insert into rgh.armor ([level], name, [description], value, costs) values (3, 'Goldhelm', 'Erhöht Verteidigung um 3.', 3, 3)
insert into rgh.armor ([level], name, [description], value, costs) values (1, 'Lederrüstung', 'Erhöht Verteidigung um 1.', 1, 1)
insert into rgh.armor ([level], name, [description], value, costs) values (2, 'Eisenrüstung', 'Erhöht Verteidigung um 2.', 2, 2)
insert into rgh.armor ([level], name, [description], value, costs) values (3, 'Goldrüstung', 'Erhöht Verteidigung um 3.', 3, 3)
insert into rgh.armor ([level], name, [description], value, costs) values (1, 'Holzschild', 'Erhöht Verteidigung um 1.', 1, 1)
insert into rgh.armor ([level], name, [description], value, costs) values (2, 'Eisenschild', 'Erhöht Verteidigung um 2.', 2, 2)
insert into rgh.armor ([level], name, [description], value, costs) values (3, 'Goldschild', 'Erhöht Verteidigung um 3.', 3, 3)
go

-- insert special armor
insert into rgh.armor ([level], name, [description], value, fkSkillId) values (1, 'Raketenstiefel', 'Erhöht Verteidigung um 1. Erleichtert entkommen.', 1, (select id from rgh.skill where name = 'Entkommen (klein)'))
insert into rgh.armor ([level], name, [description], value, fkSkillId) values (3, 'Nanohandschuh', 'Erhöht Verteidigung um 1. Steigert Geschick.', 1, (select id from rgh.skill where name = 'Geschick'))
insert into rgh.armor ([level], name, [description], value, fkSkillId) values (5, 'Chaosrucksack', 'Erhöht Verteidigung um 2. Chaos Raketen erhöhen Angriff um 2 und greifen alle Gegner an.', 2, (select id from rgh.skill where name = 'Explosion'))
insert into rgh.armor ([level], name, [description], value, fkSkillId) values (3, 'Runenschild', 'Erhöht Verteidigung um 2. Schildschlag erhöht Angriff um 2.', 2, (select id from rgh.skill where name = 'Angriff'))
go

-- WEAPONS --

delete from rgh.weapon
go

-- insert basic weapons
insert into rgh.weapon ([level], name, [description], value, costs) values (1, 'Eisenschwert', 'Erhöht Angriff um 1.', 1, 1)
insert into rgh.weapon ([level], name, [description], value, costs) values (2, 'Stahlschwert', 'Erhöht Angriff um 2.', 2, 3)
insert into rgh.weapon ([level], name, [description], value, costs) values (3, 'Goldschwert', 'Erhöht Angriff um 3.', 3, 5)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (1, 'Hammer', 'Erhöht Angriff um 2.', 2, 2, 2)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (2, 'Zweihänder', 'Erhöht Angriff um 4.', 4, 6, 2)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (3, 'Doppelaxt', 'Erhöht Angriff um 6.', 6, 10, 2)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (1, 'Jagtbogen', 'Erhöht Angriff um 2.', 2, 2, 2)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (2, 'Eichenbogen', 'Erhöht Angriff um 4.', 4, 6, 2)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (3, 'Kriegsbogen', 'Erhöht Angriff um 6.', 6, 10, 2)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (1, 'Eichenstab', 'Erhöht Angriff um 2.', 2, 2, 2)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (2, 'Feuerstab', 'Erhöht Angriff um 4.', 4, 6, 2)
insert into rgh.weapon ([level], name, [description], value, costs, hands) values (3, 'Arcanstab', 'Erhöht Angriff um 6.', 6, 10, 2)
go

-- insert special weapons
insert into rgh.weapon ([level], name, [description], value, hands, fkSkillId) values (1, 'Bastardschwert', 'Erhöht Angriff um 2.', 2, 1, (select id from rgh.skill where name = 'Angriff'))
insert into rgh.weapon ([level], name, [description], value, hands, fkSkillId) values (1, 'Berserkeraxt', 'Erhöht Angriff um 6.', 6, 2, (select id from rgh.skill where name = 'Todesstoß'))
go

-- ITEMS --
delete from rgh.item
go

-- insert basic items
insert into rgh.item ([level], name, [description], value, costs, fkSkillId) values (1, 'Heiltrank (klein)', 'Stellt 1 Vitalität wieder her.', 1, 1, (select id from rgh.skill where name = 'Heilung'))
insert into rgh.item ([level], name, [description], value, costs, fkSkillId) values (3, 'Heiltrank (groß)', 'Stellt 2 Vitalität wieder her.', 2, 2, (select id from rgh.skill where name = 'Heilung'))
insert into rgh.item ([level], name, [description], value, costs, fkSkillId) values (1, 'Stärketrank', 'Erhöht Stärke um 1 für den Kampf.', 1, 1, (select id from rgh.skill where name = 'Stärke'))
insert into rgh.item ([level], name, [description], value, costs, fkSkillId) values (1, 'Geschicktrank', 'Erhöht Geschick um 1 für den Kampf.', 1, 1, (select id from rgh.skill where name = 'Geschick'))
insert into rgh.item ([level], name, [description], value, costs, fkSkillId) values (1, 'Klemmfalle', 'Ermöglicht einfacheres Entkommen.', 1, 1, (select id from rgh.skill where name = 'Entkommen (klein)'))
insert into rgh.item ([level], name, [description], value, costs, fkSkillId) values (1, 'Spruchrolle Feuer', 'Erhöht Angriff um 1 für diese Kampfrunde.', 1, 1, (select id from rgh.skill where name = 'Angriff'))
insert into rgh.item ([level], name, [description], value, costs, fkSkillId) values (1, 'Spruchrolle Eis', 'Erhöht Verteidigung um 1 für diese Kampfrunde.', 1, 1, (select id from rgh.skill where name = 'Verteidigung'))
insert into rgh.item ([level], name, [description], value, costs, fkSkillId) values (1, 'Feuerbombe', 'Erhöht Angriff um 2 für diese Kampfrunde und greift alle Gegner an.', 2, 2, (select id from rgh.skill where name = 'Explosion'))
insert into rgh.item ([level], name, [description], costs, fkSkillId) values (1, 'Rauchbombe', 'Ermöglicht Entkommen aus dem Kampf.', 3, (select id from rgh.skill where name = 'Entkommen (groß)'))
go

-- CHARACTERS --
delete from rgh.character
go

-- insert basic items
insert into rgh.character (id, name, fixed, level, strength, vitality, dexterity, fkRaceId, fkClassId)
values (1, 'dnd-test-char', 0, 1, 2, 3, 1, (select id from rgh.race where name = 'Zwerg'), (select id from rgh.class where name = 'Krieger'))
go
