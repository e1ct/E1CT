package kr.co.e1ct.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.e1ct.mappers.TSecretRepository;
import kr.co.e1ct.vo.TSecretVo;

@Service
// @AllArgsConstructor
public class TSecretService {

	@Autowired
	private TSecretRepository tSecretRepository;
	
	public TSecretVo authenticate(String empNo, String empPwd) {
		TSecretVo vo = new TSecretVo();
		vo.setEmpNo( empNo );
		vo.setEmpPwd( empPwd );
		return tSecretRepository.findByEmpNo( vo );
	}
}
