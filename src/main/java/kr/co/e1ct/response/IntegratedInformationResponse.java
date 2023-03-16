package kr.co.e1ct.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegratedInformationResponse {

    private String scs;

    private String msg = "";

    private String trmnlCode;

    private String cntnrCode;

    private String mtshVygNo;

    private String shcmCode;

    private String shcmName;

    private String xpimpDvsnName;

    private String cntnrLctn;

    private String fcntnrEcntnrCode;

    private String sizeType;

    private String vhclNo;

    private String cntnrWght;

    private String imdgNo;

    private String unno;

    private String rfrgrTmprt;

    private String xRay;

    private String rdoChck;

    private String yrdLctn;

    private String loadPrtCode;

    private String unldPrtCode;

    private String tkinDt;

    private String tkoutDt;

    private String tkoutImprtyYn;

    private String tkoutImprtyGroup;

    private String tkoutImprtyCode;

    private String tkoutImprtyRsn;

    private String dmg;

    private String stckDays;

    private String cnCl;



}
