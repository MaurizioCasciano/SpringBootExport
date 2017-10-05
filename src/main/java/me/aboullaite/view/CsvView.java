package me.aboullaite.view;

import me.aboullaite.model.User;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by aboullaite on 2017-02-24.
 */
public class CsvView extends AbstractCsvView {

	public static final String CSV_EXTENSION = ".csv";

	@Override
	protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String fileName = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss.SSS").format(new Date());
		fileName += CSV_EXTENSION;
		System.out.println("FileName: " + fileName);

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename='" + fileName + "'");
		
		List<User> users = (List<User>) model.get("users");
		String[] header = { "Firstname", "LastName", "LastName", "JobTitle", "Company", "Address", "City", "Country",
				"PhoneNumber" };
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		csvWriter.writeHeader(header);

		for (User user : users) {
			csvWriter.write(user, header);
		}
		csvWriter.close();

	}
}
