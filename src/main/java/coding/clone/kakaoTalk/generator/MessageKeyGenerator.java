package coding.clone.kakaoTalk.generator;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MessageKeyGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		UUID uuid = UUID.randomUUID();
		String key = uuid.toString() + "_" + System.currentTimeMillis();
		
		return key;
	}

}
