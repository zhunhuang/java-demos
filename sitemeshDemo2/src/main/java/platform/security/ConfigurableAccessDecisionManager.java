package platform.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import platform.config.Constants;

import java.util.Collection;
import java.util.List;

/**
 * 根据qconfig配置的访问控制
 * @author ke.gong
 * @date 2016-11-10
 */
public class ConfigurableAccessDecisionManager extends AffirmativeBased {

    public ConfigurableAccessDecisionManager(List<AccessDecisionVoter> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
            InsufficientAuthenticationException {
        // 未开启权限校验 跳过
        if (!Constants.AuthorizeSwitch) {
            return;
        }
        
        super.decide(authentication, object, configAttributes);
    }

}
