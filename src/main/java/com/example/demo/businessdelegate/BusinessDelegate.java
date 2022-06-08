package com.example.demo.businessdelegate;

import java.util.List;

import com.example.demo.model.prchasing.Purchaseorderdetail;
import com.example.demo.model.prchasing.Purchaseorderheader;
import com.example.demo.model.prchasing.Shipmethod;
import com.example.demo.model.prchasing.Vendor;
import com.example.demo.model.prod.Billofmaterial;
import com.example.demo.model.prod.Productreview;

public interface BusinessDelegate {

    // Vendors
    void saveVendor(Vendor vendor);

    void editVendor(Vendor vendor);

    void deleteVendor(Vendor vendor);

    List<Vendor> findAllVendors();

    Vendor findByIdVendor(Integer id);

    // Ship method

    Shipmethod saveShipMethod(Shipmethod shipmethod);

    void editShipMethod(Shipmethod shipmethod);

    void deleteShipMethod(Shipmethod shipmethod);

    List<Shipmethod> findAllShipMethods();

    Shipmethod findByIdShipMethod(Integer id);

    // Purchase order details(pod)

    void savePod(Purchaseorderdetail pod);

    void editPod(Purchaseorderdetail pod);

    void deletePod(Purchaseorderdetail pod);

    List<Purchaseorderdetail> findAllPod();

    Purchaseorderdetail findByIPod(Integer id);

    // Purchase order headers (poh)
    void savePoh(Purchaseorderheader poh);

    void editPoh(Purchaseorderheader poh);

    void deletePoh(Purchaseorderheader poh);

    List<Purchaseorderheader> findAllPoh();

    Purchaseorderheader findByIdPoh(Integer id);

    // out of tests
    // Product review
    void saveProductreview(Productreview pr);

    void editProductreview(Productreview pr);

    void deleteProductreview(Productreview pr);

    List<Productreview> findAllProductreview();

    Productreview findByIdProductreview(Integer id);

    // Bill of material
    void saveBill(Billofmaterial bm);

    void editBill(Billofmaterial bm);

    void deleteBill(Billofmaterial bm);

    List<Billofmaterial> findAllBillofmaterial();

    Billofmaterial findByIdBillofmaterial(Integer id);

}
