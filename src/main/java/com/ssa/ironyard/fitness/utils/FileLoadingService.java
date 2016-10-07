package com.ssa.ironyard.fitness.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.ssa.ironyard.fitness.dao.ExerciseDAOImpl;
import com.ssa.ironyard.fitness.dao.RegimenDAOImpl;
import com.ssa.ironyard.fitness.dao.WorkoutHistoryDAOImpl;
import com.ssa.ironyard.fitness.model.Exercise;
import com.ssa.ironyard.fitness.services.DataSourceConfiguration;

public class FileLoadingService {

    static String URL = "jdbc:mysql://localhost/fitness?" + "user=root&password=root" + "&useServerPrepStmt=true";
    static final Logger LOGGER = LogManager.getLogger(DataSourceConfiguration.class);

    MysqlDataSource mysqlDataSource;

    WorkoutHistoryDAOImpl workoutHistoryDAO;
    RegimenDAOImpl regimenDAO;
    ExerciseDAOImpl exerciseDAO;

    public DataSource datasource() {
        LOGGER.debug("YEAH Annotation based processing is working");
        mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl(URL);
        exerciseDAO = new ExerciseDAOImpl(mysqlDataSource);
        regimenDAO = new RegimenDAOImpl(mysqlDataSource);
        workoutHistoryDAO = new WorkoutHistoryDAOImpl(mysqlDataSource);
        return mysqlDataSource;
    }

    public void intialize() throws URISyntaxException, IOException {
        datasource();
        workoutHistoryDAO.clear();
        regimenDAO.clear();
        exerciseDAO.clear();
        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(
                    Paths.get("C:/Users/admin/workspace/FinalProject/src/main/resources/static/data.txt"));

            String line;

            while (null != (line = reader.readLine())) {
                List<String> exerciseList = Arrays.asList(line.split(","));
                Exercise e = new Exercise();
                e.setCategory(exerciseList.get(0));
                e.setMuscles(exerciseList.get(1));
                e.setExercise_name(exerciseList.get(3));
                e.setImage(exerciseList.get(4));
                e.setInstructions(exerciseList.get(5));

                exerciseDAO.insert(e);
            }
        } catch (IOException iex) {
            System.err.println(iex);
            throw iex;
        } finally {
            if (null != reader)
                reader.close();
        }
        ;
    }
}
