if exists (select 1 from sysobjects where name='Users')
drop table Users
create table Users (
	id			int identity(1,1) not null primary key,
	loginName	varchar(20),
	name		varchar(20),
	password	varchar(32),
	regTime		datetime,
	gid			int,
)
create unique nonclustered index idx_Users_loginName on Users(loginName)
--default admin user 111/111
insert Users(loginName, name, password, regTime, gid)
select '111', 'aaa', '96E79218965EB72C92A549DD5A330112', '2010-1-1', 1

if exists (select 1 from sysobjects where name='Vehicles')
drop table Vehicles
create table Vehicles (
	uid		int not null primary key,
	status	int,
	biddingStartOn	datetime,
	biddingStopOn	datetime,
	brand	varchar(50),
	licensePlate	varchar(10),
)

if exists (select * from sysobjects where name='VehicleReports')
drop table VehicleReports
create table VehicleReports (
	id		int not null primary key,
	comment	varchar(50),
)

if exists (select 1 from sysobjects where name='VehicleBid')
drop table VehicleBid
create table VehicleBid (
	vid		int,
	uid		int,
	price	int,
	bidTime	datetime,	
)
