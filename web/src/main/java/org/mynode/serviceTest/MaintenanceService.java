package org.mynode.serviceTest;

import org.mynode.model.Maintenance;
import org.mynode.repository.MaintenanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenanceDao maintenanceDao;

    public Maintenance save(Maintenance maintenance){
        return maintenanceDao.save(maintenance);
    }

    public boolean deleteMaintenanceById(long id) {
        return maintenanceDao.deleteMaintenanceById(id);
    }

    public List<Maintenance> getMaintenanceOrders(){
        return maintenanceDao.getMaintenanceOrders();
    }

    public Maintenance getMaintenanceById(long id){
        return maintenanceDao.getMaintenanceById(id);
    }
}
