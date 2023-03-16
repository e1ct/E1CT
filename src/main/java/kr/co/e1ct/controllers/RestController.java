package kr.co.e1ct.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.e1ct.mappers.TCntrRepository;
import kr.co.e1ct.request.ExportableInformationRequest;
import kr.co.e1ct.response.ExportableInformationResponse;
import kr.co.e1ct.response.IntegratedInformationResponse;
import kr.co.e1ct.services.InfoService;
import kr.co.e1ct.vo.TCntrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/")
public class RestController {


    @Autowired
    private InfoService infoService;


    @GetMapping("/integratedInformation")
    public ResponseEntity<IntegratedInformationResponse> integratedInformation(String cntnrCode){
        IntegratedInformationResponse response = infoService.getIntegratedInformation(cntnrCode);
        if(response == null){
            response = new IntegratedInformationResponse();
            response.setScs("N");
            response.setMsg("컨테이너정보없음");
        }
        else{
            response.setScs("Y");
            response.setMsg(" ");
            response.setTrmnlCode("IT002");
            response.setXRay(" ");
            response.setRdoChck(" ");
            response.setTkoutImprtyGroup(" ");
            response.setTkoutImprtyCode(" ");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/exportableInformation", consumes = "application/json;")
    public ResponseEntity<ExportableInformationResponse> exportableInformation(@RequestBody ExportableInformationRequest request){
        ExportableInformationResponse response = infoService.getExportableInformation(request.getCntnrList());
        return ResponseEntity.ok(response);
    }


}
