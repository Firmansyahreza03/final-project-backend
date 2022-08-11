package com.lawencon.community.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.paymentTransaction.PojoDataPaymentTransaction;
import com.lawencon.community.service.PaymentTransactionService;
import com.lawencon.model.SearchQuery;
import com.lawencon.util.JasperUtil;

@RestController
@RequestMapping("report")
public class ReportController {

	@Autowired
	private PaymentTransactionService service;
	@Autowired
	private JasperUtil jasperUtil;
	
	@GetMapping("/payment")
	public ResponseEntity<?> reportSample() throws Exception {
		SearchQuery<PojoDataPaymentTransaction> listData = service.getAll(null, null, null);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("company", "PT. Company Internasional");

		byte[] out = jasperUtil.responseToByteArray(listData.getData(), map, "paymentReport");
		
		String fileName = "paymentReport.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+ "\"")
				.body(out);
	}

//	Menampilkan report informasi member yang mengikuti event ataupun course pada periode tertentu 

//	Menampilkan report informasi penghasilan dari event ataupun course pada periode tertentu 

}
