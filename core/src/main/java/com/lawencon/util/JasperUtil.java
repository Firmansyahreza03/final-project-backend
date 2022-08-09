package com.lawencon.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author lawencon05
 */

@Component
public class JasperUtil {
	
	@Value("${jasper.template-folder}")
	private String mailTemplateFolder;

	public byte[] responseToByteArray(Collection<?> data, 
			Map<String, Object> mapParams,
			String jasperName)
			throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			File files = ResourceUtils.getFile("classpath:" + mailTemplateFolder + "/" + jasperName + ".jasper");
			JasperReport jasper = (JasperReport) JRLoader.loadObject(files);
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
