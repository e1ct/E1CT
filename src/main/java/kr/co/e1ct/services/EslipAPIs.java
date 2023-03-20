package kr.co.e1ct.services;

import kr.co.e1ct.request.EslipReissueRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.Map;

public interface EslipAPIs {


    @POST("api/eslip/eslipissu.do")
    @Headers("Content-Type: application/json")
    Call<Object> eslipReissue(@Body EslipReissueRequest request);

}
