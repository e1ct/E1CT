package kr.co.e1ct.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.co.e1ct.services.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MemberService memberService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().ignoringAntMatchers("/api/**").and().cors().disable();

		http.authorizeRequests()
				/*
				.antMatchers( "/static/**" ).permitAll()
				.anyRequest().authenticated()
				*/

				
				.antMatchers( "/resources/**", "/static/**" ).permitAll()
				
				.antMatchers( "/admin/**", "/terminal/gallery/write" ).hasAnyRole( "S" )
				
				// 전체 사용자
				.antMatchers(
						"/info/terminal/berthText",
						"/info/terminal/berthGraphic",
						"/info/terminal/vesselWork",
						"/info/terminal/yardStack",
						"/info/terminal/codeVessel",
						"/info/terminal/codePort",
						"/info/terminal/codeShipper",
						"/info/terminal/codeDanger",
						"/info/terminal/emptyContainer",
						"/info/cntrList/copino",
						"/info/cntrInfo/details",
						"/info/cntrInfo/workingTime",
						"/info/sms/customer",
						"/info/sms/details",
						"/info/sms/conclude"
						).permitAll()
				
				// 선사, E1CT 일반, E1CT 장비
				.antMatchers( 
						"/info/terminal/byOperYard",
						"/info/cntrList/cllOverStorage",
						"/info/cntrList/longStack",
						"/info/cntrList/reeferCargo",
						"/info/cntrList/cntrDanger",
						"/info/cntrInfo/stock",
						"/info/cntrInfo/freeTime"
						).hasAnyRole( "O", "G", "E", "S" )
				
				// 선사, 운송사, E1CT 일반, E1CT 장비
				.antMatchers( 
						"/info/cntrList/gateInOut"
						).hasAnyRole( "O", "T", "G", "E", "S" )
				
				// 선사, E1CT 일반, E1CT 장비, 검수업체
				.antMatchers( 
						"/info/cntrList/notGateInOut",
						"/info/cntrList/byVslDisLod",
						"/info/cntrList/cntrCancel",
						"/info/cntrList/cntrRehandling"
						).hasAnyRole( "O", "G", "E", "M", "S" )
				
				// 선사, 운송사, E1CT 일반
				.antMatchers(
						"/info/edi/copinoGateInRsrv",
						"/info/edi/copinoGateInRsrvList",
						"/info/onDock/bookingList",
						"/info/edi/copinoList"
						).hasAnyRole( "O", "T", "G", "S" )
				
				// 선사, E1CT 일반
				.antMatchers(
						"/info/onDock/insertOrder",
						"/info/onDock/deliveryManager",
						"/info/onDock/stock",
						"/info/onDock/freeVanPool",
						"/info/onDock/damage",
						"/info/onDock/overStock",
						"/info/onDock/holding",
						"/info/onDock/gatePass",
						"/info/edi/byVslCll"
						).hasAnyRole( "O", "G", "S" )
				
				// 관세사, 검역대행, E1CT 일반
				.antMatchers(
						"/info/cust/inspection",
						"/info/cust/insPvnt",
						"/info/cust/custItemWindow",
						"/info/cust/custItemWindow/save",
						"/info/cust/custItemWindow/delete"
						).hasAnyRole( "C", "I", "G", "S" )
				
				// E1CT 일반
				.antMatchers(
						"/info/cust/inspectionManage",
						"/info/cust/insPvntManage"
						).hasAnyRole( "G", "S" )
				
				// 수리업체
				.antMatchers( 
						"/info/intra/damageReq",
						"/info/intra/damageCom",
						"/info/intra/intrDamage"
						).hasAnyRole( "P", "S" )
				
				.antMatchers( 
						"/info/intra/vesselDetail" 
						).hasAnyRole( "G", "E", "S" )
				
				// 관리자
				.antMatchers(
						"/info/edi/copinoGateInIns",
						"/info/edi/copinoGateOutIns"
						).hasAnyRole( "S" )
				
				
				.anyRequest().permitAll()
				;
		
		/*
		 * I : 검역대행
		 * P : 수리업체
		 * M : 검수업체
		 * T : 운송사
		 * O : 선사
		 * S : 관리자
		 * C : 관세사
		 * G : E1CT 일반
		 * E : E1CT 장비
		 */
		
		http.formLogin()
				.loginPage( "/member/signIn" )
				.usernameParameter( "empNo" )
				.passwordParameter( "empPwd" )
				.defaultSuccessUrl( "/member/signIn/result" )
				.failureForwardUrl( "/member/invalid" )
				.permitAll();
		
		http.logout()
				.logoutRequestMatcher( new AntPathRequestMatcher( "/member/signOut" ) )
				.logoutSuccessUrl( "/member/signOut/result" )
				.invalidateHttpSession( true );
		
		http.exceptionHandling()
				.accessDeniedPage( "/member/accessDenied" );
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService( memberService ).passwordEncoder( NoOpPasswordEncoder.getInstance() ); //.passwordEncoder( passwordEncoder() );
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers( "/static/**" );
	}
}
