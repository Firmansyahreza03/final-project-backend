package com.lawencon.community.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.memberCommunity.PojoFindMemberCommunityWithTimeLimitReq;
import com.lawencon.community.pojo.paymentTransaction.PojoDataPaymentTransaction;
import com.lawencon.community.pojo.report.PojoReportPaymentByCommunity;
import com.lawencon.community.pojo.report.PojoReportUserByCommunity;
import com.lawencon.community.service.PaymentTransactionService;
import com.lawencon.community.service.ReportService;
import com.lawencon.model.SearchQuery;
import com.lawencon.util.JasperUtil;

@RestController
@RequestMapping("report")
public class ReportController {
	@Autowired
	private ReportService service;
	@Autowired
	private PaymentTransactionService paymentService;
	@Autowired
	private JasperUtil jasperUtil;
	
	@GetMapping("/payment")
	public ResponseEntity<?> reportSample() throws Exception {
		SearchQuery<PojoDataPaymentTransaction> listData = paymentService.getAll(null, null, null);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("company", "Filos Community");

		byte[] out = jasperUtil.responseToByteArray(listData.getData(), map, "payment");
		
		String fileName = "payment.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+ "\"")
				.body(out);
	}

//	Menampilkan report informasi member yang mengikuti event ataupun course pada periode tertentu 
	@GetMapping("/user")
	public ResponseEntity<?> reportUserComm(@RequestBody @Valid PojoFindMemberCommunityWithTimeLimitReq req) throws Exception {
		SearchQuery<PojoReportUserByCommunity> listData = service.userReport(req);
		
		Map<String, Object> map = new HashMap<>();
		map.put("company", "Filos Community");

		byte[] out = jasperUtil.responseToByteArray(listData.getData(), map, "userReport");
		
		String fileName = "AttendCommunityReport.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+ "\"")
				.body(out);
	}
	
//	Menampilkan report informasi penghasilan dari event ataupun course pada periode tertentu 
	@GetMapping("/income")
	public ResponseEntity<?> reportIncomeComm(@RequestBody @Valid PojoFindMemberCommunityWithTimeLimitReq req) throws Exception {
		SearchQuery<PojoReportPaymentByCommunity> listData = service.paymentReport(req);
		
		Map<String, Object> map = new HashMap<>();
		map.put("company", "Filos Community");

		byte[] out = jasperUtil.responseToByteArray(listData.getData(), map, "paymentReport");
		
		String fileName = "IncomeCommunityReport.pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+ "\"")
				.body(out);
	}
}
