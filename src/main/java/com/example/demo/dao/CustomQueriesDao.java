package com.example.demo.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.model.prchasing.Purchaseorderheader;

import org.springframework.stereotype.Repository;

@Repository
public class CustomQueriesDao implements ICustomQueriesDao {

    @PersistenceContext
    public EntityManager entityManager;

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
    @Override
    public List<Object[]> findPurchaseOrderHeaderByDate(LocalDate initial, LocalDate finaldate) {
        String jpql = "SELECT poh, sum(pod.unitprice) FROM Purchaseorderheader poh, Purchaseorderdetail pod " +
                "  WHERE size(poh.purchaseorderdetails)>0 AND pod.purchaseorderheader.purchaseorderid = poh.purchaseorderid "
                +
                " AND poh.orderdate >= :initialDate AND poh.orderdate <= :finalDate  GROUP BY poh ORDER BY poh.orderdate ASC";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("initialDate", initial);
        query.setParameter("finalDate", finaldate);
        return query.getResultList();
    }

    /**
     * Mostrar el listado de encabezados de órdenes de compra para las órdenes que
     * tienen
     * al menos dos detalles de órdenes de compra.
     * 
     */
    @Override
    public List<Purchaseorderheader> findByLeast2() {
        String jpql = "SELECT poh FROM Purchaseorderheader poh WHERE (Select count(pod) from Purchaseorderdetail pod WHERE pod.purchaseorderheader.purchaseorderid = poh.purchaseorderid) > 1 ";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

}
