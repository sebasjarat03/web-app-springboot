package com.example.demo.businessdelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.prchasing.Purchaseorderdetail;
import com.example.demo.model.prchasing.Purchaseorderheader;
import com.example.demo.model.prchasing.Shipmethod;
import com.example.demo.model.prchasing.Vendor;
import com.example.demo.model.prod.Billofmaterial;
import com.example.demo.model.prod.Productreview;

@Component
public class BusinessDelegateImp implements BusinessDelegate {

    private final static String URL = "http://localhost:8080/api";
    private final static String VENDOR_URL = URL + "/vendrs/";
    private final static String SHIPMETHOD_URL = URL + "/shmeth/";
    private final static String POD_URL = URL + "/pods/";
    private final static String POH_URL = URL + "/pohs/";
    private final static String PREVIEW_URL = URL + "/prdreviews/";
    private final static String BILL_URL = URL + "/bills/";

    private RestTemplate restTemplate;

    @Autowired
    public BusinessDelegateImp() {
        this.restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }

    @Override
    public void saveVendor(Vendor vendor) {
        HttpEntity<Vendor> request = new HttpEntity<>(vendor);
        restTemplate.postForObject(VENDOR_URL, request, Vendor.class);

    }

    @Override
    public void editVendor(Vendor vendor) {
        restTemplate.put(VENDOR_URL, vendor, Vendor.class);

    }

    @Override
    public void deleteVendor(Vendor vendor) {
        restTemplate.delete(VENDOR_URL + vendor.getBusinessentityid());

    }

    @Override
    public List<Vendor> findAllVendors() {

        return Arrays.asList(restTemplate.getForObject(VENDOR_URL, Vendor[].class));
    }

    @Override
    public Vendor findByIdVendor(Integer id) {

        return restTemplate.getForObject(VENDOR_URL + id, Vendor.class);
    }

    @Override
    public Shipmethod saveShipMethod(Shipmethod shipmethod) {
        HttpEntity<Shipmethod> request = new HttpEntity<>(shipmethod);
        return restTemplate.postForObject(SHIPMETHOD_URL, request, Shipmethod.class);
    }

    @Override
    public void editShipMethod(Shipmethod shipmethod) {
        restTemplate.put(SHIPMETHOD_URL, shipmethod, Shipmethod.class);

    }

    @Override
    public void deleteShipMethod(Shipmethod shipmethod) {
        restTemplate.delete(SHIPMETHOD_URL + shipmethod.getShipmethodid());

    }

    @Override
    public List<Shipmethod> findAllShipMethods() {
        return Arrays.asList(restTemplate.getForObject(SHIPMETHOD_URL, Shipmethod[].class));
    }

    @Override
    public Shipmethod findByIdShipMethod(Integer id) {

        return restTemplate.getForObject(SHIPMETHOD_URL + id, Shipmethod.class);
    }

    @Override
    public void savePod(Purchaseorderdetail pod) {
        HttpEntity<Purchaseorderdetail> request = new HttpEntity<>(pod);
        restTemplate.postForObject(POD_URL, request, Purchaseorderdetail.class);

    }

    @Override
    public void editPod(Purchaseorderdetail pod) {
        restTemplate.put(POD_URL, pod, Purchaseorderdetail.class);

    }

    @Override
    public void deletePod(Purchaseorderdetail pod) {
        restTemplate.delete(POD_URL + pod.getId());

    }

    @Override
    public List<Purchaseorderdetail> findAllPod() {
        return Arrays.asList(restTemplate.getForObject(POD_URL, Purchaseorderdetail[].class));
    }

    @Override
    public Purchaseorderdetail findByIPod(Integer id) {
        return restTemplate.getForObject(POD_URL + id, Purchaseorderdetail.class);
    }

    @Override
    public void savePoh(Purchaseorderheader poh) {
        HttpEntity<Purchaseorderheader> request = new HttpEntity<>(poh);
        restTemplate.postForObject(POH_URL, request, Purchaseorderheader.class);

    }

    @Override
    public void editPoh(Purchaseorderheader poh) {
        restTemplate.put(POH_URL, poh, Purchaseorderheader.class);

    }

    @Override
    public void deletePoh(Purchaseorderheader poh) {
        restTemplate.delete(POH_URL + poh.getPurchaseorderid());

    }

    @Override
    public List<Purchaseorderheader> findAllPoh() {
        return Arrays.asList(restTemplate.getForObject(POH_URL, Purchaseorderheader[].class));
    }

    @Override
    public Purchaseorderheader findByIdPoh(Integer id) {
        return restTemplate.getForObject(POH_URL + id, Purchaseorderheader.class);
    }

    @Override
    public void saveProductreview(Productreview pr) {
        HttpEntity<Productreview> request = new HttpEntity<>(pr);
        restTemplate.postForObject(PREVIEW_URL, request, Productreview.class);

    }

    @Override
    public void editProductreview(Productreview pr) {
        restTemplate.put(PREVIEW_URL, pr, Productreview.class);

    }

    @Override
    public void deleteProductreview(Productreview pr) {
        restTemplate.delete(PREVIEW_URL + pr.getProductreviewid());

    }

    @Override
    public List<Productreview> findAllProductreview() {
        return Arrays.asList(restTemplate.getForObject(PREVIEW_URL, Productreview[].class));
    }

    @Override
    public Productreview findByIdProductreview(Integer id) {
        return restTemplate.getForObject(PREVIEW_URL + id, Productreview.class);
    }

    @Override
    public void saveBill(Billofmaterial bm) {
        HttpEntity<Billofmaterial> request = new HttpEntity<>(bm);
        restTemplate.postForObject(BILL_URL, request, Billofmaterial.class);

    }

    @Override
    public void editBill(Billofmaterial bm) {
        restTemplate.put(BILL_URL, bm, Billofmaterial.class);

    }

    @Override
    public List<Billofmaterial> findAllBillofmaterial() {
        return Arrays.asList(restTemplate.getForObject(BILL_URL, Billofmaterial[].class));
    }

    @Override
    public Billofmaterial findByIdBillofmaterial(Integer id) {
        return restTemplate.getForObject(BILL_URL + id, Billofmaterial.class);
    }

    @Override
    public void deleteBill(Billofmaterial bm) {
        restTemplate.delete(BILL_URL + bm.getBillofmaterialsid());

    }

}
