package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * description:
 *
 * @author zhunhuang, 2020/3/3
 */
public class ValidatorUtil {

    private static final Logger log = LoggerFactory.getLogger(ValidatorUtil.class);

    private static Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();


    /**
     * 参数校验, 使用默认校验组
     * <p>
     * 本方法会屏蔽校验中出现的异常, 如若出现未知异常则默认校验通过！！
     * </p>
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> ValidateVO validate(T obj) {
        return validate(obj, Default.class);
    }


    /**
     * 参数校验
     *
     * <p>
     * 本方法会屏蔽校验中出现的异常, 如若出现未知异常则默认校验通过！！
     * </p>
     *
     * @param obj    待校验对象
     * @param groups 校验组
     * @param <T>
     * @return
     */
    public static <T> ValidateVO validate(T obj, Class<?>... groups) {
        ValidateVO validateVO = new ValidateVO();

        try {

            if (obj == null) {
                validateVO.setSuccess(false);
                validateVO.setMessage("The object to be validated must not be null");
                return validateVO;
            }

            Set<ConstraintViolation<T>> set = validator.validate(obj, groups);
            if (set != null && !set.isEmpty()) {
                validateVO.setSuccess(false);

                StringBuilder strBuilder = new StringBuilder();
                for (ConstraintViolation<T> cv : set) {
                    validateVO.addProperty(cv.getPropertyPath().toString(), cv.getMessage());
                    strBuilder.append(cv.getMessage()).append(";");
                }

                validateVO.setMessage(strBuilder.toString());
            }
        } catch (Exception e) {
            log.error("参数校验异常, obj={}, groups={}", obj, groups, e);
            /** 如若出现异常则默认返回校验通过 */
            validateVO.setSuccess(true);
            validateVO.setMessage("exception!!!");
        }

        return validateVO;
    }

}
