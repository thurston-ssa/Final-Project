package com.ssa.ironyard.fitness.daoTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.ssa.ironyard.fitness.crypto.BCryptSecurePassword;
import com.ssa.ironyard.fitness.dao.AccountDAOimpl;
import com.ssa.ironyard.fitness.dao.ExerciseDAOImpl;
import com.ssa.ironyard.fitness.dao.GoalDAOImpl;
import com.ssa.ironyard.fitness.dao.WeeklyScoreDAOImpl;
import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAOImpl;
import com.ssa.ironyard.fitness.model.Account;
import com.ssa.ironyard.fitness.model.Exercise;
import com.ssa.ironyard.fitness.model.Exercise.Category;
import com.ssa.ironyard.fitness.model.Goal;
import com.ssa.ironyard.fitness.model.Password;
import com.ssa.ironyard.fitness.model.WorkoutHistory;

public class WorkoutHistoryTests {

    static String URL = "jdbc:mysql://localhost/fitness?" + "user=root&password=root" + "&useServerPrepStmt=true";

    WeeklyScoreDAOImpl weeklyScoresDAO;
    WorkoutHistoryDAOImpl workoutHistoryDAO;
    ExerciseDAOImpl exerciseDAO;
    GoalDAOImpl goalDAO;
    AccountDAOimpl accountDAO;

    @Before
    public void setup() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl(URL);
        workoutHistoryDAO = new WorkoutHistoryDAOImpl(mysqlDataSource);
        exerciseDAO = new ExerciseDAOImpl(mysqlDataSource);
        accountDAO = new AccountDAOimpl(mysqlDataSource);
        goalDAO = new GoalDAOImpl(mysqlDataSource);
        weeklyScoresDAO = new WeeklyScoreDAOImpl(mysqlDataSource);
        weeklyScoresDAO.clear();
        workoutHistoryDAO.clear();
        accountDAO.clear();
        exerciseDAO.clear();
        goalDAO.clear();
    }

    @Test
    public void workoutHistoryInsert() {
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
        a.setUsername("fitness123");
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

        WorkoutHistory wh = new WorkoutHistory();
        wh.setAccount(a);
        wh.setExercise(e);
        wh.setWorkout_date(LocalDateTime.of(2015, 10, 6, 10, 12));
        wh.setDistance(5);
        wh.setWeight(135);
        wh.setReps(10);
        wh.setSets(3);
        wh.setTime(Duration.ofMillis(10000));

        wh = workoutHistoryDAO.insert(wh);
        assertTrue(wh.equals(workoutHistoryDAO.read(wh.getId())));

    }

    @Test
    public void workoutHistoryReadById() {
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
        a.setUsername("fitness123");
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

        WorkoutHistory wh = new WorkoutHistory();
        wh.setAccount(a);
        wh.setExercise(e);
        wh.setWorkout_date(LocalDateTime.of(2016, 10, 6, 10, 12));
        wh.setDistance(5);
        wh.setWeight(135);
        wh.setReps(10);
        wh.setSets(3);
        wh.setTime(Duration.ofMillis(10000));

        wh = workoutHistoryDAO.insert(wh);
        assertEquals(1, (workoutHistoryDAO.readByUserId(a.getId()).size()));

    }

}
