use ta_report_system;

INSERT INTO
  `authority` (id, name)
VALUES
  (1, 'TA'),
  (2, 'Staff'),
  (3, 'Faculty');

INSERT INTO
  `account` (id, authority_id, username, password)
VALUES
  (1, 1, 'm5251201', '111'),
  (2, 1, 'm5261202', '222'),
  (3, 1, 's1270141', '333'),
  (4, 2, 's100', '444'),
  (5, 3, 'f100', '555');

INSERT INTO
  `user` (
    id,
    account_id,
    first_name,
    middle_name,
    last_name,
    start_date
  )
VALUES
  (
    1,
    1,
    'Makoto',
    NULL,
    'Takeuchi',
    '2020-04-01 00:00:00'
  ),
  (
    2,
    2,
    'Atsuki',
    NULL,
    'Yanada',
    '2021-04-01 00:00:00'
  ),
  (
    3,
    3,
    'Sosyu',
    NULL,
    'Kurakane',
    '2020-04-01 00:00:00'
  ),
  (
    4,
    4,
    'Takanori',
    NULL,
    'Fukuchi',
    '2012-04-01 00:00:00'
  ),
  (
    5,
    5,
    'Rentaro',
    NULL,
    'Yoshioka',
    '2012-04-01 00:00:00'
  );

INSERT INTO
  `assistant_nationality` (id, name, weekly_work_hour_limit)
VALUES
  (1, 'Japanese', '40:00:00'),
  (2, 'International', '28:00:00');

INSERT INTO
  `assistant_graduate` (id, name)
VALUES
  (1, 'Undergraduate'),
  (2, 'Graduate');

INSERT INTO
  `assistant` (
    id,
    user_id,
    assistant_nationality_id,
    assistant_graduate_id,
    student_year,
    student_id
  )
VALUES
  (1, 1, 1, 2, 25, 'm5251201'),
  (2, 2, 1, 2, 26, 'm5261202'),
  (3, 3, 2, 1, 27, 's1270141');

INSERT INTO
  `staff` (id, user_id)
VALUES
  (1, 4);

INSERT INTO
  `faculty` (id, user_id)
VALUES
  (1, 5);

INSERT INTO
  `course_name` (id, name)
VALUES
  (1, 'HS01'),
  (2, 'HS03'),
  (3, 'HS04'),
  (4, 'HS05'),
  (5, 'HS06'),
  (6, 'HS07'),
  (7, 'HS09'),
  (8, 'HS10'),
  (9, 'HS11'),
  (10, 'HS12'),
  (11, 'HS13'),
  (12, 'HS16'),
  (13, 'HS17'),
  (14, 'HS19'),
  (15, 'HS20'),
  (16, 'HS21'),
  (17, 'HS22'),
  (18, 'HS23'),
  (19, 'PA01'),
  (20, 'PA02'),
  (21, 'PA03'),
  (22, 'PA04'),
  (23, 'EN01'),
  (24, 'EN02'),
  (25, 'EN03'),
  (26, 'EN04'),
  (27, 'EN05'),
  (28, 'EN06'),
  (29, 'EN07'),
  (30, 'EN08'),
  (31, 'EG10'),
  (33, 'JP01'),
  (34, 'JP02'),
  (35, 'JP03'),
  (36, 'JP04'),
  (37, 'JP05'),
  (38, 'JP06'),
  (39, 'JP07'),
  (40, 'EL10'),
  (41, 'EL11'),
  (42, 'EL13'),
  (44, 'EL14'),
  (46, 'EL15'),
  (49, 'EL21'),
  (50, 'EL22'),
  (51, 'EL24'),
  (54, 'EL31'),
  (58, 'EL32'),
  (60, 'EL33'),
  (62, 'MA01'),
  (63, 'MA02'),
  (64, 'MA03'),
  (65, 'MA04'),
  (66, 'MA05'),
  (67, 'MA06'),
  (68, 'MA07'),
  (69, 'MA08'),
  (70, 'MA09'),
  (71, 'MA10'),
  (72, 'NS01'),
  (73, 'NS02'),
  (74, 'NS03'),
  (75, 'NS04'),
  (76, 'NS05'),
  (77, 'LI01'),
  (78, 'LI03'),
  (79, 'LI04'),
  (80, 'LI06'),
  (81, 'LI07'),
  (82, 'LI08'),
  (83, 'LI09'),
  (84, 'LI10'),
  (85, 'LI11'),
  (86, 'LI12'),
  (87, 'LI13'),
  (88, 'LI14'),
  (89, 'PL01'),
  (90, 'PL02'),
  (91, 'PL03'),
  (92, 'PL04'),
  (93, 'PL05'),
  (94, 'PL06'),
  (95, 'FU01'),
  (96, 'FU02'),
  (97, 'FU03'),
  (98, 'FU04'),
  (99, 'FU05'),
  (100, 'FU06'),
  (101, 'FU08'),
  (102, 'FU09'),
  (103, 'FU10'),
  (104, 'FU11'),
  (105, 'FU14'),
  (106, 'FU15'),
  (107, 'SY02'),
  (108, 'SY04'),
  (109, 'SY05'),
  (110, 'SY06'),
  (111, 'SY07'),
  (112, 'CN02'),
  (113, 'CN03'),
  (114, 'CN04'),
  (115, 'CN05'),
  (116, 'IT01'),
  (117, 'IT02'),
  (118, 'IT03'),
  (119, 'IT05'),
  (120, 'IT06'),
  (121, 'IT08'),
  (122, 'IT09'),
  (123, 'IT10'),
  (124, 'IT11'),
  (125, 'SE01'),
  (126, 'SE04'),
  (127, 'SE05'),
  (128, 'SE06'),
  (129, 'SE07'),
  (130, 'SE08'),
  (131, 'OT01'),
  (133, 'OT02'),
  (140, 'OT04'),
  (141, 'OT05'),
  (142, 'OT06'),
  (143, 'OT08'),
  (145, 'OT09'),
  (146, 'OT10'),
  (151, 'OT11'),
  (152, 'TE01'),
  (153, 'TE02'),
  (154, 'TE03'),
  (155, 'TE04'),
  (156, 'TE05'),
  (157, 'TE06'),
  (158, 'TE07'),
  (159, 'TE08'),
  (160, 'TE09'),
  (161, 'TE10'),
  (162, 'TE11'),
  (163, 'TE12'),
  (164, 'TE13'),
  (165, 'TE14'),
  (166, 'TE15'),
  (167, 'TE16'),
  (168, 'TE17'),
  (169, 'TE18'),
  (170, 'TE19'),
  (171, 'TE20'),
  (172, 'TE21'),
  (173, 'TE22'),
  (174, 'TE23'),
  (175, 'IE01'),
  (176, 'IE02'),
  (177, 'IE03'),
  (178, 'IE04'),
  (179, 'OT03');

INSERT INTO
  `term` (id, name)
VALUES
  (1, 'S1'),
  (2, 'Q1'),
  (3, 'Q2'),
  (4, 'S2'),
  (5, 'Q3'),
  (6, 'Q4');

INSERT INTO
  `period` (id, term_id, period_from, period_to)
VALUES
  (1, 1, '2022-04-01', '2022-09-30'),
  (2, 2, '2022-04-01', '2022-06-12'),
  (3, 3, '2022-06-13', '2022-09-30'),
  (4, 4, '2022-10-01', '2022-03-31'),
  (5, 5, '2022-10-01', '2022-12-06'),
  (6, 6, '2022-12-07', '2022-03-31');

INSERT INTO
  `course` (
    id,
    coordinator_id,
    course_name_id,
    period_id,
    open_year
  )
VALUES
  (1, 1, 178, 4, 2022);

INSERT INTO
  `assistant_course` (id, assistant_id, course_id)
VALUES
  (1, 1, 1),
  (2, 3, 1);

INSERT INTO
  `faculty_course` (faculty_id, course_id)
VALUES
  (1, 1);

INSERT INTO
  `assistance_type` (id, name, hourly_pay)
VALUES
  (1, 'TA', 1000),
  (2, 'SA', 900);

INSERT INTO
  `report` (
    id,
    assistant_course_id,
    assistance_type_id,
    target_year,
    target_month,
    monthly_total_work_hour,
    monthly_total_allocated_hour,
    verified,
    verified_staff_id,
    verified_date,
    approved,
    approved_faculty_id,
    approved_date,
    edited_user_id,
    edited_date
  )
VALUES
  (
    1,
    1,
    1,
    2022,
    '2022-11-01',
    '04:10:00',
    '120:00:00',
    0,
    NULL,
    NULL,
    0,
    NULL,
    NULL,
    1,
    '2022-11-21'
  ),
  (
    2,
    2,
    2,
    2022,
    '2022-11-01',
    '12:05:25',
    '120:00:00',
    1,
    1,
    '2022-12-03',
    1,
    1,
    '2022-12-01',
    5,
    '2022-12-03'
  );

INSERT INTO
  `work_category` (id, name)
VALUES
  (1, 'Lectures and exercises'),
  (2, 'Supervising examinations'),
  (3, 'Preparation of teaching materials'),
  (4, 'Grading'),
  (5, 'Others');

INSERT INTO
  `record` (
    report_id,
    work_category_id,
    target_date,
    start_time,
    end_time,
    break_time,
    total_time
  )
VALUES
  (
    1,
    1,
    '2022-11-01',
    '09:00:00',
    '10:40:00',
    '00:00:00',
    '01:40:00'
  ),
  (
    1,
    1,
    '2022-11-02',
    '13:20:00',
    '15:00:00',
    '00:00:00',
    '01:40:00'
  ),
  (
    1,
    4,
    '2022-11-08',
    '13:00:00',
    '16:00:00',
    '00:30:00',
    '02:30:00'
  ),
  (
    2,
    2,
    '2022-11-05',
    '09:00:00',
    '10:40:00',
    '00:00:00',
    '01:40:00'
  ),
  (
    2,
    3,
    '2022-11-02',
    '09:00:00',
    '10:40:00',
    '00:00:00',
    '01:40:00'
  );