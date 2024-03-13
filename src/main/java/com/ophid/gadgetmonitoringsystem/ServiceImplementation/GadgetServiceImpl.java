package com.ophid.gadgetmonitoringsystem.ServiceImplementation;

import com.ophid.gadgetmonitoringsystem.Entity.EmployeeLazarous;
import com.ophid.gadgetmonitoringsystem.Entity.GadgetLazarous;
import com.ophid.gadgetmonitoringsystem.Entity.GadgetMaintananceHistoryLazarous;
import com.ophid.gadgetmonitoringsystem.Entity.Status;
import com.ophid.gadgetmonitoringsystem.Repository.EmployeeRepository;
import com.ophid.gadgetmonitoringsystem.Repository.GadgetMaintananceRepository;
import com.ophid.gadgetmonitoringsystem.Repository.GadgetRepository;
import com.ophid.gadgetmonitoringsystem.Service.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GadgetServiceImpl implements GadgetService {

    @Autowired
    private GadgetRepository gadgetRepository;



    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GadgetMaintananceRepository gadgetMaintananceRepository;
    @Override
    public List<GadgetLazarous> findAll() {
        return gadgetRepository.findAll();
    }

    @Override
    public GadgetLazarous findById(String Id) {

        GadgetLazarous gadget = gadgetRepository.findById(Id).get();
//        gadget.setMaintananceHistories(new ArrayList<>());
//
//        List<GadgetMaintananceHistory> maintananceHistories =gadgetMaintananceRepository.findAll();
//
//        List<GadgetMaintananceHistory> mygadgetHistory=new ArrayList<>();
//
//
//        for (GadgetMaintananceHistory maintananceHistory : maintananceHistories) {
//            if (maintananceHistory.getGadget().getSerialNumber().equals(Id)) {
//               mygadgetHistory.add(maintananceHistory);
//            }
//        }
//
//        gadget.setMaintananceHistories(mygadgetHistory);


        return gadget;
    }

    @Override
    public void save(GadgetLazarous gadget) {

    }

    @Override
    public void delete(String Id) {

    }

    @Override
    public void saveGadget(GadgetLazarous gadget) {
        gadgetRepository.save(gadget);
    }

    @Override
    public GadgetLazarous updateGadget(GadgetLazarous gadget, String id) {
        return null;
    }

    @Override
    public void updateGadget2(GadgetLazarous gadget, Long id, boolean isNewImage) {

    }

    @Override
    public void finishPackaging(Long id) {

    }

    @Override
    @Transactional
    public String assignGadget(String gadgetID, String employeeID) {
        GadgetLazarous gadget = gadgetRepository.findById(gadgetID).get();
        gadget.setStatus(Status.Allocated   );
        gadget.setDateIssuedToEmployee(LocalDateTime.now());
        gadgetRepository.save(gadget);

        EmployeeLazarous employee = employeeRepository.findByEmpId(employeeID);

        List<GadgetLazarous> gadgetList= employee.getGadgetList();
       gadgetList.add(gadget);

       employee.setGadgetList(gadgetList);

         employeeRepository.save(employee);
          return "Gadget Assigned successfully";

    }

    @Override
    public EmployeeLazarous getAllocation(String gadgetID) {

        List<EmployeeLazarous> employeeList = employeeRepository.findAll();

        for (EmployeeLazarous employee : employeeList) {
            List<GadgetLazarous> gadgetList = employee.getGadgetList();
            for (GadgetLazarous gadget : gadgetList) {
                if (gadget.getSerialNumber().equals(gadgetID)) {
                    return employee;
                }
            }
        }
        return null;
    }

    @Override
    public String saveMaintanance(GadgetMaintananceHistoryLazarous maintananceRequest) {
        gadgetMaintananceRepository.save(maintananceRequest);
        return "Maintanance Request Saved successfully";
    }

    @Override
    public List<GadgetMaintananceHistoryLazarous> getMaintanancesForGadget(String gadgateID) {
        List<GadgetMaintananceHistoryLazarous> maintananceHistories=gadgetMaintananceRepository.findAll();

        List<GadgetMaintananceHistoryLazarous> gadgetMaintananceHistories=new ArrayList<>();

        for (GadgetMaintananceHistoryLazarous gadgetMaintanance:maintananceHistories){
            if (gadgetMaintanance.getGadget().getSerialNumber().equals(gadgateID)){
                gadgetMaintananceHistories.add(gadgetMaintanance);
            }
        }

     return    gadgetMaintananceHistories;
    }
}
