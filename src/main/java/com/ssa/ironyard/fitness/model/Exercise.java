package com.ssa.ironyard.fitness.model;

public class Exercise implements DomainObject {
    Integer id;
    String exercise_name;
    INTENSITY intensity;
    EQUIPMENT equipment;
    Region region;

    public enum EQUIPMENT {
        FREEWEIGHTS("F"), GYM("G"), NONE("N");
        public final String abbrev;

        private EQUIPMENT(String abbrev) {
            this.abbrev = abbrev;
        }

        public static EQUIPMENT getInstance(String abbrev) {
            for (EQUIPMENT equip : values()) {
                if (equip.abbrev.equals(abbrev))
                    return equip;
            }
            return null;
        }
    }

    public enum INTENSITY {
        LIGHT, MEDIUM, INTENSE
    }

    public Exercise() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public INTENSITY getIntensity() {
        return intensity;
    }

    public void setIntensity(INTENSITY intensity) {
        this.intensity = intensity;
    }

    public EQUIPMENT getEquipment() {
        return equipment;
    }

    public void setEquipment(EQUIPMENT equipment) {
        this.equipment = equipment;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Exercise [id=" + id + ", exercise_name=" + exercise_name + ", intensity=" + intensity + ", equipment="
                + equipment + ", region=" + region + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
        result = prime * result + ((exercise_name == null) ? 0 : exercise_name.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((intensity == null) ? 0 : intensity.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Exercise other = (Exercise) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
      
        return true;
    }
    
    @Override
    public boolean deeplyEquals(DomainObject obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Exercise other = (Exercise) obj;
        if (equipment != other.equipment)
            return false;
        if (exercise_name == null) {
            if (other.exercise_name != null)
                return false;
        } else if (!exercise_name.equals(other.exercise_name))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (intensity != other.intensity)
            return false;
        if (region == null) {
            if (other.region != null)
                return false;
        } else if (!region.equals(other.region))
            return false;
        return true;
    }
    
    @Override
    public Exercise clone(){
        try{
            Exercise e = (Exercise) super.clone();
            e.setRegion(this.region.clone());
            return e;
        }
        catch(Exception e){
            
        }
        return null;
    }

}
