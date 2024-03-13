package com.ophid.gadgetmonitoringsystem.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ophid.gadgetmonitoringsystem.Config.ResponseMessage;
import com.ophid.gadgetmonitoringsystem.Entity.EmployeeLazarous;
import com.ophid.gadgetmonitoringsystem.Entity.GadgetLazarous;
import com.ophid.gadgetmonitoringsystem.Entity.GadgetMaintananceHistoryLazarous;
import com.ophid.gadgetmonitoringsystem.Entity.LocationLazarous;
import com.ophid.gadgetmonitoringsystem.Service.DocumentService;
import com.ophid.gadgetmonitoringsystem.Service.GadgetService;
import com.ophid.gadgetmonitoringsystem.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
public class GadgetController {

    @Autowired
    private GadgetService gadgetService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private LocationService locationService;


    ObjectMapper objectmapper = new ObjectMapper();

    @RequestMapping(value = "/saveGadget/{locationId}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseMessage> saveGadget(
            @RequestParam(required = true, value = "jsonData") String jsonData,
            @RequestParam(required = true, value = "gadget_image") MultipartFile imageData,
            @PathVariable Long locationId
    ) throws IOException {

        LocationLazarous location = locationService.getLocationByID(locationId);

        GadgetLazarous gadget = this.objectmapper.readValue(jsonData, GadgetLazarous.class);
        gadget.setImage(Objects.requireNonNull(imageData.getOriginalFilename()).replaceAll(" ", "_"));
        gadget.setLocation(location);
        System.out.println("Location  : " + gadget.getLocation());
        gadgetService.saveGadget(gadget);
        String sn = gadget.getSerialNumber();
        System.out.println("Serial NUmber: " + sn);
        String message = "";

        try {
            this.documentService.store(imageData, "gadget", sn);

            message = "Gadget Saved successfully : ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception var8) {
            message = "Could not upload the image";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }


    }

    @GetMapping("getAllGadgets")
    private List<GadgetLazarous> gadgetList() {
        return gadgetService.findAll();
    }


    @GetMapping("getGadget/{gadgetID}")
    private GadgetLazarous getGadget(@PathVariable String gadgetID) {
        return gadgetService.findById(gadgetID);
    }

    @PutMapping("assign/{gadgetID}/{employeeID}")
    private String assignGadget(@PathVariable String gadgetID, @PathVariable String employeeID) {
        return gadgetService.assignGadget(gadgetID, employeeID);
    }


    @GetMapping("getAllocation/{gadgetID}")
    private EmployeeLazarous getAllocation(@PathVariable String gadgetID) {
        return gadgetService.getAllocation(gadgetID);
    }


    @PostMapping("saveMaintanance/{gadgetID}")
    private String saveMaintanance(@PathVariable String gadgetID, @RequestBody GadgetMaintananceHistoryLazarous maintananceRequest) {
      GadgetLazarous gadget = gadgetService.findById(gadgetID);

      maintananceRequest.setGadget(gadget);
      maintananceRequest.setDateTime(LocalDateTime.now());

        return gadgetService.saveMaintanance(maintananceRequest);
    }


    @GetMapping("GetMaintananceHistory/{gadgetId}")
    public  List<GadgetMaintananceHistoryLazarous> gadgetMaintananceHistories(@PathVariable String gadgetId){
        return gadgetService.getMaintanancesForGadget(gadgetId);
    }

}
