USE [job_shop]
GO
/****** Object:  Table [dbo].[account]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[account_number] [varchar](30) NOT NULL,
	[established_date] [date] NULL,
	[cost] [decimal](18, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[account_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[assembly]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[assembly](
	[assembly_id] [int] IDENTITY(1,1) NOT NULL,
	[date_ordered] [datetime] NULL,
	[assembly_detail] [varchar](255) NULL,
	[customer_id] [int] NULL,
 CONSTRAINT [PK_assembly] PRIMARY KEY CLUSTERED 
(
	[assembly_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[assembly_account]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[assembly_account](
	[account_number] [varchar](30) NOT NULL,
	[assembly_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[account_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[customer_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
	[address] [varchar](255) NULL,
	[category] [varchar](255) NULL,
 CONSTRAINT [PK__customer__3213E83F13AB5EFA] PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cut_job]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cut_job](
	[job_number] [int] NOT NULL,
	[machine_type] [varchar](255) NULL,
	[machine_used_time] [int] NULL,
	[material_used] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[job_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cut_process]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cut_process](
	[process_id] [int] NOT NULL,
	[cutting_type] [varchar](255) NULL,
	[machine_type] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[process_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[department]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[department](
	[department_id] [int] IDENTITY(1,1) NOT NULL,
	[department_data] [varchar](255) NULL,
 CONSTRAINT [PK__departme__C2232422022B4C2B] PRIMARY KEY CLUSTERED 
(
	[department_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[department_account]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[department_account](
	[account_number] [varchar](30) NOT NULL,
	[department_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[account_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[fit_job]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fit_job](
	[job_number] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[job_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[fit_process]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fit_process](
	[process_id] [int] NOT NULL,
	[fit_type] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[process_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[job]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[job](
	[job_number] [int] IDENTITY(1,1) NOT NULL,
	[completed_date] [date] NULL,
	[commenced_date] [date] NULL,
	[assembly_id] [int] NULL,
	[process_id] [int] NULL,
	[labor_time] [int] NULL,
 CONSTRAINT [PK__job__488896ED480A45E4] PRIMARY KEY CLUSTERED 
(
	[job_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[paint_job]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[paint_job](
	[job_number] [int] NOT NULL,
	[color] [varchar](255) NULL,
	[volume] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[job_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[paint_process]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[paint_process](
	[process_id] [int] NOT NULL,
	[paint_type] [varchar](255) NULL,
	[paint_method] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[process_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[process]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[process](
	[process_id] [int] IDENTITY(1,1) NOT NULL,
	[process_data] [varchar](255) NULL,
	[department_id] [int] NULL,
 CONSTRAINT [PK__process__9446C3E18FA8FD66] PRIMARY KEY CLUSTERED 
(
	[process_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[process_account]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[process_account](
	[account_number] [varchar](30) NOT NULL,
	[process_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[account_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[transaction]    Script Date: 10/26/2023 11:06:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[transaction](
	[transaction_number] [int] IDENTITY(1,1) NOT NULL,
	[supplied_cost] [decimal](18, 0) NULL,
	[account_number] [varchar](30) NULL,
 CONSTRAINT [PK__transact__8D977EB3DA08005E] PRIMARY KEY CLUSTERED 
(
	[transaction_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[customer] ON 

INSERT [dbo].[customer] ([customer_id], [name], [address], [category]) VALUES (1, N'Cus A', N'Address A', N'Category A')
INSERT [dbo].[customer] ([customer_id], [name], [address], [category]) VALUES (2, N'Customer A', N'Address A', N'1')
INSERT [dbo].[customer] ([customer_id], [name], [address], [category]) VALUES (3, N'Customer B', N'Address B', N'1')
INSERT [dbo].[customer] ([customer_id], [name], [address], [category]) VALUES (4, N'Customer C', N'Address C', N'2')
INSERT [dbo].[customer] ([customer_id], [name], [address], [category]) VALUES (5, N'Customer D', N'Address D', N'3')
INSERT [dbo].[customer] ([customer_id], [name], [address], [category]) VALUES (6, N'Customer E', N'Address E', N'4')
SET IDENTITY_INSERT [dbo].[customer] OFF
GO
INSERT [dbo].[cut_process] ([process_id], [cutting_type], [machine_type]) VALUES (6, N'Cutting type 1', N'Machine type 1')
INSERT [dbo].[cut_process] ([process_id], [cutting_type], [machine_type]) VALUES (7, N'Cutting type 2', N'Machine type 2')
INSERT [dbo].[cut_process] ([process_id], [cutting_type], [machine_type]) VALUES (8, N'Cutting type 3', N'Machine type 3')
GO
SET IDENTITY_INSERT [dbo].[department] ON 

INSERT [dbo].[department] ([department_id], [department_data]) VALUES (1, N'Department data A')
INSERT [dbo].[department] ([department_id], [department_data]) VALUES (2, N'Department data B')
INSERT [dbo].[department] ([department_id], [department_data]) VALUES (3, N'Department ata C')
INSERT [dbo].[department] ([department_id], [department_data]) VALUES (4, N'Department data D')
INSERT [dbo].[department] ([department_id], [department_data]) VALUES (5, N'Department data E')
SET IDENTITY_INSERT [dbo].[department] OFF
GO
INSERT [dbo].[fit_process] ([process_id], [fit_type]) VALUES (2, N'Fit type 1')
INSERT [dbo].[fit_process] ([process_id], [fit_type]) VALUES (3, N'Fit type 2')
INSERT [dbo].[fit_process] ([process_id], [fit_type]) VALUES (4, N'Fit type 3')
INSERT [dbo].[fit_process] ([process_id], [fit_type]) VALUES (5, N'Fit type 4')
GO
INSERT [dbo].[paint_process] ([process_id], [paint_type], [paint_method]) VALUES (9, N'Paint type 1', N'Paint method 1')
INSERT [dbo].[paint_process] ([process_id], [paint_type], [paint_method]) VALUES (10, N'Paint type 2', N'Paint method 2')
INSERT [dbo].[paint_process] ([process_id], [paint_type], [paint_method]) VALUES (11, N'Paint type 3', N'Paint method 3')
GO
SET IDENTITY_INSERT [dbo].[process] ON 

INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (2, N'Fit process data 1', 1)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (3, N'Fit process data 2', 2)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (4, N'Fit process data 3', 3)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (5, N'Fit process 4', 4)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (6, N'Cut process data 1', 3)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (7, N'Cut process data 2', 4)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (8, N'Cut process data 3', 5)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (9, N'Paint process 1', 1)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (10, N'Paint process data 2', 5)
INSERT [dbo].[process] ([process_id], [process_data], [department_id]) VALUES (11, N'Paint process data 3', 4)
SET IDENTITY_INSERT [dbo].[process] OFF
GO
ALTER TABLE [dbo].[assembly]  WITH CHECK ADD  CONSTRAINT [fk_assembly_1] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([customer_id])
GO
ALTER TABLE [dbo].[assembly] CHECK CONSTRAINT [fk_assembly_1]
GO
ALTER TABLE [dbo].[assembly_account]  WITH CHECK ADD  CONSTRAINT [assembly_account_1] FOREIGN KEY([account_number])
REFERENCES [dbo].[account] ([account_number])
GO
ALTER TABLE [dbo].[assembly_account] CHECK CONSTRAINT [assembly_account_1]
GO
ALTER TABLE [dbo].[cut_job]  WITH CHECK ADD  CONSTRAINT [fk_cut_job_1] FOREIGN KEY([job_number])
REFERENCES [dbo].[job] ([job_number])
GO
ALTER TABLE [dbo].[cut_job] CHECK CONSTRAINT [fk_cut_job_1]
GO
ALTER TABLE [dbo].[cut_process]  WITH CHECK ADD  CONSTRAINT [fk_cut_process_1] FOREIGN KEY([process_id])
REFERENCES [dbo].[process] ([process_id])
GO
ALTER TABLE [dbo].[cut_process] CHECK CONSTRAINT [fk_cut_process_1]
GO
ALTER TABLE [dbo].[department_account]  WITH CHECK ADD  CONSTRAINT [fk_department_account_1] FOREIGN KEY([account_number])
REFERENCES [dbo].[account] ([account_number])
GO
ALTER TABLE [dbo].[department_account] CHECK CONSTRAINT [fk_department_account_1]
GO
ALTER TABLE [dbo].[department_account]  WITH CHECK ADD  CONSTRAINT [fk_department_account_2] FOREIGN KEY([department_id])
REFERENCES [dbo].[department] ([department_id])
GO
ALTER TABLE [dbo].[department_account] CHECK CONSTRAINT [fk_department_account_2]
GO
ALTER TABLE [dbo].[fit_job]  WITH CHECK ADD  CONSTRAINT [fk_fit_job_1] FOREIGN KEY([job_number])
REFERENCES [dbo].[job] ([job_number])
GO
ALTER TABLE [dbo].[fit_job] CHECK CONSTRAINT [fk_fit_job_1]
GO
ALTER TABLE [dbo].[fit_process]  WITH CHECK ADD  CONSTRAINT [fk_fit_process_1] FOREIGN KEY([process_id])
REFERENCES [dbo].[process] ([process_id])
GO
ALTER TABLE [dbo].[fit_process] CHECK CONSTRAINT [fk_fit_process_1]
GO
ALTER TABLE [dbo].[job]  WITH CHECK ADD  CONSTRAINT [fk_job_2] FOREIGN KEY([process_id])
REFERENCES [dbo].[process] ([process_id])
GO
ALTER TABLE [dbo].[job] CHECK CONSTRAINT [fk_job_2]
GO
ALTER TABLE [dbo].[paint_job]  WITH CHECK ADD  CONSTRAINT [fk_paint_job_1] FOREIGN KEY([job_number])
REFERENCES [dbo].[job] ([job_number])
GO
ALTER TABLE [dbo].[paint_job] CHECK CONSTRAINT [fk_paint_job_1]
GO
ALTER TABLE [dbo].[paint_process]  WITH CHECK ADD  CONSTRAINT [fk_paint_process_1] FOREIGN KEY([process_id])
REFERENCES [dbo].[process] ([process_id])
GO
ALTER TABLE [dbo].[paint_process] CHECK CONSTRAINT [fk_paint_process_1]
GO
ALTER TABLE [dbo].[process]  WITH CHECK ADD  CONSTRAINT [fk_department_1] FOREIGN KEY([department_id])
REFERENCES [dbo].[department] ([department_id])
GO
ALTER TABLE [dbo].[process] CHECK CONSTRAINT [fk_department_1]
GO
ALTER TABLE [dbo].[process_account]  WITH CHECK ADD  CONSTRAINT [fk_process_account_1] FOREIGN KEY([account_number])
REFERENCES [dbo].[account] ([account_number])
GO
ALTER TABLE [dbo].[process_account] CHECK CONSTRAINT [fk_process_account_1]
GO
ALTER TABLE [dbo].[process_account]  WITH CHECK ADD  CONSTRAINT [fk_process_account_2] FOREIGN KEY([process_id])
REFERENCES [dbo].[process] ([process_id])
GO
ALTER TABLE [dbo].[process_account] CHECK CONSTRAINT [fk_process_account_2]
GO
ALTER TABLE [dbo].[transaction]  WITH CHECK ADD  CONSTRAINT [fk_transaction_1] FOREIGN KEY([account_number])
REFERENCES [dbo].[account] ([account_number])
GO
ALTER TABLE [dbo].[transaction] CHECK CONSTRAINT [fk_transaction_1]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'// Unit: hours' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'cut_job', @level2type=N'COLUMN',@level2name=N'machine_used_time'
GO
