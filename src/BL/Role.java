package BL;

import DAL.RoleDB;

import java.util.Collection;

/**
 * Created by Anton on 2016-09-28.
 */
public class Role {
    private int id;
    private String name;

    protected Role(int id, String name){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Collection<Role> getAll(){
        return RoleDB.getAllFromDB();
    }
}
