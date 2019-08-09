package base;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * description:
 * create: 2019-07-05
 *
 * @author zhun.huang
 */
public class ZonedDateTimeProvider {

	public static ZonedDateTime provideDateTImeProvider() {
		return ZonedDateTime.now(ZoneId.of("UTC"));
	}
}
