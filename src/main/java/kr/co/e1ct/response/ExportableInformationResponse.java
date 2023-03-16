package kr.co.e1ct.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportableInformationResponse {

    private String scs;
    private String msg;
    private List<ExportableInformationDTO> cntrList;

}
