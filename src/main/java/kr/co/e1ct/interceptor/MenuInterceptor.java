package kr.co.e1ct.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.e1ct.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MenuInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<MenuVo> subMenuList = new ArrayList<MenuVo>();
		
		subMenuList.add( new MenuVo( "/info/terminal/berthText", "터미널정보", "Terminal Info" ) );
		subMenuList.add( new MenuVo( "/info/cntrList/copino", "컨테이너목록", "Container List" ) );
		subMenuList.add( new MenuVo( "/info/cntrInfo/details", "컨테이너정보", "Container Info" ) );
		subMenuList.add( new MenuVo( "/info/onDock/bookingList", "On-Dock", "On-Dock" ) );
		subMenuList.add( new MenuVo( "/info/cust/inspection", "세관업무", "Customs" ) );
		subMenuList.add( new MenuVo( "/info/edi/copinoList", "EDI", "EDI" ) );
		subMenuList.add( new MenuVo( "/info/sms/customer", "정산업무", "Calculate" ) );
		if( SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains( new SimpleGrantedAuthority( "ROLE_P" ) ) ) {
			subMenuList.add( new MenuVo( "/info/intra/damageReq", "인트라넷", "Intranet" ) );
		} else {
			subMenuList.add( new MenuVo( "/info/intra/vesselDetail", "인트라넷", "Intranet" ) );
		}
		
		request.setAttribute( "subMenuList" , subMenuList );
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
