package com.ophid.gadgetmonitoringsystem.Service;

import com.ophid.gadgetmonitoringsystem.Entity.EmployeeLazarous;
import com.ophid.gadgetmonitoringsystem.Entity.GadgetLazarous;
import com.ophid.gadgetmonitoringsystem.Entity.GadgetMaintananceHistoryLazarous;

import java.util.List;

public interface GadgetService {
    public List<GadgetLazarous> findAll();

    public GadgetLazarous findById(String Id);

    public void save(GadgetLazarous gadget);

    public void delete(String Id);

    void saveGadget(GadgetLazarous gadget);

    GadgetLazarous updateGadget(GadgetLazarous gadget, String id);

    void updateGadget2(GadgetLazarous gadget, Long id, boolean isNewImage);

    void finishPackaging(Long id);

    String assignGadget(String gadgetID, String employeeID);

    EmployeeLazarous getAllocation(String gadgetID);

    String saveMaintanance(GadgetMaintananceHistoryLazarous maintananceRequest);

    List<GadgetMaintananceHistoryLazarous> getMaintanancesForGadget(String gadgateID);
}
