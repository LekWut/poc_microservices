package base.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.json.JSONObject;

import base.backingbean.BKSessionBean;
import base.utils.AppUtil;
import base.utils.Crypto;

public class FileServlet extends HttpServlet {
	private static Logger log = LogManager.getLogger(FileServlet.class);
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 1024; // 1MB.

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			String p = request.getParameter("p");
			/* ปกติ Request จะ decode ให้แล้ว */
			String decodeParam = p;
			// log.debug("decodeParam=" + decodeParam);
			Crypto crypto = new Crypto(AppUtil.getAppConfigByKey("PARAM_SECURE_KEY"));
			String decryptJson = crypto.decryptBase64(decodeParam);
			JSONObject jsonObject = new JSONObject(decryptJson);
			// log.debug("jsonObject=" + jsonObject);
			String action = jsonObject.has("action") ? jsonObject.getString("action") : null;
			String fullFileName = jsonObject.has("fullFileName") ? jsonObject.getString("fullFileName") : null;
			String blobUuid = jsonObject.has("blobUuid") ? jsonObject.getString("blobUuid") : null;
			
			response.reset();
			/* Get the MIME type of the image */
			ServletContext sc = getServletContext();
			if (action.equalsIgnoreCase("PreviewFile") || action.equalsIgnoreCase("DownloadFile")) {
				String mimeType = sc.getMimeType(fullFileName);
				// log.debug("mimeType = " + mimeType);
				if (mimeType == null) {
					log.debug("Could not get MIME type of " + fullFileName);
					String fileExtension = FilenameUtils.getExtension(fullFileName).toUpperCase();
					if (fileExtension.equalsIgnoreCase("PDF")) {
						mimeType = "application/pdf";
					} else {
						mimeType = "image/jpeg";
					}
				}
				/* Set content type */
				response.setContentType(mimeType);
			} else {
				response.setContentType("application/octet-stream");
			}
			response.setBufferSize(DEFAULT_BUFFER_SIZE);
			// response.setHeader("Cache-Control", "no-cache");
			if (action.equals("PreviewFile")) {
				response.setHeader("Content-Disposition", "inline; filename=\"" + FilenameUtils.getName(fullFileName)
						+ "\"");
				File file = new File(fullFileName);
				response.setContentLength((int) file.length());
				response.setContentType("application/pdf");
				input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
			} else if (action.equals("DownloadFile")) {
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + FilenameUtils.getName(fullFileName) + "\"");
				File file = new File(fullFileName);
				response.setContentLength((int) file.length());
				input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
			} else if (action.equals("PreviewBlob")) {
				response.setContentType("application/pdf");
				Object sessionBeanObj = request.getSession().getAttribute("bkSessionBean");
				if (sessionBeanObj != null) {
					BKSessionBean bkSessionBean = (BKSessionBean) sessionBeanObj;
					byte[] data = (byte[]) bkSessionBean.getImageMap().get(blobUuid);
					ByteArrayInputStream bis = new ByteArrayInputStream(data);
					response.setHeader("Content-Disposition", "inline; filename=\"" + FilenameUtils.getName(blobUuid)
							+ "\"");
					input = new BufferedInputStream(bis, DEFAULT_BUFFER_SIZE);
				}
			} else if (action.equals("DownloadBlob")) {
				response.setContentType("application/pdf");
				Object sessionBeanObj = request.getSession().getAttribute("bkSessionBean");
				if (sessionBeanObj != null) {
					BKSessionBean bkSessionBean = (BKSessionBean) sessionBeanObj;
					byte[] data = (byte[]) bkSessionBean.getImageMap().get(blobUuid);
					ByteArrayInputStream bis = new ByteArrayInputStream(data);
					response.setHeader("Content-Disposition", "attachment; filename=\"" + FilenameUtils.getName(blobUuid)
							+ "\"");
					input = new BufferedInputStream(bis, DEFAULT_BUFFER_SIZE);
				}
			}
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			/* Copy the contents of the file to the output stream */
			byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
			int count = 0;
			while ((count = input.read(buf)) >= 0) {
				output.write(buf, 0, count);
			}
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex.getCause());
		} finally {
			/* Gently close streams */
			close(output);
			close(input);
		}
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it.
				e.printStackTrace();
			}
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
