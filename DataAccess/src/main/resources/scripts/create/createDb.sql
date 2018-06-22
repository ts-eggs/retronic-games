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