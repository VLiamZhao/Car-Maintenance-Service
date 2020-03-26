package org.mynode.controller;

import org.mynode.model.Customer;
import org.mynode.model.Maintenance;
import org.mynode.service.MaintenanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/maintenance"})
public class MaintenanceController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MaintenanceService maintenanceService;

    /**
     * GET {prefix}/maintenance
     * @param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Maintenance> getMaintenanceOrders(){
        return maintenanceService.getMaintenanceOrders();
    }

    /**
     * POST {prefix}/maintenance/body
     * @param maintenance
     * @return
     */
    @RequestMapping(value = "/body", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Maintenance createMaintenance(@RequestBody Maintenance maintenance){
        Maintenance ma = maintenanceService.save(maintenance);
        return ma;
    }
}
