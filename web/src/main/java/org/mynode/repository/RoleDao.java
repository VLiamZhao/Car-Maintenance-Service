package org.mynode.repository;

import org.mynode.model.Role;

public interface RoleDao {
    Role getRoleByName(String name);
    Role getRoleById(long id);
}