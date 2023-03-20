package kr.co.e1ct.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EslipReissueRequest {

    @SerializedName("apiKey")
    private String apiKey;
    @SerializedName("issu_dvsn_code")
    private String issuDvsnCode;
    @SerializedName("vhcl_crd_no")
    private String vhclCrdNo;
    @SerializedName("vhcl_no")
    private String vhclNo;
    @SerializedName("tkin_1_cntnr_no")
    private String tkin1CntrNo;
    @SerializedName("tkin_1_strge_lctn_adrs")
    private String tkin1StrgeLctnAdrs;
    @SerializedName("tkin_1_cntnr_kind_value")
    private String tkin1CntnrKindValue;
    @SerializedName("tkin_1_fcntnr_ecntnr_dvsn_code_value")
    private String tkin1FcntnrEcntnrDvsnCd;
    @SerializedName("tkin_1_shcm_code")
    private String tkin1ShcmCode;
    @SerializedName("tkin_1_cntnr_dmg_cntnt")
    private String tkin1CntnrDmgCntnt;
    @SerializedName("tkin_2_cntnr_no")
    private String tkin2CntrNo;
    @SerializedName("tkin_2_strge_lctn_adrs")
    private String tkin2StrgeLctnAdrs;
    @SerializedName("tkin_2_cntnr_kind_value")
    private String tkin2CntnrKindValue;
    @SerializedName("tkin_2_fcntnr_ecntnr_dvsn_code_value")
    private String tkin2FcntnrEcntnrDvsnCd;
    @SerializedName("tkin_2_shcm_code")
    private String tkin2ShcmCode;
    @SerializedName("tkin_2_cntnr_dmg_cntnt")
    private String tkin2CntnrDmgCntnt;
    @SerializedName("tkout_1_cntnr_no")
    private String tkout1CntrNo;
    @SerializedName("tkout_1_strge_lctn_adrs")
    private String tkout1StrgeLctnAdrs;
    @SerializedName("tkout_1_cntnr_kind_value")
    private String tkout1CntnrKindValue;
    @SerializedName("tkout_1_fcntnr_ecntnr_dvsn_code_value")
    private String tkout1FcntnrEcntnrDvsnCd;
    @SerializedName("tkout_1_shcm_code")
    private String tkout1ShcmCode;
    @SerializedName("tkout_1_cntnr_dmg_cntnt")
    private String tkout1CntnrDmgCntnt;
    @SerializedName("tkout_2_cntnr_no")
    private String tkout2CntrNo;
    @SerializedName("tkout_2_strge_lctn_adrs")
    private String tkout2StrgeLctnAdrs;
    @SerializedName("tkout_2_cntnr_kind_value")
    private String tkout2CntnrKindValue;
    @SerializedName("tkout_2_fcntnr_ecntnr_dvsn_code_value")
    private String tkout2FcntnrEcntnrDvsnCd;
    @SerializedName("tkout_2_shcm_code")
    private String tkout2ShcmCode;
    @SerializedName("tkout_2_cntnr_dmg_cntnt")
    private String tkout2CntnrDmgCntnt;
    @SerializedName("ntfct_msg_cntnt")
    private String ntfctMsgCntnt;
    @SerializedName("err_msg_cntnt")
    private String errMsgCntnt;


}
