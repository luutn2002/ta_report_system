INSERT
  INTOINSERT INTO `report` (
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