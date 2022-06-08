package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.demo.dao.BillofmaterialDao;
import com.example.demo.dao.ProducreviewDao;
import com.example.demo.model.UserType;
import com.example.demo.model.Userr;
import com.example.demo.model.hr.Employee;
import com.example.demo.model.prchasing.Purchaseorderdetail;
import com.example.demo.model.prchasing.Purchaseorderheader;
import com.example.demo.model.prchasing.Shipmethod;
import com.example.demo.model.prchasing.Vendor;
import com.example.demo.model.prod.Billofmaterial;
import com.example.demo.model.prod.Productreview;
import com.example.demo.repositories.BusinessEntityRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.PurchaseOrderDetailRepository;
import com.example.demo.repositories.PurchaseOrderHeaderRepository;
import com.example.demo.repositories.ShipMethodRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PurchaseOrderDetailService;
import com.example.demo.services.PurchaseOrderHeaderService;
import com.example.demo.services.ShipMethodService;
import com.example.demo.services.VendorServiceImp;

/* @EnableJpaRepositories(basePackages = { "com.example.demo.repositories" })
@EntityScan(basePackages = { "com.example.demo.model" })*/
@ComponentScan(basePackages = { "com.example.demo" })
@SpringBootApplication
public class Taller1Application {

	public static void main(String[] args) {
		SpringApplication.run(Taller1Application.class, args);
	}

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner init(UserRepository userRepository, VendorServiceImp vendorService,
			BusinessEntityRepository ber, ShipMethodService sms, ShipMethodRepository smr,
			PurchaseOrderDetailRepository podr, PurchaseOrderDetailService pods, PurchaseOrderHeaderService pohs,
			PurchaseOrderHeaderRepository pohr, PersonRepository personRepository,
			EmployeeRepository employeeRepository, ProducreviewDao productreviewDao, BillofmaterialDao bmDao) {
		return (args) -> {
			/*
			 * Person p1 = new Person();
			 * p1.setFirstname("Alfonso");
			 * p1.setLastname("Bustamante");
			 * personRepository.save(p1);
			 * 
			 * Person p2 = new Person();
			 * p2.setFirstname("Luis");
			 * p2.setLastname("Arias");
			 * personRepository.save(p2);
			 * 
			 * Person p3 = new Person();
			 * p3.setFirstname("Luisa");
			 * p3.setLastname("Ramirez");
			 * personRepository.save(p3);
			 */

			Billofmaterial bm1 = new Billofmaterial();
			bm1.setBomlevel(2);
			bm1.setPerassemblyqty(new BigDecimal(15));
			bmDao.save(bm1);

			Productreview pr1 = new Productreview();
			pr1.setComments("Muy bueno");
			pr1.setEmailaddress("consumer@hotmail.com");
			pr1.setReviewername("Francisco");
			productreviewDao.save(pr1);

			Employee e1 = new Employee();
			e1.setGender("masculino");
			employeeRepository.save(e1);

			Employee e2 = new Employee();
			e2.setGender("masculino");
			employeeRepository.save(e2);

			Employee e3 = new Employee();
			e3.setGender("femenino");
			employeeRepository.save(e3);

			Purchaseorderheader poh1 = new Purchaseorderheader();
			poh1.setOrderdate(LocalDate.now());
			poh1.setSubtotal(new BigDecimal(15));
			pohs.save(poh1, 0, e1.getBusinessentityid());

			Purchaseorderdetail pod1 = new Purchaseorderdetail();
			pod1.setOrderqty(10);
			pod1.setUnitprice(3.0);
			pods.save(pod1, poh1.getPurchaseorderid());

			Purchaseorderdetail pod2 = new Purchaseorderdetail();
			pod2.setOrderqty(10);
			pod2.setUnitprice(3.0);
			pod2 = podr.save(pod2);

			Purchaseorderdetail pod3 = new Purchaseorderdetail();
			pod3.setOrderqty(15);
			pod3.setUnitprice(4.0);
			pod3 = podr.save(pod3);

			List<Purchaseorderdetail> podslist = new ArrayList<Purchaseorderdetail>();
			podslist.add(pod2);
			podslist.add(pod3);

			Purchaseorderheader poh2 = new Purchaseorderheader();
			poh2.setPurchaseorderdetails(podslist);
			poh2 = pohr.save(poh2);

			pod2.setPurchaseorderheader(poh2);
			pod3.setPurchaseorderheader(poh2);

			podr.save(pod2);
			podr.save(pod3);

			Shipmethod sm1 = new Shipmethod();
			sm1.setName("Ship method 1");
			sm1.setShipbase(new BigDecimal(15));
			sm1.setShiprate(new BigDecimal(10));
			sms.save(sm1);

			Vendor v1 = new Vendor();
			v1.setName("Sebastian");
			v1.setCreditrating(12);
			v1.setPurchasingwebserviceurl("https:/amazon.com");
			v1.setAccountnumber("123");
			vendorService.save(v1);

			Userr u1 = new Userr();
			u1.setUsername("admin");
			u1.setId(1);
			u1.setPassword("{noop}admin");
			u1.setUserType(UserType.ADMINISTRADOR);
			userRepository.save(u1);

			Userr u2 = new Userr();
			u2.setUsername("oper");
			u2.setId(2);
			u2.setPassword("{noop}oper");
			u2.setUserType(UserType.OPERADOR);
			userRepository.save(u2);

		};
	}

}
