package kr.co.e1ct.util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtils {

	public static String EMPTY = "";
	public static String REGEX_REMOVE_SPACE = "\\p{Z}";
	public static String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	

	public static boolean isNumeric( String s ) {
		try {
			Double.parseDouble( s );
			return true;
		} catch ( NumberFormatException e ) {
			return false;
		}
	}
	
	public static String removeSpace( String s ) {
		return s.replaceAll(REGEX_REMOVE_SPACE, "");
	}
	
	public static boolean isEmpty( String s ) {
		if( s == null || removeSpace(s).equals("") || removeSpace(s).length() == 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isEmpty( Long l ) {
		String s = String.valueOf( l );
		return isEmpty( s );
	}
	
	public static boolean isEmail( String s ) {
		return Pattern.matches( REGEX_EMAIL, s );
	}
	
	public static String lpad( String s, int pad, String strChar ) {
		String strResult = "";
		
		StringBuilder sbAddChar = new StringBuilder();
		for( int i=s.length(); i<pad; i++ ) {
			sbAddChar.append(strChar);
		}
		
		strResult = sbAddChar + s;
		return strResult;
	}
	
	public static String rpad( String s, int pad, String strChar ) {
		String strResult = "";
		
		StringBuilder sbAddChar = new StringBuilder();
		for( int i=s.length(); i<pad; i++ ) {
			sbAddChar.append(strChar);
		}
		
		strResult = s + sbAddChar;
		return strResult;
	}
	
	public static String replaceAll( String s, String cng, String tok ) {
		String t = s;
		while( t.indexOf( cng ) >= 0 ) {
			t = t.replace( cng, tok );
		}
		return t;
	}
	
	public static String substrByte( String s, int len ) {
		if( !s.isEmpty() ) {
			s = s.trim();
			if( s.getBytes().length <= len ) {
				return s;
			} else {
				StringBuffer sb = new StringBuffer( len );
				int cnt = 0;
				for( char ch:s.toCharArray() ) {
					cnt += String.valueOf(ch).getBytes().length;
					if( cnt > len ) break;
					sb.append( ch );
				}
				return sb.toString();
			}
		} else {
			return EMPTY;
		}
	}
	
	public static String nullIfReturn( String s ) {
		return isEmpty(s) ? EMPTY : s;
	}
	
	public static String getRemoteIp( HttpServletRequest request ) {
		String IP = request.getHeader( "X-FORWARDED-FOR" );
		
		if( IP == null || IP.length() == 0 ) {
			IP = request.getHeader( "Proxy-Client-IP" );
		}
		
		if( IP == null || IP.length() == 0 ) {
			IP = request.getHeader( "WL-Proxy-Client-IP" );
		}
		
		if( IP == null || IP.length() == 0 ) {
			IP = request.getRemoteAddr();
		}
		
		return IP;
	}
}
