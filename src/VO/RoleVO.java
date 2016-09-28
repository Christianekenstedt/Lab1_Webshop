package VO;

import BL.Role;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-28.
 */
public class RoleVO {
    private int id;
    private String name;

    public RoleVO(Role role){
        this.id = role.getId();
        this.name = role.getName();
    }

    public static Collection<RoleVO> viewAllRoles(){
        Vector<RoleVO> roles = new Vector<>();

        for(Role r : Role.getAll()){
            roles.add(new RoleVO(r));
        }
        return roles;
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
}
