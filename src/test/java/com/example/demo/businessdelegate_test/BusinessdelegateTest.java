package com.example.demo.businessdelegate_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.businessdelegate.BusinessDelegateImp;
import com.example.demo.model.prchasing.Purchaseorderdetail;
import com.example.demo.model.prchasing.Purchaseorderheader;
import com.example.demo.model.prchasing.Shipmethod;
import com.example.demo.model.prchasing.Vendor;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BusinessdelegateTest {
    private final static String URL = "http://localhost:8080/api";
    private final static String VENDOR_URL = URL + "/vendrs/";
    private final static String SHIPMETHOD_URL = URL + "/shmeth/";
    private final static String POD_URL = URL + "/pods/";
    private final static String POH_URL = URL + "/pohs/";
    private final static String PREVIEW_URL = URL + "/prdreviews/";
    private final static String BILL_URL = URL + "/bills/";

    @Mock
    private static RestTemplate restTemplate;

    @InjectMocks
    private static BusinessDelegateImp bd;

    // VENDOR
    @Test
    public void saveVendorTest() {
        Vendor v = new Vendor();
        HttpEntity<Vendor> request = new HttpEntity<>(v);

        when(restTemplate.postForObject(VENDOR_URL, request, Vendor.class)).thenReturn(v);
        bd.saveVendor(v);
        verify(restTemplate).postForObject(VENDOR_URL, request, Vendor.class);
    }

    @Test
    public void editVendorTest() {
        Vendor v = new Vendor();
        bd.editVendor(v);
        verify(restTemplate).put(VENDOR_URL, v, Vendor.class);
    }

    @Test
    public void deleteVendorTest() {
        Vendor v = new Vendor();
        bd.deleteVendor(v);

        verify(restTemplate).delete(VENDOR_URL + v.getBusinessentityid());
    }

    @Test
    public void findAllVendorsTest() {
        Vendor[] list = new Vendor[4];

        when(restTemplate.getForObject(VENDOR_URL, Vendor[].class)).thenReturn(list);

        assertEquals(bd.findAllVendors().size(), 4);
    }

    @Test
    public void findByIdVendorsTest() {
        Vendor v = new Vendor();
        when(restTemplate.getForObject(VENDOR_URL + v.getBusinessentityid(), Vendor.class)).thenReturn(v);

        assertEquals(bd.findByIdVendor(v.getBusinessentityid()).getBusinessentityid(), v.getBusinessentityid());
    }

    // SHIPMETHODS

    @Test
    public void saveSMTest() {
        Shipmethod sm = new Shipmethod();
        HttpEntity<Shipmethod> request = new HttpEntity<>(sm);

        when(restTemplate.postForObject(SHIPMETHOD_URL, request, Shipmethod.class)).thenReturn(sm);
        bd.saveShipMethod(sm);
        verify(restTemplate).postForObject(SHIPMETHOD_URL, request, Shipmethod.class);
    }

    @Test
    public void editSMTest() {
        Shipmethod sm = new Shipmethod();
        bd.editShipMethod(sm);
        verify(restTemplate).put(SHIPMETHOD_URL, sm, Shipmethod.class);
    }

    @Test
    public void deleteSMTest() {
        Shipmethod sm = new Shipmethod();
        bd.deleteShipMethod(sm);

        verify(restTemplate).delete(SHIPMETHOD_URL + sm.getShipmethodid());
    }

    @Test
    public void findAllSMTest() {
        Shipmethod[] list = new Shipmethod[6];

        when(restTemplate.getForObject(SHIPMETHOD_URL, Shipmethod[].class)).thenReturn(list);

        assertEquals(bd.findAllShipMethods().size(), 6);
    }

    @Test
    public void findByIdSMTest() {
        Shipmethod sm = new Shipmethod();
        when(restTemplate.getForObject(SHIPMETHOD_URL + sm.getShipmethodid(), Shipmethod.class)).thenReturn(sm);

        assertEquals(bd.findByIdShipMethod(sm.getShipmethodid()).getShipmethodid(), sm.getShipmethodid());
    }

    // PURCHASE ORDER DETAILS

    @Test
    public void savePODTest() {
        Purchaseorderdetail pod = new Purchaseorderdetail();
        HttpEntity<Purchaseorderdetail> request = new HttpEntity<>(pod);

        when(restTemplate.postForObject(POD_URL, request, Purchaseorderdetail.class)).thenReturn(pod);
        bd.savePod(pod);
        verify(restTemplate).postForObject(POD_URL, request, Purchaseorderdetail.class);
    }

    @Test
    public void editPODTest() {
        Purchaseorderdetail pod = new Purchaseorderdetail();
        bd.editPod(pod);
        verify(restTemplate).put(POD_URL, pod, Purchaseorderdetail.class);
    }

    @Test
    public void deletePODTest() {
        Purchaseorderdetail pod = new Purchaseorderdetail();
        bd.deletePod(pod);

        verify(restTemplate).delete(POD_URL + pod.getId());
    }

    @Test
    public void findAllPODTest() {
        Purchaseorderdetail[] list = new Purchaseorderdetail[5];

        when(restTemplate.getForObject(POD_URL, Purchaseorderdetail[].class)).thenReturn(list);

        assertEquals(bd.findAllPod().size(), 5);
    }

    @Test
    public void findByIdPODTest() {
        Purchaseorderdetail pod = new Purchaseorderdetail();
        when(restTemplate.getForObject(POD_URL + pod.getId(), Purchaseorderdetail.class)).thenReturn(pod);

        assertEquals(bd.findByIPod(pod.getId()).getId(), pod.getId());
    }

    // PURCHASE ORDER HEADER

    @Test
    public void savePOHTest() {
        Purchaseorderheader poh = new Purchaseorderheader();
        HttpEntity<Purchaseorderheader> request = new HttpEntity<>(poh);

        when(restTemplate.postForObject(POH_URL, request, Purchaseorderheader.class)).thenReturn(poh);
        bd.savePoh(poh);
        verify(restTemplate).postForObject(POH_URL, request, Purchaseorderheader.class);
    }

    @Test
    public void editPOHTest() {
        Purchaseorderheader poh = new Purchaseorderheader();
        bd.editPoh(poh);
        verify(restTemplate).put(POH_URL, poh, Purchaseorderheader.class);
    }

    @Test
    public void deletePOHTest() {
        Purchaseorderheader poh = new Purchaseorderheader();
        bd.deletePoh(poh);

        verify(restTemplate).delete(POH_URL + poh.getPurchaseorderid());
    }

    @Test
    public void findAllPOHTest() {
        Purchaseorderheader[] list = new Purchaseorderheader[4];

        when(restTemplate.getForObject(POH_URL, Purchaseorderheader[].class)).thenReturn(list);

        assertEquals(bd.findAllPoh().size(), 4);
    }

    @Test
    public void findByIdPOHTest() {
        Purchaseorderheader poh = new Purchaseorderheader();
        when(restTemplate.getForObject(POH_URL + poh.getPurchaseorderid(), Purchaseorderheader.class)).thenReturn(poh);

        assertEquals(bd.findByIdPoh(poh.getPurchaseorderid()).getPurchaseorderid(), poh.getPurchaseorderid());
    }
}