package com.sifr.my.retrofit;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitInterface {

    @Multipart
    @POST("otp_9403453182.php")//get otp for verification as response not SMS
    Call<Model4Y> retroInterfGetOTPtmp(@Part("yc") RequestBody yc, @Part("yo") RequestBody yo);

    @Multipart
//@POST("otp3.php")//get otp for verification by SMS
    @POST("otp4.php")//get otp for verification by SMS
    Call<Model4Y> retroInterfGetOTP(@Part("yc") RequestBody yc, @Part("yo") RequestBody yo);

    @Multipart
    @POST("otpv.php")//send otp for verification
    Call<Model4Y> retroInterfVeriOTP(@Part("yc") RequestBody yc, @Part("yo") RequestBody yo, @Part("ot") RequestBody ot);

    @Multipart
    @POST("otpw.php")//set pin after verifying otp again
    Call<Model4Y> retroInterfSetPw(@Part("yc") RequestBody yc, @Part("yo") RequestBody yo, @Part("ot") RequestBody ot, @Part("pi") RequestBody pi);

    @POST("del2.php")//add order to list
    Call<Model4MeWithOrder> retroInterfPlaceOrder(@Body Model4MeWithOrder model4MeWithOrder);

    //get all work buttons assigned to yz for given MXmodel
    @POST("mz.php")
    Call<Model4MXeBtns> retroInterfSetMX(@Body Model4MXonly model4MXonly);

    @POST("sm.php")
//ordered product list
    Call<Model4VehicleSeatsLst> retroInterfGetSeatLst(@Body Model4VehicleSeatsLst model4VehicleSeatsLst);

    @POST("bk.php")
//all product list
    Call<Model4VehicleSeatsLst> retroInterfBookSeats(@Body Model4VehicleSeatsLst model2bookTravelSeats);

    @POST("vel.php")
//all vehicle list
    Call<Model4lstVehicleAvailableByDt> retroInterfGetVehListOfDt(@Body Model4vehicleAvailableByDt model4vehicleAvailableByDt);

    @POST("mx.php")
//all product list
    Call<Model4lstOfVehicle> retroInterfGetVecLst(@Body Model4MXonly model4MXonly);

    @POST("mw.php")
//all product list
    Call<Model4successEid> retroInterfAddVecInOurLst(@Body Model4vehicle mModel4vehicle);

    @POST("mv.php")
//all product list
    Call<Model4successEid> retroInterfAorUVecAvailByDt(@Body Model4vehicleAvailableByDt pModel4vehicleAvailableByDt);

    @POST("mu.php")
//ordered product list
    Call<Model4LstTravelStops> retroInterfGetStopLst(@Body Model4VehicleSeatsLst model4VehicleSeatsLst);

    @POST("mt.php")
//all product list
    Call<Model4successEid> retroInterfChkPiGetTk(@Body Model4Y mModel4Y);

    //update new mo. number if button id exists or in model or
    //add new phone number in permissions (p1) list
    @POST("mr.php")
    Call<Model4successEid> retroInterfSetORupBtnPerm(@Body Model4MXeBtns mModel4MXeBtns);

    @POST("ms.php")
    //all product list
    Call<Model4LstSPJC> retroInterfGetSerProdJobConsumList(@Body Model4MXonly model4MXonly);

    @POST("my.php")
//ordered product list
    Call<Model4VehicleSeatsLst> retroInterfCancelSeat(@Body Model4VehicleSeatsLst model4VehicleSeatsLst);

    @POST("mq.php")
    //get list of sites
    Call<Model4lstSite> retroInterfGetSiteLst(@Body Model4MXonly model4MXonly);

    @POST("mp.php")
        //get list of labours on given site
    Call<Model4empPresencyLst> retroInterfGetLabourLst(@Body Model4MXonly model4MXonly);

    @POST("mo.php")
    Call<Model4successEid> retroInterfCRUDemp(@Body Model2addEmp2Site model2addEmp2Site);

    @POST("mn.php")
    Call<Model4successEid> retroInterfUpInLabourPresency(@Body Model4empPresencyLst pModel4empPresencyLst);

    @POST("ml.php")
    //add progress report to site;
    Call<Model4successEid> retroInterfAddPrgrsStmt(@Body Model4lstPrgrsRpt mModel4lstPrgrsRpt);

    @POST("mj.php")
        //get list of sites
    Call<Model4lstPrgrsRpt> retroInterfGetPrgrsRprtLst(@Body Model4MXonly model4MXonly);

    @POST("mh.php")//copy of mq.php
    //get list of expenditure
    Call<Model4lstXp> retroInterfGetXpLst(@Body Model4MXonly model4MXonly);

    @POST("mg.php")//copy of ml.php; add newe expenses name;
    Call<Model4successEid> retroInterfAddXpNm(@Body Model4MXonly mmModel4MXonly);

    @POST("mf.php")//copy of ml.php;add expenditure quantity;
    Call<Model4successEid> retroInterfAddXp(@Body Model4snglXp mModel4snglXp);

    @POST("me.php")//copy of mq.php
        //get list of expenditure
    Call<Model4lstXp> retroInterfGetLstXpQty(@Body Model4MXonly model4MXonly);

    @POST("mc.php")//copy of mh.php; get list of machines
    Call<Model4lstMch> retroInterfGetMchLst(@Body Model4MXonly model4MXonly);

    @POST("mb.php")//copy of mg.php; add new machine name;
    Call<Model4successEid> retroInterfAddMchNm(@Body Model4MXonly mmModel4MXonly);

    @POST("ma.php")//copy of mf.php;add machine quantity to site;
    Call<Model4successEid> retroInterfAddMch2Site(@Body Model4snglMch mModel4snglMch);

    @POST("m9.php")//copy of mh.php; get list of machines with separate site
    Call<Model4lstMch> retroInterfGetMchLstOnSite(@Body Model4MXonly model4MXonly);

//    @POST("otp2.php")//get otp for verification
//    Call<Model4Y> retroInterfGetOTP(@Body Model4Y mModel4Y);

//    @POST("otpv.php")
////send otp for verification
//    Call<Model4Y> retroInterfVeriOTP(@Body Model4Y mModel4Y);

    @POST("prodsl.php")
//get list of multiple images of selected item
    Call<Model4Y> retroInterfMultiSPJImgs(@Body Model4Y mModel4Y);

/*

    //login with papw@emr and get last update date in return.
//    @POST("spc_2.php")
//    Call<Model4D> retroInterfLoginWithPaPw(@Body ModelLstOfEmUVY modelLstOfEmUVY);
    @POST("spc_2.php")
    Call<Model4successEid> retroInterfLoginWithPaPw(@Body Model4successEid model4Me);

    //    @POST("spc_1.php")
////individiual form
//    Call<ModelResponseStandard> retroInterfSaveIndividual(@Body ModelLstOfEmUVY modelLstOfEmUVY);
    @POST("spc_1.php")
//individiual form
    Call<Model4successEid> retroInterfCParty(@Body Model4successEid mModel4Me);//create party

    @POST("spc_4.php")
//individiual form
    Call<Model4successEid> retroInterfUParty(@Body Model4successEid mModel4Me);//update party

    @POST("7/delete22.php")
//contractor form
    Call<ModelResponseStandard> contractorFormCall(@Body ModelGivenContractorForm modelGivenContractorForm);
*/



/*

    @POST("7/delete19.php")
//party(customer or supplier) list
    Call<ModelParty> retroInterfGetPartyList(@Body ModelParty modelParty);

    @Multipart
    @POST("spc_5.php")
    Call<ModelResponseOfFileUpload> retroInterfUploadFls(@Part("e") RequestBody e,
                                                         @Part("m") RequestBody m,
                                                         @Part("pa") RequestBody pa,
                                                         @Part("pw") RequestBody pw,
                                                         @Part("dd") RequestBody dd,
                                                         @Part("id[]") String[] id,
                                                         @Part List<MultipartBody.Part> multipleFlsToUpload
    );

    @Multipart
    @POST("7/delete17.php")
    Call<ModelResponseDefualt> retroInterfSaveReminder(@Part("api_token") RequestBody api_token,
                                                       @Part("name") RequestBody name,
                                                       @Part("country_code") RequestBody countrycode,
                                                       @Part("mobile_number") RequestBody mobilenumber,
                                                       @Part("company_name") RequestBody companyName,
                                                       @Part("note") RequestBody note,
                                                       @Part("reminder_date") RequestBody reminderDate,
                                                       @Part("reminder_time") RequestBody reminderTime,
                                                       @Part List<MultipartBody.Part> multipleDocFiles2Upload
    );

    @POST("gro/receiptupload.php")
//Receipt upload
    Call<ModelResponseDefualt> retroInterfReceiptListUpload(@Body ArrayList<ModelRequestReceiptUpload> modelRequestReceiptUpload);

    @POST("7/delete6.php")
//product list
    Call<ModelResponseDefualt> retroInterfSalesListUpload(@Body List<ModelRequestSales> listOfModelRequestSales);
*/
}
