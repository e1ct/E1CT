package kr.co.e1ct.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class E1EslipReissueRequest {

    private String apiKey;

    private String vhcleNo;

    @JsonProperty("issu_request")
    private String issuRequest;

}
