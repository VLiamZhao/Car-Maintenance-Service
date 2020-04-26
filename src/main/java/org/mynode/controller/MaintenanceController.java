package org.mynode.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mynode.model.Customer;
import org.mynode.model.Maintenance;
import org.mynode.model.view.JsView;
import org.mynode.service.MaintenanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/maintenance"})

public class MaintenanceController {
    private Logger logger = LoggerFactory.getLogger(getClass());
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
     * POST {prefix}/maintenance
     * @param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Maintenance createMaintenance(@RequestBody Maintenance maintenance){
        return maintenanceService.save(maintenance);
    }

    /**
     * GET {prefix}/{id}
     * @param inputId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Maintenance getMaintenanceById(@PathVariable(name = "id") long inputId){
        Maintenance m1 = maintenanceService.getMaintenanceById(inputId);
        return m1;
    }

    /**
     * DELETE {prefix}/{id}
     * @param inputId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean deleteMaintenanceById(@PathVariable(name = "id") long inputId){
        return maintenanceService.deleteMaintenanceById(inputId);
    }
}
