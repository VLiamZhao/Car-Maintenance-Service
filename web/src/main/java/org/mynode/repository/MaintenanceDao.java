package org.mynode.repository;

import org.mynode.model.Maintenance;

import java.util.List;

public interface MaintenanceDao {
    Maintenance save(Maintenance maintenance);
    boolean deleteMaintenanceById(long id);
    List<Maintenance> getMaintenanceOrders();
    Maintenance getMaintenanceById(long id);
}
