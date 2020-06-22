SET IDENTITY_INSERT [dbo].[TestData] ON
INSERT INTO [dbo].[TestData] ([ID], [FirstNumber], [SecondNumber], [Sum], [Product], [subtract], [Division]) VALUES (1, 2, 3, 5, NULL, NULL, NULL)
INSERT INTO [dbo].[TestData] ([ID], [FirstNumber], [SecondNumber], [Sum], [Product], [subtract], [Division]) VALUES (2, 0, 1, 1, NULL, NULL, NULL)
INSERT INTO [dbo].[TestData] ([ID], [FirstNumber], [SecondNumber], [Sum], [Product], [subtract], [Division]) VALUES (3, -6, 4, 2, NULL, NULL, NULL)
INSERT INTO [dbo].[TestData] ([ID], [FirstNumber], [SecondNumber], [Sum], [Product], [subtract], [Division]) VALUES (4, 5, -10.2, -5, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[TestData] OFF
