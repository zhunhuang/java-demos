package support;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis 的 number 和枚举互转 handler
 * @param <E> 要转换的枚举
 */
public class EnumOrdinalTypeHandler<E extends IntegerEnumConvertible> extends BaseTypeHandler<E> {

	@SuppressWarnings("unused")
	private Class<E> type;

	@SuppressWarnings("unused")
	private E[] enums;

	public EnumOrdinalTypeHandler(Class<E> type) {
		this.type = type;
		this.enums = type.getEnumConstants();
		if (this.enums == null) {
			throw new IllegalArgumentException(type.getSimpleName()
					+ " does not represent an enum type.");
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
	                                JdbcType jdbcType) throws SQLException {
		ps.setObject(i, parameter.getValue());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		Object i = rs.getObject(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			return fromCode(i, type);

		}
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		Object i = rs.getObject(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			return fromCode(i, type);
		}
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Object i = cs.getObject(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			return fromCode(i, type);
		}
	}

	public static <E extends IntegerEnumConvertible> E fromCode(Object code, Class<E> enumType) {
		for (E anEnum : enumType.getEnumConstants()) {
			if (anEnum.getValue().equals(code)) {
				return anEnum;
			}
		}
		return null;
	}
}
