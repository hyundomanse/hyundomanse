package egovframework.com.utl.fcc.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;

import com.devwork.common.util.WebUtil;

/**
 * egovframework.com.utl.fcc.service 
 *    |_ EgovFormBasedFileUtil.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class EgovFormBasedFileUtil {
	
    /** Buffer size */
    public static final int BUFFER_SIZE = 8192;

    public static final String SEPERATOR = File.separator;

    public static final int THUMBNAIL_WIDTH = 150;

    public static final  int THUMBNAIL_HEIGHT = 150;
    
    public static final String NOIMAGEBIG = "tm-img_600.png";
    public static final String NOIMAGEMIDDLE = "tm-img_300.png";
    public static final String NOIMAGESMALL = "tm-img_82.png";
    
    public static final int RATIO = 0;
    public static final int SAME = -1;
    
    /**
     * 오늘 날짜 문자열 취득.
     * ex) 20090101
     * @return
     */
    public static String getTodayString() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    	return format.format(new Date());
    }

    /**
     * 물리적 파일명 생성.
     * @return
     */
    public static String getPhysicalFileName() {
    	return EgovFormBasedUUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 파일명 변환.
     * @param filename String
     * @return
     * @throws Exception
     */
    protected static String convert(String filename) throws Exception {
    	//return java.net.URLEncoder.encode(filename, "utf-8");
    	return filename;
    }

    /**
     * Stream으로부터 파일을 저장함.
     * @param is InputStream
     * @param file File
     * @throws IOException
     */
    public static long saveFile(InputStream is, File file) throws IOException {
		// 디렉토리 생성
		if (! file.getParentFile().exists()) {
		    file.getParentFile().mkdirs();
		}
	
		OutputStream os = null;
		long size = 0L;
	
		try {
		    os = new FileOutputStream(file);
	
		    int bytesRead = 0;
		    byte[] buffer = new byte[BUFFER_SIZE];
	
		    while ((bytesRead = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
		    	size += bytesRead;
		    	os.write(buffer, 0, bytesRead);
		    }
		} finally {
		    if (os != null) {
		    	os.close();
		    }
		}
		return size;
    }

    
    /**
     * Stream으로부터 썸네일 파일을 저장함.
     * @param is InputStream
     * @param file File
     * @throws IOException
     */
    								
    public static void saveThumbFile(InputStream is, File file , File file2 , int imgWidth , int imgHeight) throws IOException {
		// 디렉토리 생성
		if (! file.getParentFile().exists()) {
		    file.getParentFile().mkdirs();
		}
		if (! file2.getParentFile().exists()) {
		    file2.getParentFile().mkdirs();
		}
		
		try {
		    BufferedImage bufferOriginalImage = ImageIO.read(file);
	        BufferedImage bufferThumbnailImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_3BYTE_BGR);
	        Graphics2D graphic = bufferThumbnailImage.createGraphics();
	        graphic.drawImage(bufferOriginalImage, 0, 0, imgWidth, imgHeight, null);
	        ImageIO.write(bufferThumbnailImage, "jpg", file2);
		} catch (Exception e) {
			Logger.getLogger(EgovFormBasedFileUtil.class).info("THUM_FILE_EXC=["+file2.getName()+"]");
		
	    } finally {
			
	//	    if (os != null) {
	//		os.close();
	//	    }
		}

    }
    
    /**
     * 파일을 Upload 처리한다.
     *
     * @param request
     * @param where
     * @param maxFileSize
     * @return
     * @throws Exception
     */
    public static List<EgovFormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize) throws Exception {
    	List<EgovFormBasedFileVo> list = new ArrayList<EgovFormBasedFileVo>();

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	
		if (isMultipart) {
		    // Create a new file upload handler
		    ServletFileUpload upload = new ServletFileUpload();
		    upload.setFileSizeMax(maxFileSize);	// SizeLimitExceededException
	
		    // Parse the request
		    FileItemIterator iter = upload.getItemIterator(request);
		    while (iter.hasNext()) {
		        FileItemStream item = iter.next();
		        String name = item.getFieldName();
		        InputStream stream = item.openStream();
		        if (item.isFormField()) {
		            ////log.debug("Form field '" + name + "' with value '" + Streams.asString(stream) + "' detected.");
		            Logger.getLogger(EgovFormBasedFileUtil.class).info("Form field '" + name + "' with value '" + Streams.asString(stream) + "' detected.");
		        } else {
		            ////log.debug("File field '" + name + "' with file name '" + item.getName() + "' detected.");
		            Logger.getLogger(EgovFormBasedFileUtil.class).info("File field '" + name + "' with file name '" + item.getName() + "' detected.");
	
		            if ("".equals(item.getName())) {
		            	continue;
		            }
		            // Process the input stream
		            EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
		            String tmp = item.getName();
	
		            if (tmp.lastIndexOf("\\") >= 0) {
		            	tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
		            }
	
		            vo.setFileName(tmp);
		            vo.setContentType(item.getContentType());
		            vo.setServerSubPath(getTodayString());
		            vo.setPhysicalName(getPhysicalFileName());
	
		            if (tmp.lastIndexOf(".") >= 0) {
		            	vo.setPhysicalName(vo.getPhysicalName() + tmp.substring(tmp.lastIndexOf(".")));
		            }
		            long size = saveFile(stream, new File(WebUtil.filePathBlackList(where) + SEPERATOR + vo.getServerSubPath() + SEPERATOR + vo.getPhysicalName()));
		            vo.setSize(size);
		            list.add(vo);
		        }
		    }
		} else {
		    throw new IOException("form's 'enctype' attribute have to be 'multipart/form-data'");
		}
	
		return list;
    }

    /**
     * 파일을 Download 처리한다.
     *
     * @param response
     * @param where
     * @param serverSubPath
     * @param physicalName
     * @param original
     * @throws Exception
     */
    public static void downloadFile(HttpServletResponse response, String where, String serverSubPath, String physicalName, String original) throws Exception {
		String downFileName = where + SEPERATOR + serverSubPath + SEPERATOR + physicalName;
	
		File file = new File(WebUtil.filePathBlackList(downFileName));
	
		if (!file.exists()) {
		    throw new FileNotFoundException(downFileName);
		}
	
		if (!file.isFile()) {
		    throw new FileNotFoundException(downFileName);
		}
	
		byte[] b = new byte[BUFFER_SIZE];
	
		original = original.replaceAll("\r", "").replaceAll("\n", "");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + convert(original) + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
	
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
	
		try {
		    fin = new BufferedInputStream(new FileInputStream(file));
		    outs = new BufferedOutputStream(response.getOutputStream());
		    int read = 0;
		    while ((read = fin.read(b)) != -1) {
		    	outs.write(b, 0, read);
		    }
		} finally {
		    if (outs != null) {
		    	outs.close();
		    }
		    if (fin != null) {
		    	fin.close();
		    }
		}
    }

    /**
     * 이미지에 대한 미리보기 기능을 제공한다.
     *
     * mimeType의 경우는 JSP 상에서 다음과 같이 얻을 수 있다.
     * getServletConfig().getServletContext().getMimeType(name);
     *
     * @param response
     * @param where
     * @param serverSubPath
     * @param physicalName
     * @param mimeType
     * @throws Exception
     */
    public static void viewFile(HttpServletResponse response, String where, String section , String serverSubPath, String physicalName, String mimeTypeParam) throws Exception {
		String mimeType = mimeTypeParam;
		String downFileName = where + section + SEPERATOR + serverSubPath + SEPERATOR + physicalName;
		String imgFileName	= "";
		if (section.equals("thumb")) {
			if (physicalName.indexOf("basic") > -1) {
				imgFileName = NOIMAGEMIDDLE;
			} else if (physicalName.indexOf("small") > -1) {
				imgFileName = NOIMAGESMALL;
			} else {
				imgFileName = NOIMAGEMIDDLE;
			}
			
		} else {
			if (serverSubPath.equals("brand")) {
				imgFileName = NOIMAGESMALL;
			} else {
				imgFileName = NOIMAGEBIG;
			}
			
			if(serverSubPath.equals("event")){
				if (physicalName.indexOf("bigBanner") > -1) {
					imgFileName = "t_event_no.jpg";
				} else if (physicalName.indexOf("smallBanner") > -1) {
					imgFileName = "m_event_no.jpg";
				} else if (physicalName.indexOf("contentImage") > -1) {
					imgFileName = "m_event_no.jpg";
				} else {
					imgFileName = "t_event_no.jpg";
				}
			}
		}
		String noImageFile	= where + SEPERATOR + "noImage" + SEPERATOR + imgFileName; 
		File file	 		= new File(WebUtil.filePathBlackList(downFileName));
		File noImage	 	= new File(WebUtil.filePathBlackList(noImageFile));
		
		if (!file.exists()) {
	//	    throw new FileNotFoundException(downFileName);
			file = noImage;
		}
	
		if (!file.isFile()) {
			file = noImage;
	//	    throw new FileNotFoundException(downFileName);
		}
		byte[] b = new byte[BUFFER_SIZE];
	
		if (mimeType == null) {
		    mimeType = "application/octet-stream;";
		}
	
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "filename=image;");
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
		    fin = new BufferedInputStream(new FileInputStream(file));
		    outs = new BufferedOutputStream(response.getOutputStream());
		    int read = 0;
		    while ((read = fin.read(b)) != -1) {
		    	outs.write(b, 0, read);
		    }
		// 2011.10.10 보안점검 후속조치
		} finally {
		    if (outs != null) {
				try {
				    outs.close();
				} catch (Exception ignore) {
				    //log.debug("IGNORE: " + ignore);
				}
		    }
		    if (fin != null) {
		    	try {
		    		fin.close();
		    	} catch (Exception ignore) {
		    		//log.debug("IGNORE: " + ignore);
		    	}
		    }
		}
    }
    
    @SuppressWarnings("deprecation")
	public static void saveThumbFilebak(File src, File dest, int width, int height) throws IOException {
        Image srcImg = null;
        String suffix = src.getName().substring(src.getName().lastIndexOf('.')+1).toLowerCase();
        if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) {
            srcImg = ImageIO.read(src);
        } else {
            // BMP가 아닌 경우 ImageIcon을 활용해서 Image 생성
            // 이렇게 하는 이유는 getScaledInstance를 통해 구한 이미지를
            // PixelGrabber.grabPixels로 리사이즈 할때
            // 빠르게 처리하기 위함이다.
            srcImg = new ImageIcon(src.toURL()).getImage();
        }
        
        int srcWidth = srcImg.getWidth(null);
        int srcHeight = srcImg.getHeight(null);
        
        int destWidth = -1, destHeight = -1;
        
        if (width == SAME) {
            destWidth = srcWidth;
        } else if (width > 0) {
            destWidth = width;
        }
        
        if (height == SAME) {
            destHeight = srcHeight;
        } else if (height > 0) {
            destHeight = height;
        }
        
        if (width == RATIO && height == RATIO) {
            destWidth = srcWidth;
            destHeight = srcHeight;
        } else if (width == RATIO) {
            double ratio = ((double)destHeight) / ((double)srcHeight);
            destWidth = (int)((double)srcWidth * ratio);
        } else if (height == RATIO) {
            double ratio = ((double)destWidth) / ((double)srcWidth);
            destHeight = (int)((double)srcHeight * ratio);
        }
        
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH); 
        int pixels[] = new int[destWidth * destHeight]; 
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        	System.out.println("RESIZE--FAIL!!");
            throw new IOException(e.getMessage());
        } 
        BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB); 
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        
        ImageIO.write(destImg, "jpg", dest);
    }
    
}
