package aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * description:
 * create: 2018-09-16
 *
 * @author zhun.huang
 */
@Service
public class HelloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    public void sayHello(){
        LOGGER.info("hello....");
    }

}
