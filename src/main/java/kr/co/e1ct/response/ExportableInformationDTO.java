package kr.co.e1ct.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportableInformationDTO {

    private String trmnlCode;
    private String cntnrCode;
    private String mtshCode;
    private String mtshVygNo;
    private String mtshYr;
    private String cntnrIdntfNo;
    private String trmnlTkoutImprtyYn;
    private String trmnlTkoutImprtyRsn;
    private String cstmsTkoutImprtyYn;
    private String cstmsTkoutImprtyRsn;
    private String regDt;
    private String rspnsScs;
    private String rspnsMsg;

}
