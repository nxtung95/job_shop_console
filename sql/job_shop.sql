/*
 Navicat Premium Data Transfer

 Source Server         : SQL_Server
 Source Server Type    : SQL Server
 Source Server Version : 15002000
 Source Host           : localhost:1433
 Source Catalog        : job_shop
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15002000
 File Encoding         : 65001

 Date: 23/10/2023 22:58:42
*/


-- ----------------------------
-- Table structure for account
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[account]') AND type IN ('U'))
	DROP TABLE [dbo].[account]
GO

CREATE TABLE [dbo].[account] (
  [account_number] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [established_date] date  NULL,
  [cost] decimal(18)  NULL
)
GO

ALTER TABLE [dbo].[account] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for assembly
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[assembly]') AND type IN ('U'))
	DROP TABLE [dbo].[assembly]
GO

CREATE TABLE [dbo].[assembly] (
  [assembly_id] int  IDENTITY(1,1) NOT NULL,
  [date_ordered] datetime  NULL,
  [assembly_detail] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [customer_id] int  NULL
)
GO

ALTER TABLE [dbo].[assembly] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for assembly_account
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[assembly_account]') AND type IN ('U'))
	DROP TABLE [dbo].[assembly_account]
GO

CREATE TABLE [dbo].[assembly_account] (
  [account_number] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [assembly_id] int  NULL
)
GO

ALTER TABLE [dbo].[assembly_account] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for customer
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[customer]') AND type IN ('U'))
	DROP TABLE [dbo].[customer]
GO

CREATE TABLE [dbo].[customer] (
  [customer_id] int  IDENTITY(1,1) NOT NULL,
  [name] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [address] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [category] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[customer] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for cut_job
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cut_job]') AND type IN ('U'))
	DROP TABLE [dbo].[cut_job]
GO

CREATE TABLE [dbo].[cut_job] (
  [job_number] int  NOT NULL,
  [machine_type] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [machine_used_time] int  NULL,
  [material_used] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[cut_job] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'// Unit: hours',
'SCHEMA', N'dbo',
'TABLE', N'cut_job',
'COLUMN', N'machine_used_time'
GO


-- ----------------------------
-- Table structure for cut_process
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cut_process]') AND type IN ('U'))
	DROP TABLE [dbo].[cut_process]
GO

CREATE TABLE [dbo].[cut_process] (
  [process_id] int  NOT NULL,
  [cutting_type] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [machine_type] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[cut_process] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for department
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[department]') AND type IN ('U'))
	DROP TABLE [dbo].[department]
GO

CREATE TABLE [dbo].[department] (
  [department_id] int  IDENTITY(1,1) NOT NULL,
  [department_data] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[department] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for department_account
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[department_account]') AND type IN ('U'))
	DROP TABLE [dbo].[department_account]
GO

CREATE TABLE [dbo].[department_account] (
  [account_number] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [department_id] int  NULL
)
GO

ALTER TABLE [dbo].[department_account] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for fit_job
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[fit_job]') AND type IN ('U'))
	DROP TABLE [dbo].[fit_job]
GO

CREATE TABLE [dbo].[fit_job] (
  [job_number] int  NOT NULL
)
GO

ALTER TABLE [dbo].[fit_job] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for fit_process
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[fit_process]') AND type IN ('U'))
	DROP TABLE [dbo].[fit_process]
GO

CREATE TABLE [dbo].[fit_process] (
  [process_id] int  NOT NULL,
  [fit_type] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[fit_process] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for job
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[job]') AND type IN ('U'))
	DROP TABLE [dbo].[job]
GO

CREATE TABLE [dbo].[job] (
  [job_number] int  IDENTITY(1,1) NOT NULL,
  [completed_date] date  NULL,
  [commenced_date] date  NULL,
  [assembly_id] int  NULL,
  [process_id] int  NULL,
  [labor_time] int  NULL
)
GO

ALTER TABLE [dbo].[job] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for paint_job
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[paint_job]') AND type IN ('U'))
	DROP TABLE [dbo].[paint_job]
GO

CREATE TABLE [dbo].[paint_job] (
  [job_number] int  NOT NULL,
  [color] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [volume] int  NULL
)
GO

ALTER TABLE [dbo].[paint_job] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for paint_process
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[paint_process]') AND type IN ('U'))
	DROP TABLE [dbo].[paint_process]
GO

CREATE TABLE [dbo].[paint_process] (
  [process_id] int  NOT NULL,
  [paint_type] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [paint_method] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[paint_process] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for process
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[process]') AND type IN ('U'))
	DROP TABLE [dbo].[process]
GO

CREATE TABLE [dbo].[process] (
  [process_id] int  IDENTITY(1,1) NOT NULL,
  [process_data] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [department_id] int  NULL
)
GO

ALTER TABLE [dbo].[process] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for process_account
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[process_account]') AND type IN ('U'))
	DROP TABLE [dbo].[process_account]
GO

CREATE TABLE [dbo].[process_account] (
  [account_number] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [process_id] int  NULL
)
GO

ALTER TABLE [dbo].[process_account] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for transaction
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[transaction]') AND type IN ('U'))
	DROP TABLE [dbo].[transaction]
GO

CREATE TABLE [dbo].[transaction] (
  [transaction_number] int  IDENTITY(1,1) NOT NULL,
  [supplied_cost] decimal(18)  NULL,
  [account_number] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[transaction] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Primary Key structure for table account
-- ----------------------------
ALTER TABLE [dbo].[account] ADD CONSTRAINT [PK__account__AF91A6AC9AE4C130] PRIMARY KEY CLUSTERED ([account_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for assembly
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[assembly]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table assembly
-- ----------------------------
ALTER TABLE [dbo].[assembly] ADD CONSTRAINT [PK_assembly] PRIMARY KEY CLUSTERED ([assembly_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table assembly_account
-- ----------------------------
ALTER TABLE [dbo].[assembly_account] ADD CONSTRAINT [PK__assembly__AF91A6AC95187101] PRIMARY KEY CLUSTERED ([account_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for customer
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[customer]', RESEED, 1)
GO


-- ----------------------------
-- Indexes structure for table customer
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_category_customer_1]
ON [dbo].[customer] (
  [category] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table customer
-- ----------------------------
ALTER TABLE [dbo].[customer] ADD CONSTRAINT [PK__customer__3213E83F13AB5EFA] PRIMARY KEY CLUSTERED ([customer_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table cut_job
-- ----------------------------
ALTER TABLE [dbo].[cut_job] ADD CONSTRAINT [PK__cut_job__488896ED618D7C59] PRIMARY KEY CLUSTERED ([job_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table cut_process
-- ----------------------------
ALTER TABLE [dbo].[cut_process] ADD CONSTRAINT [PK__cut_proc__9446C3E1D2CC835F] PRIMARY KEY CLUSTERED ([process_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for department
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[department]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table department
-- ----------------------------
ALTER TABLE [dbo].[department] ADD CONSTRAINT [PK__departme__C2232422022B4C2B] PRIMARY KEY CLUSTERED ([department_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table department_account
-- ----------------------------
ALTER TABLE [dbo].[department_account] ADD CONSTRAINT [PK__departme__AF91A6AC60435E55] PRIMARY KEY CLUSTERED ([account_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table fit_job
-- ----------------------------
ALTER TABLE [dbo].[fit_job] ADD CONSTRAINT [PK__fit_job__488896EDBA20C49C] PRIMARY KEY CLUSTERED ([job_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table fit_process
-- ----------------------------
ALTER TABLE [dbo].[fit_process] ADD CONSTRAINT [PK__fit_proc__9446C3E120600FBF] PRIMARY KEY CLUSTERED ([process_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for job
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[job]', RESEED, 1)
GO


-- ----------------------------
-- Indexes structure for table job
-- ----------------------------
CREATE NONCLUSTERED INDEX [idx_completed_date_job_1]
ON [dbo].[job] (
  [completed_date] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table job
-- ----------------------------
ALTER TABLE [dbo].[job] ADD CONSTRAINT [PK__job__488896ED480A45E4] PRIMARY KEY CLUSTERED ([job_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table paint_job
-- ----------------------------
ALTER TABLE [dbo].[paint_job] ADD CONSTRAINT [PK__paint_jo__488896ED187D3019] PRIMARY KEY CLUSTERED ([job_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table paint_process
-- ----------------------------
ALTER TABLE [dbo].[paint_process] ADD CONSTRAINT [PK__paint_pr__9446C3E1CB97D9F6] PRIMARY KEY CLUSTERED ([process_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for process
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[process]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table process
-- ----------------------------
ALTER TABLE [dbo].[process] ADD CONSTRAINT [PK__process__9446C3E18FA8FD66] PRIMARY KEY CLUSTERED ([process_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table process_account
-- ----------------------------
ALTER TABLE [dbo].[process_account] ADD CONSTRAINT [PK__process___AF91A6ACB1C2368E] PRIMARY KEY CLUSTERED ([account_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for transaction
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[transaction]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table transaction
-- ----------------------------
ALTER TABLE [dbo].[transaction] ADD CONSTRAINT [PK__transact__8D977EB3DA08005E] PRIMARY KEY CLUSTERED ([transaction_number])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table assembly
-- ----------------------------
ALTER TABLE [dbo].[assembly] ADD CONSTRAINT [fk_assembly_1] FOREIGN KEY ([customer_id]) REFERENCES [dbo].[customer] ([customer_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table assembly_account
-- ----------------------------
ALTER TABLE [dbo].[assembly_account] ADD CONSTRAINT [assembly_account_1] FOREIGN KEY ([account_number]) REFERENCES [dbo].[account] ([account_number]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table cut_job
-- ----------------------------
ALTER TABLE [dbo].[cut_job] ADD CONSTRAINT [fk_cut_job_1] FOREIGN KEY ([job_number]) REFERENCES [dbo].[job] ([job_number]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table cut_process
-- ----------------------------
ALTER TABLE [dbo].[cut_process] ADD CONSTRAINT [fk_cut_process_1] FOREIGN KEY ([process_id]) REFERENCES [dbo].[process] ([process_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table department_account
-- ----------------------------
ALTER TABLE [dbo].[department_account] ADD CONSTRAINT [fk_department_account_1] FOREIGN KEY ([account_number]) REFERENCES [dbo].[account] ([account_number]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[department_account] ADD CONSTRAINT [fk_department_account_2] FOREIGN KEY ([department_id]) REFERENCES [dbo].[department] ([department_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table fit_job
-- ----------------------------
ALTER TABLE [dbo].[fit_job] ADD CONSTRAINT [fk_fit_job_1] FOREIGN KEY ([job_number]) REFERENCES [dbo].[job] ([job_number]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table fit_process
-- ----------------------------
ALTER TABLE [dbo].[fit_process] ADD CONSTRAINT [fk_fit_process_1] FOREIGN KEY ([process_id]) REFERENCES [dbo].[process] ([process_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table job
-- ----------------------------
ALTER TABLE [dbo].[job] ADD CONSTRAINT [fk_job_2] FOREIGN KEY ([process_id]) REFERENCES [dbo].[process] ([process_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table paint_job
-- ----------------------------
ALTER TABLE [dbo].[paint_job] ADD CONSTRAINT [fk_paint_job_1] FOREIGN KEY ([job_number]) REFERENCES [dbo].[job] ([job_number]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table paint_process
-- ----------------------------
ALTER TABLE [dbo].[paint_process] ADD CONSTRAINT [fk_paint_process_1] FOREIGN KEY ([process_id]) REFERENCES [dbo].[process] ([process_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table process
-- ----------------------------
ALTER TABLE [dbo].[process] ADD CONSTRAINT [fk_department_1] FOREIGN KEY ([department_id]) REFERENCES [dbo].[department] ([department_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table process_account
-- ----------------------------
ALTER TABLE [dbo].[process_account] ADD CONSTRAINT [fk_process_account_1] FOREIGN KEY ([account_number]) REFERENCES [dbo].[account] ([account_number]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[process_account] ADD CONSTRAINT [fk_process_account_2] FOREIGN KEY ([process_id]) REFERENCES [dbo].[process] ([process_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table transaction
-- ----------------------------
ALTER TABLE [dbo].[transaction] ADD CONSTRAINT [fk_transaction_1] FOREIGN KEY ([account_number]) REFERENCES [dbo].[account] ([account_number]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

