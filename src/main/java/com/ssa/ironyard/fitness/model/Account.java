package com.ssa.ironyard.fitness.model;

import com.ssa.ironyard.fitness.model.Exercise.EQUIPMENT;

public class Account implements DomainObject
{

    Integer id;
    String username;
    String password;

    String firstName;
    String lastName;

    Integer age;
    Gender gender;
    Double height;
    Double weight;

    WorkoutHistory workoutHistory;
    Goal goal;

    boolean isLoaded = false;

    public Account()
    {
    }

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Account(Integer id, String username, String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public enum Gender{
        Male('M'), Female('F');
        
        public final char abbrev;

        private Gender(char abbrev) {
            this.abbrev = abbrev;
        }

        public static Gender getInstance(char abbrev) {
            for (Gender g : values()) {
                if (g.abbrev == abbrev)
                    return g;
            }
            return null;
        }
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Character getGender()
    {
        return gender;
    }

    public void setGender(Character gender)
    {
        this.gender = gender;
    }

    public Double getHeight()
    {
        return height;
    }

    public void setHeight(Double height)
    {
        this.height = height;
    }

    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    public WorkoutHistory getWorkoutHistory()
    {
        return workoutHistory;
    }

    public void setWorkoutHistory(WorkoutHistory workoutHistory)
    {
        this.workoutHistory = workoutHistory;
    }

    public Goal getGoal()
    {
        return goal;
    }

    public void setGoal(Goal goal)
    {
        this.goal = goal;
    }

    public boolean isLoaded()
    {
        return isLoaded;
    }

    public void setLoaded(boolean isLoaded)
    {
        this.isLoaded = isLoaded;
    }

    @Override
    public Account clone()
    {
        try
        {
            return (Account) super.clone();
        } catch (CloneNotSupportedException ex)
        {
        }
        return null;
    }

    public boolean equals(Object obj)
    {
        Account other = (Account) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((goal == null) ? 0 : goal.hashCode());
        result = prime * result + ((height == null) ? 0 : height.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (isLoaded ? 1231 : 1237);
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((workoutHistory == null) ? 0 : workoutHistory.hashCode());
        return result;
    }

    public boolean deepEquals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (age == null)
        {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (firstName == null)
        {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (gender == null)
        {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (goal == null)
        {
            if (other.goal != null)
                return false;
        } else if (!goal.equals(other.goal))
            return false;
        if (height == null)
        {
            if (other.height != null)
                return false;
        } else if (!height.equals(other.height))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isLoaded != other.isLoaded)
            return false;
        if (lastName == null)
        {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (password == null)
        {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null)
        {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (weight == null)
        {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (workoutHistory == null)
        {
            if (other.workoutHistory != null)
                return false;
        } else if (!workoutHistory.equals(other.workoutHistory))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", age=" + age + ", gender=" + gender + ", height=" + height + ", weight="
                + weight + ", workoutHistory=" + workoutHistory + ", goal=" + goal + ", isLoaded=" + isLoaded + "]";
    }

   

}
