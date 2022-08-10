package com.lawencon.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author lawencon05
 */
public class JasperUtil {

	public static byte[] responseToByteArray(Collection<?> data, String jrxmlName, Map<String, Object> mapParams)
			throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			File files = ResourceUtils.getFile("classpath:report/" + jrxmlName + ".jrxml");
			JasperReport jasper = JasperCompileManager.compileReport(files.getAbsolutePath());
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, mapParams, ds);
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);

			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

}
