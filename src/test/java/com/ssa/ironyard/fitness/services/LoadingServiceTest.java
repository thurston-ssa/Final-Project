package com.ssa.ironyard.fitness.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.ssa.ironyard.fitness.crypto.BCryptSecurePassword;
import com.ssa.ironyard.fitness.dao.AccountDAOimpl;
import com.ssa.ironyard.fitness.dao.ExerciseDAOImpl;
import com.ssa.ironyard.fitness.dao.GoalDAOImpl;
import com.ssa.ironyard.fitness.dao.RegimenDAOImpl;
import com.ssa.ironyard.fitness.dao.WeeklyScoreDAOImpl;
import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAOImpl;
import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Exercise;
import com.ssa.ironyard.fitness.model.Goal;
import com.ssa.ironyard.fitness.model.Password;
import com.ssa.ironyard.fitness.model.Regimen;
import com.ssa.ironyard.fitness.model.Exercise.Category;
import com.ssa.ironyard.fitness.model.Regimen.DAY;
import com.ssa.ironyard.fitness.utils.FileLoadingService;

public class LoadingServiceTest {

    static String URL = "jdbc:mysql://localhost/fitness?" + "user=root&password=root" + "&useServerPrepStmt=true";

    AccountDAOimpl accountDAO;
    GoalDAOImpl goalDAO;
    WeeklyScoreDAOImpl weeklyScoresDAO;
    RegimenDAOImpl regimenDAO;
    ExerciseDAOImpl exerciseDAO;
    WorkoutHistoryDAOImpl workoutHistoryDAO;

    @Before
    public void setup() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl(URL);
        workoutHistoryDAO = new WorkoutHistoryDAOImpl(mysqlDataSource);
        regimenDAO = new RegimenDAOImpl(mysqlDataSource);
        exerciseDAO = new ExerciseDAOImpl(mysqlDataSource);
        accountDAO = new AccountDAOimpl(mysqlDataSource);
        goalDAO = new GoalDAOImpl(mysqlDataSource);
        weeklyScoresDAO = new WeeklyScoreDAOImpl(mysqlDataSource);
        
        regimenDAO.clear();
        exerciseDAO.clear();

        weeklyScoresDAO.clear();
        accountDAO.clear();
        goalDAO.clear();
    }

    @Test
    public void initializeDatabase() throws URISyntaxException, IOException {
        FileLoadingService fls = new FileLoadingService();
        fls.intialize();
        user1();
        user2();
        user3();
    }

    private void user1() {

        Goal g = new Goal();
        g.setType(Goal.Type.Endurance);
        g = goalDAO.insert(g);

        BCryptSecurePassword crypt = new BCryptSecurePassword();
        Password p = crypt.secureHash("password");

        Account a = new Account();
        a.setAge(18);
        a.setFirstName("David");
        a.setLastName("Shea");
        a.setGender(Account.Gender.Male);
        a.setHeight(6.00);
        a.setWeight(300.4);
        a.setUsername("fitness1");
        a.setGoal(g);
        a.setPassword(p);

        a = accountDAO.insert(a);

        Exercise e = new Exercise();
        e.setExercise_name("Push-ups");
        e.setCategory(Category.ARMS);
        e.setImage("http:image.com");
        e.setInstructions("Push up and then push down");
        e.setMuscles("Arms arms arms");
        e = exerciseDAO.insert(e);

        Regimen r = new Regimen();
        r.setAccount(a);
        r.setExercise(e);
        r.setDay(DAY.Friday);
        r.setDistance(new BigDecimal(5));
        r.setWeight(new BigDecimal(135));
        r.setReps(10);
        r.setSets(3);
        r.setTime(new BigDecimal(5.10));

    }

    private void user2() {
        Goal g = new Goal();
        g.setType(Goal.Type.Endurance);
        g = goalDAO.insert(g);

        BCryptSecurePassword crypt = new BCryptSecurePassword();
        Password p = crypt.secureHash("password");
        Account a = new Account();
        a.setAge(18);
        a.setFirstName("Ami");
        a.setLastName("Shah");
        a.setGender(Account.Gender.Female);
        a.setHeight(6.00);
        a.setWeight(300.4);
        a.setUsername("fitness2");
        a.setGoal(g);
        a.setPassword(p);

        a = accountDAO.insert(a);
    }

    private void user3() {
        Goal g = new Goal();
        g.setType(Goal.Type.Endurance);
        g = goalDAO.insert(g);

        BCryptSecurePassword crypt = new BCryptSecurePassword();
        Password p = crypt.secureHash("password");

        Account a = new Account();
        a.setAge(18);
        a.setFirstName("Todd");
        a.setLastName("Brown");
        a.setGender(Account.Gender.Male);
        a.setHeight(6.00);
        a.setWeight(300.4);
        a.setUsername("fitness3");
        a.setGoal(g);
        a.setPassword(p);

        a = accountDAO.insert(a);
    }

}
