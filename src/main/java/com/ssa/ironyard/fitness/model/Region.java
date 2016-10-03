package com.ssa.ironyard.fitness.model;

public class Region implements DomainObject {
    Integer id;

    REGION region;
    
    public Region() {
        // TODO Auto-generated constructor stub
    }
    
    public enum REGION{
       ARMS, BACK, CORE, CARDIO, LEGS 
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public REGION getRegion() {
        return region;
    }

    public void setRegion(REGION region) {
        this.region = region;
    }
    
    @Override
    public String toString() {
        return "Region [id=" + id + ", region=" + region + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Region other = (Region) obj;
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
        Region other = (Region) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (region != other.region)
            return false;
        return true;
    }
    
    @Override
    public Region clone(){
        try{
            Region r = (Region) super.clone();
            return r;
        }
        catch(Exception e){
            
        }
        return null;
    }
}
