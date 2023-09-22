package jp.ac.u_aizu.ta_report_system.script;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jp.ac.u_aizu.ta_report_system.entity.Account;
import jp.ac.u_aizu.ta_report_system.entity.AssistanceType;
import jp.ac.u_aizu.ta_report_system.entity.Assistant;
import jp.ac.u_aizu.ta_report_system.entity.AssistantGraduate;
import jp.ac.u_aizu.ta_report_system.entity.AssistantNationality;
import jp.ac.u_aizu.ta_report_system.entity.Authority;
import jp.ac.u_aizu.ta_report_system.entity.Course;
import jp.ac.u_aizu.ta_report_system.entity.CourseName;
import jp.ac.u_aizu.ta_report_system.entity.Faculty;
import jp.ac.u_aizu.ta_report_system.entity.Period;
import jp.ac.u_aizu.ta_report_system.entity.Staff;
import jp.ac.u_aizu.ta_report_system.entity.Term;
import jp.ac.u_aizu.ta_report_system.entity.User;
import jp.ac.u_aizu.ta_report_system.entity.WorkCategory;
import jp.ac.u_aizu.ta_report_system.repository.AccountRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistanceTypeRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistantGraduateRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistantNationalityRepository;
import jp.ac.u_aizu.ta_report_system.repository.AssistantRepository;
import jp.ac.u_aizu.ta_report_system.repository.AuthorityRepository;
import jp.ac.u_aizu.ta_report_system.repository.CourseNameRepository;
import jp.ac.u_aizu.ta_report_system.repository.CourseRepository;
import jp.ac.u_aizu.ta_report_system.repository.FacultyRepository;
import jp.ac.u_aizu.ta_report_system.repository.PeriodRepository;
import jp.ac.u_aizu.ta_report_system.repository.StaffRepository;
import jp.ac.u_aizu.ta_report_system.repository.TermRepository;
import jp.ac.u_aizu.ta_report_system.repository.UserRepository;
import jp.ac.u_aizu.ta_report_system.repository.WorkCategoryRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    //Java scipt file for inject data to database if not created. To turn off, set alreadySetup = true.
    boolean alreadySetup = false;

    int numberOfHSCourse = 23;
    int numberOfPACourse = 4;
    int numberOfENCourse = 8;
    int numberOfJPCourse = 7;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private CourseNameRepository courseNameRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AssistantRepository assistantRepository;

    @Autowired
    private AssistanceTypeRepository assistanceTypeRepository;

    @Autowired
    private AssistantGraduateRepository gradTypeRepository;

    @Autowired
    private AssistantNationalityRepository nationalityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private WorkCategoryRepository workCategoryRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private CourseRepository courseRepository;
  
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;
        
        createAuthorityIfNotFound("TA");
        createAuthorityIfNotFound("Staff");
        createAuthorityIfNotFound("Faculty");

        createNationalityIfNotFound("Japanese", "40:00:00");
        createNationalityIfNotFound("International", "28:00:00");

        createAssistantGraduateIfNotFound("Undergraduate");
        createAssistantGraduateIfNotFound("Graduate");

        createAssistantTypeIfNotFound("TA", 1000);
        createAssistantTypeIfNotFound("SA", 900);

        createTermIfNotFound("S1");
        createTermIfNotFound("Q1");
        createTermIfNotFound("Q2");
        createTermIfNotFound("S2");
        createTermIfNotFound("Q3");
        createTermIfNotFound("Q4");

        createWorkCatagoryIfNotFound("Lectures and exercises");
        createWorkCatagoryIfNotFound("Supervising examinations");
        createWorkCatagoryIfNotFound("Preparation of teaching materials");
        createWorkCatagoryIfNotFound("Grading");
        createWorkCatagoryIfNotFound("Others");

        for (int i=0; i < numberOfHSCourse; i++) createCourseNameIfNotFound("HS" + String.format("%02d", (i+1)));
        for (int i=0; i < numberOfPACourse; i++) createCourseNameIfNotFound("PA" + String.format("%02d", (i+1)));
        for (int i=0; i < numberOfENCourse; i++) createCourseNameIfNotFound("EN" + String.format("%02d", (i+1)));
        createCourseNameIfNotFound("EG10");
        for (int i=0; i < numberOfJPCourse; i++) createCourseNameIfNotFound("JP" + String.format("%02d", (i+1)));
        createCourseNameIfNotFound("IE04");
    
        createAccountAndUserIfNotExist("m5251201", "111", "Makoto", null, "Takeuchi", "TA", "Japanese", "Graduate", 25);
        createAccountAndUserIfNotExist("m5261202", "222", "Atsuki", null, "Yanada", "TA", "Japanese", "Graduate", 26);
        createAccountAndUserIfNotExist("s1270141", "333", "Sosyu", null, "Kurakane", "TA", "Japanese", "Undergraduate", 27);
        createAccountAndUserIfNotExist("s100", "444", "Takanori", null, "Fukuchi", "Staff", "Japanese", null, null);
        createAccountAndUserIfNotExist("f100", "555", "Rentaro", null, "Yoshioka", "Faculty", "Japanese", null, null);

        createPeriodIfNotFound("S1", LocalDate.of(2022, 4, 1), LocalDate.of(2022, 9, 30));
        createPeriodIfNotFound("Q1", LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 12));
        createPeriodIfNotFound("Q2", LocalDate.of(2022, 6, 13), LocalDate.of(2022, 9, 30));
        createPeriodIfNotFound("S2", LocalDate.of(2022, 10, 1), LocalDate.of(2022, 3, 31));
        createPeriodIfNotFound("Q3", LocalDate.of(2022, 10, 1), LocalDate.of(2022, 12, 6));
        createPeriodIfNotFound("Q4", LocalDate.of(2022, 12, 7), LocalDate.of(2022, 3, 31));

        creatCourseIfNotExist("IE04", "S2", "f100", 2022);

        alreadySetup = true;
    }

    @Transactional
    void createAuthorityIfNotFound(String name) {
 
        Authority auth = authorityRepository.findByName(name);
        if (auth == null) {
            auth = new Authority();
            auth.setName(name);
            authorityRepository.save(auth);
        }
    }

    @Transactional
    void createPeriodIfNotFound(String termName, LocalDate periodFrom, LocalDate periodTo) {
        Period period = periodRepository.findByPeriodFromAndPeriodTo(periodFrom, periodTo);
        if(period == null){
            period = new Period();
            period.setPeriodFrom(periodFrom);
            period.setPeriodTo(periodTo);
            Term term = termRepository.findByName(termName);
            period.setTerm(term);
            periodRepository.save(period);
        }
    }
    
    @Transactional
    void creatCourseIfNotExist(String courseName, String termName, String userName, int openYear){
        CourseName courseNameObj = courseNameRepository.findByName(courseName);
        Optional<Course> course = courseRepository.findByCourseName(courseNameObj);
        if(course.isEmpty()){
            Course newCourse = new Course();
            Faculty lecturer = facultyRepository.findByUser(userRepository.findByAccount(accountRepository.findByUsername(userName)));
            newCourse.setCoordinator(lecturer);
            newCourse.setCourseName(courseNameObj);
            Period period = periodRepository.findByTerm(termRepository.findByName(termName));
            newCourse.setPeriod(period);
            newCourse.setOpenYear(openYear);
            List<Course> list = new ArrayList<Course>();
            if(courseNameObj.getCourses() != null){
                list = courseNameObj.getCourses();
                list.add(newCourse);
            } else {
                list.add(newCourse);
                courseNameObj.setCourses(list);
            }
            courseRepository.save(newCourse);
        }
    }

    @Transactional
    void createCourseNameIfNotFound(String name) {
        CourseName courseName = courseNameRepository.findByName(name);
        if (courseName == null) {
            courseName = new CourseName();
            courseName.setName(name);
            courseNameRepository.save(courseName);
        }
    }

    @Transactional
    void createTermIfNotFound(String name) {
        Term term = termRepository.findByName(name);
        if(term == null){
            term = new Term();
            term.setName(name);
            termRepository.save(term);
        }
    }

    @Transactional
    void createWorkCatagoryIfNotFound(String name) {
        WorkCategory workType = workCategoryRepository.findByName(name);
        if(workType == null){
            workType = new WorkCategory();
            workType.setName(name);
            workCategoryRepository.save(workType);
        }
    }

    @Transactional
    void createAssistantTypeIfNotFound(String name, long hourlyPay) {
        AssistanceType assistType = assistanceTypeRepository.findByName(name);
        if (assistType == null) {
            assistType = new AssistanceType();
            assistType.setName(name);
            assistType.setHourlyPay(hourlyPay);
            assistanceTypeRepository.save(assistType);
        }
    }

    @Transactional
    void createAccountAndUserIfNotExist(String userName, String password, String firstName, String middleName, 
                                        String lastName, String auth, String nationality, String grad, Integer year){
 
        Account account = accountRepository.findByUsername(userName);
        User user = userRepository.findByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName);
        
        if (account == null) {            
            account = new Account();
            account.setUsername(userName);
            account.setPassword(passwordEncoder.encode(password));
            account.setAuthority(authorityRepository.findByName(auth));

            if (user == null) {
                user = new User();
                user.setFirstName(firstName);
                if(middleName != null) user.setMiddleName(middleName);
                user.setLastName(lastName);
                user.setStartDate(LocalDate.now());
                
            }
            user.setAccount(account);
            account.setUser(user);

            accountRepository.save(account);
            userRepository.save(user);

            if(auth == "TA"){
                Assistant assist = assistantRepository.findByUser(user);
                AssistantNationality nation = nationalityRepository.findByName(nationality);
                AssistantGraduate gradType = gradTypeRepository.findByName(grad);

                if(assist == null){
                    assist = new Assistant();
                    assist.setAssistantGraduate(gradType);
                    assist.setUser(user);
                    assist.setAssistantNationality(nation);
                    assist.setStudentYear(year);
                    assist.setStudentId(userName);
                    assistantRepository.save(assist);
                }
            } else if(auth == "Staff"){
                Staff staff = staffRepository.findByUser(user);
                if(staff == null){
                    staff = new Staff();
                    staff.setUser(user);
                    staffRepository.save(staff);
                }

            } else if(auth == "Faculty"){
                Faculty fac = facultyRepository.findByUser(user);
                if(fac == null){
                    fac = new Faculty();
                    fac.setUser(user);
                    facultyRepository.save(fac);
                }
            }
        }
    }

    @Transactional
    void createNationalityIfNotFound(String name, String workHour) {
 
        AssistantNationality nation = nationalityRepository.findByName(name);
        if (nation == null) {
            nation = new AssistantNationality();
            nation.setName(name);
            nation.setWeeklyWorkHourLimit(workHour);
            nationalityRepository.save(nation);
        }
    }

    @Transactional
    void createAssistantGraduateIfNotFound(String name) {
 
        AssistantGraduate gradType = gradTypeRepository.findByName(name);
        if (gradType == null) {
            gradType = new AssistantGraduate();
            gradType.setName(name);
            gradTypeRepository.save(gradType);
        }
    }
}
