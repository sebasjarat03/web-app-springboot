package com.example.demo.dao;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.prchasing.Purchaseorderheader;

public interface ICustomQueriesDao {
    /*
     * Lo(s) encabezados de órdenes de producto (s) con sus datos y suma de valor de
     * los
     * detalles de órden (actualizadas en un rango de fechas de orden dadas),
     * ordenados por
     * la fecha de orden. Recibe como parámetro un rango de fechas y retorna todos
     * los
     * encabezados que cumplen con tener al menos un detalle de orden para esas
     * fechas.
     */
    List<Object[]> findPurchaseOrderHeaderByDate(LocalDate initial, LocalDate finaldate);

    /**
     * Mostrar el listado de encabezados de órdenes de compra para las órdenes que
     * tienen
     * al menos dos detalles de órdenes de compra.
     * 
     */

    List<Purchaseorderheader> findByLeast2();
}
