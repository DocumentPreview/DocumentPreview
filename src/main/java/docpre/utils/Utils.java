package docpre.utils;

import docpre.preview.DocumentType;
import docpre.preview.PREVIEW_TYPES;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	private static String regx = ".wps|wpt|doc|docx|dot|rtf|xml|docm|et|ett|xls|xlsx|xlsm|xlsb|xlam|xltx|xltm|xls|xlt|xla|xlw|odc|uxdc|txt|dbf|prn|dps|dpt|pptx|ppt|pptm|ppsx|pps|ppsm|potx|pot|potm|pd$";
	private static Pattern r = Pattern.compile(regx);
	
	private static List<String> textExtensions = new ArrayList<String>();
	private static List<String> imageExtensions = new ArrayList<String>();
	
	static {
		textExtensions.add("js");
		textExtensions.add("go");
		textExtensions.add("py");
		textExtensions.add("md");
		textExtensions.add("pac");
		textExtensions.add("json");
		textExtensions.add("c");
		textExtensions.add("h");
		textExtensions.add("html");
		textExtensions.add("txt");
		textExtensions.add("ru");
		
		imageExtensions.add("psd");
		imageExtensions.add("svg");
	}

	public static boolean isSupportOfficeFile(String fileExtension) {
		Matcher m = r.matcher(fileExtension);
		return m.find();
	}

	public static DocumentType getDocumentType(String fileExtension) {
		DocumentType type = null;
		if (!isSupportOfficeFile(fileExtension)) {
			return null;
		}
		switch (fileExtension) {
		case "wps":
		case "wpt":
		case "doc":
		case "docx":
		case "dot":
		case "rtf":
		case "xml":
		case "docm":
			// Word
			type = DocumentType.WORD;
			break;
		case "et":
		case "ett":
		case "xls":
		case "xlsx":
		case "xlsm":
		case "xlsb":
		case "xlam":
		case "xltx":
		case "xltm":
		case "xlt":
		case "xla":
		case "xlw":
		case "odc":
		case "uxdc":
		case "txt":
		case "dbf":
		case "prn":
			// Excel
			type = DocumentType.EXCEL;
			break;
		case "dps":
		case "dpt":
		case "pptx":
		case "ppt":
		case "pptm":
		case "ppsx":
		case "pps":
		case "ppsm":
		case "potx":
		case "pot":
		case "potm":
		case "wpd":
			// Powerpoint
			type = DocumentType.POWERPOINT;
			break;
		default:
			break;
		}
		return type;
	}

	public static boolean isTextFile(String fileExtension) {
		return textExtensions.contains(fileExtension);
	}
	
	public static boolean isImageFile(String fileExtension) {
		return imageExtensions.contains(fileExtension);
	}

	public static PREVIEW_TYPES translateContentType(String contentType) {
		if (contentType.startsWith("image")) {
			return PREVIEW_TYPES.NORMAL_IMAGE;
		}
		if (contentType.startsWith("text")) {
			return PREVIEW_TYPES.TEXT;
		}
		if ("application/pdf".equalsIgnoreCase(contentType)) {
			return PREVIEW_TYPES.PDF;
		}
		return PREVIEW_TYPES.NOT_SUPPORT;
	}
}
